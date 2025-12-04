package ayed.resources;

import java.time.LocalDateTime;

import ayed.DTOs.ModUsuarioRequestDTO;
import ayed.DTOs.UsuarioRequestDTO;
import ayed.models.Notificacion;
import ayed.models.Usuario;
import ayed.services.ManagerUsuario;
import ayed.structures.ListaCola;
import ayed.structures.ListaCustom;
import ayed.structures.Nodo;
import ayed.structures.NodoUsuarioGrafo;
import ayed.structures.UsuariosRepositorio;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
public class UsuariosResource {

    private final UsuariosRepositorio repo = UsuariosRepositorio.getInstance();
    private final ManagerUsuario managerUsuario = new ManagerUsuario();
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)    
    public Response crearUsuario(UsuarioRequestDTO dto){
        if(dto == null){
            return Response.status(Status.BAD_REQUEST)
                .entity("Por favor complete todos los datos")
                .build();
        }           

        int usuarioId = repo.generarIdUsuario();
        Usuario nuevo = new Usuario (
            usuarioId,
            dto.getEmail(),
            dto.getNombre(),
            dto.getApellido(),
            dto.getGenero(),
            dto.getNacionalidad(),
            LocalDateTime.now()            
        );

        // Guarda en la lista enlazada generica
        repo.getUsuarios().agregarAlInicio(nuevo);

        // Guarda en el grafo/hash TablaHash<Integer, NodoUsuarioGrafo>
        boolean result = managerUsuario.agregarUsuario(nuevo);

        if (result == false){
            return Response.status(Status.BAD_REQUEST).entity("Error al crear el usuario").build();
        }

        return Response.status(Status.CREATED).entity(nuevo).build();
    }     

    @GET
    @Path("{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetUsuario (@PathParam("idUsuario") int idUsuario){
        System.out.println("Buscando usuario con ID: " + idUsuario);
        NodoUsuarioGrafo nodoUsuario = repo.getGrafoUsuarios().buscar(idUsuario);

        if(nodoUsuario == null){
            System.out.println("Usuario no encontrado");
            return Response.status(Status.NOT_FOUND).entity("Usuario no encontrado").build();
        }

        return Response.status(Status.OK).entity(nodoUsuario.getUsuario()).build();
    }
    
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListarUsuarios(){        
        ListaCustom<Usuario> usuarios = managerUsuario.obtenerUsuarios();

        if (usuarios.getTamano() == 0){
            return Response.status(Status.NO_CONTENT).build();
        }

        Usuario[] usuariosArray = usuarios.toArray(new Usuario[usuarios.getTamano()]);

        return Response.status(Status.OK)
                .entity(usuariosArray)
                .build();
    }

    @PUT
    public Response ModificarUsuario(ModUsuarioRequestDTO user){
        if(user == null){
            return Response.status(Status.BAD_REQUEST).build();
        }

        NodoUsuarioGrafo nodoUsuario = repo.getGrafoUsuarios().buscar(user.getIdUsuario());

        if(nodoUsuario == null){
            return Response.status(Status.NOT_FOUND).entity("Usuario no encontrado").build();
        }

        Usuario userMod = nodoUsuario.getUsuario();
        userMod.setEmail(user.getEmail());
        userMod.setNombre(user.getNombre());
        userMod.setApellido(user.getApellido());
        userMod.setGenero(user.getGenero());

        return Response.status(Status.OK).entity(userMod).build();
    }

    @DELETE
    @Path("{idUsuario}")
    public Response EliminarUsuario(@PathParam("idUsuario") int idUsuario){        
        NodoUsuarioGrafo nodoUsuario = repo.getGrafoUsuarios().buscar(idUsuario);

        if(nodoUsuario == null){
            return Response.status(Status.NOT_FOUND).entity("No se encontró el usuario con id = " + idUsuario).build();
        }

        boolean result = managerUsuario.eliminarUsuario(idUsuario);

        if(!result){
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error al eliminar el usuario con id = " + idUsuario).build();
        }

        return Response.status(Status.OK).entity("Usuario id : " + idUsuario + " eliminado correctamente").build();
    }

    @POST
    @Path("{idUsuario}/seguir/{idAmigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response seguirUsuario(@PathParam("idUsuario") int idUsuario, @PathParam("idAmigo") int idAmigo){

        if(idUsuario == idAmigo){
            return Response.status(Status.BAD_REQUEST)
                .entity("Un usuario no puede seguirse a si mismo")
                .build();            
        }       
        
        // busco en la tabla hash para chequear si existen ambos, seguidor y seguido
        NodoUsuarioGrafo nodoSeguidor = repo.getGrafoUsuarios().buscar(idUsuario);
        NodoUsuarioGrafo nodoSeguido = repo.getGrafoUsuarios().buscar(idAmigo);        
        
        if(nodoSeguidor == null || nodoSeguido == null){
            return Response.status(Status.NOT_FOUND)
                .entity("Alguno de los usuarios no existe")
                .build();
        }

        boolean result = managerUsuario.agregarAmigo(idUsuario, idAmigo);

        String emailSeguidor = nodoSeguidor.getUsuario().getEmail();
        String emailSeguido = nodoSeguido.getUsuario().getEmail();

        if(result == true){
            return Response.status(Status.OK)
                .entity("Usuario: "+ emailSeguidor + " ahora sigue a: " + emailSeguido)
                .build();
        }else{
            return Response.status(Status.BAD_REQUEST)
                .entity("No se pudo realizar la accion")
                .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{idUsuario}/notificaciones")
    public Response notificaciones(@PathParam("idUsuario") int idUsuario){

        if(idUsuario <= 0) {
            return Response.status(Status.BAD_REQUEST)
                .entity("Pasame el id del usuario papi")
                .build();
        }

        // Busco el nodo del usuario en el grafo
        NodoUsuarioGrafo nodoUsuario = repo.getGrafoUsuarios().buscar(idUsuario);
        if (nodoUsuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("Usuario no encontrado")
                .build();
        }

        Usuario usuario = nodoUsuario.getUsuario();
        ListaCola<Notificacion> listaNotificaciones = usuario.getNotificaciones();

        // Paso la cola a una ListaCustom, consumiendo la cola (ListaCola)
        ListaCustom<Notificacion> lista = new ListaCustom<>();
        Nodo<Notificacion> nodo;

        while ((nodo = listaNotificaciones.eliminarPrimerElemento()) != null) {
            lista.agregarAlInicio(nodo.getDato());
        }

        // Si no hay notificaciones, devuevlo array vacio
        if (lista.getTamano() == 0) {
            return Response.status(Response.Status.OK)
                .entity(new Notificacion[0])
                .build();
        }

        // Pasamos ListaCustom a Notificacion[] array
        Notificacion[] arrayNotificaciones = lista.toArray(new Notificacion[lista.getTamano()]);

        return Response.status(Response.Status.OK)
            .entity(arrayNotificaciones)
            .build();

    }  

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idUsuarioUno}/amigos-en-comun/{idUsuarioDos}")
    public Response amigosEnComun(@PathParam("idUsuarioUno") int idUsuarioUno, @PathParam("idUsuarioDos") int idUsuarioDos){

        // validamos los ids
        if(idUsuarioUno <= 0 || idUsuarioDos <= 0){
            return Response.status(Status.BAD_REQUEST)
                .entity("pasame bien los ids por favor")
                .build();
        }

        // validamos que existan los usuarios
        NodoUsuarioGrafo nodoUsuarioUno = repo.getGrafoUsuarios().buscar(idUsuarioUno);
        NodoUsuarioGrafo nodoUsuarioDos = repo.getGrafoUsuarios().buscar(idUsuarioDos);

        if(nodoUsuarioUno == null || nodoUsuarioDos == null) {
            return Response.status(Status.BAD_REQUEST)
                .entity("Alguno de los usuarios no existen")
                .build();
        }

        // ahora que hice todos los chequeos llamo al metood amigosEnComun en ManagerUsuario
        ListaCustom<Usuario> listaConAmichis = managerUsuario.amigosEnComun(idUsuarioUno, idUsuarioDos);

        if(listaConAmichis.getTamano() == 0){
            return Response.status(Status.ACCEPTED)
                .entity("No hay amigos en comun")
                .build();
        }

        // si hay amigos en comun paso la lista a un array de tipo Usuario
        Usuario[] arrayAmigos = listaConAmichis.toArray(new Usuario[listaConAmichis.getTamano()]);

        return Response.status(Status.ACCEPTED)
            .entity(arrayAmigos)
            .build();

    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idUsuario}/amigos-de-amigos")
    public Response amigosDeAmigos(@PathParam("idUsuario") int idUsuario){
        NodoUsuarioGrafo nodoUsuario = repo.getGrafoUsuarios().buscar(idUsuario);

        if (nodoUsuario == null) {
            return Response.status(Status.NOT_FOUND)
                .entity("No existe el usuario")
                .build();
        }

        ListaCustom<Usuario> amigosDeAmigos = managerUsuario.obtenerAmigosDeAmigos(nodoUsuario);

        Usuario[] arrayAmigosDeAmigos = amigosDeAmigos.toArray(new Usuario[amigosDeAmigos.getTamano()]);

        return Response.status(Status.OK)
            .entity(arrayAmigosDeAmigos)
            .build();
    }

}

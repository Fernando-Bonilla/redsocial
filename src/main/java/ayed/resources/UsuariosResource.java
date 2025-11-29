package ayed.resources;

import java.time.LocalDateTime;

import ayed.DTOs.ModUsuarioRequestDTO;
import ayed.DTOs.UsuarioRequestDTO;
import ayed.models.Usuario;
import ayed.services.ManagerUsuario;
import ayed.structures.ListaCustom;
import ayed.structures.NodoUsuarioGrafo;
import ayed.structures.UsuarioNodoListaEnlazada;
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
            return null;
        }

        UsuarioNodoListaEnlazada nodo = new UsuarioNodoListaEnlazada();
        nodo.setEmail(dto.getEmail());
        nodo.setNombre(dto.getNombre());
        nodo.setApellido(dto.getApellido());        
        nodo.setGenero(dto.getGenero());

        repo.getUsuarios().AgregarNodo(nodo);

        // Agregar al grafo
        Usuario nuevo = new Usuario (repo.generarIdUsuario(),
            dto.getEmail(),
            dto.getNombre(),
            dto.getApellido(),
            dto.getGenero(),
            LocalDateTime.now()
        );

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

}

package ayed.resources;

import java.time.LocalDateTime;

import ayed.DTOs.UsuarioRequestDTO;
import ayed.models.Usuario;
import ayed.services.ManagerUsuario;
import ayed.structures.NodoUsuarioGrafo;
import ayed.structures.TablaHash;
import ayed.structures.UsuarioNodoListaEnlazada;
import ayed.structures.UsuariosListaEnlazada;
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
                                                LocalDateTime.now());

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

        UsuariosListaEnlazada listaCustom = repo.getUsuarios();    
        
        TablaHash<Integer, NodoUsuarioGrafo> grafoUsuarios = repo.getGrafoUsuarios();

        return Response.status(Status.OK)
                .build();
    }

    @PUT
    public UsuarioRequestDTO ModificarUsuario(UsuarioRequestDTO dto){
        if(dto == null){
            return null;
        }

        boolean usuarioUpdateado = repo.getUsuarios().ModificarUsuario(dto.getEmail(), dto.getNombre(), dto.getApellido(), dto.getGenero());

        if(usuarioUpdateado == false) {
            return null;
        }

        return dto;
    }

    @DELETE
    @Path("{email}")
    public Response EliminarUsuario(@PathParam("email") String email){
        boolean usuarioEliminado = repo.getUsuarios().EliminarUsuario(email);

        if(usuarioEliminado == true){
            String json = "{ \"mensaje\": \"Usuario '" + email + "' eliminado\" }";
            return Response.ok(json).build();
        }else {
            String json = "{ \"mensaje\": \"Usuario '" + email + "' No encontrado\" }";
            return Response.status(Response.Status.NOT_FOUND).entity(json).build();
        }
    }

}

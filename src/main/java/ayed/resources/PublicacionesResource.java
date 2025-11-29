package ayed.resources;

import ayed.structures.UsuariosListaEnlazada;
import ayed.structures.UsuarioNodoListaEnlazada;
import ayed.structures.UsuariosRepositorio;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ayed.DTOs.PublicacionRequestDTO;
import ayed.DTOs.UsuarioRequestDTO;

@Path("publicaciones")
@Consumes(MediaType.APPLICATION_JSON)
public class PublicacionesResource {

    private final UsuariosRepositorio repo = UsuariosRepositorio.getInstance(); // singleton para poder tener persistencia entre requests
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)    
    public PublicacionRequestDTO crearPublicacion(PublicacionRequestDTO dto){
        /* if(dto == null){
            return null;
        }

        UsuarioNodoListaEnlazada nodo = new UsuarioNodoListaEnlazada();
        nodo.setEmail(dto.getEmail());
        nodo.setNombre(dto.getNombre());
        nodo.setApellido(dto.getApellido());        
        nodo.setGenero(dto.getGenero());

        repo.getUsuarios().AgregarNodo(nodo); */

        return dto;
    }     
    
    

}

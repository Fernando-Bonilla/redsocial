package ayed.resources;

import ayed.structures.UsuariosListaEnlazada;
import ayed.structures.UsuarioNodoListaEnlazada;
import ayed.structures.UsuariosRepositorio;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import ayed.DTOs.UsuarioRequestDTO;

@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
public class UsuariosResource {

    private final UsuariosRepositorio repo = UsuariosRepositorio.getInstance();
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)    
    public UsuarioRequestDTO crearUsuario(UsuarioRequestDTO dto){
        if(dto == null){
            return null;
        }

        UsuarioNodoListaEnlazada nodo = new UsuarioNodoListaEnlazada();
        nodo.setEmail(dto.getEmail());
        nodo.setNombre(dto.getNombre());
        nodo.setApellido(dto.getApellido());        
        nodo.setGenero(dto.getGenero());

        repo.getUsuarios().AgregarNodo(nodo);

        return dto;
    }
      
    

    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    public String ListarUsuarios(){

        UsuariosListaEnlazada listaCustom = repo.getUsuarios();       

        return listaCustom.ListarUsuarios();
    }

}

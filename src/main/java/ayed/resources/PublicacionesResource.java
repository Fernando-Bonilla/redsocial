package ayed.resources;

import ayed.structures.UsuariosRepositorio;
import ayed.structures.NodoUsuarioGrafo;
import ayed.structures.PublicacionesRepositorio;
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
import jakarta.ws.rs.core.Response.Status;
import ayed.DTOs.PublicacionRequestDTO;
import ayed.models.Publicacion;

@Path("publicaciones")
@Consumes(MediaType.APPLICATION_JSON)
public class PublicacionesResource {

    private final PublicacionesRepositorio pubRepo = PublicacionesRepositorio.getInstance();
    private final UsuariosRepositorio repo = UsuariosRepositorio.getInstance(); // singleton para poder tener persistencia entre requests
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)    
    public Response crearPublicacion(PublicacionRequestDTO dto){
        if(dto == null){
            return Response.status(Status.BAD_REQUEST)
                .entity("Completar los datos por favor")
                .build();
        }

        int idAutor = dto.getIdAutor();
        String cuerpoPub = dto.getCuerpoPublicacion();

        if(cuerpoPub == null || cuerpoPub.isEmpty()) {
            return Response.status(Status.BAD_REQUEST)
                .entity("Por favor agregar contenido al cuerpo de la publicacion")
                .build();
        }
        
        // Valido que exista el usuario autor en el grafo/tabla hash
        NodoUsuarioGrafo nodoAutor = repo.getGrafoUsuarios().buscar(idAutor);
        if(nodoAutor == null) {
            return Response.status(Status.NOT_FOUND)
                .entity("No existe el usuario")
                .build();
        }

        // Ahora creo la puclikeishon
        Publicacion nuevaPub = pubRepo.agregarPublicacion(idAutor, cuerpoPub);
               
        return Response.status(Status.CREATED)
            .entity(nuevaPub)
            .build();
    }     
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(){

        Publicacion[] arrayPub = pubRepo.listarPubliciones();

        return Response.status(Status.ACCEPTED)
            .entity(arrayPub)
            .build();

    }

}

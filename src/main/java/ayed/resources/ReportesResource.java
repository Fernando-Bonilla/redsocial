package ayed.resources;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ayed.DTOs.UsuariosPeriodoRegistroRequestDTO;
import ayed.DTOs.*;
import ayed.models.Publicacion;
import ayed.models.Usuario;
import ayed.services.ManagerUsuario;
import ayed.structures.ListaCola;
import ayed.structures.ListaCustom;
import ayed.structures.Nodo;
import ayed.structures.NodoUsuarioGrafo;
import ayed.structures.PublicacionesRepositorio;
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

@Path("reportes")
@Consumes(MediaType.APPLICATION_JSON)
public class ReportesResource {

    private final UsuariosRepositorio repo = UsuariosRepositorio.getInstance();
    private final PublicacionesRepositorio pubRepo = PublicacionesRepositorio.getInstance();

    private final ManagerUsuario managerUsuario = new ManagerUsuario();
    private final DateTimeFormatter formateadorDeFecha = DateTimeFormatter.ofPattern( "dd/MM/yyyy" ); 
    
    @POST // uso post asi es mas facil pasar las fechas por body y no por la url, esta mal, pero no tan mal
    @Path("fecha-registro")
    public Response listarUsuariosPorFechaRegistro(UsuariosPeriodoRegistroRequestDTO dto){

        if(dto == null || dto.getDesde() == null || dto.getHasta() == null){

            return Response.status(Status.BAD_REQUEST)
                .entity("Por favor completame las fechas desde y hasta, formato dd/MM/yyyy")
                .build();
        }           

        // parseamos las fechas que vienen en formato string
        LocalDate desde;
        LocalDate hasta; 

        desde = LocalDate.parse(dto.getDesde(), formateadorDeFecha);
        hasta = LocalDate.parse(dto.getHasta(), formateadorDeFecha);

        // valido que hasta no sea menor a desde
        if(hasta.isBefore(desde)){
            return Response.status(Status.BAD_REQUEST)
                .entity("La fecha final (hasta) no puede ser anterior a la de inicio")
                .build();
        }

        // ahora usamos el metodo que me filtra por rango de fecha
        ListaCustom<Usuario> listaUsuarios = managerUsuario.usuariosRegistradosPorPeriodo(desde, hasta);
        int cantidad = listaUsuarios.getTamano();
        Usuario[] arrayUsuarios = listaUsuarios.toArray(new Usuario[cantidad]);

        // ahora armo el dto de respuesta
        UsuariosPeriodoRegistroResponseDTO dtoResponse = new UsuariosPeriodoRegistroResponseDTO(cantidad, arrayUsuarios);

        return Response.status(Status.ACCEPTED)
            .entity(dtoResponse)
            .build();       
        
    }

    @GET
    @Path("top-publicaciones")
    public Response topTenPublicaciones(){
        ListaCustom<Publicacion> listaPublicaciones = pubRepo.topTenPublicacionesMasComentadas();

        int cantidadPub = listaPublicaciones.getTamano();

        Publicacion[] arrPublicaciones = listaPublicaciones.toArray(new Publicacion[cantidadPub]);

        // ahora lo mapeo a dto
        PublicacionTopResponseDTO[] dto = new PublicacionTopResponseDTO[cantidadPub];

        for(int i = 0; i < arrPublicaciones.length; i++) {
            dto[i] = new PublicacionTopResponseDTO(arrPublicaciones[i]);
        }

        return Response.status(Status.ACCEPTED)
            .entity(dto)
            .build();

    }
    

}

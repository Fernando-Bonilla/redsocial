package ayed.DTOs;

import ayed.models.Publicacion;
import ayed.models.Comentario;

public class PublicacionTopResponseDTO {
    private int idPublicacion;
    private String mensaje;
    private int idAutor;
    private java.time.LocalDateTime fechaPublicacion;
    private Comentario[] comentarios;

    public PublicacionTopResponseDTO(Publicacion pub) {
        this.idPublicacion = pub.getIdPublicacion();
        this.mensaje = pub.getMensaje();
        this.idAutor = pub.getIdAutor();
        this.fechaPublicacion = pub.getFechaPublicacion();
        this.comentarios = pub.getComentariosArray(); 
    }

    public int getIdPublicacion() { 
        return idPublicacion; 
    }

    public String getMensaje() { 
        return mensaje; 
    }

    public int getIdAutor() { 
        return idAutor; 
    }
    
    public java.time.LocalDateTime getFechaPublicacion() { 
        return fechaPublicacion; 
    }

    public Comentario[] getComentarios() { 
        return comentarios; 
    }
}

package ayed.models;

import java.time.LocalDateTime;

public class Comentario {
    private int idComentario;
    private String comentario;
    private LocalDateTime fechaComentario;
    private int idAutor;
    private int idPublicacion;

    public Comentario(int idComentario, String comentario, LocalDateTime fechaComentario, int idAutor, int idPublicacion) {
        this.idComentario = idComentario;
        this.comentario = comentario;
        this.fechaComentario = fechaComentario;
        this.idAutor = idAutor;
        this.idPublicacion = idPublicacion;
    }

    public int getIdComentario() { return idComentario; }
    public void setIdComentario(int idComentario) { this.idComentario = idComentario; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public LocalDateTime getFechaComentario() { return fechaComentario; }
    public void setFechaComentario(LocalDateTime fechaComentario) { this.fechaComentario = fechaComentario; }
    public int getIdAutor() { return idAutor; }
    public void setIdAutor(int idAutor) { this.idAutor = idAutor; }
    public int getIdPublicacion() { return idPublicacion; }
    public void setIdPublicacion(int idPublicacion) { this.idPublicacion = idPublicacion; }
}

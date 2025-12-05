package ayed.models;

import java.time.LocalDateTime;

import ayed.structures.ListaCustom;

public class Publicacion {
    private int idPublicacion;
    private String mensaje;
    private int idAutor;
    private LocalDateTime fechaPublicacion;

    // propiedad tipo lista enlazada para guardar los comentarios de cada publicacion
    private final ListaCustom<Comentario> comentarios;
     
    public Publicacion(int idPublicacion, String mensaje, int idAutor, LocalDateTime fechaPublicacion) {
        this.idPublicacion = idPublicacion;
        this.mensaje = mensaje;
        this.idAutor = idAutor;
        this.fechaPublicacion = fechaPublicacion;

        // para crear la lista vacia en el new
        this.comentarios = new ListaCustom<>();
    }

    public int getIdPublicacion() { return idPublicacion; }
    public void setIdPublicacion(int idPublicacion) { this.idPublicacion = idPublicacion; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public int getIdAutor() { return idAutor; }
    public void setIdAutor(int idAutor) { this.idAutor = idAutor; }
    public LocalDateTime getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(LocalDateTime fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

    public ListaCustom<Comentario> getComentarios(){
        return comentarios;
    }
    
    public Comentario[] getComentariosArray() {
        return comentarios.toArray(new Comentario[comentarios.getTamano()]);
    }
}

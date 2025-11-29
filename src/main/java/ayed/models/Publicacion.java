package ayed.models;

import java.time.LocalDateTime;

public class Publicacion {
    private int idPublicacion;
    private String mensaje;
    private int idAutor;
    private LocalDateTime fechaPublicacion;
     
    public Publicacion(int idPublicacion, String mensaje, int idAutor, LocalDateTime fechaPublicacion) {
        this.idPublicacion = idPublicacion;
        this.mensaje = mensaje;
        this.idAutor = idAutor;
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getIdPublicacion() { return idPublicacion; }
    public void setIdPublicacion(int idPublicacion) { this.idPublicacion = idPublicacion; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public int getIdAutor() { return idAutor; }
    public void setIdAutor(int idAutor) { this.idAutor = idAutor; }
    public LocalDateTime getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(LocalDateTime fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }
}

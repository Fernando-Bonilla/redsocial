package ayed.models;

import java.time.LocalDateTime;

import ayed.structures.ListaCola;

public class Usuario {
    private int idUsuario;
    private String email;
    private String nombre;
    private String apellido;
    private String genero;
    private String nacionalidad;
    private LocalDateTime fechaRegistro;

    private ListaCola<Notificacion> notificaciones;

    public Usuario(int idUsuario, String email, String nombre, String apellido, String genero, String nacionalidad, LocalDateTime fechaRegistro) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
        this.fechaRegistro = fechaRegistro;        

        // por las dudas creo una lista vacia para evitar conflictos
        this.notificaciones = new ListaCola<Notificacion>();
    }

    public Usuario() {}

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) {this.nacionalidad = nacionalidad;}   


    public ListaCola<Notificacion> getNotificaciones(){
        return notificaciones;
    }

    
}

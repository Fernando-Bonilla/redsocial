package ayed.models;

import java.time.LocalDateTime;


public class Usuario {
    private int idUsuario;
    private String email;
    private String nombre;
    private String apellido;
    private String genero;
    private LocalDateTime fechaRegistro;

    public Usuario(int idUsuario, String email, String nombre, String apellido, String genero, LocalDateTime fechaRegistro) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.fechaRegistro = fechaRegistro;
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
}

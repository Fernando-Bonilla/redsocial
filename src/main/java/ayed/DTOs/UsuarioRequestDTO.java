package ayed.DTOs;

public class UsuarioRequestDTO {
    private String email;
    private String nombre;
    private String apellido;
    private String genero;
    private String nacionalidad;

    public UsuarioRequestDTO(){

    }

    public UsuarioRequestDTO(String email, String nombre, String apellido, String genero, String nacionalidad){
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public String getGenero(){
        return genero;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }

    public String getNacionalidad(){
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad){
        this.nacionalidad = nacionalidad;
    }

}
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

    public void setEmail(String _value){
        this.email = _value;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String _value){
        this.nombre = _value;
    }

    public String getApellido(){
        return apellido;
    }

    public void setApellido(String _value){
        this.apellido = _value;
    }

    public String getGenero(){
        return genero;
    }

    public void setGenero(String _value){
        this.genero = _value;
    }

    public String getNacionalidad(){
        return nacionalidad;
    }

    public void setNacionalidad(String _value){
        this.nacionalidad = _value;
    }



}
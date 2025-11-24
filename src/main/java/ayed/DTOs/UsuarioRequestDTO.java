package ayed.DTOs;

public class UsuarioRequestDTO {
    private String email;
    private String nombre;
    private String apellido;
    private String genero;

    public UsuarioRequestDTO(){

    }

    public UsuarioRequestDTO(String email, String nombre, String apellido, String genero){
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
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
        this.nombre = _value;
    }

    public String getGenero(){
        return genero;
    }

    public void setGenero(String _value){
        this.genero = _value;
    }



}
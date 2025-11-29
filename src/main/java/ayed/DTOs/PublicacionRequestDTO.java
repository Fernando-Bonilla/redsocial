package ayed.DTOs;

public class PublicacionRequestDTO {
    private String emailAutor;
    private String cuerpoPublicacion;    

    public PublicacionRequestDTO(){

    }

    public PublicacionRequestDTO(String emailAutor, String cuerpoPublicacion){
        this.emailAutor = emailAutor;
        this.cuerpoPublicacion = cuerpoPublicacion;        
    }

    public String getEmailAutor(){
        return emailAutor;
    }

    public void setEmailAutor(String _value){
        this.emailAutor = _value;
    }

    public String getCuerpoPublicacion(){
        return cuerpoPublicacion;
    }

    public void setCuerpoPublicacion(String _value){
        this.cuerpoPublicacion = _value;
    }   



}
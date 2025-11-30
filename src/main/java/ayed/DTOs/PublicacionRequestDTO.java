package ayed.DTOs;

public class PublicacionRequestDTO {
    private int idAutor;
    private String cuerpoPublicacion;    

    public PublicacionRequestDTO(){

    }

    public PublicacionRequestDTO(int idAutor, String cuerpoPublicacion){
        this.idAutor = idAutor;
        this.cuerpoPublicacion = cuerpoPublicacion;        
    }

    public int getIdAutor(){
        return idAutor;
    }

    public void setIdAutor(int _value){
        this.idAutor = _value;
    }

    public String getCuerpoPublicacion(){
        return cuerpoPublicacion;
    }

    public void setCuerpoPublicacion(String _value){
        this.cuerpoPublicacion = _value;
    }

}
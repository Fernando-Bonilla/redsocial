package ayed.DTOs;

public class ComentarioRequestDTO {
    private int _idAutor;
    private String _cuerpoComentario;

    public ComentarioRequestDTO(){
        
    }

    public ComentarioRequestDTO(int idAutor, String cuerpoComentario){
        this._idAutor = idAutor;
        this._cuerpoComentario = cuerpoComentario;
    }

    public int getIdAutor(){
        return _idAutor;
    }

    public void setIdAutor(int value) {
        this._idAutor = value;
    }

    public String getCuerpoComentario(){
        return _cuerpoComentario;
    }

    public void setCuerpoComentario(String value){
        this._cuerpoComentario = value;
    }
     

}

package ayed.DTOs;

public class UsuariosPeriodoRegistroRequestDTO {
    private String desde;
    private String hasta;

    public UsuariosPeriodoRegistroRequestDTO(){
        
    }

    public String getDesde(){
        return desde;
    }

    public void setDesde(String value){
        this.desde = value;
    }

    public String getHasta(){
        return hasta;
    }

    public void setHasta(String value){
        this.hasta = value;
    }
    
}

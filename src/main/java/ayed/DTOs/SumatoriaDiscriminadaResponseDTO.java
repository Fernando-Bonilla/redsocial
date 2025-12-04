package ayed.DTOs;


public class SumatoriaDiscriminadaResponseDTO {
    private int sumatoria;
    private String nacionalidad;
    private String genero;

    public SumatoriaDiscriminadaResponseDTO() {}

    public SumatoriaDiscriminadaResponseDTO(int sumatoria, String nacionalidad, String genero) {
        this.sumatoria = sumatoria;
        this.nacionalidad = nacionalidad;
        this.genero = genero;
    }

    public int getSumatoria() { return sumatoria; }

    public String getNacionalidad() { return nacionalidad; }

    public String getGenero() { return genero; }

    public void setSumatoria(int sumatoria) { this.sumatoria = sumatoria; }

    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    
    public void setGenero(String genero) { this.genero = genero; }
}

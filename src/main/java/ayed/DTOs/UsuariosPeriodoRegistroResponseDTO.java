package ayed.DTOs;

import ayed.models.Usuario;

public class UsuariosPeriodoRegistroResponseDTO {

    private int cantUsuarios;
    private Usuario[] usuarios;

    public UsuariosPeriodoRegistroResponseDTO(int cantidadUsuarios, Usuario[] usuarios){
        this.cantUsuarios = cantidadUsuarios;
        this.usuarios = usuarios;
    }

    public int getCantidad(){
        return cantUsuarios;
    }

    public Usuario[] getUsuarios(){
        return usuarios;
    }
    
}

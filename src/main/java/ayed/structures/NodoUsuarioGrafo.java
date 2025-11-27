package ayed.structures;

import ayed.models.Usuario;

public class NodoUsuarioGrafo {
    private Usuario usuario;
    private ListaCustom<Integer> amigosIds;

    public NodoUsuarioGrafo(Usuario usuarioNuevo) {
        this.usuario = usuarioNuevo;
        
        this.amigosIds = new ListaCustom();
    }

    public NodoUsuarioGrafo (){}

    public Usuario getUsuario() {
        return usuario;
    }
    public ListaCustom<Integer> getAmigosIds() {
        return amigosIds;
    }

    public void setAmigosId(Integer amigoId){
        this.amigosIds.agregarAlInicio(amigoId);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}

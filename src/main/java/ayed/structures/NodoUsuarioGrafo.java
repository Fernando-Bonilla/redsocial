package ayed.structures;

import ayed.models.Usuario;

public class NodoUsuarioGrafo {
    private Usuario usuario;
    private ListaCustom<Integer> amigosIds;


    public NodoUsuarioGrafo(Usuario usuarioNuevo) {
        this.usuario = usuarioNuevo;
        
        this.amigosIds = new ListaCustom<>();
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

        // Aca llamo a este metodo para agregar una notificacion cada vez que recibe un nuevo amigo
        UsuariosRepositorio.getInstance().agregarNotificacion(this.usuario.getIdUsuario(), "Usted a recibido un nuevo amichi");
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}

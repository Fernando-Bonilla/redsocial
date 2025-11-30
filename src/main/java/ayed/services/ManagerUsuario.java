package ayed.services;

import ayed.models.Usuario;
import ayed.structures.ListaCustom;
import ayed.structures.Nodo;
import ayed.structures.NodoUsuarioGrafo;
import ayed.structures.TablaHash;
import ayed.structures.UsuariosRepositorio;

public class ManagerUsuario {
    
    private final TablaHash<Integer, NodoUsuarioGrafo> usuariosGrafo;
    private final UsuariosRepositorio usuariosRepo;

    public ManagerUsuario() {
        this.usuariosRepo = UsuariosRepositorio.getInstance();
        this.usuariosGrafo = usuariosRepo.getGrafoUsuarios();
    }
    
    public boolean agregarUsuario(Usuario user)
    {
        if(usuariosGrafo.buscar(user.getIdUsuario()) != null){
            return false;
        }

        NodoUsuarioGrafo newNodoUsuarioGrafo = new NodoUsuarioGrafo(user);
        usuariosGrafo.insertar(user.getIdUsuario(), newNodoUsuarioGrafo);
        
        return true;
    }

    public boolean agregarAmigo(int idUsuario, int idAmigo)
    {
        NodoUsuarioGrafo nodoUsuario = usuariosGrafo.buscar(idUsuario);
        NodoUsuarioGrafo nodoAmigo = usuariosGrafo.buscar(idAmigo);

        if(nodoUsuario == null || nodoAmigo == null){
            return false;
        }

        nodoUsuario.setAmigosId(idAmigo);

        return true;
    }

    public boolean eliminarUsuario(int idUsuario)
    {
        return usuariosGrafo.eliminiar(idUsuario);
    }

    public ListaCustom<Usuario> obtenerUsuarios(){
        ListaCustom<NodoUsuarioGrafo> lista;
        
        lista = usuariosGrafo.listarValores();
        Nodo<NodoUsuarioGrafo> actual = lista.getCabeza();
        ListaCustom<Usuario> usuarios = new ListaCustom<>();

        while(actual != null){
            usuarios.agregarAlInicio(actual.getDato().getUsuario());
            actual = actual.getSiguiente();
        }

        return usuarios;

    }

}

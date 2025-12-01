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

    public ListaCustom<Usuario> amigosEnComun(int idUsuarioUno, int idUsuarioDos){

        // busco los nodos en el grafo
        NodoUsuarioGrafo nodoUno = usuariosGrafo.buscar(idUsuarioUno);
        NodoUsuarioGrafo nodoDos = usuariosGrafo.buscar(idUsuarioDos);

        if(nodoUno == null || nodoDos == null) {
            return new ListaCustom<>(); // si no existe ningun usuario devuelvo una lista vacia
        }

        // ahora busco los ids de los usuarios que siguen
        ListaCustom<Integer> amigosDelUsuarioUno = nodoUno.getAmigosIds();
        ListaCustom<Integer> amigosDelUsuarioDos = nodoDos.getAmigosIds();

        ListaCustom<Usuario> amigosEnComun = new ListaCustom<>();

        // Ahora recorro ambas listas, basicamente un bucle adentro de otro para encontrar coincidencias
        Nodo<Integer> actualUsuarioUno = amigosDelUsuarioUno.getCabeza();
        while (actualUsuarioUno != null) {

            Integer idAmigoDelUno = actualUsuarioUno.getDato();
            
            // Ahora busco en la lista de ids de amigos del usuario 2 si está el id de arriba
            Nodo<Integer> actualUsuarioDos = amigosDelUsuarioDos.getCabeza();
            while (actualUsuarioDos != null) {
                Integer idAmigoDelDos = actualUsuarioDos.getDato();

                // si son el mismo lo agrego a la lista
                if(idAmigoDelUno.equals(idAmigoDelDos)){
                    
                    NodoUsuarioGrafo nodoAmigo = usuariosGrafo.buscar(idAmigoDelDos);
                    if (nodoAmigo != null) {
                        amigosEnComun.agregarAlInicio(nodoAmigo.getUsuario());
                    }
                    break; // ya sabemos que este idAmigoDelUno esta en las dos listas, asi evito duplicados
                }

                actualUsuarioDos = actualUsuarioDos.getSiguiente();
            }

            actualUsuarioUno = actualUsuarioUno.getSiguiente();
        }

        return amigosEnComun;
    }

}

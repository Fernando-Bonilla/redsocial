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
        NodoUsuarioGrafo nodoUsuario = usuariosGrafo.buscar(idUsuario); //para no buscarlo dos veces, podriamos recibir el nodo directamente
        NodoUsuarioGrafo nodoAmigo = usuariosGrafo.buscar(idAmigo);     //por paramentro en este metodo, ya que en el endpoint ya lo buscamos.

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

    public ListaCustom<Usuario> obtenerAmigosDeAmigos(NodoUsuarioGrafo nodoUsuario){

        //Lo implemento con tabla Hash para poder buscar en el array mas rapido y corroborar no repetidos. Ya que esta implementado 
        //todo para pasar de una lista hash a una lista.

        TablaHash<Integer, Usuario> amigosDeAmigo = new TablaHash<>();

        if(nodoUsuario == null){
            return amigosDeAmigo.listarValores();
        }

        Nodo<Integer> actualAmigo = nodoUsuario.getAmigosIds().getCabeza();
        Nodo<Integer> amigoDeAmigo;

        while(actualAmigo != null){
            int idAmigo = actualAmigo.getDato();
            NodoUsuarioGrafo nodoAmigo = usuariosGrafo.buscar(idAmigo);
            
            amigoDeAmigo = nodoAmigo.getAmigosIds().getCabeza();
            while(amigoDeAmigo != null){
                int idAmigoDeAmigo = amigoDeAmigo.getDato();
                
                if (idAmigoDeAmigo != nodoUsuario.getUsuario().getIdUsuario() && amigosDeAmigo.buscar(idAmigoDeAmigo) == null)
                {
                    NodoUsuarioGrafo nodoAmigoDeAmigo = usuariosGrafo.buscar(idAmigoDeAmigo);
                    if (nodoAmigoDeAmigo != null) {
                        amigosDeAmigo.insertar(idAmigoDeAmigo, nodoAmigoDeAmigo.getUsuario());
                    }
                }

                amigoDeAmigo = amigoDeAmigo.getSiguiente();
            }

            actualAmigo = actualAmigo.getSiguiente();

        }

        ListaCustom<Usuario> listaAmigosDeAmigos = amigosDeAmigo.listarValores(); 

        listaAmigosDeAmigos = filtrarLista(listaAmigosDeAmigos, nodoUsuario.getAmigosIds());

        return listaAmigosDeAmigos;
    }

    public ListaCustom<Usuario> filtrarLista(ListaCustom<Usuario> listaBase, ListaCustom<Integer> listaIds)
    {
        Nodo<Usuario> actual = listaBase.getCabeza();
        Nodo<Usuario> anterior = null;
        Nodo<Integer> comparado = listaIds.getCabeza();

        if(comparado == null){
            return listaBase;
        }

        while(actual != null)
        {
            int idActual = actual.getDato().getIdUsuario();

            while(comparado != null)
            {
                if(comparado.getDato() == idActual)
                {
                    if(anterior == null)
                    {
                        listaBase.setCabeza(actual.getSiguiente());
                    }
                    else
                    {
                        anterior.setSiguiente(actual.getSiguiente());
                    }
                    break;
                }

                comparado = comparado.getSiguiente();

            }

            anterior = actual;
            actual = actual.getSiguiente();
            comparado = listaIds.getCabeza();

        }
    
        return listaBase;
    }
}

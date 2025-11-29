package ayed.structures;

public class UsuariosRepositorio {    
    public static UsuariosRepositorio _instance;
    private final UsuariosListaEnlazada _usuarios;
    private final TablaHash<Integer, NodoUsuarioGrafo> grafoUsuarios;
    private int contadorIds = 0;

    private UsuariosRepositorio(){
        this._usuarios = new UsuariosListaEnlazada();
        this.grafoUsuarios = new TablaHash<>();
    }

    public UsuariosListaEnlazada getUsuarios(){
        return _usuarios;
    }

    public static UsuariosRepositorio getInstance(){
        if(_instance == null){
            _instance = new UsuariosRepositorio();
        }

        return _instance;
    }

    public TablaHash<Integer, NodoUsuarioGrafo> getGrafoUsuarios() {
        return grafoUsuarios;
    }

    public int generarIdUsuario(){
        return ++contadorIds;
    }

    public int obtenerUltimoIdUsuario(){
        return contadorIds;
    }

    public void guardarEnMemoria(){
        //Logica para serializar y guardar en un archivo local
    }

    public void cargarDeMemoria(){
        //Logica para cargar el archivo local y deserializar
    }
}

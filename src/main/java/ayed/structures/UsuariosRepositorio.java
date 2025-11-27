package ayed.structures;

public class UsuariosRepositorio {    
    public static UsuariosRepositorio _instance;
    private UsuariosListaEnlazada _usuarios;

    private UsuariosRepositorio(){
        this._usuarios = new UsuariosListaEnlazada();
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
}

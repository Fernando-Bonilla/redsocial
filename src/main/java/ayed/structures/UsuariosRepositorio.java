package ayed.structures;

public class UsuariosRepositorio {
    private int contador;
    public static UsuariosRepositorio _instance;

    private UsuariosRepositorio(){
        this.contador = 0;
    }

    public static UsuariosRepositorio getInstance(){
        if(_instance == null){
            _instance = new UsuariosRepositorio();
        }

        return _instance;
    }

    public int sumarContador(){
        this.contador += 1;
        return contador;
    }
}

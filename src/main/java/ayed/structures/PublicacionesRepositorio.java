package ayed.structures;

import java.time.LocalDateTime;

import ayed.models.Publicacion;
import ayed.models.Usuario;

public class PublicacionesRepositorio {    
    public static PublicacionesRepositorio _instance;
    private final UsuariosRepositorio _repoUsuarios = UsuariosRepositorio.getInstance();
    private final ListaCustom<Publicacion> _publicaciones;
    private final TablaHash<Integer, Publicacion> _indicePublicaciones;
    private int contadorIds = 0;

    private PublicacionesRepositorio(){
        this._publicaciones = new ListaCustom<>();
        this._indicePublicaciones = new TablaHash<>();
        crearPublicacionesParaPrueba();
    }

    public ListaCustom<Publicacion> getPublicaciones(){
        return _publicaciones;
    }

    public static PublicacionesRepositorio getInstance(){
        if(_instance == null){
            _instance = new PublicacionesRepositorio();
        }

        return _instance;
    }

    public TablaHash<Integer, Publicacion> getIndicePublicaciones() {
        return _indicePublicaciones;
    }

    public int generarIdPublicacion(){
        return ++contadorIds;
    }
    

    

    private void crearPublicacionesParaPrueba(){
        crearPublicacionInicial(1, "Testeando publicacion");
        crearPublicacionInicial(1, "Alto chango yo soy");
        crearPublicacionInicial(2, "Publicacion autor ind 2");
        crearPublicacionInicial(3, "Pisteando como un campeon");
        crearPublicacionInicial(4, "Como dijo paulo cohelo, no hay insomnio que aguante...");
        crearPublicacionInicial(5, "asdasdasdasd");
        crearPublicacionInicial(5, "alto alto");       

    }

    private void crearPublicacionInicial(int idAutor, String cuerpoPublicacion){
        int idPublicacion = generarIdPublicacion();

        Publicacion publicacion = new Publicacion(
            idPublicacion,
            cuerpoPublicacion,
            idAutor,            
            LocalDateTime.now()
        );

        _publicaciones.agregarAlInicio(publicacion);        
        _indicePublicaciones.insertar(idPublicacion, publicacion);

    }
}

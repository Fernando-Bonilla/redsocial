package ayed.structures;

import java.lang.reflect.Array;
import java.time.LocalDateTime;

import ayed.models.Comentario;
import ayed.models.Notificacion;
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

    public Publicacion agregarPublicacion(int idAutor, String cuerpoPublicacion){
        int idPublicacion = generarIdPublicacion();

        Publicacion nuevaPublicacion = new Publicacion(
            idPublicacion, 
            cuerpoPublicacion, 
            idAutor, 
            LocalDateTime.now()
        );

        // aca lo guardo en la lista enlazada, que me va a servir para recorrer y ver la que tiene mas comentario
        _publicaciones.agregarAlInicio(nuevaPublicacion);
        // y aca lo agrego a la HashMap indexado
        _indicePublicaciones.insertar(idPublicacion, nuevaPublicacion);

        return nuevaPublicacion;
    }    

    // aca devuelvo un array porque ya tengo el total de publicaciones
    public Publicacion[] listarPubliciones(){
        Publicacion[] arrayPublicaciones = _publicaciones.toArray(new Publicacion[contadorIds]);
        
        return arrayPublicaciones;
    }

    public Comentario comentarPublicacion(int idPub, int idAutorComentario , String comentarioCuerpo){

        Publicacion publicacion = _indicePublicaciones.buscar(idPub);
        // Buscar publicación        
        if (publicacion == null) {
            // No existe la publicación
            return null;
        }

        Comentario nuevComentario = new Comentario(
            comentarioCuerpo, 
            LocalDateTime.now(), 
            idAutorComentario, 
            idPub
        );

        publicacion.getComentarios().agregarAlInicio(nuevComentario);      
        
        // Uso este metodo para agregarle una notificacion al autor cada vez que recibe un comentario
        
        _repoUsuarios.agregarNotificacion(publicacion.getIdAutor(),  "Se agregó un nuevo comentario a una de tus publicaciones");      

        return nuevComentario;
        
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

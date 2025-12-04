package ayed.structures;

import java.time.LocalDateTime;

import ayed.models.Notificacion;
import ayed.models.Usuario;

public class UsuariosRepositorio {    
    public static UsuariosRepositorio _instance;
    private final ListaCustom<Usuario> _usuarios;
    private final TablaHash<Integer, NodoUsuarioGrafo> grafoUsuarios;
    private int contadorIds = 0;

    private UsuariosRepositorio(){
        this._usuarios = new ListaCustom<>();
        this.grafoUsuarios = new TablaHash<>();
        crearUsuariosParaPrueba();
    }

    public ListaCustom<Usuario> getUsuarios(){
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

    // metodo que voy a usar en Publicaciones Agregar y cuando un usuario mme sigue, para agregarle una nueva notificacion
    public void agregarNotificacion(int idAutorPublicacion, String mensaje) {
        Usuario usuarioAutorPublicacion = _instance.getGrafoUsuarios().buscar(idAutorPublicacion).getUsuario();
        Notificacion notificacion = new Notificacion(mensaje);
        usuarioAutorPublicacion.getNotificaciones().agregarNodo(notificacion);

    }     

    private void crearUsuariosParaPrueba(){
        crearUsuarioInicial("alberto@gmail.com", "Alberto", "García", "M", LocalDateTime.of(2025, 12, 01, 15, 30), "uruguay");
        crearUsuarioInicial("pepe@gmail.com", "Pepe",   "Pérez",  "M", LocalDateTime.of(2025, 12, 02, 16, 30), "uruguay");
        crearUsuarioInicial("carla@gmail.com", "Carla", "López",  "F", LocalDateTime.of(2025, 11, 27, 15, 30), "uruguay");
        crearUsuarioInicial("cecilia@gmail.com", "Cecilia", "López",  "F", LocalDateTime.now(), "uruguay");
        crearUsuarioInicial("fefo@gmail.com", "Fede", "Roro",  "M", LocalDateTime.now(), "uruguay");
        crearUsuarioInicial("fernando@gmail.com", "Fernando", "Bonilla",  "M", LocalDateTime.now(), "uruguay");
        crearUsuarioInicial("matias@gmail.com", "Matias", "Arruabarrena",  "M", LocalDateTime.now(), "argentina");
        crearUsuarioInicial("rupestre@gmail.com", "Ruben", "Yorugua",  "M", LocalDateTime.now(),"argentina");
        crearUsuarioInicial("laMona@gmail.com", "Ramon", "Gimenez",  "M", LocalDateTime.now(),"argentina");
        crearUsuarioInicial("pipi@gmail.com", "Pipona", "Alberti",  "F", LocalDateTime.now(), "argentina");
        crearUsuarioInicial("miguelAltoChango@gmail.com", "Miguel", "Perez",  "M", LocalDateTime.now(), "paraguay");
        crearUsuarioInicial("natalia@gmail.com", "Natalia", "Murriaga",  "F", LocalDateTime.now(), "paraguay");

    }

    private void crearUsuarioInicial(String email, String nombre, String apellido, String genero, LocalDateTime fechaRegistro, String nacionalidad){
        int id = generarIdUsuario();

        Usuario usuario = new Usuario(
            id,
            email,
            nombre,
            apellido,
            genero,
            fechaRegistro,
            nacionalidad
        );

        _usuarios.agregarAlInicio(usuario);
        NodoUsuarioGrafo nodo = new NodoUsuarioGrafo(usuario);
        grafoUsuarios.insertar(id, nodo);

    }
}

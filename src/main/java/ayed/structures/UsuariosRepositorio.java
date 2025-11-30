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
        crearUsuarioInicial("alberto@gmail.com", "Alberto", "García", "M");
        crearUsuarioInicial("pepe@gmail.com", "Pepe",   "Pérez",  "M");
        crearUsuarioInicial("carla@gmail.com", "Carla", "López",  "F");
        crearUsuarioInicial("cecilia@gmail.com", "Cecilia", "López",  "F");
        crearUsuarioInicial("fefo@gmail.com", "Fede", "Roro",  "M");
        crearUsuarioInicial("fernando@gmail.com", "Fernando", "Bonilla",  "M");
        crearUsuarioInicial("matias@gmail.com", "Matias", "Arruabarrena",  "M");
        crearUsuarioInicial("rupestre@gmail.com", "Ruben", "Yorugua",  "M");
        crearUsuarioInicial("laMona@gmail.com", "Ramon", "Gimenez",  "M");
        crearUsuarioInicial("pipi@gmail.com", "Pipona", "Alberti",  "F");
        crearUsuarioInicial("miguelAltoChango@gmail.com", "Miguel", "Perez",  "M");
        crearUsuarioInicial("natalia@gmail.com", "Natalia", "Murriaga",  "F");

    }

    private void crearUsuarioInicial(String email, String nombre, String apellido, String genero){
        int id = generarIdUsuario();

        Usuario usuario = new Usuario(
            id,
            email,
            nombre,
            apellido,
            genero,
            LocalDateTime.now()
        );

        _usuarios.agregarAlInicio(usuario);
        NodoUsuarioGrafo nodo = new NodoUsuarioGrafo(usuario);
        grafoUsuarios.insertar(id, nodo);

    }
}

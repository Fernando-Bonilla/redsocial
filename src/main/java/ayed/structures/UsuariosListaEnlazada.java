package ayed.structures;

public class UsuariosListaEnlazada {

    private UsuarioNodoListaEnlazada head;
    private UsuarioNodoListaEnlazada ultimo;

    public void AgregarNodo(UsuarioNodoListaEnlazada nodo){

        if(head == null){
            this.head = nodo;
            this.ultimo = nodo;
        }else{
            ultimo.setSiguiente(nodo);
            ultimo = nodo;
        }        
        
    }

    public void ImprimirList(){
        ImprimirNodo(head);
    }

    public void ImprimirNodo(UsuarioNodoListaEnlazada nodo){

        //System.out.println(nodo.getId());

        if(nodo.getSiguiente() != null){

            ImprimirNodo(nodo.getSiguiente());
        }
        
    }

    public String ListarUsuarios(){
        if (head == null) {
            return "[]";
        }

        String body = MostrarNodo(head);
        return "[" + body + "]";
    }

    public String MostrarNodo(UsuarioNodoListaEnlazada nodo){

        String listaAcumulada = "{"
        + "\"email\":\""   + nodo.getEmail()   + "\","
        + "\"nombre\":\""  + nodo.getNombre()  + "\","
        + "\"apellido\":\""+ nodo.getApellido()+"\","
        + "\"genero\":\""  + nodo.getGenero()  + "\""
        + "}";

        if(nodo.getSiguiente() != null){

            return listaAcumulada + "," + MostrarNodo(nodo.getSiguiente());
        }else{
           return listaAcumulada;
        }
        
    }

    public boolean ModificarUsuario(String email, String nuevoNombre, String nuevoApellido, String nuevoGenero) {
        
        UsuarioNodoListaEnlazada actual = head;
        
        while(actual != null) {
            if(actual.getEmail() != null && actual.getEmail().equals(email)) {

                actual.setNombre(nuevoNombre);
                actual.setApellido(nuevoApellido);
                actual.setGenero(nuevoGenero);

                return true;

            }

            actual = actual.getSiguiente();
        }

        return false;
    }

    public boolean EliminarUsuario(String email) {        

        if(head == null) {
            return false;
        }

        if(head.getEmail() != null && head.getEmail().equals(email)){
            head = head.getSiguiente();

            if(head == null) {
                ultimo = null;
            }

            return true;
        }

        UsuarioNodoListaEnlazada actual = head;

        while (actual.getSiguiente() != null) {

            UsuarioNodoListaEnlazada siguiente = actual.getSiguiente();

            if(siguiente.getSiguiente().getEmail() != null && siguiente.getSiguiente().getEmail().equals(email)) {

                //aca si lo encuentro lo desconecto y apunto al siguiente del siguiente
                actual.setSiguiente(siguiente.getSiguiente());

                if(siguiente == ultimo) { //aca el siguiente es el que borro
                    ultimo = actual; // y aca el anterior pasa a ser el ultimo
                }

                return true;
            }

            actual = actual.getSiguiente();
        }

        // y si llego hasta acá no encontré ningun usuario con ese email
        return false;
    }
    
}

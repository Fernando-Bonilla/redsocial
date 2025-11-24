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
    
}

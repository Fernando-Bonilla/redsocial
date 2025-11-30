package ayed.structures;

public class ListaCola<T> {

    private Nodo<T> head;
    private Nodo<T> ultimo;

    public void agregarNodo(T nodo){
        Nodo<T> nuevoNodo = new Nodo<>(nodo);

        if(head == null){
            this.head = nuevoNodo;
            this.ultimo = head;
        }else {
            ultimo.setSiguiente(nuevoNodo);
            this.ultimo = nuevoNodo;
        }            
        
    }
    
    public Nodo<T> eliminarPrimerElemento(){
        if(head == null){
            return null;
        }

        Nodo<T> actual = head;
        this.head = actual.getSiguiente();
        
        return actual;
    }
    
}

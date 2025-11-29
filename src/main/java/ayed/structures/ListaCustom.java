package ayed.structures;

public class ListaCustom<T> {
    private Nodo<T> cabeza;

    private int tamano;

    public ListaCustom(Nodo<T> dato) {
        this.cabeza = dato;
        this.tamano = 1;
    }

    public ListaCustom() {
        this.cabeza = null;
        this.tamano = 0;
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public int getTamano() {
        return tamano;
    }

    public void agregarAlInicio(T nuevoDato) {
        Nodo<T> nuevoNodo = new Nodo<>(nuevoDato);
        nuevoNodo.setSiguiente(cabeza);
        cabeza = nuevoNodo;
        tamano++;
    }

    public T[] toArray(T[] array){
        Nodo<T> actual = cabeza;
        
        int i = 0;
        while(actual != null){
            array[i++] = actual.getDato();
            actual = actual.getSiguiente();
        }

        return array;
    }
}

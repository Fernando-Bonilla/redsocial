package ayed.structures;

public class ListaCustom<T> {
    private Nodo<T> cabeza;
    private Nodo<T> siguiente;
    private int tamano;

    public ListaCustom(Nodo<T> dato) {
        this.cabeza = dato;
        this.siguiente = null;
        this.tamano = 1;
    }

    public ListaCustom() {
        this.cabeza = null;
        this.siguiente = null;
        this.tamano = 0;
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public int getTamano() {
        return tamano;
    }

    public void agregarAlInicio(Nodo<T> nuevoNodo) {
        nuevoNodo.setSiguiente(cabeza);
        cabeza = nuevoNodo;
        tamano++;
    }
}

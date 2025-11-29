package ayed.structures;

public class NodoHash<C, V> {
    C clave;
    V valor;

    NodoHash<C, V> siguiente;

    public NodoHash() {
    }

    public NodoHash(C clave, V valor)
    {
        this.clave = clave;
        this.valor = valor;
        this.siguiente = null;
    }

    public C getClave() {
        return clave;
    }

    public void setClave(C clave) {
        this.clave = clave;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public NodoHash<C, V> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoHash<C, V> siguiente) {
        this.siguiente = siguiente;
    }
    
}

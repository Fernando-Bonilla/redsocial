package ayed.structures;

public class NodoEnlazadoCustom {
    
    private int _id;
    private NodoEnlazadoCustom _siguiente;    

    public NodoEnlazadoCustom(int pid) {
        this._id = pid;

    }

    public int getId(){
        return _id;
    }

    public void setId(int value){
        this._id = value;
    }

    public NodoEnlazadoCustom getSiguiente(){
        return _siguiente;
    }

    public void setSiguiente(NodoEnlazadoCustom value){
        this._siguiente = value;
    }

}

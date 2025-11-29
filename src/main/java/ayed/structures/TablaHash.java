package ayed.structures;

public class TablaHash<C, V> {
    private final NodoHash<C, V>[] tabla;

    private final int capacidad;
    private int tamaño;

    public static final int CAPACIDAD_DEFAULT = 101;   //Se sugiere numeros primos para evitar colisiones en el hash


    @SuppressWarnings("unchecked")
    public TablaHash() {
        this.capacidad = CAPACIDAD_DEFAULT;
        this.tamaño = 0;
        this.tabla = (NodoHash<C, V>[]) new NodoHash[CAPACIDAD_DEFAULT];
    }

    public int getCapacidad() {
        return capacidad;
    }

    private int getClave (C clave){
        int hash = clave.hashCode();

        return (hash % capacidad + capacidad ) % capacidad;
    }

    public void insertar(C clave, V valor) {
        int indice = getClave(clave);
        NodoHash<C, V> nuevoNodo = new NodoHash<>(clave, valor);

        if (tabla[indice] == null) {
            tabla[indice] = nuevoNodo;
        } else {
            NodoHash<C, V> actual = tabla[indice];
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamaño++;
    }

    public V buscar (C clave){
        int indice = getClave(clave);

        NodoHash<C, V> actual = tabla[indice];

        while(actual != null){
            if(actual.getClave().equals(clave)){
                return actual.getValor();
            }

            actual = actual.getSiguiente();
        }
        
        return null;
    }

    public boolean eliminiar(C clave){
        int indice = getClave(clave);

        NodoHash<C, V> actual = tabla[indice];
        NodoHash<C, V> anterior = null;

        while(actual != null){
            if(actual.getClave().equals(clave)){
                if(anterior == null){
                    tabla[indice] = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                tamaño--;
                return true;
            }

            anterior = actual;
            actual = actual.getSiguiente();
        }

        return false;
    }

    public ListaCustom<V> listarValores(){
        ListaCustom<V> lista = new ListaCustom<>();
        NodoHash<C, V> actual;

        for (int i = 0; i < capacidad; i++){
            actual = tabla[i];
            while(actual != null){
                lista.agregarAlInicio(actual.getValor());
                actual = actual.getSiguiente();
            }
        }

        return lista;
    }
}

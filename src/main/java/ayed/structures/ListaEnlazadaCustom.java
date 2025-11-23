package ayed.structures;


public class ListaEnlazadaCustom {

    private NodoEnlazadoCustom head;
    private NodoEnlazadoCustom ultimo;

    public void AgregarNodo(NodoEnlazadoCustom nodo){

        if(head == null){
            this.head = nodo;
            this.ultimo = nodo;
        }

        ultimo.setSiguiente(nodo);
        ultimo = nodo;
        
    }

    public void ImprimirList(){
        ImprimirNodo(head);
    }

    public void ImprimirNodo(NodoEnlazadoCustom nodo){

        System.out.println(nodo.getId());

        if(nodo.getSiguiente() != null){

            ImprimirNodo(nodo.getSiguiente());
        }
        
    }

    public String ListarUsuarios(){
        if (head == null) {
            return "";
        }
        return MostrarNodo(head, "");
    }

    public String MostrarNodo(NodoEnlazadoCustom nodo, String listaAcumulada){

        listaAcumulada += (nodo.getId()) + ", ";

        if(nodo.getSiguiente() != null){

            return MostrarNodo(nodo.getSiguiente(), listaAcumulada);
        }else{
           return listaAcumulada;
        }
        
    }
    
}

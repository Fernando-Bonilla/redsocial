package com.redsocial;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("usuarios")
public class UsuariosResource {
    
    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        UsuariosRepositorio usuarioR = UsuariosRepositorio.getInstance();
        
        int cont = usuarioR.sumarContador();

        ListaEnlazadaCustom listaCustom = new ListaEnlazadaCustom();

        for(int i = 1; i < 6; i++){
            NodoEnlazadoCustom nodo = new NodoEnlazadoCustom(i);
            listaCustom.AgregarNodo(nodo);
        }
        
        listaCustom.ImprimirList();

        return "Hola JAX-RS con Jersey + Grizzly: " + cont;
    }

    @GET
    @Path("listar")
    @Produces(MediaType.APPLICATION_JSON)
    public String ListarUsuarios(){

        ListaEnlazadaCustom listaCustom = new ListaEnlazadaCustom();

        for(int i = 1; i < 6; i++){
            NodoEnlazadoCustom nodo = new NodoEnlazadoCustom(i);
            listaCustom.AgregarNodo(nodo);
        }

        return listaCustom.ListarUsuarios();
    }


}

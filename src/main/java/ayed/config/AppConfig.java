package ayed.config;

import java.util.HashSet;
import java.util.Set;

import ayed.resources.PublicacionesResource;
import ayed.resources.UsuariosResource;
import jakarta.ws.rs.core.Application;

public class AppConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        // Agregar todas las clases endpoints aca
        
        resources.add(UsuariosResource.class);
        resources.add(PublicacionesResource.class);

        return resources;
    }
}
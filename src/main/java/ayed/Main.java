package ayed;
import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jsonb.JsonBindingFeature;

public class Main {  
    // URL base del API
    public static final String BASE_URI = "http://localhost:8080/api/";

    public static HttpServer startServer() {
        // Configuración de Jersey: escanea el paquete com.ejemplo
        final ResourceConfig rc = new ResourceConfig()
                .packages("ayed.resources")
                .register(JsonBindingFeature.class);

        // Crea y arranca el server HTTP en BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println("Servidor JAX-RS levantado en " + BASE_URI);
        System.out.println("Probá: " + BASE_URI + "usuarios");
        System.out.println("Presioná ENTER para detener...");

        System.in.read();
        server.shutdownNow();
    }
}

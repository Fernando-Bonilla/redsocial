## Instalacion de entorno de desarrollo

### 1- Instalar scoop y maven en windows powershell
```Set-ExecutionPolicy RemoteSigned -Scope CurrentUser```
```irm get.scoop.sh | iex```
```scoop install maven```

### 2- Clonar repo localmente

### 3- Entrar al directorio del proyecto y correr
```mvn compile```

## Para levantar el server correr el comando 
```mvn package```
```java -jar target\jaxrs-demo-1.0-SNAPSHOT.jar```

## Estructura sugerida

```/RedSocialAYED```/
```/|-- pom.xml                <-- Archivo de configuración de Maven```/
```/|-- /src```/
```/|   |-- /main```/
```/|   |   |-- /java```/
```/|   |   |   |-- /uy/edu/instituto/ayed```/
```/|   |   |   |   |-- /config          <-- Configuración del Servidor (JAX-RS Application)```/
```/|   |   |   |   |   |-- AppConfig.java```/
```/|   |   |   |   |-- /model           <-- Entidades de Datos```/
```/|   |   |   |   |   |-- Usuario.java```/
```/|   |   |   |   |   |-- Publicacion.java```/
```/|   |   |   |   |   |-- Notificacion.java```/
```/|   |   |   |   |-- /structures      <-- ¡Tus implementaciones propias!```/
```/|   |   |   |   |   |-- ListaEnlazada.java```/
```/|   |   |   |   |   |-- Cola.java```/
```/|   |   |   |   |   |-- HashTable.java```/
```/|   |   |   |   |   |-- Grafo.java```/
```/|   |   |   |   |-- /manager         <-- Lógica de Negocio y Estructuras Centrales```/
```/|   |   |   |   |   |-- RedSocialManager.java (Contiene las instancias de Grafo, HashTable, etc.)```/
```/|   |   |   |   |-- /resource        <-- Tus Endpoints JAX-RS (Servicios REST)```/
```/|   |   |   |   |   |-- UsuarioResource.java```/
```/|   |   |   |   |   |-- PublicacionResource.java```/
```/|   |   |   |   |   |-- ConsultaResource.java```/
```/|   |   |   |   |-- MainApp.java     <-- Clase principal para iniciar el servidor```
```/|   |   |-- /resources   <-- Archivos de configuración, si los hay. ```


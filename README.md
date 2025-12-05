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


## Requisitos para ejecutar el proyecto solo usando el archivo jar

- Version de java utilizada 21.0.8

Se comparten las dos versiones de archivos jar, la que viene con todas las dependencias incluidas y la que viene si ellas:
- jaxrs-demo-1.0-SNAPSHOT-shaded.jar - Version que incluye todas las dependencias 
- jaxrs-demo-1.0-SNAPSHOT.jar - version sin dependencias.

### Como ejecutar:

1- Ir a la carpeta donde está el archivo:
    jaxrs-demo-1.0-SNAPSHOT-shaded.jar (archivo con todas las dependencias incluidas)

2- Ejecutar:
    java -jar jaxrs-demo-1.0-SNAPSHOT-shaded.jar

El servidor levanta en http://localhost:8080 (en mi caso es asi, puede que varíe),
y se pueden probar los endpoints con Postman.
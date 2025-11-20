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
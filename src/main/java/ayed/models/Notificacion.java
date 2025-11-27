package ayed.models;

public class Notificacion {
    private int idNotificacion;
    private String mensaje;

    public Notificacion(int idNotificacion, String mensaje) {
        this.idNotificacion = idNotificacion;
        this.mensaje = mensaje;
    }

    public int getIdNotificacion() { return idNotificacion; }
    public void setIdNotificacion(int idNotificacion) { this.idNotificacion = idNotificacion; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}

package com.ArcaDeLaAlianza.ArcaDeLaAlianza.service.models;

public class EmailDTO {
    private String destinatario;
    private String aseunto;
    private String mensaje;

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAseunto() {
        return aseunto;
    }

    public void setAseunto(String aseunto) {
        this.aseunto = aseunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

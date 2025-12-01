package com.booksy.dto;

/**
 * dto para respuestas simples con mensaje
 */
public class MensajeRespuesta {
    
    private String mensaje;
    private boolean exito;
    
    public MensajeRespuesta() {
    }
    
    public MensajeRespuesta(String mensaje, boolean exito) {
        this.mensaje = mensaje;
        this.exito = exito;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public boolean isExito() {
        return exito;
    }
    
    public void setExito(boolean exito) {
        this.exito = exito;
    }
}

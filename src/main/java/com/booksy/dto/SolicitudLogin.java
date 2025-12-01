package com.booksy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * dto para la solicitud de login
 */
public class SolicitudLogin {
    
    @NotBlank(message = "el email es obligatorio")
    @Email(message = "el email debe ser valido")
    private String email;
    
    @NotBlank(message = "la contraseña es obligatoria")
    private String password;
    
    public SolicitudLogin() {
    }
    
    public SolicitudLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}

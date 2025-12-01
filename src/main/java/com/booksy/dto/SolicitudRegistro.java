package com.booksy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * dto para la solicitud de registro
 */
public class SolicitudRegistro {
    
    @NotBlank(message = "el email es obligatorio")
    @Email(message = "el email debe ser valido")
    private String email;
    
    @NotBlank(message = "la contraseña es obligatoria")
    @Size(min = 6, message = "la contraseña debe tener al menos 6 caracteres")
    private String password;
    
    @NotBlank(message = "el nombre es obligatorio")
    private String nombre;
    
    public SolicitudRegistro() {
    }
    
    public SolicitudRegistro(String email, String password, String nombre) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
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
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

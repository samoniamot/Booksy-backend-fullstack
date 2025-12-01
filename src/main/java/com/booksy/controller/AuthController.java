package com.booksy.controller;

import com.booksy.dto.MensajeRespuesta;
import com.booksy.dto.RespuestaAuth;
import com.booksy.dto.SolicitudLogin;
import com.booksy.dto.SolicitudRegistro;
import com.booksy.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * controlador para la autenticacion
 * endpoints de login y registro
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@Tag(name = "autenticacion", description = "endpoints para login y registro de usuarios")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @Operation(summary = "registrar un nuevo usuario")
    @PostMapping("/registro")
    public ResponseEntity<?> registro(@Valid @RequestBody SolicitudRegistro solicitud) {
        try {
            RespuestaAuth respuesta = authService.registrar(solicitud);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new MensajeRespuesta(e.getMessage(), false));
        }
    }
    
    @Operation(summary = "iniciar sesion")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SolicitudLogin solicitud) {
        try {
            RespuestaAuth respuesta = authService.login(solicitud);
            return ResponseEntity.ok(respuesta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new MensajeRespuesta(e.getMessage(), false));
        }
    }
    
    @Operation(summary = "verificar si el token es valido")
    @GetMapping("/verificar")
    public ResponseEntity<?> verificar() {
        return ResponseEntity.ok(new MensajeRespuesta("token valido", true));
    }
}

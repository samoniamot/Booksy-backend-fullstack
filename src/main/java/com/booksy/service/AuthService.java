package com.booksy.service;

import com.booksy.dto.RespuestaAuth;
import com.booksy.dto.SolicitudLogin;
import com.booksy.dto.SolicitudRegistro;
import com.booksy.model.Usuario;
import com.booksy.repository.UsuarioRepository;
import com.booksy.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * servicio para manejar la autenticacion de usuarios
 * login registro y validacion
 */
@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;
    
    // registra un nuevo usuario
    public RespuestaAuth registrar(SolicitudRegistro solicitud) {
        // verificar si el email ya existe
        if (usuarioRepository.existsByEmail(solicitud.getEmail())) {
            throw new RuntimeException("el email ya esta registrado");
        }
        
        // crear el usuario con rol user por defecto
        Usuario usuario = new Usuario(
            solicitud.getEmail(),
            passwordEncoder.encode(solicitud.getPassword()),
            solicitud.getNombre(),
            "user"
        );
        
        usuario = usuarioRepository.save(usuario);
        
        // generar el token
        String token = jwtService.generarToken(usuario.getEmail(), usuario.getRol(), usuario.getId());
        
        return new RespuestaAuth(
            token,
            usuario.getId(),
            usuario.getEmail(),
            usuario.getNombre(),
            usuario.getRol()
        );
    }
    
    // inicia sesion y devuelve el token
    public RespuestaAuth login(SolicitudLogin solicitud) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(solicitud.getEmail());
        
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("credenciales incorrectas");
        }
        
        Usuario usuario = usuarioOpt.get();
        
        if (!passwordEncoder.matches(solicitud.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("credenciales incorrectas");
        }
        
        // generar el token
        String token = jwtService.generarToken(usuario.getEmail(), usuario.getRol(), usuario.getId());
        
        return new RespuestaAuth(
            token,
            usuario.getId(),
            usuario.getEmail(),
            usuario.getNombre(),
            usuario.getRol()
        );
    }
    
    // obtiene el usuario actual por email
    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("usuario no encontrado"));
    }
}

package com.proyecto.controller;

import com.proyecto.models.Usuario;
import com.proyecto.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    // Mostrar formulario de registro
    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro"; // Nombre del archivo HTML para el registro
    }

    // Procesar registro
    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String username, 
                                   @RequestParam String password, 
                                   @RequestParam String email, Model model) {
        // Verificar si el nombre de usuario ya existe
        if (usuarioService.findByNombre(username) != null) {
            model.addAttribute("error", "El nombre de usuario ya está registrado.");
            return "registro"; // Redirigir a la página de registro con un mensaje de error
        }

        // Crear un nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(username);
//        usuario.setContraseña(passwordEncoder.encode(password));
        usuario.setCorreo(email);

        // Guardar el usuario en la base de datos
//        usuarioService.save(usuario);

        // Redirigir al login
        return "redirect:/login";
    }


    // Mostrar formulario de inicio de sesión
    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login"; // Nombre del archivo HTML para el login
    }

    // Procesar inicio de sesión
    @PostMapping("/login")
    public String iniciarSesion(
            @RequestParam String correo,
            @RequestParam String contraseña,
            Model model) {
        if (usuarioService.autenticarUsuario(correo, contraseña)) {
            // Inicio de sesión exitoso: redirigir al inicio o dashboard
            return "redirect:/";
        } else {
            // Credenciales incorrectas: mostrar mensaje de error
            model.addAttribute("error", "Correo o contraseña incorrectos.");
            return "login"; // Retorna al formulario de login
        }
    }

}

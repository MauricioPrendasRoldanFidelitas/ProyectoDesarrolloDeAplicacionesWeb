package com.proyecto.controller;

import com.proyecto.models.Usuario;
import com.proyecto.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    // Mostrar formulario de inicio de sesión
    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login"; // Nombre del archivo HTML para el login
    }

    // Procesar inicio de sesión
    @PostMapping("/login")
    public String iniciarSesion(@RequestParam String correo,
                                @RequestParam String contraseña,
                                Model model) {
        // Obtener usuario por correo
        Usuario usuario = usuarioService.findByCorreo(correo);

        if (usuario != null) {
            // Comparar la contraseña ingresada con la almacenada
            if (passwordEncoder.matches(contraseña, usuario.getContraseña())) {
                System.out.println("Contraseña ingresada coincide con la base de datos.");
                return "redirect:/inicio";
            } else {
                System.out.println("Contraseña incorrecta.");
                model.addAttribute("error", "Contraseña incorrecta.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
            model.addAttribute("error", "Usuario no encontrado.");
        }

        return "login";
    }

}

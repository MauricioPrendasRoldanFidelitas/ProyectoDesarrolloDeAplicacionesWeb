package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.proyecto.models.Usuario;
import com.proyecto.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario()); // Asegúrate de inicializar el objeto
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        boolean registrado = usuarioService.registrarUsuario(usuario);
        if (registrado) {
            model.addAttribute("mensaje", "Usuario registrado con éxito.");
            return "registro";
        } else {
            model.addAttribute("error", "Error: El usuario ya existe.");
            return "registro";
        }
    }
}
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

    // Registro de usuario
    /*@PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@ModelAttribute Usuario usuario) {
        boolean registrado = usuarioService.registrarUsuario(usuario);
        if (registrado) {
            return ResponseEntity.ok("Usuario registrado con éxito.");
        } else {
            return ResponseEntity.badRequest().body("Error: El usuario ya existe.");
        }
    }*/

    /*
        return ResponseEntity.ok, solo funciona para peticiones tipo request de retorno JSON, etc. no para
        formularios
    */

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

    /*
    * Esta seccion la quitaria y la pondria en otro controller, para que sea tipo RequestController
    * */
}
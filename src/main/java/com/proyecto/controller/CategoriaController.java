/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.models.Producto;
import com.proyecto.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoriaController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/{categoria}")
    public String mostrarCategoria(@PathVariable String categoria, Model model) {
        List<Producto> productos = productoService.buscarProductosPorCategoria(categoria);
        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Tienda de " + categoria); // Título dinámico
        return "categoria"; // Nombre de la plantilla genérica
    }
}

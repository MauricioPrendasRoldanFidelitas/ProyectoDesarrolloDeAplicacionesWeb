package com.proyecto.services;

import com.proyecto.models.Producto;
import com.proyecto.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    // Obtener un producto por su ID
    public Producto obtenerProductoPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null); // Retorna el producto si existe, o null si no existe
    }
    
    public List<Producto> obtenerProductosEnOferta() {
        return productoRepository.findByEnOfertaTrue();
    }
    
    public List<Producto> buscarProductosPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    public List<Producto> buscarProductosPorCategoria(String categoria) {
        return productoRepository.findByCategoriaContainingIgnoreCase(categoria);
    }

}
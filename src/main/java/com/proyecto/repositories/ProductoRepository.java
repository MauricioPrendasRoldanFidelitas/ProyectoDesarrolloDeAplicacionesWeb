package com.proyecto.repositories;

import com.proyecto.models.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    // Encuentra los productos donde 'enOferta' sea verdadero
    List<Producto> findByEnOfertaTrue();
    
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    
    List<Producto> findByCategoriaContainingIgnoreCase(String categoria);

}
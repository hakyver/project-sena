package com.davidproject.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.davidproject.apirest.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // Puedes agregar métodos de consulta personalizados si es necesario
}

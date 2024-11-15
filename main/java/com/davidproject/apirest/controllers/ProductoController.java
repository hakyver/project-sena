package com.davidproject.apirest.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidproject.apirest.Repositories.ProductoRepository;
import com.davidproject.apirest.entities.Producto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Método para obtener todos los productos
    @GetMapping
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    // Método para crear un nuevo producto
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // Método para actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int id, @RequestBody Producto detallesProducto) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        
        if (productoOptional.isPresent()) {
            Producto productoExistente = productoOptional.get();
            productoExistente.setNombre(detallesProducto.getNombre());
            productoExistente.setPrecio(detallesProducto.getPrecio());
            // Agrega más campos a actualizar según sea necesario
            productoRepository.save(productoExistente);
            return ResponseEntity.ok(productoExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable int id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        return productoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable int id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

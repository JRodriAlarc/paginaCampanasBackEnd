package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controller;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.domain.Product;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductController {
    @Autowired
    private ProductRepository prodServis;

    @GetMapping("/all")
    public ResponseEntity<?> obtenerProducto() {
        try {
            List<Product> products = prodServis.findAll();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Integer id) {
        try {
            Product product = prodServis.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}



package com.ArcaDeLaAlianza.ArcaDeLaAlianza.service.impl;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.domain.Product;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.service.ProductService;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public Product findByName(String name) {
        Optional<Product> product = productRepository.findByName(name);
        return product.orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

}

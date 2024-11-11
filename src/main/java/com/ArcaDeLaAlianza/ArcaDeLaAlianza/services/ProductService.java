package com.ArcaDeLaAlianza.ArcaDeLaAlianza.services;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.*;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImgBBService imgBBService;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(String id){

        return productRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "producto no encontrrado con id: " + id)
        );

    }

    public Product saveProduct(MultipartFile file, Product product) throws Exception {
        Optional<Product> existingProduct = productRepository.findByName(product.getName());

        if (existingProduct.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe este producto" );
        }
        if (file.isEmpty()) {
            return  productRepository.save(product);
        }

        String imageUrl = imgBBService.uploadImage(file);  // Subir cada archivo

        String[] parts = imageUrl.split("/");
        String imgId = parts[parts.length - 2];  // Obtener el ID de la imagen
        Image img = new Image(imgId, imageUrl);
        product.setImage(img);

        return productRepository.save(product);
    }


    public void deleteProduct(String id) {
        Product alloy = productRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto con id: " + id));

        productRepository.deleteById(id);

    }


}

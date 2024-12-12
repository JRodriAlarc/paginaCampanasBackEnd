package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controllers;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.Product;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    private final ObjectMapper objectMapper;
    private final Validator validator;

    public ProductController(ObjectMapper objectMapper, Validator validator) {
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductBYId(@PathVariable String id){
        return ResponseEntity.ok(productService.getProductById(id));
    }



    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveProduct(@RequestPart("image") MultipartFile image,
                                              @RequestPart("product")  String productJson)
            throws Exception {

        try {
            Product product = objectMapper.readValue(productJson, Product.class);

            Set<ConstraintViolation<Product>> violations = validator.validate(product);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
            return ResponseEntity.ok(productService.saveProduct(image, product));

        }
        catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


    @DeleteMapping("/{id}")

    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("producto eliminado correctamente");
    }
}

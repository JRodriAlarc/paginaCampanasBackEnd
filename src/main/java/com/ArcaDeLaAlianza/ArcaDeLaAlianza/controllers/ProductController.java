package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controllers;


import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.StockProduct;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.services.StockProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @Autowired
    private StockProductService stockProductService;

    @PostMapping("/stock")
    public ResponseEntity<?> saveStockProduct(@Valid @RequestBody StockProduct stockProduct){

        return ResponseEntity.ok(stockProductService.saveStockProduct(stockProduct));
    }
}

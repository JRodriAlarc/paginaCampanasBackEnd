package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.Stock;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories.StockRepository;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.StockDTO;

import java.util.List;

@Validated
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockRepository productRepo;

    // Endpoint para agregar stock usando StockDTO
    @PostMapping ("/enviar")
    public ResponseEntity<?> saveStock(@Valid @RequestBody StockDTO stockDTO) {
        try {
            // Convertir StockDTO a Stock
            Stock stock = new Stock(
                    null, // ID se generará automáticamente
                    stockDTO.getNombre(),
                    stockDTO.getDescripcion(),
                    stockDTO.getStock(),
                    stockDTO.getPrecio()
            );

            Stock savedStock = productRepo.save(stock);
            return new ResponseEntity<>(savedStock, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para mostrar todo el stock
    @GetMapping("/Productos")
    public ResponseEntity<?> findAllStock() {
        try {
            List<Stock> stockList = productRepo.findAll();
            return new ResponseEntity<>(stockList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para obtener productos con stock disponible
    @GetMapping("/enStock")
    public ResponseEntity<?> findStockAvailable() {
        try {
            List<Stock> availableStock = productRepo.findByStockGreaterThan(0);
            return new ResponseEntity<>(availableStock, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

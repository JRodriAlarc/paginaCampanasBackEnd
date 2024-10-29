package com.ArcaDeLaAlianza.ArcaDeLaAlianza.services;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.StockProduct;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories.StockProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockProductService {
    @Autowired
    private StockProductRepository stockProductRepository;

    public List<StockProduct> getStockProducts(){
        return stockProductRepository.findAll();
    }

    public StockProduct getStockProductById(String id){
        return stockProductRepository.findById(id).orElse(null);
    }

    public StockProduct saveStockProduct(StockProduct stockProduct){
        return stockProductRepository.save(stockProduct);
    }

}

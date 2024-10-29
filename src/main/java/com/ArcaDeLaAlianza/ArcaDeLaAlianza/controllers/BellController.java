package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controllers;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellAlloy;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellFinish;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellWeightSize;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.services.BellService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bells")
@Validated
public class BellController {
    @Autowired
    private BellService bellService;


    @GetMapping
    public ResponseEntity<?> getBellCustomizationOptions(){
        return ResponseEntity.ok(bellService.getBellCustomizationOptions());
    }

//    Control de las aleaciones de campanas
    @GetMapping("/alloy")
    public ResponseEntity<?> getBellAlloys(){
        return ResponseEntity.ok(bellService.getBellAlloys());
    }

    @PostMapping("/alloy")
    public ResponseEntity<?> saveBellAlloy(@Valid @RequestBody BellAlloy bellAlloy){
        try {
            return ResponseEntity.ok(bellService.saveBellAlloy(bellAlloy));
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/alloy/{id}")
    public ResponseEntity<?> updateBellAlloy(@PathVariable String id,
                                             @Valid @RequestBody BellAlloy alloy ){
        bellService.updateBellAlloy(id, alloy);

        return ResponseEntity.ok("Aleación actualizada correctamente.");
    }

    //    Control de las aleaciones de campanas
    @GetMapping("/sizes")
    public ResponseEntity<?> getBellWeightSizes(){
        return ResponseEntity.ok(bellService.getBellWeightSizes());
    }

    @PostMapping("/sizes")
    public ResponseEntity<?> saveBellWeightSize(@Valid @RequestBody BellWeightSize bellWeightSize){
        try {
            return ResponseEntity.ok(bellService.saveBellWeightSize(bellWeightSize));
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/sizes/{id}")
    public ResponseEntity<?> updateBellWeightSize(@PathVariable String id,
                                             @Valid @RequestBody BellWeightSize bellWeightSize){
        bellService.updateBellWeightSize(id, bellWeightSize);

        return ResponseEntity.ok("Aleación actualizada correctamente.");
    }

    @DeleteMapping("/alloy/{id}")
    public ResponseEntity<?> deleteBellAlloy(@PathVariable String id){
        bellService.deleteBellAlloy(id);
        return ResponseEntity.ok("Aleación eliminada correctamente.");
    }


//    control de los acabados de campanas
    @GetMapping("/finish")
    public ResponseEntity<?> getBellFinishes(){
        return ResponseEntity.ok(bellService.getBellFinishes());
    }

    @PostMapping("/finish")
    public ResponseEntity<?> saveBellFinish(@Valid @RequestBody BellFinish bellFinish){
        try {
            return ResponseEntity.ok(bellService.saveBellFinish(bellFinish));
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/finish/{id}")
    public ResponseEntity<?> updateBellFinish(@PathVariable String id,
                                             @Valid @RequestBody BellFinish bellFinish){
        bellService.updateBellFinish(id, bellFinish);

        return ResponseEntity.ok("Acabado actualizado correctamente.");
    }

    @DeleteMapping("/finish/{id}")
    public ResponseEntity<?> deleteBellFinish(@PathVariable String id){
        bellService.deleteBellFinish(id);
        return ResponseEntity.ok("Acabado eliminado correctamente.");
    }




}

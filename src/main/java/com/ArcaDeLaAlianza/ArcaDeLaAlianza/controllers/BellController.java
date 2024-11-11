package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controllers;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellAlloy;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellFinish;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellWeightSize;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.services.BellService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@RequestMapping("/bells")
@Validated
public class BellController {
    @Autowired
    private BellService bellService;

    private final ObjectMapper objectMapper;
    private final Validator validator;

    public BellController(ObjectMapper objectMapper, Validator validator) {
        this.objectMapper = objectMapper;
        this.validator = validator;
    }


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


    @PostMapping(value = "/finish", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveBellFinish(@RequestPart("files") MultipartFile[] files,
                                             @RequestPart("bellFinish")  String finishJson) throws Exception {
        try {
            BellFinish bellFinish = objectMapper.readValue(finishJson, BellFinish.class);

            Set<ConstraintViolation<BellFinish>> violations = validator.validate(bellFinish);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
             return ResponseEntity.ok(bellService.saveBellFinish(files, bellFinish));

        }
        catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/finish/{id}/img")
    public ResponseEntity<?> addBellFinishImage(@PathVariable String id,
                                               @RequestPart("files") MultipartFile[] files) throws Exception {
        bellService.addBellFinishImg(id, files);
        return ResponseEntity.ok("Imagenes agregadas correctamente.");
    }

    @PutMapping("/finish/{id}/img/{imgId}")
    public ResponseEntity<?> deleteBellFinishImage(@PathVariable String id,
                                                  @PathVariable String imgId){
        bellService.deleteBellFinishImg(id, imgId);

        return ResponseEntity.ok("Imagen eliminada correctamente.");
    }



    @PutMapping("/finish/{id}")
    public ResponseEntity<?> updateBellFinish(@PathVariable String id,
                                             @Valid @RequestBody BellFinish bellFinish){
        bellService.updateBellFinish(id, bellFinish);

        return ResponseEntity.ok("Acabado actualizado correctamente.");
    }


    @DeleteMapping("/finish/{id}")
    public ResponseEntity<?> deleteBellFinish(@PathVariable String id){
//        eliminar las imagenes tambien
        bellService.deleteBellFinish(id);
        return ResponseEntity.ok("Acabado eliminado correctamente.");
    }




}

package com.ArcaDeLaAlianza.ArcaDeLaAlianza.services;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.BellCustomizationOptionsDTO;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellAlloy;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellFinish;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellWeightSize;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories.BellAlloyRepository;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories.BellFinishRepository;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories.BellWeightSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BellService {
    @Autowired
    BellAlloyRepository bellAlloyRepository;
    @Autowired
    BellWeightSizeRepository bellWeightSizeRepository;
    @Autowired
    BellFinishRepository bellFinishRepository;

    public BellCustomizationOptionsDTO getBellCustomizationOptions(){
        List<BellAlloy> alloys= bellAlloyRepository.findAll();
        List<BellWeightSize> weightSizes= bellWeightSizeRepository.findAll();
        List<BellFinish> finishes = bellFinishRepository.findAll();

        return new BellCustomizationOptionsDTO(finishes, alloys, weightSizes);

    }

    //    Control de las aleaciones de campanas
    public List<BellAlloy> getBellAlloys() {
        return bellAlloyRepository.findAll();
    }

    public BellAlloy getBellAlloyById(String id) {
        return bellAlloyRepository.findById(id).orElse(null);
    }


    public BellAlloy saveBellAlloy(BellAlloy bellAlloy) {

        BellAlloy existingAlloy = bellAlloyRepository.findByType(bellAlloy.getType());
        if (existingAlloy != null) {
            throw new IllegalArgumentException("Ya existe una aleación con el tipo especificado");
        }
        return bellAlloyRepository.save(bellAlloy);
    }

    public void updateBellAlloy(String id, BellAlloy bellAlloy) {

        BellAlloy alloy = bellAlloyRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Aleación no encontrada con id: " + id));

        BellAlloy existingAlloy = bellAlloyRepository.findByType(bellAlloy.getType());

        if (existingAlloy != null && !existingAlloy.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe una aleación con el tipo: " + alloy.getType());
        }

            // actualizar con los nuevos datos
            alloy.setType(bellAlloy.getType());
            alloy.setPricePerKg(bellAlloy.getPricePerKg());
            bellAlloyRepository.save(alloy);

    }

    public void deleteBellAlloy(String id) {
        BellAlloy alloy = bellAlloyRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Aleación no encontrada con id: " + id));

         bellAlloyRepository.deleteById(id);

    }




    //    Control de las dimensiones y peso de campanas

    public List<BellWeightSize> getBellWeightSizes(){
        return bellWeightSizeRepository.findAll();
    }

    public BellWeightSize getBellWeightSizeById(String id){
        return bellWeightSizeRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "no encontrado"));
    }

    public BellWeightSize saveBellWeightSize(BellWeightSize bellWeightSize) {

        return bellWeightSizeRepository.save(bellWeightSize);
    }

    public void updateBellWeightSize(String id, BellWeightSize bellWeightSize) {

        BellWeightSize bellSize = bellWeightSizeRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "medidadas no encontrada con id: " + id));

        // actualizar con los nuevos datos
        bellSize.setWeight(bellWeightSize.getWeight());
        bellSize.setHeight(bellWeightSize.getHeight());
        bellSize.setDiameter(bellWeightSize.getDiameter());

        bellWeightSizeRepository.save(bellSize);

    }

    public void deleteBellWeightSize(String id) {
        BellWeightSize bellWeightSize = bellWeightSizeRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "peso no encontrada con id: " + id));
        bellAlloyRepository.deleteById(id);
    }
//    control de los acabados de campanas
    public List<BellFinish> getBellFinishes(){
        return bellFinishRepository.findAll();
    }

    public BellFinish getBellFinishById(String id){
        return bellFinishRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "no encontrado"));
    }

    public BellFinish saveBellFinish(BellFinish bellFinish) {

        return bellFinishRepository.save(bellFinish);
    }
    public void updateBellFinish(String id, BellFinish bellFinish) {

        BellFinish finish = bellFinishRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "medidadas no encontrada con id: " + id));

        // actualizar con los nuevos datos
        finish.setFinish(bellFinish.getFinish());
        finish.setDescription(bellFinish.getDescription());
        finish.setImages(bellFinish.getImages());

        bellFinishRepository.save(finish);

    }
    public  void deleteBellFinish(String id){
        BellFinish bellFinish = bellFinishRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "peso no encontrada con id: " + id));
        bellFinishRepository.deleteById(id);
    }



   


}

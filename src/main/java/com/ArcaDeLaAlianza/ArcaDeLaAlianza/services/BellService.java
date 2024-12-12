package com.ArcaDeLaAlianza.ArcaDeLaAlianza.services;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.dto.BellCustomizationOptionsDTO;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellAlloy;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellFinish;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellWeightSize;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.Image;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories.BellAlloyRepository;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories.BellFinishRepository;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories.BellWeightSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BellService {
    @Autowired
    BellAlloyRepository bellAlloyRepository;
    @Autowired
    BellWeightSizeRepository bellWeightSizeRepository;
    @Autowired
    BellFinishRepository bellFinishRepository;

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private ImgBBService imgBBService;



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
        bellWeightSizeRepository.deleteById(id);


    }


//    control de los acabados de campanas
    public List<BellFinish> getBellFinishes(){
        return bellFinishRepository.findAll();
    }

    public BellFinish getBellFinishById(String id){
        return bellFinishRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "no encontrado"));
    }

    public BellFinish saveBellAcabado(BellFinish bellfinish) {
        return bellFinishRepository.save(bellfinish);
    }


//    guardar los acabados, ademas de subir las imagenes que traen consigo
    public Object saveBellFinish(MultipartFile[] files , BellFinish bellFinish)
            throws Exception {
            if (files.length == 0 || (files.length == 1 && files[0].isEmpty())) {
                bellFinish.setImages(new ArrayList<>());
                return  bellFinishRepository.save(bellFinish);
            }
            List<Image> imageUrls = new ArrayList<>();

        uploadImages(files, bellFinish, imageUrls);
        return bellFinishRepository.save(bellFinish);

    }

//    agregar nueva imagen
    public void addBellFinishImg(String id, MultipartFile[] files) throws Exception {

        BellFinish finish = bellFinishRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "acabado no encontrado con id: " + id));

//        agregar mas imagenes
        List<Image> images = finish.getImages();

        uploadImages(files, finish, images);

        bellFinishRepository.save(finish);

    }

    private void uploadImages(MultipartFile[] files, BellFinish finish, List<Image> images) throws Exception {
        for (MultipartFile file : files) {
            String imageUrl = imgBBService.uploadImage(file);  // Subir cada archivo
//                divir la url por las diagonales
            String[] parts = imageUrl.split("/");
            System.out.println(Arrays.toString(parts));
            String imgId = parts[parts.length - 2];  // Obtener el ID de la imagen
            Image img = new Image(imgId, imageUrl);
            images.add(img);  // Guardar la URL en la lista
        }
        finish.setImages(images);
    }

    public void deleteBellFinishImg(String id, String imgId) {
        BellFinish finish = bellFinishRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "acabado no encontrada con id: " + id));
        List<Image> images = finish.getImages();

        images.remove(images.stream().filter(
                img -> img.getImageId().equals(imgId))
                .findFirst().orElse(null));
        finish.setImages(images);
        bellFinishRepository.save(finish);
    }


    public void updateBellFinish(String id, BellFinish bellFinish) {

        BellFinish finish = bellFinishRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "acabado no encontrada con id: " + id));
        // actualizar con los nuevos datos
        finish.setFinish(bellFinish.getFinish());
        finish.setDescription(bellFinish.getDescription());

        bellFinishRepository.save(finish);

    }


    public  void deleteBellFinish(String id){
        BellFinish bellFinish = bellFinishRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "acabado no encontrado con id: " + id));
        bellFinishRepository.deleteById(id);
    }



   


}

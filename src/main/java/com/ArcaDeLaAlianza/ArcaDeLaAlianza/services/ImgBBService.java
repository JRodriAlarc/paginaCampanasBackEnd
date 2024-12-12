package com.ArcaDeLaAlianza.ArcaDeLaAlianza.services;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;


@Service
public class ImgBBService {

    private final RestTemplate restTemplate;


    private final String apiKey= System.getenv("IMGBB_API_KEY");

    public ImgBBService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    String uploadImage(MultipartFile file) throws Exception {
        String url = "https://api.imgbb.com/1/upload?key=" + apiKey;

        // Crear los encabezados
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Crear el cuerpo de la solicitud
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        // Realizar la solicitud POST a la API
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            String imageUrl = (String) ((Map<String, Object>) responseBody.get("data")).get("url");
            return imageUrl;  // Retorna la URL de la imagen
        } else {
            throw new Exception("Error al cargar la imagen.");
        }
    }
}
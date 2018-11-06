package com.doubleip.geoapibenchmark.controllers.mongo;

import com.doubleip.geoapibenchmark.model.mongo.MGeoObject;
import com.doubleip.geoapibenchmark.model.postgres.PGeoObject;
import com.doubleip.geoapibenchmark.repositories.mongo.MGeoObjectRepository;
import com.doubleip.geoapibenchmark.repositories.postgres.PGeoObjectRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pgeo")
@CrossOrigin(origins = "*")
public class PGeoObjectController {

    @Autowired
    private PGeoObjectRepository pGeoObjectRepository;

    @Value("${pageSize}")
    private int pageSize;


    @RequestMapping(value="/create", method={RequestMethod.POST},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(
            @RequestParam("lon") @Valid Double longitude,
            @RequestParam("lat") @Valid Double latitude
    ) throws JsonProcessingException {

        PGeoObject pGeoObject = new PGeoObject();
        pGeoObject.setLatitude(latitude);
        pGeoObject.setLongitude(longitude);

        pGeoObjectRepository.save(pGeoObject);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(pGeoObject);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value="/list", method={RequestMethod.GET},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> list(
            @RequestParam("page") int page
    ) throws JsonProcessingException {

        Iterable<PGeoObject> pGeoObjects=null;
        pGeoObjects = pGeoObjectRepository.findAll(PageRequest.of(page, pageSize));

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(pGeoObjects);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }
}

package com.doubleip.geoapibenchmark.controllers.elasticsearch;

import com.doubleip.geoapibenchmark.model.elasticsearch.EGeoObject;
import com.doubleip.geoapibenchmark.model.mongo.MGeoObject;
import com.doubleip.geoapibenchmark.repositories.elasticsearch.EGeoObjectRepository;
import com.doubleip.geoapibenchmark.repositories.mongo.MGeoObjectRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/egeo")
@CrossOrigin(origins = "*")
public class EGeoObjectController {

    @Autowired
    private EGeoObjectRepository eGeoObjectRepository;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Value("${pageSize}")
    private int pageSize;

    @RequestMapping(value="/create", method={RequestMethod.POST},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(
            @RequestParam("lon") @Valid Double longitude,
            @RequestParam("lat") @Valid Double latitude
    ) throws JsonProcessingException {

        esTemplate.createIndex(EGeoObject.class);

        EGeoObject eGeoObject = new EGeoObject();
        eGeoObject.setLatitude(latitude);
        eGeoObject.setLongitude(longitude);

        eGeoObjectRepository.save(eGeoObject);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(eGeoObject);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value="/list", method={RequestMethod.GET},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> list(
            @RequestParam("page") int page
    ) throws JsonProcessingException {

        Iterable<EGeoObject> eGeoObjects=null;
        eGeoObjects = eGeoObjectRepository.findAll(PageRequest.of(page, pageSize));

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(eGeoObjects);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

}

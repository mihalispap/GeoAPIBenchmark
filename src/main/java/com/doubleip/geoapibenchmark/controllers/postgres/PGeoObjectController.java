package com.doubleip.geoapibenchmark.controllers.postgres;

import com.doubleip.geoapibenchmark.model.mongo.MGeoObject;
import com.doubleip.geoapibenchmark.model.postgres.CompFoo;
import com.doubleip.geoapibenchmark.model.postgres.PGeoObject;
import com.doubleip.geoapibenchmark.repositories.mongo.MGeoObjectRepository;
import com.doubleip.geoapibenchmark.repositories.postgres.CompFooRepo;
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

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Tuple;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pgeo")
@CrossOrigin(origins = "*")
public class PGeoObjectController {

    @Autowired
    private PGeoObjectRepository pGeoObjectRepository;

    @Autowired
    private CompFooRepo repo;

    @Value("${pageSize}")
    private int pageSize;

    @PersistenceContext
    private EntityManager em;


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

    @RequestMapping(value="/function", method={RequestMethod.GET},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> function() throws JsonProcessingException {

        Integer obj = pGeoObjectRepository.addGeoObject(12.0, 24.0);

        StoredProcedureQuery query = em
                .createStoredProcedureQuery("getFoo")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .setParameter(1, 1L)
                .setParameter(2, 1L);

        query.execute();
        List<CompFoo[]> postComments = query.getResultList();

//        CompFoo cfoo = repo.getFoo(1L, 2L);
//        CompFoo objs = (CompFoo) pGeoObjectRepository.getFoo(1L, 2L);
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(obj);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }
}

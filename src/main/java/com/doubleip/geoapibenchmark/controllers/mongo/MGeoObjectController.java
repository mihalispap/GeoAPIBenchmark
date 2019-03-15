package com.doubleip.geoapibenchmark.controllers.mongo;

import com.doubleip.geoapibenchmark.model.mongo.MGeoObject;
import com.doubleip.geoapibenchmark.model.mongo.User;
import com.doubleip.geoapibenchmark.repositories.mongo.MGeoObjectRepository;
import com.doubleip.geoapibenchmark.repositories.mongo.UserRepository;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mgeo")
@CrossOrigin(origins = "*")
public class MGeoObjectController {

    @Autowired
    private MGeoObjectRepository mGeoObjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${pageSize}")
    private int pageSize;

    @RequestMapping(value = "/create", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(
            @RequestParam("lon") @Valid Double longitude,
            @RequestParam("lat") @Valid Double latitude
    ) throws JsonProcessingException {

        MGeoObject mGeoObject = new MGeoObject();
        mGeoObject.setLatitude(latitude);
        mGeoObject.setLongitude(longitude);

        mGeoObjectRepository.save(mGeoObject);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(mGeoObject);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/mass-create", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> massCreate() throws JsonProcessingException {

        List<User> users = new ArrayList<>();

        List<MGeoObject> objs = new ArrayList<>();
        objs.add(new MGeoObject("mgeo0", (double)0.0f, (double)0.0f));
        objs.add(new MGeoObject("mgeo1", (double)1.0f, (double)1.0f));
        objs.add(new MGeoObject("mgeo2", (double)2.0f, (double)2.0f));
        mGeoObjectRepository.saveAll(objs);

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId((long)i);

            user.setUsername("username"+i);
            if (i%2==0)
                user.setName("Mihalis");
            else
                user.setName("Konstantina");

            for (int j=0;j<5;j++) {
                if (j<3 && i < 2)
                    user.getGeoObjects().add(mGeoObjectRepository.findById("mgeo"+j).get());
                else if (i<2)
                    user.getGeoObjects().add(mGeoObjectRepository.findById("mgeo"+(j-3)).get());
            }
            users.add(user);
        }

        userRepository.saveAll(users);
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(users);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> list(
            @RequestParam("page") int page
    ) throws JsonProcessingException {

        Iterable<MGeoObject> mGeoObjects = null;
        mGeoObjects = mGeoObjectRepository.findAll(PageRequest.of(page, pageSize));

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(mGeoObjects);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

}

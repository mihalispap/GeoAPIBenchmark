package com.doubleip.geoapibenchmark.controllers.mysql;

import com.doubleip.geoapibenchmark.model.foodie.mysql.MRecipe;
import com.doubleip.geoapibenchmark.model.foodie.mysql.MUser;
import com.doubleip.geoapibenchmark.model.mongo.MGeoObject;
import com.doubleip.geoapibenchmark.repositories.mongo.MGeoObjectRepository;
import com.doubleip.geoapibenchmark.repositories.mysql.MRecipeRepository;
import com.doubleip.geoapibenchmark.repositories.mysql.MUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/mrecipe")
@CrossOrigin("*")
public class MRecipeController {

    @Autowired
    private MRecipeRepository mRecipeRepository;

    @Value("${pageSize}")
    private int pageSize;

    @RequestMapping(value="/create", method={RequestMethod.POST},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(
            @RequestParam("name") @Valid String name
    ) throws JsonProcessingException {

        MRecipe mRecipe = new MRecipe();
        mRecipe.setName(name);

        mRecipeRepository.save(mRecipe);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(mRecipe);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value="/list", method={RequestMethod.GET},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> list(
            @RequestParam("page") int page
    ) throws JsonProcessingException {

        Iterable<MRecipe> mRecipes=null;
        mRecipes = mRecipeRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(mRecipes);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

}

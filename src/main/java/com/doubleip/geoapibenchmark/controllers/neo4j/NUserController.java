package com.doubleip.geoapibenchmark.controllers.neo4j;

import com.doubleip.geoapibenchmark.model.foodie.NRecipe;
import com.doubleip.geoapibenchmark.model.foodie.NUser;
import com.doubleip.geoapibenchmark.repositories.neo4j.NRecipeRepository;
import com.doubleip.geoapibenchmark.repositories.neo4j.NUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/nuser")
@CrossOrigin("*")
public class NUserController {

    @Autowired
    private NUserRepository nUserRepository;

    @Autowired
    private NRecipeRepository nRecipeRepository;

    @Value("${pageSize}")
    private int pageSize;

    @RequestMapping(value = "/create", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(
            @RequestParam("name") @Valid String name
    ) throws JsonProcessingException {

        NUser nUser = new NUser();
        nUser.setName(name);

        nUserRepository.save(nUser);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(nUser);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> list(
            @RequestParam("page") int page
    ) throws JsonProcessingException {

        Iterable<NUser> nUsers = null;
        nUsers = nUserRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(nUsers);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/rate", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> rate(
    ) throws JsonProcessingException {

        Iterable<NUser> nUsers = nUserRepository.findAll();
        List<Long> uids = new ArrayList<>();
//        final Long[] uid = new Long[(int)mUsers.spliterator().getExactSizeIfKnown()];
        nUsers.forEach(n -> uids.add(n.getId()));

        Iterable<NRecipe> mRecipes = nRecipeRepository.findAll();
        List<Long> rids = new ArrayList<>();
//        final Long[] rid = new Long[(int)mRecipes.spliterator().getExactSizeIfKnown()];
        mRecipes.forEach(n -> rids.add(n.getId()));

        int uid = ThreadLocalRandom.current().nextInt(0, uids.size());
        int rid = ThreadLocalRandom.current().nextInt(0, rids.size());

        NUser nUser = nUserRepository.findById(uids.get(uid)).get();
        NRecipe nRecipe = nRecipeRepository.findById(rids.get(rid)).get();

        if (nUser.getRatedRecipes() == null) {
            nUser.setRatedRecipes(new ArrayList<>());
        }
        nUser.getRatedRecipes().add(nRecipe);

        if (nRecipe.getHasBeenRatedBy() == null) {
            nRecipe.setHasBeenRatedBy(new ArrayList<>());
        }
        nRecipe.getHasBeenRatedBy().add(nUser);

        nUserRepository.save(nUser);
        nRecipeRepository.save(nRecipe);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(nUser);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }
}

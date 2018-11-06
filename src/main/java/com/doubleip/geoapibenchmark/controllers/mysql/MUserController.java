package com.doubleip.geoapibenchmark.controllers.mysql;

import com.doubleip.geoapibenchmark.model.foodie.NRecipe;
import com.doubleip.geoapibenchmark.model.foodie.NUser;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/muser")
@CrossOrigin("*")
public class MUserController {

    @Autowired
    private MUserRepository mUserRepository;

    @Autowired
    private MRecipeRepository mRecipeRepository;

    @Value("${pageSize}")
    private int pageSize;

    @RequestMapping(value = "/create", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(
            @RequestParam("name") @Valid String name
    ) throws JsonProcessingException {

        MUser mUser = new MUser();
        mUser.setName(name);

        mUserRepository.save(mUser);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(mUser);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> list(
            @RequestParam("page") int page
    ) throws JsonProcessingException {

        Iterable<MUser> mUsers = null;
        mUsers = mUserRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(mUsers);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }

    @RequestMapping(value = "/rate", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> rate(
    ) throws JsonProcessingException {

        Iterable<MUser> mUsers = mUserRepository.findAll();
        List<Long> uids = new ArrayList<>();
//        final Long[] uid = new Long[(int)mUsers.spliterator().getExactSizeIfKnown()];
        mUsers.forEach(n -> uids.add(n.getId()));

        Iterable<MRecipe> mRecipes = mRecipeRepository.findAll();
        List<Long> rids = new ArrayList<>();
//        final Long[] rid = new Long[(int)mRecipes.spliterator().getExactSizeIfKnown()];
        mRecipes.forEach(n -> rids.add(n.getId()));

        int uid = ThreadLocalRandom.current().nextInt(0, uids.size());
        int rid = ThreadLocalRandom.current().nextInt(0, rids.size());

        MUser mUser = mUserRepository.findById(uids.get(uid)).get();
        MRecipe mRecipe = mRecipeRepository.findById(rids.get(rid)).get();

        if (mUser.getRatedRecipes() == null) {
            mUser.setRatedRecipes(new ArrayList<>());
        }
        mUser.getRatedRecipes().add(mRecipe);

        if (mRecipe.getRatedByUsers() == null) {
            mRecipe.setRatedByUsers(new ArrayList<>());
        }
        mRecipe.getRatedByUsers().add(mUser);

        mUserRepository.save(mUser);
        mRecipeRepository.save(mRecipe);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(mUser);

        return ResponseEntity.status(HttpStatus.OK).body(jsonResult);
    }
}

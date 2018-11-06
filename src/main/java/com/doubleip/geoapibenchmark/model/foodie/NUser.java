package com.doubleip.geoapibenchmark.model.foodie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.catalina.LifecycleState;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

@NodeEntity
public class NUser {

    @GeneratedValue
    @Id
    private Long id;

    private String name;

    @JsonIgnore
    private List<NRecipe> ratedRecipes;

    public NUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NRecipe> getRatedRecipes() {
        return ratedRecipes;
    }

    public void setRatedRecipes(List<NRecipe> ratedRecipes) {
        this.ratedRecipes = ratedRecipes;
    }
}

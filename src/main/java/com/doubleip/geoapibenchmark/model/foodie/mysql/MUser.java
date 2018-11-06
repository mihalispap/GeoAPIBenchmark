package com.doubleip.geoapibenchmark.model.foodie.mysql;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class MUser {

    @GeneratedValue
    @Id
    private Long id;

    private String name;

    @ManyToMany
    @JsonIgnore
    private List<MRecipe> ratedRecipes;

    public MUser() {
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

    public List<MRecipe> getRatedRecipes() {
        return ratedRecipes;
    }

    public void setRatedRecipes(List<MRecipe> ratedRecipes) {
        this.ratedRecipes = ratedRecipes;
    }
}

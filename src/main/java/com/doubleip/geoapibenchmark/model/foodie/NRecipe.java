package com.doubleip.geoapibenchmark.model.foodie;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class NRecipe {

    @GeneratedValue
    @Id
    private Long id;

    private String name;

    private List<NUser> hasBeenRatedBy;

    public NRecipe() {
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

    public List<NUser> getHasBeenRatedBy() {
        return hasBeenRatedBy;
    }

    public void setHasBeenRatedBy(List<NUser> hasBeenRatedBy) {
        this.hasBeenRatedBy = hasBeenRatedBy;
    }
}

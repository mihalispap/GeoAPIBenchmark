package com.doubleip.geoapibenchmark.model.foodie.mysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class MRecipe {

    @GeneratedValue
    @Id
    private Long id;

    private String name;

    @ManyToMany
    private List<MUser> ratedByUsers;

    public MRecipe() {
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

    public List<MUser> getRatedByUsers() {
        return ratedByUsers;
    }

    public void setRatedByUsers(List<MUser> ratedByUsers) {
        this.ratedByUsers = ratedByUsers;
    }
}

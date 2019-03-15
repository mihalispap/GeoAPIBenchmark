package com.doubleip.geoapibenchmark.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="user")
public class User {

    @Id
    private Long id;

    private String name;

    private String username;

    private List<MGeoObject> geoObjects = new ArrayList<>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MGeoObject> getGeoObjects() {
        return geoObjects;
    }

    public void setGeoObjects(List<MGeoObject> geoObjects) {
        this.geoObjects = geoObjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

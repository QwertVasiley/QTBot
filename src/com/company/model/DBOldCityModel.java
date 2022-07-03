package com.company.model;

public class DBOldCityModel {
    private Long id;
    private String name;

    public DBOldCityModel() {
    }

    public DBOldCityModel(Long id) {
        this.id = id;
    }

    public DBOldCityModel(String name) {
        this.name = name;
    }

    public DBOldCityModel(Long id, String name) {
        this.id = id;
        this.name = name;
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
}

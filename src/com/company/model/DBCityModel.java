package com.company.model;

public class DBCityModel {
    private Long country_id;
    private Long region_id;
    private String name;
    private Long id_city;

    public DBCityModel() {
    }


    public DBCityModel(Long country_id, Long region_id, String name, Long id_city) {
        this.country_id = country_id;
        this.region_id = region_id;
        this.name = name;
        this.id_city = id_city;
    }

    public DBCityModel(Long country_id, String name) {
        this.country_id = country_id;
        this.name = name;
    }

    public DBCityModel(String name) {
        this.name = name;
    }

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public Long getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Long region_id) {
        this.region_id = region_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId_city() {
        return id_city;
    }

    public void setId_city(Long id_city) {
        this.id_city = id_city;
    }

    @Override
    public String toString() {
        return "DBCityModel{" +
                "country_id=" + country_id +
                ", region_id=" + region_id +
                ", name='" + name + '\'' +
                ", id_city=" + id_city +
                '}';
    }
}

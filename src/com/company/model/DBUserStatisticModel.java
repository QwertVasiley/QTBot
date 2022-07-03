package com.company.model;

public class DBUserStatisticModel {
    private Long id;
    private Long user_tel_id;
    private String first_name;
    private Long game_score;

    public DBUserStatisticModel() {
    }

    public DBUserStatisticModel(Long id, Long user_tel_id, String first_name, Long game_score) {
        this.id = id;
        this.user_tel_id = user_tel_id;
        this.first_name = first_name;
        this.game_score = game_score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_tel_id() {
        return user_tel_id;
    }

    public void setUser_tel_id(Long user_tel_id) {
        this.user_tel_id = user_tel_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Long getGame_score() {
        return game_score;
    }

    public void setGame_score(Long game_score) {
        this.game_score = game_score;
    }
}

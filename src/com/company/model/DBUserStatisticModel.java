package com.company.model;

public class DBUserStatisticModel {
    private Long id;
    private Long user_id_ident;
    private String firstName;
    private Long game_score;

    public DBUserStatisticModel() {
    }

    public DBUserStatisticModel(Long id, Long user_id_ident, String first_name, Long game_score) {
        this.id = id;
        this.user_id_ident = user_id_ident;
        this.firstName = first_name;
        this.game_score = game_score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id_ident() {
        return user_id_ident;
    }

    public void setUser_id_ident(Long user_id_ident) {
        this.user_id_ident = user_id_ident;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getGame_score() {
        return game_score;
    }

    public void setGame_score(Long game_score) {
        this.game_score = game_score;
    }
}

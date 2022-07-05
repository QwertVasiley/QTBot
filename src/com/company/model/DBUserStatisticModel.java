package com.company.model;

public class DBUserStatisticModel {
    private Long id;
    private Integer user_id_ident;
    private String userName;
    private Long score;

    public DBUserStatisticModel() {
    }

    public DBUserStatisticModel(Long id, Integer user_id_ident, String first_name, Long score) {
        this.id = id;
        this.user_id_ident = user_id_ident;
        this.userName = first_name;
        this.score = score;
    }

    public DBUserStatisticModel(Integer user_id_ident, String userName, Long score) {
        this.user_id_ident = user_id_ident;
        this.userName = userName;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUser_id_ident() {
        return user_id_ident;
    }

    public void setUser_id_ident(Integer user_id_ident) {
        this.user_id_ident = user_id_ident;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}

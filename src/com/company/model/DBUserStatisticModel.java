package com.company.model;

public class DBUserStatisticModel {
    private Long id;
    private int user_id_ident;
    private String userName;
    private Long score;

    public DBUserStatisticModel() {
    }

    public DBUserStatisticModel(Long id, int user_id_ident, String first_name, Long score) {
        this.id = id;
        this.user_id_ident = user_id_ident;
        this.userName = first_name;
        this.score = score;
    }

    public DBUserStatisticModel(int user_id_ident, String userName, Long score) {
        this.user_id_ident = user_id_ident;
        this.userName = userName;
        this.score = score;
    }

    public DBUserStatisticModel(String userName, Long score) {
        this.userName = userName;
        this.score = score;
    }

    public DBUserStatisticModel(int user_id_ident, Long score) {
        this.user_id_ident = user_id_ident;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUser_id_ident() {
        return user_id_ident;
    }

    public void setUser_id_ident(int user_id_ident) {
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

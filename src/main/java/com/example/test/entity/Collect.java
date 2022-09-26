package com.example.test.entity;

public class Collect implements Runnable{
    private Integer id;
    private Integer user_id;
    private String local_name;
    private String collectX;
    private String collectY;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getLocal_name() {
        return local_name;
    }

    public void setLocal_name(String local_name) {
        this.local_name = local_name;
    }


    public String getCollectX() {
        return collectX;
    }

    public void setCollectX(String collectX) {
        this.collectX = collectX;
    }

    public String getCollectY() {
        return collectY;
    }

    public void setCollectY(String collectY) {
        this.collectY = collectY;
    }

    @Override
    public void run() {

    }
}

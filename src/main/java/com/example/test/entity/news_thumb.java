package com.example.test.entity;

public class news_thumb implements Runnable{
    Integer thumb_id;
    String thumb_url;
    Integer news_id;

    public Integer getThumb_id() {
        return thumb_id;
    }

    public void setThumb_id(Integer thumb_id) {
        this.thumb_id = thumb_id;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public Integer getNews_id() {
        return news_id;
    }

    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }

    @Override
    public void run() {

    }
}

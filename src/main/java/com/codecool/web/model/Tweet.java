package com.codecool.web.model;

public class Tweet {
    private final String poster;
    private final String content;
    private final int id;
    private final long date;


    public Tweet(String poster, String content, int id, long date) {
        this.poster = poster;
        this.content = content;
        this.id = id;
        this.date = date;
    }

    public String getPoster() {
        return poster;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public long getDate() {
        return date;
    }
}

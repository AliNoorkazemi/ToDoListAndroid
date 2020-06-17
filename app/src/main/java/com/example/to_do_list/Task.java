package com.example.to_do_list;


import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

    private String title;
    private String description;
    boolean done;
    private Date date;


    Task(String title, String description, boolean done, Date date) {
        this.title = title;
        this.description = description;
        this.done = done;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}

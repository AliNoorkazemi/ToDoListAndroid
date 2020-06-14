package com.example.to_do_list;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Task implements Serializable {

    private String title;
    private String description;
    boolean done;
    private String expirationDate;

    Task(String title, String description, String expirationDate) {
        this.title = title;
        this.description = description;
        this.expirationDate = expirationDate;
        this.done=false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
}

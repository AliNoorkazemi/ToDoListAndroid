package com.example.to_do_list;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public boolean isExpire(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        Date time = Calendar.getInstance().getTime();
        String current_date_string = formatter.format(time);
        Date current_date = null;
        try {
            current_date = formatter.parse(current_date_string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.getDate().compareTo(current_date)<0;
    }
}

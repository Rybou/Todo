package com.bourymbodj.todo;

/**
 * Created by bourymbodj on 16-07-22.
 */
public class ToDo {

    private int id;
    private String title;
    private String description;
    private String date;
    private int status;

    public ToDo() {
    }


    public ToDo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public ToDo(int id, String title, String description, String date, int status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.status = status;
    }
    public ToDo( String title, String description, String date, int status) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.status = status;
    }




    public ToDo(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}

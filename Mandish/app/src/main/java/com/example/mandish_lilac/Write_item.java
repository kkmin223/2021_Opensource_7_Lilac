package com.example.mandish_lilac;

import java.util.ConcurrentModificationException;

public class Write_item {
    private String id;
    private String title;
    private String contents;
    private String name;
    private String time;

    public Write_item() {
    }

    public Write_item(String id, String title, String contents, String name, String time) {
        this.id =id;
        this.title =title;
        this.contents =contents;
        this.name =name;
        this.time = time;
    }

    public String getId(){
        return id;
    }
    public void setId(String id) {
        this.id =id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title) {
        this.title =title;
    }
    public String getContents(){
        return contents;
    }
    public void setContents(String contents) {
        this.contents =contents;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name =name;
    }
    public String getTime(){ return time; }
    public void setTime(String time) {
        this.time =time;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", name='" + name + '\'' +
                ", time=" + time + '\'' +
                '}';
    }
}
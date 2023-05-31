package com.example.teamproject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommnunityItem {

    private String title;
    private String writer;
    private String place;
    private String date;
    CommnunityItem(){}
    public CommnunityItem(String title, String writer, String place, String date){
        this.title = title;
        this.writer = writer;
        this.place = place;
        this.date = date;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @NotNull
    public static ArrayList<CommnunityItem> createContactsList(int numContacts) {
        ArrayList<CommnunityItem> contacts = new ArrayList<>();
        contacts.add(new CommnunityItem("제목","작성자","장소","일시")); // 1번째 아이템 <-이 형태로 글 수정하면 됨
        for(int i =1; i <= numContacts; i++){
            contacts.add(new CommnunityItem());
        }

        return contacts;
    }
}
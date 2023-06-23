package com.example.teamproject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommunityItem {

    private String title;
    private String writer;
    private String place;
    private String date;
    private String content;
    private String sport;
    CommunityItem(){}

    //제목,작성자,위치,날짜,내용순
    public CommunityItem(String title, String writer, String place, String date, String content, String sport){
        this.title = title;
        this.writer = writer;
        this.place = place;
        this.date = date;
        this.content = content;
        this.sport = sport;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) { this.content = content; }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    @NotNull
    public static ArrayList<CommunityItem> createContactsList(int numContacts) {
        ArrayList<CommunityItem> contacts = new ArrayList<>();
        for(int i =1; i <= numContacts; i++){
            contacts.add(new CommunityItem());
        }

        return contacts;
    }
}

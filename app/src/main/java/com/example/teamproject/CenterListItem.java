package com.example.teamproject;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.RatingBar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CenterListItem {
    private int centerImage;
    private String centerName;
    private String place;
    private String category;
    private float rating;
    CenterListItem(){}
    public CenterListItem(int centerImage, String centerName, String place, String category, float rating){
        this.centerImage = centerImage;
        this.centerName = centerName;
        this.place = place;
        this.category = category;
        this.rating = rating;
    }
    public int getCenterImage(){return centerImage;}

    public void setCenterImage(int centerImage){ this.centerImage = centerImage;}

    public String getCenterName(){
        return centerName;
    }

    public void setCenterName(String centerName){
        this.centerName = centerName;
    }

    public String getPlace(){
        return place;
    }

    public void setPlace(String place){
        this.place = place;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public float getRating(){
        return rating;
    }

    @NotNull
    public static ArrayList<CenterListItem> createContactsList(int centerlistSize) {

        ArrayList<CenterListItem> contacts = new ArrayList<>();
        contacts.add(new CenterListItem(R.drawable.pyeonganpark, "센터이름", "장소", "카테고리", 4.5f)); // 1번째 아이템 <-이 형태로 글 수정하면 됨
        contacts.add(new CenterListItem(R.drawable.pyeonganpark, "센터이름", "장소", "카테고리", 4.5f)); // 2번째 아이템 <-이 형태로 글 수정하면 됨
        for(int i =1; i <= centerlistSize; i++){
            contacts.add(new CenterListItem());
        }

        return contacts;
    }
}

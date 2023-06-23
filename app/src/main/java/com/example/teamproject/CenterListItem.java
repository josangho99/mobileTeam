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
        contacts.add(new CenterListItem(R.drawable.pyeonganpark, "센터이름", "장소", "농구", 4.5f)); // 1번째 아이템 <-이 형태로 글 수정하면 됨
        for(int i = 1; i <= centerlistSize; i++){
            contacts.add(new CenterListItem());
        }
        for(int i = 0; i <= centerlistSize; i++) {
            if(contacts.get(i).category == "농구"){
                contacts.get(i).centerImage = R.drawable.basket_ball;
            }
            else if(contacts.get(i).category == "야구"){
                contacts.get(i).centerImage = R.drawable.baseball_2;
            }
            else if(contacts.get(i).category == "배구"){
                contacts.get(i).centerImage = R.drawable.voley_ball;
            }
            else if(contacts.get(i).category == "축구"){
                contacts.get(i).centerImage = R.drawable.soccer_ball;
            }
            else if(contacts.get(i).category == "탁구"){
                contacts.get(i).centerImage = R.drawable.table_tenis;
            }
            else if(contacts.get(i).category == "테니스"){
                contacts.get(i).centerImage = R.drawable.tenis_ball;
            }
            else if(contacts.get(i).category == "당구"){
                contacts.get(i).centerImage = R.drawable.pool;
            }
            else if(contacts.get(i).category == "헬스"){
                contacts.get(i).centerImage = R.drawable.health;
            }
            else if(contacts.get(i).category == "골프"){
                contacts.get(i).centerImage = R.drawable.golf;
            }
            else if(contacts.get(i).category == "볼링"){
                contacts.get(i).centerImage = R.drawable.bowling;
            }
            else if(contacts.get(i).category == "배드민턴"){
                contacts.get(i).centerImage = R.drawable.badminton;
            }
            else if(contacts.get(i).category == "권투"){
                contacts.get(i).centerImage = R.drawable.boxing_glov;
            }
            else{
                contacts.get(i).centerImage = R.drawable.horn;
            }
        }
        return contacts;
    }
}

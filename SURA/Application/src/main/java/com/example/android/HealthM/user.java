package com.example.android.HealthM;

/**
 * Created by hp on 18-06-2018.
 */

public class user {

    public String name;
    public String height;
    public String weight;
    public String bp;
    public String heart;
    public String temperature;
    public String lung;

        public user(){

        }

    public user(String name, String height, String weight, String temperature, String lung ,String bp,String heart) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.temperature = temperature;
        this.lung = lung;
        this.bp = bp;
        this.heart = heart;
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getLung() {
        return lung;
    }

    public String getBp() {
        return bp;
    }

    public String getHeart() {
        return heart;
    }
}

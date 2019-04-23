package com.example.tanmaygoyal.lab_exp;

/**
 * Created by hp on 18-06-2018.
 */

public class user {

    public String one;
    public String two;
    public String three;
    public String four;
    public String five;
    public String six;


        public user(){

        }

    public user(String name, String height, String weight, String temperature, String lung , String bp) {
        this.one = name;
        this.two = height;
        this.three = weight;
        this.four = temperature;
        this.five = lung;
        this.six = bp;

    }

    public String getOne() {
        return one;
    }

    public String getTwo() {
        return two;
    }

    public String getThree() {
        return three;
    }

    public String getFour() {
        return four;
    }

    public String getFive() {
        return five;
    }

    public String getSix() {
        return six;
    }


}

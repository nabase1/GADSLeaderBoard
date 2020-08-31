package com.nabase1.gadsleaderboard.modals;

public class Learners {
    private String name;
    private String hours;
    private String country;
    private String score;
    private String imageUrl;

    public Learners() {
    }

    public Learners(String name, String hours, String country,String score, String imageUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.imageUrl = imageUrl;
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}



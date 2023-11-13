package com.example.crapmap.model;

public class Rating {
    private UserProfile rater;
    private ToiletProfile ratee;
    private int numStars;
    private String review;

    public Rating( UserProfile rater, ToiletProfile ratee, int numStars, String review)
    {
        this.rater = rater;
        this.ratee = ratee;
        this.numStars = numStars;
        this.review = review;
    }



    public int getNumStars() {
        return numStars;
    }

    public void setNumStars(int numStars) {
        this.numStars = numStars;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public ToiletProfile getRatee() {
        return ratee;
    }

    public void setRatee(ToiletProfile ratee) {
        this.ratee = ratee;
    }

    public UserProfile getRater() {
        return rater;
    }

    public void setRater(UserProfile rater) {
        this.rater = rater;
    }
}

package com.example.FilterReviews.service.impl;

import com.example.FilterReviews.model.Review;

import java.util.Comparator;
import java.util.List;

public class MyObjectComparator implements Comparator<Review> {

    @Override
    public int compare(Review o1, Review o2) {
        String status1 = o1.getReviewText();
        String status2 = o2.getReviewText();

        if (status1.length()>=1 && status2.length()==0) {
            return -1; // "yes" comes before "no"
        } else if (status1.length()==0 && status2.length()>=1) {
            return 1; // "no" comes after "yes"
        } else {
            return 0; // same status, no change in order
        }
    }

}


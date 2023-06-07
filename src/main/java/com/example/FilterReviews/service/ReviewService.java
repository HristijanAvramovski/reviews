package com.example.FilterReviews.service;

import com.example.FilterReviews.model.Review;

import java.util.List;

public interface ReviewService {
    public List<Review> readJsonFile();
    public List<Review> sortedReviews(String orderByRating, Integer minimumRating, String orderByDate, String prioritizeByText);
}

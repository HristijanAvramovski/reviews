package com.example.FilterReviews.web.rest;

import com.example.FilterReviews.model.Review;
import com.example.FilterReviews.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class FilterReviewsRestController {
    private final ReviewService reviewService;

    public FilterReviewsRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<Review> listReviews(){
        return reviewService.readJsonFile();
    }
    @PostMapping("/reviews/filter")
    public List<Review> sortedReviews(@RequestParam String orderByRating,
                                      @RequestParam Integer minimumRating,
                                      @RequestParam String orderByDate,
                                      @RequestParam String prioritizeByText){
        return this.reviewService.sortedReviews(orderByRating, minimumRating, orderByDate, prioritizeByText);
    }
}

package com.example.FilterReviews.service.impl;

import com.example.FilterReviews.model.Review;
import com.example.FilterReviews.service.ReviewService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Override
    public List<Review> readJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Review> reviews = null;

        try {
            File file = ResourceUtils.getFile("classpath:reviews.json");
            reviews = objectMapper.readValue(file, new TypeReference<List<Review>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reviews;
    }

    @Override
    public List<Review> sortedReviews(String orderByRating, Integer minimumRating, String orderByDate, String prioritizeByText)
    {
        List<Review> reviews=readJsonFile();
        //increasing order
        Comparator<Review> dateComparatorIncreaseOrder=Comparator.comparing(Review::getReviewCreatedOnDate);
        Comparator<Review> ratingComparatorIncreaseOrder=Comparator.comparing(Review::getRating);
        //decreasing order
        Comparator<Review> dateComparatorDecreaseOrder=Comparator.comparing(Review::getReviewCreatedOnDate).reversed();
        Comparator<Review> ratingComparatorDecreaseOrder=Comparator.comparing(Review::getRating).reversed();
        List<Review> reviewsGreaterOrEqualToMinRating=new ArrayList<>();
        for(int i=0;i<reviews.size();i++)
        {
            if(reviews.get(i).getRating() >= minimumRating)
            {
                reviewsGreaterOrEqualToMinRating.add(reviews.get(i));
            }
        }
        if(prioritizeByText.equals("Yes"))
        {
            if(orderByDate.equals("Newest First") && orderByRating.equals("Highest First"))
            {
                reviewsGreaterOrEqualToMinRating.sort(new MyObjectComparator().thenComparing(ratingComparatorDecreaseOrder).thenComparing(dateComparatorDecreaseOrder));
                return reviewsGreaterOrEqualToMinRating;
            }
            else if(orderByDate.equals("Newest First") && orderByRating.equals("Lowest First"))
            {
                reviewsGreaterOrEqualToMinRating.sort(new MyObjectComparator().thenComparing(ratingComparatorIncreaseOrder).thenComparing(dateComparatorDecreaseOrder));
                return reviewsGreaterOrEqualToMinRating;
            }
            else if(orderByDate.equals("Oldest First") && orderByRating.equals("Highest First"))
            {
                reviewsGreaterOrEqualToMinRating.sort(new MyObjectComparator().thenComparing(ratingComparatorDecreaseOrder).thenComparing(dateComparatorIncreaseOrder));
                return reviewsGreaterOrEqualToMinRating;
            }
            else if(orderByDate.equals("Oldest First") && orderByRating.equals("Lowest First"))
            {
                reviewsGreaterOrEqualToMinRating.sort(new MyObjectComparator().thenComparing(ratingComparatorIncreaseOrder).thenComparing(dateComparatorIncreaseOrder));
                return reviewsGreaterOrEqualToMinRating;
            }
        }
        else
        {
            if(orderByDate.equals("Newest First") && orderByRating.equals("Highest First"))
            {
                reviewsGreaterOrEqualToMinRating.sort(ratingComparatorDecreaseOrder.thenComparing(dateComparatorDecreaseOrder));
                return reviewsGreaterOrEqualToMinRating;
            }
            else if(orderByDate.equals("Newest First") && orderByRating.equals("Lowest First"))
            {
                reviewsGreaterOrEqualToMinRating.sort(ratingComparatorIncreaseOrder.thenComparing(dateComparatorDecreaseOrder));
                return reviewsGreaterOrEqualToMinRating;
            }
            else if(orderByDate.equals("Oldest First") && orderByRating.equals("Highest First"))
            {
                reviewsGreaterOrEqualToMinRating.sort(ratingComparatorDecreaseOrder.thenComparing(dateComparatorIncreaseOrder));
                return reviewsGreaterOrEqualToMinRating;
            }
            else if(orderByDate.equals("Oldest First") && orderByRating.equals("Lowest First"))
            {
                reviewsGreaterOrEqualToMinRating.sort(ratingComparatorIncreaseOrder.thenComparing(dateComparatorIncreaseOrder));
                return reviewsGreaterOrEqualToMinRating;
            }
        }
        return reviewsGreaterOrEqualToMinRating;
    }
}

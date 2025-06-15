package edu.gdou.recipebackend.core.controller;

import edu.gdou.recipebackend.core.entity.base.Result;
import edu.gdou.recipebackend.core.entity.dto.ReviewDto;
import edu.gdou.recipebackend.core.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping
    public Result<Integer> comment(@RequestBody ReviewDto reviewDto){
        int reviewId = reviewService.comment(reviewDto);
        return Result.ok(reviewId);
    }
}

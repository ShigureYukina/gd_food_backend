package edu.gdou.recipebackend.core.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewDto {
    @NotBlank
    private int recipeId;
    @NotBlank
    private int rating;
    private String comment;
}

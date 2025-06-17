package edu.gdou.recipebackend.core.entity.dto;


import lombok.Data;

@Data
public class ReviewRecipeDto {
    Long recipeId;
    Boolean reviewState;
}

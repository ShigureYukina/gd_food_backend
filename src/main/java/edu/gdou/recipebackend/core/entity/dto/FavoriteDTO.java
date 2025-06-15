package edu.gdou.recipebackend.core.entity.dto;

import lombok.Data;

@Data
public class FavoriteDTO {
    private int recipeId;
    Boolean isFavorite;
}

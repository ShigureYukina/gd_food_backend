package edu.gdou.recipebackend.core.entity.vo;

import edu.gdou.recipebackend.core.entity.po.RecipePO;
import lombok.Data;

@Data
public class RecipeVO extends RecipePO {
    private int rating;
}

package edu.gdou.recipebackend.core.entity.vo;

import edu.gdou.recipebackend.core.entity.po.RecipePO;
import edu.gdou.recipebackend.core.entity.po.ReviewPO;
import edu.gdou.recipebackend.core.entity.po.StoryPO;
import lombok.Data;

import java.util.List;
@Data
public class RecipeDetailVO {
    RecipePO recipe;
    StoryPO story;
    List<ReviewPO> reviews;
}

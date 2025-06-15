package edu.gdou.recipebackend.core.entity.vo;

import edu.gdou.recipebackend.core.entity.po.RecipePO;
import lombok.Data;

import java.util.List;
@Data
public class CookBookVO {
    private int total;
    private List<RecipePO> recipes;
}

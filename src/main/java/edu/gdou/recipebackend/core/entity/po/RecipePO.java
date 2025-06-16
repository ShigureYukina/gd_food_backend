package edu.gdou.recipebackend.core.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("recipe")
public class RecipePO {
    /**
     * 菜谱ID - 主键
     */
    @TableId(value = "RecipeID",type = IdType.AUTO)
    private int recipeID;
    /**
     * 用户ID
     */
    @TableField("UserID")
    private int userID;
    @TableField("Title")
    private String title;
    @TableField("Ingredients")
    private String ingredients;
    @TableField("Difficulty")
    private String difficulty;
    @TableField("Steps")
    private String steps;
    @TableField("VideoLink")
    private String videoLink;
    @TableField("ImageLinks")
    private String imageLinks;
    @TableField("UploadTime")
    private String uploadTime;
    @TableField("ReviewState")
    private int reviewState;
    @TableField("RecipeTypeIds")
    private String recipeTypeIds;
    @TableField("RecipeTypeNames")
    private String recipeTypeNames;
}

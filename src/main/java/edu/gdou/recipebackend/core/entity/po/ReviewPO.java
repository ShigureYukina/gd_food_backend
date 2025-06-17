package edu.gdou.recipebackend.core.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("review")
public class ReviewPO {
    @TableId(value = "ReviewID",type = IdType.AUTO)
    private int reviewId;
    @TableField("UserID")
    private int userId;
    @TableField("RecipeID")
    private int recipeId;
    @TableField("Rating")
    private int rating;
    @TableField("Comment")
    private String comment;
    @TableField("ReviewTime")
    private Date reviewTime;

}

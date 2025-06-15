package edu.gdou.recipebackend.core.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("cookbook")
public class CookBookPO {
    @TableField("UserID")
    private int UserId;
    @TableField("RecipeID")
    private int RecipeId;
    @TableField("CollectinoTime")
    private Date CollectionTime;
    @TableField("Notes")
    private String Notes;
}

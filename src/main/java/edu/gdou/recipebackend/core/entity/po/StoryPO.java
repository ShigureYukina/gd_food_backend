package edu.gdou.recipebackend.core.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("story")
public class StoryPO {
    @TableId(value = "StoryID",type = IdType.AUTO)
    private int storyId;
    @TableField("RecipeID")
    private int recipeId;
    @TableField("HistoricalContext")
    private String historicalContext;
    @TableField("CulturalSignificance")
    private String culturalSignificance;
}

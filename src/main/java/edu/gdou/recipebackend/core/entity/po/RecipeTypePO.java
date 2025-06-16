package edu.gdou.recipebackend.core.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("recipetype")
@Data
public class RecipeTypePO {
    @TableId(value = "recipeTypeId",type = IdType.AUTO)
    private int recipeTypeId;
    @TableField("recipeTypeName")
    private String reciptTypeName;
}

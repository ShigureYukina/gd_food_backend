package edu.gdou.recipebackend.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdou.recipebackend.core.entity.po.RecipePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecipeMapper extends BaseMapper<RecipePO> {
}

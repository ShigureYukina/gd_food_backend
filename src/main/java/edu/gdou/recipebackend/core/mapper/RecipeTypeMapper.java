package edu.gdou.recipebackend.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdou.recipebackend.core.entity.po.RecipeTypePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecipeTypeMapper extends BaseMapper<RecipeTypePO> {
}

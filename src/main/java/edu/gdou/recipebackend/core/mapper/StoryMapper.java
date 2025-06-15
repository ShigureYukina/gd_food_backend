package edu.gdou.recipebackend.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdou.recipebackend.core.entity.po.StoryPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoryMapper extends BaseMapper<StoryPO> {
}

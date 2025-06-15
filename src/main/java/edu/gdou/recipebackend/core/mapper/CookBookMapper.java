package edu.gdou.recipebackend.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdou.recipebackend.core.entity.po.CookBookPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CookBookMapper extends BaseMapper<CookBookPO> {
}

package edu.gdou.recipebackend.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdou.recipebackend.core.entity.po.RecipePO;
import edu.gdou.recipebackend.core.entity.vo.RecipeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecipeMapper extends BaseMapper<RecipePO> {
    List<RecipeVO> search(@Param("keywords") String keywords, @Param("type") String type,@Param("pageSize") int pageSize, @Param("offset") int offset);
}

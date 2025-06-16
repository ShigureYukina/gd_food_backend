package edu.gdou.recipebackend.core.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdou.recipebackend.core.entity.po.RecipePO;
import edu.gdou.recipebackend.core.entity.po.RecipeTypePO;
import edu.gdou.recipebackend.core.mapper.RecipeMapper;
import edu.gdou.recipebackend.core.mapper.RecipeTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeTypeService extends ServiceImpl<RecipeTypeMapper, RecipeTypePO> {
    @Autowired
    private RecipeMapper recipeMapper;
    @Autowired
    private RecipeTypeMapper recipeTypeMapper;
    public List<RecipeTypePO> getRecipeTypeByRecipeId(Long recipeId) {
        //先查询菜谱拿到菜谱类别ids
        RecipePO recipe = recipeMapper.selectById(recipeId);
        String recipeTypeIds = recipe.getRecipeTypeIds();
        String[] typeids = recipeTypeIds.split(",");
        List<Integer> typeIds = new ArrayList<>();
        for (String typeid : typeids) {
            int i = Integer.parseInt(typeid);
            typeIds.add(i);
        }
        return recipeTypeMapper.selectByIds(typeIds);
    }
    //根据类别id查询类别
    private RecipeTypePO getRecipeTypeById(String typeid) {
        return recipeTypeMapper.selectById(typeid);
    }
}

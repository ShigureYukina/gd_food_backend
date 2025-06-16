package edu.gdou.recipebackend;

import edu.gdou.recipebackend.core.entity.po.RecipeTypePO;
import edu.gdou.recipebackend.core.mapper.RecipeTypeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RecipebackendApplicationTests {
    @Autowired
    RecipeTypeMapper recipeTypeMapper;
    @Test
    void contextLoads() {
        String abc = "1,2,3";
        String[] typeids = abc.split(",");
        List<Integer> typeIds = new ArrayList<>();
        for (String typeid : typeids) {
            int i = Integer.parseInt(typeid);
            typeIds.add(i);
        }
        List<RecipeTypePO> recipeTypePOS = recipeTypeMapper.selectByIds(typeIds);
        for (RecipeTypePO recipeTypePO : recipeTypePOS) {
            System.out.println(recipeTypePO);
        }

    }

}

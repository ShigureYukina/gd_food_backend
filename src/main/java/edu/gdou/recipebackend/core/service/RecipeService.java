package edu.gdou.recipebackend.core.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdou.recipebackend.core.entity.po.RecipePO;
import edu.gdou.recipebackend.core.entity.po.ReviewPO;
import edu.gdou.recipebackend.core.entity.po.StoryPO;
import edu.gdou.recipebackend.core.entity.vo.RecipeDetailVO;
import edu.gdou.recipebackend.core.mapper.RecipeMapper;
import edu.gdou.recipebackend.core.mapper.ReviewMapper;
import edu.gdou.recipebackend.core.mapper.StoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RecipeService extends ServiceImpl<RecipeMapper, RecipePO> {
    @Autowired
    RecipeMapper recipeMapper;
    @Autowired
    StoryMapper storyMapper;
    @Autowired
    ReviewMapper reviewMapper;
    public RecipeDetailVO getRecipeDetail(Long id) {
        //查询recipe
        RecipePO recipe = recipeMapper.selectById(id);
        if (recipe==null){
            log.error("当前菜谱为空");
            throw new RuntimeException("当前菜谱不存在");
        }
        log.info("recipe:{}",recipe);
        //查询story,通过菜谱id查询
        StoryPO story = storyMapper.selectOne(new LambdaQueryWrapper<StoryPO>().eq(StoryPO::getRecipeId, id));
        if (story == null) {
            log.info("当前菜谱故事为空，尚未填写故事内容");
        }else {
            log.info("story:{}",story);
        }
        //查询review,通过菜谱id查询
        List<ReviewPO> reviews = reviewMapper.selectList(new LambdaQueryWrapper<ReviewPO>().eq(ReviewPO::getRecipeId, id));
        //创建vo对象，填充数据
        RecipeDetailVO recipeDetailVO = new RecipeDetailVO();
        recipeDetailVO.setRecipe(recipe);
        recipeDetailVO.setStory(story);
        recipeDetailVO.setReviews(reviews);
        return recipeDetailVO;
    }
    public void review(Long recipeId,Boolean reviewState){
        //先查出来当前要审核的菜谱是否存在，避免已经被删除了
        RecipePO recipe = recipeMapper.selectById(recipeId);
        if (recipe==null){
            log.debug("当前菜谱不存在");
            throw new RuntimeException("菜谱不存在");
        }
        recipe.setReviewState(!reviewState ?0:1);
        int i = recipeMapper.updateById(recipe);
        if (i<=0){
            log.error("更新审核状态失败");
            throw new RuntimeException("审核出现错误");
        }
    }
}

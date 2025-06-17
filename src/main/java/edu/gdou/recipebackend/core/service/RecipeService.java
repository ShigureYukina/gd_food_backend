package edu.gdou.recipebackend.core.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdou.recipebackend.core.entity.po.RecipePO;
import edu.gdou.recipebackend.core.entity.po.RecipeTypePO;
import edu.gdou.recipebackend.core.entity.po.ReviewPO;
import edu.gdou.recipebackend.core.entity.po.StoryPO;
import edu.gdou.recipebackend.core.entity.vo.RecipeDetailVO;
import edu.gdou.recipebackend.core.entity.vo.RecipeVO;
import edu.gdou.recipebackend.core.mapper.RecipeMapper;
import edu.gdou.recipebackend.core.mapper.RecipeTypeMapper;
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
    @Autowired
    RecipeTypeMapper typeMapper;

    public RecipeDetailVO getRecipeDetail(Long id) {
        //查询recipe
        RecipePO recipe = recipeMapper.selectById(id);
        if (recipe==null){
            log.error("当前菜谱为空");
            throw new RuntimeException("当前菜谱不存在");
        }
        //将json字符串转为string
        recipe.setIngredients(JSON.toJSONString(recipe.getIngredients()));
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

    public List<RecipeVO> search(String keywords, int page, int pagesize, String type) {
        List<RecipeVO> recipeVOList = recipeMapper.search(keywords, page, pagesize, type);
        return recipeVOList;
    }

    public int InsertOrUpdate(RecipePO recipePO) {
        int recipeID = recipePO.getRecipeID();
        //查询该类别是否存在，不存在写入菜谱类型表
        long count = typeMapper.selectCount(new QueryWrapper<RecipeTypePO>()
                .eq("recipeTypeName", recipePO.getRecipeTypeNames()));

        if (count == 0){
            RecipeTypePO recipeTypePO = new RecipeTypePO();
            recipeTypePO.setReciptTypeName(recipePO.getRecipeTypeNames());
            long typeId = typeMapper.insert(recipeTypePO);
            recipePO.setRecipeTypeIds(String.valueOf(typeId));
        }
        RecipePO recipePO1 = recipeMapper.selectById(recipeID);;
        if (recipePO1==null){
            //当前id没有找到菜谱，说明为第一次，插入操作
            recipeMapper.insert(recipePO);
        } else {
            //更新操作
            recipeMapper.updateById(recipePO);
        }
        return recipePO.getRecipeID();
    }
}

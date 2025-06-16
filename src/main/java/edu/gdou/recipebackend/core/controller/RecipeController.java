package edu.gdou.recipebackend.core.controller;

import cn.dev33.satoken.stp.StpUtil;
import edu.gdou.recipebackend.core.entity.base.Result;
import edu.gdou.recipebackend.core.entity.dto.FavoriteDTO;
import edu.gdou.recipebackend.core.entity.po.RecipePO;
import edu.gdou.recipebackend.core.entity.po.RecipeTypePO;
import edu.gdou.recipebackend.core.entity.vo.CookBookVO;
import edu.gdou.recipebackend.core.entity.vo.RecipeDetailVO;
import edu.gdou.recipebackend.core.entity.vo.RecipeVO;
import edu.gdou.recipebackend.core.mapper.RecipeTypeMapper;
import edu.gdou.recipebackend.core.service.CookBookService;
import edu.gdou.recipebackend.core.service.RecipeService;
import edu.gdou.recipebackend.core.service.RecipeTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe_manager")
@Slf4j
public class RecipeController {
    @Autowired
    private CookBookService cookBookService;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeTypeService recipeTypeService;
    @Autowired
    private RecipeTypeMapper recipeTypeMapper;
    /*
     * 创建/更新菜谱
     * */
    @PostMapping("/recipe")
    public Result<Integer> CreateOrUpdateRecipe(@RequestParam RecipePO recipePO){
        int recipeId = recipeService.InsertOrUpdate(recipePO);
        return Result.ok(recipeId);
    }

    /*
    * 获取菜谱详情
    * */
    @GetMapping("/recipe_deatil/{id}")
    public Result<RecipeDetailVO> getRecipeDeatil(@PathVariable Long id){
        RecipeDetailVO recipeDetailVO = recipeService.getRecipeDetail(id);
        return Result.ok(recipeDetailVO);
    }
    /*
     * 获取所有菜谱类别
     * */
    @GetMapping("/recipes_type")
    public Result<List<RecipeTypePO>> getRecipeType(){
        List<RecipeTypePO> recipeTypePOS = recipeTypeMapper.selectList(null);
        return Result.ok(recipeTypePOS);
    }
    /*
     * 获取某个菜谱的类别
     * */
    @GetMapping("/recipes_type/{recipeId}")
    public Result<List<RecipeTypePO>> getRecipeTypeByRecipeId(@PathVariable Long recipeId){
        List<RecipeTypePO> recipeTypePOS = recipeTypeService.getRecipeTypeByRecipeId(recipeId);
        return Result.ok(recipeTypePOS);
    }
    /*
     * 搜索
     * */
    @GetMapping("/recipes")
    public Result<List<RecipeVO>> SearchRecipes(@RequestParam(required = false)String keywords,
                                                @RequestParam(defaultValue = "1")int page,
                                                @RequestParam(defaultValue = "10")int pagesize,
                                                @RequestParam(required = false)String type){
        List<RecipeVO> recipes = recipeService.search(keywords,page,pagesize,type);
        return Result.ok(recipes);
    }

    /*
     * 审核菜谱
     * */
    @PostMapping("/review_recipe")
    public Result reviewRecipe(Long recipeId,Boolean reviewState){
        recipeService.review(recipeId,reviewState);
        return Result.ok();
    }
    /*
     * 获取收藏菜谱
     * */
    @GetMapping("/favorite")
    public Result<CookBookVO> getCookBook(){
        //根据当前用户id查询其菜谱收藏表
        int userId = StpUtil.getLoginIdAsInt();
        List<RecipePO> recipes = cookBookService.getfavoriteRecipeByUserId(userId);
        if (recipes.isEmpty()){
            log.info("当前用户没有收藏菜谱");
        }else {
            log.info("recipes:{}",recipes);
        }
        //定义视图类
        CookBookVO cookBookVO = new CookBookVO();
        cookBookVO.setTotal(recipes.size());
        cookBookVO.setRecipes(recipes);
        return Result.ok(cookBookVO);
    }
    /*
     * 收藏菜谱
     * */
    @PostMapping("/favorite")
    public Result collect(@RequestBody FavoriteDTO favoriteDTO){
        cookBookService.collect(favoriteDTO);
        return Result.ok();
    }
}

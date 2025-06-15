package edu.gdou.recipebackend.core.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdou.recipebackend.core.entity.dto.FavoriteDTO;
import edu.gdou.recipebackend.core.entity.po.CookBookPO;
import edu.gdou.recipebackend.core.entity.po.RecipePO;
import edu.gdou.recipebackend.core.mapper.CookBookMapper;
import edu.gdou.recipebackend.core.mapper.RecipeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@Slf4j
public class CookBookService extends ServiceImpl<CookBookMapper, CookBookPO> {
    @Autowired
    private CookBookMapper cookBookMapper;
    @Autowired
    private RecipeMapper recipeMapper;
    public List<RecipePO> getfavoriteRecipeByUserId(int userId){
        //先查出有多少cookbook收藏记录
        List<CookBookPO> cookBookPOS = cookBookMapper.selectList(new LambdaQueryWrapper<CookBookPO>().eq(CookBookPO::getUserId, userId));
        ArrayList<Integer> recipeIds = new ArrayList<>();
        for (CookBookPO cookBookPO : cookBookPOS) {
            recipeIds.add(cookBookPO.getRecipeId());
        }
        //创建存储结果对象
        List<RecipePO> recipes = new ArrayList<>();
        //根据菜谱id查询菜谱
        for (Integer recipeId : recipeIds) {
            RecipePO recipePO = recipeMapper.selectById(recipeId);
            recipes.add(recipePO);
        }
        return recipes;
    }

    public void collect(FavoriteDTO favoriteDTO) {
        //获取当前用户id
        int userId = StpUtil.getLoginIdAsInt();
        int recipeId = favoriteDTO.getRecipeId();
        Boolean isFavorite = favoriteDTO.getIsFavorite();
        //查询当前记录是否存在；
        LambdaQueryWrapper<CookBookPO> wrapper = new LambdaQueryWrapper<CookBookPO>().eq(CookBookPO::getUserId, userId).eq(CookBookPO::getRecipeId, recipeId);
        Long count = cookBookMapper.selectCount(wrapper);
        if (isFavorite){
            //加入收藏
            if (count==0){
                //说明不存在当前用户收藏该菜谱的记录
                //插入到收藏记录表中
                CookBookPO cookBookPO = new CookBookPO();
                cookBookPO.setRecipeId(recipeId);
                cookBookPO.setUserId(userId);
                cookBookPO.setCollectionTime(new Date());
                int insert = cookBookMapper.insert(cookBookPO);
                if (insert<=0){
                    log.error("加入收藏失败");
                    throw new RuntimeException("加入收藏出现错误");
                }
            }else {
                //存在
                log.debug("当前记录已存在，不允许重复加入收藏");
            }
        }else {//取消收藏
            //将记录删除
            //先查看记录是否还存在
            if (count==0){
                //说明已经不存在了，不需要再删除记录了
                log.debug("当前记录已经不存在，不用再一次删除记录");
            }
            else {
                //记录存在，执行删除
                int delete = cookBookMapper.delete(wrapper);
                if (delete<=0){
                    log.error("删除失败");
                    throw new RuntimeException("删除失败，出现错误");
                }else {
                    log.info("删除成功");
                }
            }
        }
    }
}

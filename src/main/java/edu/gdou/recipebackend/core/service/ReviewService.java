package edu.gdou.recipebackend.core.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdou.recipebackend.core.entity.dto.ReviewDto;
import edu.gdou.recipebackend.core.entity.po.ReviewPO;
import edu.gdou.recipebackend.core.mapper.ReviewMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class ReviewService extends ServiceImpl<ReviewMapper, ReviewPO> {
    @Autowired
    private ReviewMapper reviewMapper;
    public int comment(ReviewDto reviewDto) {
        //将reviewDto转为PO对象
        ReviewPO reviewPO = new ReviewPO();
        BeanUtils.copyProperties(reviewDto, reviewPO);
        //获取当前登录用户id
        int userId = StpUtil.getLoginIdAsInt();
        reviewPO.setUserId(userId);
        reviewPO.setReviewTime(new Date());
        log.info("ReviewPO：{}", reviewPO);
        int insert = reviewMapper.insert(reviewPO);
        if (insert<=0){
            log.error("评论失败，请再试一遍");
            throw new RuntimeException("发布评论出错");
        }
        return reviewPO.getReviewId();
    }
}

package edu.gdou.recipebackend.core.service;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdou.recipebackend.core.entity.dto.LoginDTO;
import edu.gdou.recipebackend.core.entity.dto.RegDTO;
import edu.gdou.recipebackend.core.entity.po.UserPO;
import edu.gdou.recipebackend.core.entity.vo.RegAndLoginVO;
import edu.gdou.recipebackend.core.mapper.UserMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService extends ServiceImpl<UserMapper,UserPO> {


    public RegAndLoginVO register(RegDTO dto) {
        String username = dto.getUsername();
        String email = dto.getEmail();

        // 1. 检查用户名是否已存在
        if (query().eq("Username", username).count() > 0) {
            throw new RuntimeException("用户名已存在");
        }

        // 2. 检查邮箱是否已存在
        if (query().eq("Email", email).count() > 0) {
            throw new RuntimeException("邮箱已被注册");
        }

        // 3. 将 DTO 转换为 Entity
        UserPO user = new UserPO();
        user.setEmail(email);
        user.setUsername(username);
        user.setLastLoginTime(new Date());

        // 4. 密码加密（示例使用 MD5，推荐使用 BCrypt）
        String hashedPassword = BCrypt.hashpw(dto.getPassword(),BCrypt.gensalt());
        user.setPasswordHash(hashedPassword);

        if (!save(user)){
            throw new RuntimeException("插入失败");
        }

        StpUtil.login(user.getUserID());
        RegAndLoginVO vo = new RegAndLoginVO();
        vo.setUsername(username);
        vo.setToken(StpUtil.getTokenValue());
        vo.setUserId(String.valueOf(user.getUserID()));
        return vo;
    }


    public RegAndLoginVO login(LoginDTO dto){

        UserPO userPO = getOne(new LambdaQueryWrapper<UserPO>()
                .eq(UserPO::getEmail, dto.getEmail()));
        if (userPO == null){
            throw new RuntimeException("用户不存在");
        }

        if (BCrypt.checkpw(dto.getPassword(),userPO.getPasswordHash())){
            StpUtil.login(userPO.getUserID());
            RegAndLoginVO vo = new RegAndLoginVO();
            vo.setUserId(String.valueOf(userPO.getUserID()));
            vo.setToken(StpUtil.getTokenValue());
            vo.setUsername(userPO.getUsername());
            return vo;
        } else {
            throw new RuntimeException("密码错误");
        }
    }

}

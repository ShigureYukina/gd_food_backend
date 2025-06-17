package edu.gdou.recipebackend.core.controller;


import edu.gdou.recipebackend.core.entity.base.Result;
import edu.gdou.recipebackend.core.entity.dto.EditUserDTO;
import edu.gdou.recipebackend.core.entity.dto.LoginDTO;
import edu.gdou.recipebackend.core.entity.dto.RegDTO;
import edu.gdou.recipebackend.core.entity.po.UserPO;
import edu.gdou.recipebackend.core.entity.vo.RegAndLoginVO;
import edu.gdou.recipebackend.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/auth/register")
    public Result<RegAndLoginVO> register(RegDTO dto){
        RegAndLoginVO vo = userService.register(dto);
        return Result.ok(vo);
    }

    @PostMapping("/api/auth/login")
    public Result<RegAndLoginVO> login(LoginDTO dto){
        RegAndLoginVO vo = userService.login(dto);
        return Result.ok(vo);
    }
    /*
    * 根据用户id查询用户信息
    * */
    @GetMapping("/api/userinfo")
    public Result<UserPO> getUseInfo(Long userId){
        UserPO userInfo = userService.getUserInfo(userId);
        return Result.ok(userInfo);
    }
    /*
     * 更新用户信息
     * */
    @PutMapping("/api/userinfo")
    public Result<UserPO> UpdateUseInfo(@RequestBody EditUserDTO editUserDTO){
        UserPO userPO = userService.updateUserInfo(editUserDTO);
        return Result.ok(userPO);
    }


}

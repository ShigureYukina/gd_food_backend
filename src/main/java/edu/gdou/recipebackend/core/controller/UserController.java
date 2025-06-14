package edu.gdou.recipebackend.core.controller;


import edu.gdou.recipebackend.core.entity.base.Result;
import edu.gdou.recipebackend.core.entity.dto.LoginDTO;
import edu.gdou.recipebackend.core.entity.dto.RegDTO;
import edu.gdou.recipebackend.core.entity.vo.RegAndLoginVO;
import edu.gdou.recipebackend.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/auth/register")
    public Result register(RegDTO dto){
        RegAndLoginVO vo = userService.register(dto);
        return Result.ok(vo);
    }

    @PostMapping("/api/auth/login")
    public Result login(LoginDTO dto){
        RegAndLoginVO vo = userService.login(dto);
        return Result.ok(vo);
    }


}

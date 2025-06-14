package edu.gdou.recipebackend.core.entity.vo;


import lombok.Data;

@Data
public class RegAndLoginVO {

    private String token;
    private String userId;
    private String username;

}

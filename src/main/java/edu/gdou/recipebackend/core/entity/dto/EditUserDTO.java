package edu.gdou.recipebackend.core.entity.dto;

import lombok.Data;

@Data
public class EditUserDTO {
    private String username;
    private String email;
    private Integer userRole;
}

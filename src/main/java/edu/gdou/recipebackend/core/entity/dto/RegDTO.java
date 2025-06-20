package edu.gdou.recipebackend.core.entity.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegDTO {

    @NotBlank
    @Size(min = 2,max = 20)
    private String username;

    @NotBlank
    @Size(min = 6,max = 20)
    private String password;

    @NotBlank
    @Email
    private String email;

}

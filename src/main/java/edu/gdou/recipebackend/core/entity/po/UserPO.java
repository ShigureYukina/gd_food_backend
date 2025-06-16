package edu.gdou.recipebackend.core.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 用户实体类
 */
@Data // 自动生成getter、setter、toString等方法
@TableName("Users") // 指定数据库表名
public class UserPO {

    /**
     * 用户ID - 主键
     */
    @TableId(value = "UserID", type = IdType.AUTO) // 映射数据库主键
    private Integer userID;

    /**
     * 用户名 - 唯一
     */
    @TableField("Username")
    private String username;

    /**
     * 密码哈希值 - 使用BCrypt加密
     */
    @TableField("PasswordHash")
    private String passwordHash;

    /**
     * 用户邮箱 - 唯一
     */
    @TableField("Email")
    private String email;

    /**
     * 用户注册时间 - 默认当前时间
     */
    @TableField(value = "RegistrationTime", fill = FieldFill.INSERT)
    private Date registrationTime;

    /**
     * 最后登录时间
     */
    @TableField("LastLoginTime")
    private Date lastLoginTime;

    @TableField("UserRole")
    private int UserRole;
}
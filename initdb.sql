
use  recipe_db;


CREATE TABLE Users (
                       UserID INT AUTO_INCREMENT PRIMARY KEY,
                       Username VARCHAR(50) NOT NULL UNIQUE,
                       PasswordHash CHAR(60) NOT NULL,
                       Email VARCHAR(100) NOT NULL UNIQUE,
                       RegistrationTime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       LastLoginTime DATETIME DEFAULT NULL,
                       UserRole TINYINT NOT NULL DEFAULT 0 COMMENT '0: 普通用户, 1: 管理员'
);



CREATE TABLE RecipeType (
                            recipeTypeId BIGINT AUTO_INCREMENT PRIMARY KEY,
                            recipeTypeName VARCHAR(255) NOT NULL
);



CREATE TABLE Recipe (
                        RecipeID INT AUTO_INCREMENT PRIMARY KEY, -- 菜谱唯一标识
                        UserID INT NOT NULL, -- 上传者的用户ID（关联User表）
                        Title VARCHAR(100) NOT NULL, -- 菜谱标题
                        Ingredients TEXT NOT NULL, -- 食材清单（JSON格式）
                        Difficulty ENUM('简单', '中等', '困难') NOT NULL DEFAULT '中等', -- 菜谱难度等级，默认为'中等'
                        Steps TEXT NOT NULL, -- 烹饪步骤描述
                        VideoLink VARCHAR(255), -- 视频链接（可选）
                        ImageLinks TEXT, -- 图文链接（JSON格式存储多个）
                        UploadTime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 菜谱上传时间，默认为当前时间
                        ReviewState TINYINT NOT NULL DEFAULT 0, -- 审核状态：0未审核，1审核通过，默认为0
                        RecipeTypeID INT, -- 菜谱类型ID
                        RecipeTypeName VARCHAR(10) -- 菜谱类型名称，冗余字段记录类别
);





CREATE TABLE Story (
                       StoryID INT AUTO_INCREMENT PRIMARY KEY COMMENT '故事唯一标识',
                       RecipeID INT NOT NULL UNIQUE COMMENT '关联的菜谱ID（一对一关系）',  -- 唯一约束确保一对一
                       HistoricalContext TEXT NOT NULL COMMENT '历史背景描述',
                       CulturalSignificance TEXT COMMENT '文化内涵描述（可选）'
) COMMENT = '背景故事表';

CREATE TABLE Review (
                        ReviewID INT AUTO_INCREMENT PRIMARY KEY COMMENT '评价唯一标识',
                        UserID INT NOT NULL COMMENT '评价者的用户ID（关联User表）',
                        RecipeID INT NOT NULL COMMENT '被评价的菜谱ID（关联Recipe表）',
                        Rating TINYINT UNSIGNED NOT NULL COMMENT '评分（1-5分）',
                        Comment TEXT COMMENT '评价内容（可选）',
                        ReviewTime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评价时间',

    -- 添加唯一约束防止重复评价
                        UNIQUE KEY (UserID, RecipeID)
) COMMENT = '评价表';


CREATE TABLE CookBook (
                          UserID INT NOT NULL COMMENT '用户ID（关联User表）',
                          RecipeID INT NOT NULL COMMENT '收藏的菜谱ID（关联Recipe表）',
                          CollectionTime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
                          Notes TEXT COMMENT '用户备注（如心得、调整步骤等）',

    -- 联合主键
                          PRIMARY KEY (UserID, RecipeID)
) COMMENT = '个人菜谱收藏表';



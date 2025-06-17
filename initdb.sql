
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

create table if not exists recipe_db.recipe
(
    RecipeID      int auto_increment
        primary key,
    UserID        int                                                     not null,
    Title         varchar(100)                                            not null comment '菜谱标题',
    Ingredients   text                                                    not null comment '食材清单',
    Difficulty    enum ('简单', '中等', '困难') default '中等'            not null,
    Steps         text                                                    not null comment '烹饪步骤',
    VideoLink     varchar(255)                                            null,
    ImageLinks    text                                                    null,
    UploadTime    datetime                      default CURRENT_TIMESTAMP not null,
    ReviewState   tinyint                       default 0                 not null comment '审核状态，0代表未审核，1代表审核通过，默认为0',
    RecipeTypeIds varchar(20)                                            null comment '类别id，若有多个类别，用,隔开',
    RecipeTypeNames VARCHAR(255) NOT NULL
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




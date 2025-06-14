package edu.gdou.recipebackend.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入操作时自动填充字段
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 自动填充 registrationTime 字段（仅当值为空时）
        this.strictInsertFill(metaObject, "registrationTime", Date.class, new Date());

    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }


}
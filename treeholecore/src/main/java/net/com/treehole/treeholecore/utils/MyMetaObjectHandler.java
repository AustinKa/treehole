package net.com.treehole.treeholecore.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 注入雪花算法生成器
     */
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert .. ");
        this.setFieldValByName("createdTime",  LocalDateTime.now(), metaObject);
        this.setFieldValByName("userUuid",  snowflakeIdWorker.nextId(), metaObject);
        this.setFieldValByName("delStatus",  0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update .. ");
        this.setFieldValByName("updatedTime", LocalDateTime.now(),metaObject);
    }
}

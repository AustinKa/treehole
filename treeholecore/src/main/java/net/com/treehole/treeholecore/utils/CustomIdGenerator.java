package net.com.treehole.treeholecore.utils;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description 自定义的mybatisplus的自增id
 * 使用到雪花算法
 * @author ruhu
 * @date 2022/6/25 12:50
 * @version 1.0
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator {
    /**
     * 注入雪花算法生成器
     */
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public Long nextId(Object entity) {
        // 返回雪花算法生成的long
        return snowflakeIdWorker.nextId();
    }
}

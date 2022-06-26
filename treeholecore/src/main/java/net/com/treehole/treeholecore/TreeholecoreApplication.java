package net.com.treehole.treeholecore;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import net.com.treehole.treeholecore.utils.CustomIdGenerator;
import net.com.treehole.treeholecore.utils.SnowflakeIdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/***
 *  TreeholecoreApplication
 * description springboot启动类
 * @author ruhu
 * @date 2022/6/25 11:51
 * @version 1.0
 */
@SpringBootApplication
@MapperScan("net.com.treehole.treeholecore.mapper")
public class TreeholecoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(TreeholecoreApplication.class, args);
    }

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker(){
        return new SnowflakeIdWorker(0 , 0);
    }






}

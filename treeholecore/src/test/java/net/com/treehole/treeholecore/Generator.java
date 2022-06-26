package net.com.treehole.treeholecore;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 *
 * @author zhoubin
 * @since 1.0.0
 */

public class Generator {
    public static void main(String[] args) {
        String[] s = {"treehole_user_friends","treehole_chat_communication"};
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/treehole?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC", "root", "1234")
                .globalConfig(builder -> {
                    builder.author("guixiao") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("E:\\projecthome\\treehole-chat-room\\treehole\\treeholecore\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("net.com.treehole") // 设置父包名
                            .moduleName("treeholecore") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E:\\projecthome\\treehole-chat-room\\treehole\\treeholecore\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
//                .strategyConfig(builder -> {
//                    builder// 设置需要生成的表名
//                            .addInclude(s);// 设置过滤表前缀
//                })
                .strategyConfig(builder -> {
                    builder.addInclude(s)
                            .entityBuilder()
                            .enableLombok()
                            .build();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}


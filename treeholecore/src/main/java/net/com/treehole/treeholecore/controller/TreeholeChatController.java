package net.com.treehole.treeholecore.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.com.treehole.treeholecore.entity.TreeholeChat;
import net.com.treehole.treeholecore.service.ITreeholeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 聊天主表 前端控制器
 * </p>
 *
 * @author guixiao
 * @since 2022-06-25
 */
@Api(tags = "聊天主表管理控制器")
@Slf4j
@RestController
@RequestMapping("/treeholecore/treehole-chat")
public class TreeholeChatController {
    
    @Autowired
    private ITreeholeChatService iTreeholeChatService;
    
    /**
     * description 保存聊天者id到聊天主表
     * @author ruhu
     * @param treeholeChat 聊天主表
     * @return void
     * @date 2022/6/25 16:20
     */
    @ApiOperation("保存聊天主表")
    @PostMapping("/save")
    public void saveTreeholeChat(@RequestBody TreeholeChat treeholeChat){
        log.info("保存到聊天主表发起聊天用户id{},被聊天用户id{}", treeholeChat.getUserId(), treeholeChat.getAnotherId());
        boolean save = iTreeholeChatService.save(treeholeChat);
    }

}

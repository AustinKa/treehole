package net.com.treehole.treeholecore.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.com.treehole.treeholecore.entity.TreeholeChatCommunication;
import net.com.treehole.treeholecore.service.ITreeholeChatCommunicationService;
import net.com.treehole.treeholecore.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 聊天消息表 前端控制器
 * </p>
 *
 * @author guixiao
 * @since 2022-06-25
 */
@Api(tags = "聊天消息控制器")
@Slf4j
@RestController
@RequestMapping("/treeholecore/treehole-chat-communication")
public class TreeholeChatCommunicationController {
    @Autowired
    private ITreeholeChatCommunicationService  iTreeholeChatCommunicationService;


    @ApiOperation("保存聊天信息")
    @PostMapping("/save")
    public TreeholeChatCommunication save(@RequestBody TreeholeChatCommunication treeholeChatCommunication){
        iTreeholeChatCommunicationService.save(treeholeChatCommunication);
        return treeholeChatCommunication;
    }


    @ApiOperation("通过用户id和好友id查找聊天信息")
    @GetMapping("/{fromId}/{toId}")
    public Result getTreeholeChatCommunications(@PathVariable("fromId")Long fromId,@PathVariable("toId")Long toId){
        List<TreeholeChatCommunication> treeholeChatCommunicationList = iTreeholeChatCommunicationService.getTreeholeChatCommunications(fromId, toId);
        return Result.success(treeholeChatCommunicationList);
    }
}

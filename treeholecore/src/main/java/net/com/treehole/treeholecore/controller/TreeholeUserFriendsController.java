package net.com.treehole.treeholecore.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.com.treehole.treeholecore.entity.TreeholeUserFriends;
import net.com.treehole.treeholecore.service.ITreeholeUserFriendsService;
import net.com.treehole.treeholecore.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 好友表 前端控制器
 * </p>
 *
 * @author guixiao
 * @since 2022-06-25
 */
@Api(tags = "好友管理控制器")
@Slf4j
@RestController
@RequestMapping("/treeholecore/treehole-user-friends")
public class TreeholeUserFriendsController {
    @Autowired
    private ITreeholeUserFriendsService iTreeholeUserFriendsService;

    @ApiOperation("根据用户id获取所有好友")
    @GetMapping("/getFriendsByUserId/{userId}")
    public Result getFriendsByUserId(@PathVariable Long userId){
        List<TreeholeUserFriends> friendsList = iTreeholeUserFriendsService.getFriendsByUserId(userId);
        return Result.success(friendsList);
    }

    @ApiOperation("保存好友")
    @PostMapping("/save")
    public Result saveFriend(@RequestBody TreeholeUserFriends treeholeUserFriends){
        iTreeholeUserFriendsService.saveFriend(treeholeUserFriends);
        log.info("保存好友表from{}，to{}", treeholeUserFriends.getFromId(), treeholeUserFriends.getToId());
        return Result.success("ok");
    }
}

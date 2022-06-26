package net.com.treehole.treeholecore.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.com.treehole.treeholecore.entity.TreeholeUser;
import net.com.treehole.treeholecore.service.ITreeholeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author guixiao
 * @since 2022-06-25
 */
@Api(tags = "用户信息控制器")
@Slf4j
@RestController
@RequestMapping("/treeholecore/treehole-user")
public class TreeholeUserController {
    @Autowired
    private ITreeholeUserService iTreeholeUserService;

    @ApiOperation("用户遍历list")
    @GetMapping("/list")
    public List<TreeholeUser> list(){
        return iTreeholeUserService.list();
    }

    @ApiOperation("用户保存")
    @PostMapping("/save")
    public String save(@RequestBody TreeholeUser treeholeUser){
        log.info("新增用户:{}", treeholeUser);
        boolean save = iTreeholeUserService.save(treeholeUser);
        return "ok";
    }

    @ApiOperation("用户修改")
    @PutMapping("/update")
    public String update(@RequestBody TreeholeUser treeholeUser){
        log.info("修改id为:{}用户信息,为:{}", treeholeUser.getUserId(), treeholeUser);
        boolean save = iTreeholeUserService.updateById(treeholeUser);
        return "ok";
    }

}

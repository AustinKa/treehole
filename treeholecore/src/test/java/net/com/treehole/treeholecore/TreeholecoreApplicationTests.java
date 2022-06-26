package net.com.treehole.treeholecore;

import lombok.extern.slf4j.Slf4j;
import net.com.treehole.treeholecore.entity.TreeholeUser;
import net.com.treehole.treeholecore.service.ITreeholeUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class TreeholecoreApplicationTests {
    @Autowired
    private ITreeholeUserService iTreeholeUserService;

    @Test
    void contextLoads() {
        TreeholeUser treeholeUser = new TreeholeUser();
        treeholeUser.setUserName("cesgada1212");
        iTreeholeUserService.save(treeholeUser);

    }


    @Test
    void updateUser() {
        TreeholeUser treeholeUser = new TreeholeUser();
        treeholeUser.setUserName("cesasasa");
        treeholeUser.setUserId(6946203565337608192L);
        iTreeholeUserService.updateById(treeholeUser);
        log.info(treeholeUser.toString());
    }

}

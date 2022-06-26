package net.com.treehole.treeholecore;

import lombok.extern.slf4j.Slf4j;
import net.com.treehole.treeholecore.entity.TreeholeChatCommunication;
import net.com.treehole.treeholecore.entity.TreeholeUser;
import net.com.treehole.treeholecore.service.ITreeholeChatCommunicationService;
import net.com.treehole.treeholecore.service.ITreeholeChatService;
import net.com.treehole.treeholecore.service.ITreeholeUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class TreeholecoreApplicationTests {
    @Autowired
    private ITreeholeUserService iTreeholeUserService;

    @Autowired
    private ITreeholeChatService iTreeholeChatService;

    @Autowired
    private ITreeholeChatCommunicationService iTreeholeChatCommunicationService;

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

    @Test
    public void selectTreeChatByUserIdOrAuthIdTest(){
        List<TreeholeChatCommunication> treeholeChatCommunications = iTreeholeChatCommunicationService.getTreeholeChatCommunications(6946563450961133568L, 6946563468736593920L);
        log.info(treeholeChatCommunications.toString());
    }

}

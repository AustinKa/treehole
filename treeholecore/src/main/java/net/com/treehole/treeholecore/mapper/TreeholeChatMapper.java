package net.com.treehole.treeholecore.mapper;

import net.com.treehole.treeholecore.entity.TreeholeChat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 聊天主表 Mapper 接口
 * </p>
 *
 * @author guixiao
 * @since 2022-06-25
 */
public interface TreeholeChatMapper extends BaseMapper<TreeholeChat> {
    /**
     *  selectByUserIdOrAnotherId
     * description 通过用户id 和  anotherIdc查找到聊天主表
     * @author ruhu
     * @param userId  聊天发起用户id
     * @param anotherId 聊天接受用户id
     * @return java.util.List<net.com.treehole.treeholecore.entity.TreeholeChat>
     * @date 2022/6/26 15:09
     */
    List<TreeholeChat> selectByUserIdOrAnotherId(@Param("userId") Long userId, @Param("anotherId") Long anotherId);
}

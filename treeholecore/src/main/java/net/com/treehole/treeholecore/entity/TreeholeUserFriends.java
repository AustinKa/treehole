package net.com.treehole.treeholecore.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 好友表
 * </p>
 *
 * @author guixiao
 * @since 2022-06-25
 */
@Getter
@Setter
@TableName("treehole_user_friends")
@ApiModel(value = "TreeholeUserFriends对象", description = "好友表")
public class TreeholeUserFriends implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("好友表ID")
    @TableId(value = "friends_id",type = IdType.ASSIGN_ID)
    private Long friendsId;

    @ApiModelProperty("用户ID")
    private Long fromId;

    @ApiModelProperty("好友ID")
    private Long toId;

    @ApiModelProperty("好友备注")
    private String noteName;



    @ApiModelProperty("租户号")
    private String tenantId;

    @ApiModelProperty("乐观锁")
    private String revision;

    @ApiModelProperty("创建人")
    private String createdBy;

    @ApiModelProperty("创建时间")
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private LocalDateTime updatedTime;

    @ApiModelProperty("更新人")
    private String updatedBy;

    @ApiModelProperty("删除标识符默认0删除1")
    @TableField(value = "del_status",fill = FieldFill.INSERT)
    private Integer delStatus;


}

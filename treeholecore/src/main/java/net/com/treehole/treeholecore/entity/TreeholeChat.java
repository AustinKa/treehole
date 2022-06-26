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
 * 聊天主表
 * </p>
 *
 * @author guixiao
 * @since 2022-06-25
 */
@Getter
@Setter
@TableName("treehole_chat")
@ApiModel(value = "TreeholeChat对象", description = "聊天主表")
public class TreeholeChat implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("租户号")
    private String tenantId;

    @ApiModelProperty("乐观锁")
    private String revision;

    @ApiModelProperty("创建人")
    private String createdBy;

    @ApiModelProperty("创建时间")
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @ApiModelProperty("更新人")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private LocalDateTime updatedTime;

    @ApiModelProperty("charid")
    @TableId(value = "chat_id",type = IdType.ASSIGN_ID)
    private Long chatId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("对方id")
    private Long anotherId;

    @ApiModelProperty("删除标识符默认0删除1")
    @TableField(value = "del_status",fill = FieldFill.INSERT)
    private Integer delStatus;


}

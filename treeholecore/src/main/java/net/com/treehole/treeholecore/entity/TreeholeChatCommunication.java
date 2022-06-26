package net.com.treehole.treeholecore.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 聊天消息表
 * </p>
 *
 * @author guixiao
 * @since 2022-06-25
 */
@Getter
@Setter
@TableName("treehole_chat_communication")
@ApiModel(value = "TreeholeChatCommunication对象", description = "聊天消息表")
public class TreeholeChatCommunication implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("聊天消息ID")
    @TableId(value = "communication_id",type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long communicationId;

    @ApiModelProperty("发送人ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long fromId;

    @ApiModelProperty("发送人NAME")
    private String fromName;

    @ApiModelProperty("接收人ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long toId;

    @ApiModelProperty("接收人NAME")
    private String toName;

    @ApiModelProperty("消息内容")
    private String content;

    @ApiModelProperty("群ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long groupId;

    @ApiModelProperty("群NAME")
    private String groupName;

    @ApiModelProperty("是否已读,不适用于群消息")
    private Integer readStatus;

    @ApiModelProperty("消息类：1是用户聊天，2是群组聊天")
    private Integer communicationClass;

    @ApiModelProperty("消息类型：1是普通文本，2是图片，3是语音")
    private Integer type;

    @ApiModelProperty("聊天主表ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long chatId;

    @ApiModelProperty("租户号")
    private String tenantId;

    @ApiModelProperty("乐观锁")
    private String revision;

    @ApiModelProperty("创建人")
    private String createdBy;


    @ApiModelProperty("更新人")
    private String updatedBy;

    @ApiModelProperty("创建时间")
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private LocalDateTime updatedTime;

    @ApiModelProperty("删除标识符默认0删除1")
    @TableField(value = "del_status",fill = FieldFill.INSERT)
    private Integer delStatus;
}

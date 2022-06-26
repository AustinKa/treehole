package net.com.treehole.treeholecore.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author guixiao
 * @since 2022-06-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("treehole_user")
@ApiModel(value = "TreeholeUser对象", description = "用户信息表")
public class TreeholeUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId(value = "user_id",type = IdType.ASSIGN_ID)
    private Long userId;

    @ApiModelProperty("UUID")
    @TableField(value = "user_uuid",fill = FieldFill.INSERT)
    private Long userUuid;

    @ApiModelProperty("用户名称")
    private String userName;

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

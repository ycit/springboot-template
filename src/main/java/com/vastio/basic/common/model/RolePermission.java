package com.vastio.basic.common.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenxy
 * @since 2018-02-12
 */
@EqualsAndHashCode(callSuper = true)
@TableName("BASE_ROLE_PERMISSION")
@Data
@KeySequence(value = "BASE_ROLE_PERMISSION_SEQ", clazz = Integer.class)
public class RolePermission extends Model<RolePermission> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;
    @TableField("ROLE_ID")
    private Integer roleId;
    @TableField("PERMISSION_ID")
    private Integer permissionId;
    @TableField("CREATE_TIME")
    private Date createTime;
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

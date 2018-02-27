package com.vastio.basic.common.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author chenxy123
 * @since 2018-02-12
 */
@TableName("BASE_PERMISSION")
@Data
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId("ID")
    private Integer id;
    /**
     * 权限名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 所属模块
     */
    @TableField("MODULE")
    private String module;
    @TableField("CREATE_TIME")
    private Date createTime;
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
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
 * <p>
 * 角色表,角色对应的菜单采用默认
 * </p>
 *
 * @author chenxy123
 * @since 2018-02-12
 */
@EqualsAndHashCode(callSuper = true)
@TableName("BASE_ROLE")
@Data
@KeySequence(value = "BASE_ROLE_SEQ", clazz = Integer.class)
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId("ID")
    private Integer id;
    /**
     * 角色名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 菜单路径
     */
    @TableField("PATH")
    private String path;
    /**
     * 角色描述
     */
    @TableField("DESCRIPTION")
    private String description;
    @TableField("CREATE_TIME")
    private Date createTime;
    @TableField("MODIFY_TIME")
    private Date modifyTime;
    @TableField("STATUS")
    private Integer status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

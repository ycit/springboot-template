package com.vastio.basic.common.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author chenxy123
 * @since 2018-02-12
 */
@TableName("BASE_USER")
@Data
@KeySequence(value = "BASE_USER_SEQ", clazz = Integer.class)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId("ID")
    private Integer id;
    /**
     * 角色id
     */
    @TableField("ROLE_ID")
    private Integer roleId;
    /**
     * 用户唯一标识
     */
    @TableField("USERNAME")
    private String username;
    /**
     * 身份证号
     */
    @TableField("CARD_ID")
    private String cardId;
    /**
     * 用户名称
     */
    @TableField("FULL_NAME")
    private String fullName;
    /**
     * 职称
     */
    @TableField("JOB_TITLE")
    private String jobTitle;
    /**
     * 组织机构id
     */
    @TableField("ORG_ID")
    private Long orgId;
    @TableField("PASSWORD")
    private String password;
    @TableField("CREATE_TIME")
    private Date createTime;
    @TableField("MODIFY_TIME")
    private Date modifyTime;
    /**
     * 用户状态:
     */
    @TableField("STATUS")
    private Integer status;
    @TableField("TEL_NO")
    private Long telNo;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

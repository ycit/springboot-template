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
 * @author chenxy
 * @since 2018-02-12
 */
@TableName("BASE_USER_ROLE")
@Data
@KeySequence(value = "BASE_USER_ROLE_SEQ", clazz = Integer.class)
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;
    @TableField("USER_ID")
    private Integer userId;
    @TableField("ROLE_ID")
    private Integer roleId;
    @TableField("CREATE_TIME")
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

package com.vastio.basic.common.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author chenxy
 * @since 2018-02-12
 */
@EqualsAndHashCode(callSuper = true)
@TableName("BASE_USER_CONFIG")
@Data
public class UserConfig extends Model<UserConfig> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;
    @TableField("USER_ID")
    private String userId;//这里指代用户名并非用户主键
    @TableField("NAME")
    private String name;
    @TableField("VALUE")
    private String value;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

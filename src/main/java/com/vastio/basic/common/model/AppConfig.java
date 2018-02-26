package com.vastio.basic.common.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chenxy
 * @since 2018-02-12
 */
@TableName("BASE_APP_CONFIG")
@Data
public class AppConfig extends Model<AppConfig> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;
    @TableField("NAME")
    private String name;
    @TableField("VALUE")
    private String value;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
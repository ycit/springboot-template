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
@TableName("BASE_LOG")
@Data
@KeySequence(value = "BASE_LOG_SEQ", clazz = Long.class)
public class Log extends Model<Log> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
    private Long id;
    /**
     * 用户id
     */
    @TableField("USER_ID")
    private String userId;
    /**
     * 模块名称
     */
    @TableField("MODULE")
    private String module;
    /**
     * 方法名
     */
    @TableField("METHOD")
    private String method;
    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;
    /**
     * 终端地址
     */
    @TableField("TERMINAL_ID")
    private String terminalId;
    /**
     * 操作时间
     */
    @TableField("OPTIONAL_TIME")
    private Date optionalTime;
    /**
     * 操作结果
     */
    @TableField("OPTIONAL_RESULT")
    private String optionalResult;
    /**
     * 错误信息
     */
    @TableField("ERROR_CODE")
    private String errorCode;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

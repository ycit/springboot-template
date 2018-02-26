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
 * 用户使用的组织机构
 * </p>
 *
 * @author chenxy123
 * @since 2018-02-12
 */
@TableName("BASE_ORG")
@Data
public class Org extends Model<Org> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId("ID")
    private Integer id;
    /**
     * 组织机构代码
     */
    @TableField("CODE")
    private String code;
    @TableField("PARENT_ID")
    private Integer parentId;
    /**
     * 组织机构名称
     */
    @TableField("NAME")
    private String name;
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

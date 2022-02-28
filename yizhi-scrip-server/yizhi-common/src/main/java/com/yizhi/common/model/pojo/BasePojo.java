package com.yizhi.common.model.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * The type Base pojo.
 */
@Data
public abstract class BasePojo {
    @TableField(fill = FieldFill.INSERT) //MP自动填充
    private Date created;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updated;
}

package com.yizhi.common.model.pojo.mysql;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * The type Base pojo.
 */
@Data
public abstract class Base {
    @TableField(value = "created", fill = FieldFill.INSERT) //MP自动填充
    private LocalDateTime created;
    @TableField(value = "updated", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updated;
}

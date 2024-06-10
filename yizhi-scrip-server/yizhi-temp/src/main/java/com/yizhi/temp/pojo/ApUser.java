package com.yizhi.temp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author newborne
 * @since 2022 -03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_user")
public class ApUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 密码，需要加密
     */
    @TableField("password")
    private String password;

    /**
     * 创建日期
     */
    @TableField("created")
    private LocalDateTime created;

    /**
     * 更新日期
     */
    @TableField("updated")
    private LocalDateTime updated;
}

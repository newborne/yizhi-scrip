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
 * 用户信息表
 * </p>
 *
 * @author newborne
 * @since 2022 -03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_user_info")
public class ApUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 用户头像
     */
    @TableField("logo")
    private String logo;

    /**
     * 用户标签：多个用逗号分隔
     */
    @TableField("tags")
    private String tags;

    /**
     * 性别，1-男，2-女，3-未知
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 用户年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 学历
     */
    @TableField("edu")
    private String edu;

    /**
     * 居住城市
     */
    @TableField("city")
    private String city;

    /**
     * 生日
     */
    @TableField("birthday")
    private String birthday;

    /**
     * 封面图片
     */
    @TableField("cover_pic")
    private String coverPic;

    /**
     * 行业
     */
    @TableField("industry")
    private String industry;

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

    /**
     * 用户状态
     */
    @TableField("status")
    private Integer status;
}

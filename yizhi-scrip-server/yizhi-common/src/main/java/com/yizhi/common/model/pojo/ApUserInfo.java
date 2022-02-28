package com.yizhi.common.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Ap user info.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ApUserInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  // id
  @ApiModelProperty(value = "id")
  private Integer id;

  // 用户id
  @ApiModelProperty(value = "用户id")
  private Integer user_id;

  // 昵称
  @ApiModelProperty(value = "昵称")
  private String nick_name;

  // 用户头像
  @ApiModelProperty(value = "用户头像")
  private String logo;

  // 用户标签：多个用逗号分隔
  @ApiModelProperty(value = "用户标签：多个用逗号分隔")
  private String tags;

  // 性别，1-男，2-女，3-未知
  @ApiModelProperty(value = "性别，1-男，2-女，3-未知")
  private Integer sex;

  // 用户年龄
  @ApiModelProperty(value = "用户年龄")
  private Integer age;

  // 学历
  @ApiModelProperty(value = "学历")
  private String edu;

  // 居住城市
  @ApiModelProperty(value = "居住城市")
  private String city;

  // 生日
  @ApiModelProperty(value = "生日")
  private String birthday;

  // 封面图片
  @ApiModelProperty(value = "封面图片")
  private String cover_pic;

  // 行业
  @ApiModelProperty(value = "行业")
  private String industry;

  // 创建日期
  @ApiModelProperty(value = "创建日期")
  private java.util.Date created;

  // 更新日期
  @ApiModelProperty(value = "更新日期")
  private java.util.Date updated;

  // 用户状态
  @ApiModelProperty(value = "用户状态")
  private Integer status;
}

package com.yizhi.common.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Ap user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ApUser implements Serializable {

  private static final long serialVersionUID = 1L;

  // id
  @ApiModelProperty(value = "id")
  private Integer id;

  // 手机号
  @ApiModelProperty(value = "手机号")
  private String mobile;

  // 密码，需要加密
  @ApiModelProperty(value = "密码，需要加密")
  private String password;

  // 创建日期
  @ApiModelProperty(value = "创建日期")
  private java.util.Date created;

  // 更新日期
  @ApiModelProperty(value = "更新日期")
  private java.util.Date updated;
}

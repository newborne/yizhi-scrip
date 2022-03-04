package com.yizhi.common.model.pojo;

import com.yizhi.common.model.pojo.mysql.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ApQuestion extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    // id
    @ApiModelProperty(value = "id")
    private Integer id;

    // 用户id
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    // 问题内容
    @ApiModelProperty(value = "问题内容")
    private String txt;
}

package com.yizhi.common.model.pojo.mysql;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ApDictionary implements Serializable {
    private static final long serialVersionUID = 1L;
    // id
    @ApiModelProperty(value = "id")
    private Integer id;
    // 上级id
    @ApiModelProperty(value = "上级id")
    private Integer parentId;
    // 名称
    @ApiModelProperty(value = "名称")
    private String name;
    // 值
    @ApiModelProperty(value = "值")
    private Integer value;
    // 编码
    @ApiModelProperty(value = "编码")
    private String dictCode;
    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
    // 更新时间
    @ApiModelProperty(value = "更新时间")
    private java.util.Date updateTime;
    // 删除标记（0:不可用 1:可用）
    @ApiModelProperty(value = "删除标记（0:不可用 1:可用）")
    private Integer isDeleted;
}

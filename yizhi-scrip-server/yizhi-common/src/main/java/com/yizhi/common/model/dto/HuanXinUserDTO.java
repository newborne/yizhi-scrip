package com.yizhi.common.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class HuanXinUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
}

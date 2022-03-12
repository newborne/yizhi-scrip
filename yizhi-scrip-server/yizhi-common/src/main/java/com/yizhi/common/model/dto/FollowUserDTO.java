package com.yizhi.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowUserDTO {
    private Long id;
    private String logo;
    private String nickName;
    private String sex;
    private Integer age;
    private String city;
    private String edu;
    private Integer similarity; //匹配度
}

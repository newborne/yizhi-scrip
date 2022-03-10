package com.yizhi.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowCountDTO {
    private Long mutualFollowCount; //互相关注
    private Long followCount; //关注
    private Long fanCount; //粉丝
}

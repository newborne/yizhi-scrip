package com.yizhi.common.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class PageInfoDTO<T> {
    private Integer total = 0;//总记录数
    private Integer size = 0;//页大小
    private Integer page = 0;//当前页码
    private Integer pages = 0;//总页数
    private List<T> records = Collections.emptyList();
}

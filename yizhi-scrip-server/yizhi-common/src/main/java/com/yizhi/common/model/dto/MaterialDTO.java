package com.yizhi.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String materialTypeName;
    private String[] tags;
    private String text;
    private Integer likeCount;
    private Integer hasLiked;
}
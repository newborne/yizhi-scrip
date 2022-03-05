package com.yizhi.common.model.pojo.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "log")
@ApiModel
public class Log {
    private Long userId;
    private Long created;
    private String url;
    private String annotation;
}

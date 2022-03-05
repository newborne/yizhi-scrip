package com.yizhi.common.model.pojo.mongodb;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "my_post")
public class MyPost {
    private static final long serialVersionUID = 1L;
    private ObjectId id;
    private ObjectId postId;
    private Long created;
}

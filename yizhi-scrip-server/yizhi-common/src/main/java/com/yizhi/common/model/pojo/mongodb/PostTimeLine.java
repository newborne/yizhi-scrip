package com.yizhi.common.model.pojo.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "post_timeline")
public class PostTimeLine implements Serializable {
    private static final long serialVersionUID = 9096178416317502524L;

    private ObjectId id;
    private Long postUserId; // 好友id
    private ObjectId postId; //发布id
    private Long date; //发布的时间
}

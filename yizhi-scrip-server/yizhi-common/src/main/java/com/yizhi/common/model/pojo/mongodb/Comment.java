package com.yizhi.common.model.pojo.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * The type Comment.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comment")
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    private ObjectId id;
    private ObjectId publishId; //发布id
    private Long publishUserId; //发布人的用户id
    private Long userId; //评论人
    private Integer commentType; //评论类型，1-点赞，2-评论，3-喜欢
    private String text; //评论内容
    private Boolean isParent = false; //是否为父节点，默认是否
    private ObjectId parentId; // 父节点id
    private Long created; //发表时间
}

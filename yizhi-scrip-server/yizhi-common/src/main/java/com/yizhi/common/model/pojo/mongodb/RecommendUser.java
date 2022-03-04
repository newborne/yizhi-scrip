package com.yizhi.common.model.pojo.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "recommend_user")
public class RecommendUser implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id; //主键id

    private Long friendId; //推荐的用户id
    private Long userId; //用户id

    private Double similarity; //相似度
    private String date; //日期
}


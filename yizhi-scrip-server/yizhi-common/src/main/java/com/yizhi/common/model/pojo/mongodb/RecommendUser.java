package com.yizhi.common.model.pojo.mongodb;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "recommend_user")
@ApiModel
public class RecommendUser implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private ObjectId id;
    private Long friendId;
    private Long userId;
    private Integer similarity;
    private Long created;
}

package com.yizhi.common.model.pojo.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "material_listener_msg")
public class MaterialListenerMsg {
    private ObjectId id;
    private Long userId;
    private Long materialRid;
    private Double rating;
    private Long created;
}

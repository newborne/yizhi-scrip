package com.yizhi.common.model.pojo.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "video_listener_msg")
public class VideoListenerMsg {
    private ObjectId id;
    private Long userId;
    private Long videoRid;
    private Long rating;
    private Long created;
}
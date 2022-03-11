package com.yizhi.common.model.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel
public enum MsgEnum implements IEnum<Integer> {
    SAVE(1, "发布"),
    LIKE(2, "点赞"),
    DISLIKE(3, "取消点赞"),
    COMMENT(4, "评论"),
    LOVE(5, "喜欢"),
    UNLOVE(6, "取消喜欢"),
    QUERY(7, "浏览");
    private final int type;
    private final String desc;
    MsgEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
    @Override
    public Integer getValue() {
        return this.type;
    }
    @Override
    public String toString() {
        return this.desc;
    }
}

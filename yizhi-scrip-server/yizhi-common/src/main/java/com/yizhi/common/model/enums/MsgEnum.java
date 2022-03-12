package com.yizhi.common.model.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel
public enum MsgEnum implements IEnum<Integer> {
    SAVE(0, "发布"),
    LIKE(1, "点赞"),
    DISLIKE(2, "取消点赞"),
    COMMENT(3, "评论"),
    LOVE(4, "喜欢"),
    UNLOVE(5, "取消喜欢"),
    QUERY(6, "浏览");
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

package com.omni.mongotest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 单元格存储
 *
 * @author Mr.Pei
 * @date 2023/2/6 11:46
 **/
@Getter
@Setter
@ToString
public class Grain extends BaseEmpty {
    //@Serial
    private static final long serialVersionUID = -5409358636906655426L;

    @Field("f")
    private String f;

    @Field("v")
    private Object c;
}

package com.omni.mongotest.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * 数据版本控制
 *
 * @author Mr.Pei
 * @date 2023/3/6 15:25
 **/
@Getter
@Setter
public abstract class BaseEmpty implements Serializable {

    //@Serial
    private static final long serialVersionUID = 0L;

    @Field("cv")
    private Integer cv;

    @Field("hv")
    private Integer hv;

}

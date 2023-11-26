package com.omni.mongotest.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Collection;

@Data
@Document(collection = "test_data")
public class PolDoc implements Serializable {

    //@Serial
    private static final long serialVersionUID = 3450433733940718383L;

    @Id
    private String id;

    @Field("i")
    private String i;

    @Field("v")
    private Collection<Grain> v;

    @Field("whether_del")
    private Boolean whetherDel;

}

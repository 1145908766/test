package com.omni.mongotest.model.other;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Mr.Pei
 * @date 2023/11/19 11:59
 **/
@Getter
@Setter
@Document(collection = "test_doc")
public class TextDoc {

    @Id
    private String id;

    private String doc;

}

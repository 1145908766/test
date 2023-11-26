package com.omni.mongotest.manager;

import cn.hutool.core.date.TimeInterval;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omni.mongotest.model.Grain;
import com.omni.mongotest.model.PolDoc;
import com.omni.mongotest.model.other.TextDoc;
import com.omni.mongotest.utils.CodeUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 执行测试
 *
 * @author Mr.Pei
 * @date 2023/11/16 15:42
 **/
@Slf4j
@Component
@AllArgsConstructor
public class ExecManager {

    @Resource
    private MongoTemplate mongoTemplate;

    private static final AtomicLong i2 = new AtomicLong();

    public void loadTest() throws JsonProcessingException {


//        List<TextDoc> textDocs2 = mongoTemplate.find(query, TextDoc.class);

    }

    public Collection<TextDoc> insertTextDoc() {
        List<TextDoc> texts = new ArrayList<>(2000);
        for (int i = 0; i < 100; i++) {
            TextDoc textDoc = new TextDoc();
            textDoc.setDoc(CodeUtils.generateRandomChinese(4000));
            texts.add(textDoc);
        }
        long l1 = System.currentTimeMillis();
        Collection<TextDoc> textDocs = mongoTemplate.insert(texts, TextDoc.class);
        long l2 = System.currentTimeMillis();
        log.info("插入耗时：{}", l2 - l1);
        return textDocs;
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertTextDocOne() {
        TimeInterval timeInterval = new TimeInterval();
        for (int i = 0; i < 100; i++) {
            TextDoc textDoc = new TextDoc();
            textDoc.setDoc(CodeUtils.generateRandomChinese(4000));
            mongoTemplate.insert(textDoc, "test_doc");
        }
        log.info("插入耗时：{}", timeInterval.intervalRestart());

    }

    @Transactional(rollbackFor = Exception.class)
    public void insertTextDocBatch() {
        List<TextDoc> texts = new ArrayList<>(2000);
        for (int i = 0; i < 100; i++) {
            TextDoc textDoc = new TextDoc();
            textDoc.setDoc(CodeUtils.generateRandomChinese(4000));
            texts.add(textDoc);
        }
        long l1 = System.currentTimeMillis();
        List<List<TextDoc>> partition = ListUtils.partition(texts, 20);
        partition.forEach(l->{
            mongoTemplate.insert(l, TextDoc.class);
        });
        long l2 = System.currentTimeMillis();
        log.info("插入耗时：{}", l2 - l1);
    }


    public Collection<PolDoc> insertPolTest() throws JsonProcessingException {
        List<PolDoc> pols = new ArrayList<>(2000);

        for (int i = 0; i < 1000; i++) {
            PolDoc pol = new PolDoc();
            pol.setWhetherDel(false);
            pol.setI(CodeUtils.convertToBase36(i2.incrementAndGet()));
            pol.setV(new ArrayList<>(100));
            for (int j = 0; j < 100; j++) {
                Grain grain = new Grain();
                grain.setF("6555d13fc9f2aa488fb49663");
                grain.setCv(1);
                grain.setHv(1);

                com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
                jsonObject.put("aaaaa", CodeUtils.generateRandomChinese(10));
                grain.setC(jsonObject);

                pol.getV().add(grain);
            }
            pols.add(pol);
        }
        PolDoc polDoc = pols.get(0);
        TimeInterval timeInterval = new TimeInterval();


        ObjectMapper objectMapper = new ObjectMapper();

        for (int i = 0; i < 2000; i++) {
            String s = objectMapper.writeValueAsString(polDoc);
            PolDoc polDoc1 = objectMapper.readValue(s, PolDoc.class);
        }
        log.info("序列化5000次耗时：{}", timeInterval.intervalRestart());

        Collection<PolDoc> newPols = mongoTemplate.insert(pols, PolDoc.class);
        log.info("pol 插入耗时：{}", timeInterval.intervalRestart());

        return newPols;
    }


}

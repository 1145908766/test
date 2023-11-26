package com.omni.mongotest;

import cn.hutool.core.date.TimeInterval;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.omni.mongotest.manager.ExecManager;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Pei
 * @date 2023/11/16 15:45
 **/
@AllArgsConstructor
@RestController
public class MongoController {

    private final ExecManager execManager;

    @GetMapping("/all")
    public String test2() throws JsonProcessingException {
        TimeInterval timeInterval = new TimeInterval();
        execManager.loadTest();
        return "执行完毕耗时：" + timeInterval.interval() + " \t (控制台查看详情)";
    }

    @GetMapping("/a")
    public String test3() throws JsonProcessingException {
        TimeInterval timeInterval = new TimeInterval();
        execManager.insertPolTest();
        return "执行完毕耗时：" + timeInterval.interval() + " \t (控制台查看详情)";
    }

    @GetMapping("/b")
    public String test4() {
        TimeInterval timeInterval = new TimeInterval();
        execManager.insertTextDoc();
        return "执行完毕耗时：" + timeInterval.interval() + " \t (控制台查看详情)";
    }

}

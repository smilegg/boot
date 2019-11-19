package top.djpzh.boot.kafka.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.djpzh.boot.kafka.model.TestModel;

import java.util.Date;

@Service
@Slf4j
public class KfkMsgSend {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(){
        TestModel model=new TestModel();
        model.setId(1123456L).setKfkMsg("test kfk msg").setSendTime(new Date());
        log.info("send kfk msg success...............");
        kafkaTemplate.send("test", JSON.toJSONString(model));
    }

}

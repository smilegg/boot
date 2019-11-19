package top.djpzh.boot.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class KfkMsgConsumer {

    @KafkaListener(topics = {"test"})
    public void kfkListener(ConsumerRecord<?,?> record){
        Optional<?> kfkMsg = Optional.ofNullable(record.value());
        if(kfkMsg.isPresent()){
            Object testMod = kfkMsg.get();
            log.info("consumer kfk msg success record..........{}",record);
            log.info("consumer kfk msg success testMod..........{}",testMod);
        }

    }

}

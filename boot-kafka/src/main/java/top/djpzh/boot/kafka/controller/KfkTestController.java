package top.djpzh.boot.kafka.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.djpzh.boot.kafka.service.KfkMsgSend;

@RestController
@RequestMapping("/kfk")
public class KfkTestController {

    @Autowired
    private KfkMsgSend kfkMsgSend;

    @GetMapping("/msg")
    public void test(){
        kfkMsgSend.send();
    }

}

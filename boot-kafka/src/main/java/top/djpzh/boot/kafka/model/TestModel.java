package top.djpzh.boot.kafka.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class TestModel {

    private Long id;

    private String kfkMsg;

    private Date sendTime;

}

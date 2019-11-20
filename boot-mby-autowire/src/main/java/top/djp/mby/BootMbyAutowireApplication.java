package top.djp.mby;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.djp.mby.mapper*")
public class BootMbyAutowireApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMbyAutowireApplication.class, args);
    }

}

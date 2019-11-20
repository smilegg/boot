package top.djpzh.atomic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

@PropertySource(value = {"classpath:test.properties"})
@ConfigurationProperties(prefix = "test")
@Configuration
public class TestProp {

    private List<Integer> var1;

    private Map<String,Object> var2;


    public List<Integer> getVar1() {
        return var1;
    }

    public void setVar1(List<Integer> var1) {
        this.var1 = var1;
    }

    public Map<String, Object> getVar2() {
        return var2;
    }

    public void setVar2(Map<String, Object> var2) {
        this.var2 = var2;
    }

}

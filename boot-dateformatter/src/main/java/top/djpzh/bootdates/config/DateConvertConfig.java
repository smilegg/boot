package top.djpzh.bootdates.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Configuration
public class DateConvertConfig {


    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @PostConstruct
    public void addConvert(){
        ConfigurableWebBindingInitializer configurableWebBindingInitializer= (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();

        if(Objects.nonNull(configurableWebBindingInitializer.getConversionService())){
            GenericConversionService genericConversionService= (GenericConversionService) configurableWebBindingInitializer.getConversionService();
            genericConversionService.addConverter(new StringDateConverter());
        }
    }


}

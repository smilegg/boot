package com.djp.boot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * swagger2 config
 */
@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.enable}")
public class SwaggerConfig {


    /**
     * swagger2 java bean configuration
     * @return
     */
    @Bean
    public Docket swaggerApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.djp.boot.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * 构建api文档的详细信息
     * @return
     */
    private ApiInfo apiInfo(){
          return new ApiInfoBuilder()
                  .title("彩票猫 swagger api 文档")
                  .contact(new Contact("lottery cat","http://www.baidu.com",""))
                  .version("1.0")
                  .description("api description")
                  .build();
    }

}

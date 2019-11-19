package com.djp.boot.controller;


import com.djp.boot.vo.TestVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@Api("某个模块接口说明")
public class TestController {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "test接口",notes = "测试一下test接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "测试id",paramType = "path",required = true ,dataType = "string"),
            /*@ApiImplicitParam(name = "token",value = "lotteryCatToken",paramType = "header",required = true ,dataType = "string")*/
    })
    @GetMapping("/swagger2/{id}")
    public Object test(@PathVariable Integer id, HttpServletRequest request){
        logger.info("id:{}",id);
        return "测试swagger2";
    }


    @ApiOperation(value = "test post请求接口",notes = "测试一下test post接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testVO",value = "post测试实体",required = true ,dataType = "TestVO"),
    })
    @PostMapping("/swagger2/query")
    public Object testPost(@RequestBody TestVO testVO, HttpServletRequest request){
        return testVO;
    }

}

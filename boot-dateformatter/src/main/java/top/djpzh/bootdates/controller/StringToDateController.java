package top.djpzh.bootdates.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.djpzh.bootdates.model.StringToDateModel;

import java.util.Date;

@RestController
@RequestMapping("/date")
public class StringToDateController {


    @GetMapping("/test")
    public Object getStrDate(){
        StringToDateModel stringToDateModel=new StringToDateModel();
        stringToDateModel.setDateStr(new Date());
        return stringToDateModel;
    }

    @PostMapping("/test2")
    public Object getDate(@RequestBody StringToDateModel stringToDateModel){
        return stringToDateModel;
    }

}

package com.lion.controllerability.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/10
 **/
@Controller
@RequestMapping("/api")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class Api {
    @GetMapping("/img")
    @ResponseBody
    public Map img(){
        Map map = new HashMap();
        ImageP imageP = new ImageP();
        imageP.setId("1");
        imageP.setUrl("https://inews.gtimg.com/newsapp_bt/0/11432640802/1000");
        map.put("status",true);
        map.put("messge",imageP);
        return map;
    }
}

package com.lion.controllerability;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/03
 **/
@Controller
public class DefaultController {
    @RequestMapping("/")
    public String index() {
        return "login";
    }
    @RequestMapping("/infoView")
    public String infoView(){
        return "infoView";
    }
}

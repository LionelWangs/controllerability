package com.lion.controllerability;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

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
}

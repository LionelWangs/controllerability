package com.lion.controllerability.check;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/03
 * 用于登录验证
 **/
@Controller
@RequestMapping("/index")
public class checkController {
    @RequestMapping("/checkVaildate")
    public ModelAndView checkVaildate(HttpServletRequest request){
    ModelAndView mv = new ModelAndView();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if ("admin".equals(name) && "123456".equals(password)){
            mv.setViewName("index");
        }
        else
            mv.setViewName("login");
        return mv ;

    }
}

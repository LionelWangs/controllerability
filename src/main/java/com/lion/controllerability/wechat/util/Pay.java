package com.lion.controllerability.wechat.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Pay {
    @RequestMapping("weChatPayController/pay")
    public ModelAndView index(String openId, String needPayMoney, String orderNo, String attach, String recordId, String payWay)
    {
        ModelAndView mv = new ModelAndView();
        mv.addObject("openId", openId);
        mv.addObject("needPayMoney", needPayMoney);
        mv.addObject("orderNo", orderNo);
        mv.addObject("attach", attach);
//        mv.addObject("recordId" , recordId);
        mv.addObject("payWay", payWay);
        mv.setViewName("pay");
        return mv;
    }
}
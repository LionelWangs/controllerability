package com.lion.controllerability.meterBase.controller;

import com.lion.controllerability.meterBase.data.Meterbase;
import com.lion.controllerability.meterBase.service.MeterBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/18
 **/
@Controller
@RequestMapping("/meter")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class MeterBaseController {
    @Autowired
    private MeterBaseService service ;

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("meter/addMeter");
        return mv ;
    }
/**
 * 绑定
 * */
    @RequestMapping("/bind")
    public ModelAndView bind() {
        ModelAndView mv = new ModelAndView("meter/bindMeter");
        return mv ;
    }

    @RequestMapping("/addMeter")
    public ModelAndView addMeter(Meterbase meterbase) {
        ModelAndView mv = new ModelAndView("meter/meterView");
        //设置测试使用账户
        meterbase.setTypeid("W0001");
        //设置初始流量
        service.add(meterbase);
        List<Meterbase> meterbases = service.selectAll();
        mv.addObject("meterBases",meterbases);
        return mv;
    }
/**
 * 查询所有表具
 * */
    @RequestMapping("/meterView")
    public ModelAndView meterView(){
        ModelAndView mv = new ModelAndView("meter/meterView");
        List<Meterbase> meterbases = service.selectAll();
        mv.addObject("meterBases",meterbases);
        return mv;
    }



}

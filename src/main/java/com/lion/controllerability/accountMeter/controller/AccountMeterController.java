package com.lion.controllerability.accountMeter.controller;

import com.lion.controllerability.accountMeter.data.Accountmeter;
import com.lion.controllerability.accountMeter.service.AccountMeterService;
import com.lion.controllerability.basisposition.data.Basisposition;
import com.lion.controllerability.basisposition.service.BasispositionService;
import com.lion.controllerability.meterBase.data.Meterbase;
import com.lion.controllerability.meterBase.service.MeterBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/29
 **/
@Controller
@RequestMapping("/accountMeter")
public class AccountMeterController {
    @Autowired
    private AccountMeterService service ;

    @Autowired
    private MeterBaseService meterBaseService;

    @Autowired
    private BasispositionService basispositionService;
    @RequestMapping("/meterBind")
    public ModelAndView meterBind(String positionid) {
        ModelAndView mv = new ModelAndView("accountMeter/accountMeterBind");
        Basisposition basisposition  = basispositionService.getByPositionId(Long.valueOf(positionid));
        Accountmeter accountmeter = basisposition.getAccountmeter();
        if (accountmeter.getMeterid() == null){
            mv.addObject("flag" , false);
        }
        else{
            mv.addObject("flag" , true);
        }
//        if ()
//        //默认设置为生活用水
//        String useTypeFlag = "生活";
//        //默认设置为正常状态
//        byte statusFlag = 1;
        List<Meterbase> meterbases = meterBaseService.selectAll();
        mv.addObject("adress",basisposition.getEstatebriefname()+basisposition.getPositionname());
        mv.addObject("basisposition",basisposition);
        mv.addObject("positionid",positionid);
        mv.addObject("meterbases",meterbases);
        return mv;
    }
}

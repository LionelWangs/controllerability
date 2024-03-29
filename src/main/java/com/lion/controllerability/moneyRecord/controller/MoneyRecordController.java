package com.lion.controllerability.moneyRecord.controller;

import com.github.pagehelper.PageHelper;
import com.lion.controllerability.basisposition.data.Basisposition;
import com.lion.controllerability.basisposition.service.BasispositionService;
import com.lion.controllerability.moneyRecord.data.Moneyrecord;
import com.lion.controllerability.moneyRecord.service.MoneyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/10
 * 账单记录
 **/
@Controller
@RequestMapping("/money")
public class MoneyRecordController {
    @Autowired
    private MoneyRecordService service;
    @Autowired
    private BasispositionService basispositionService ;
//    根据小区查询所有账单记录
    @RequestMapping("/selectAll")
    public ModelAndView selectAll(@RequestParam(value = "positionid") String positionid,String pageNum) {
        ModelAndView mv = new ModelAndView("money/moneyList");
        List<Moneyrecord> moneyrecords = new ArrayList<>();
        Basisposition info = basispositionService.getInfo(positionid);
        List<Basisposition> basispositions = basispositionService.selectHouse(info.getPositionno(), info.getDistrictcode());
        //        加入分页功能
        if (pageNum == null){
            pageNum = "1";
        }
        for (Basisposition b : basispositions){
            Integer page = Integer.valueOf(pageNum);
            PageHelper.startPage(page ,10);
            moneyrecords.addAll(service.selectByUnit(b.getPositionid()));
            mv.addObject("pageNum",page);
        }
        mv.addObject("moneyrecords",moneyrecords);
        mv.addObject("positionid",positionid);
        return mv ;
    }
}

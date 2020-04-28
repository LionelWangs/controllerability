package com.lion.controllerability.basisposition.controller;

import com.lion.controllerability.basisposition.data.Basisposition;
import com.lion.controllerability.basisposition.data.BasispositionConstant;
import com.lion.controllerability.basisposition.data.BasispositionExample;
import com.lion.controllerability.basisposition.service.BasispositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/05
 **/
@Controller
@RequestMapping("/basisposition")
public class BasispositionController {
    @Autowired
    private BasispositionService  basispositionService ;
    /**
     * 查询所有小区
     *
     * */
    @RequestMapping(value = "/selectAll" ,method = RequestMethod.GET)
    public ModelAndView selectAll () {
        ModelAndView mv = new ModelAndView("basisposition/basispositionView");
        List<Basisposition> basispositions = basispositionService.selectAll();
        for (Basisposition building : basispositions) {
            //判断是否为楼栋
            int conutBuilding = basispositionService.countBuilding(building.getPositionno(),building.getDistrictcode());
            building.setCount(conutBuilding);
        }
        mv.addObject("basisposition",basispositions);
        return mv ;
    }
    /**
     * 查询小区下的楼栋信息
     *
     * */
    @RequestMapping("/selectBuilding")
    public ModelAndView selectBuilding (String positionno,String districtcode) {
        ModelAndView mv = new ModelAndView("basisposition/buildingView");
        List<Basisposition> buliding = basispositionService.selectBuilding(positionno,districtcode);
        for (Basisposition unit : buliding){
            int conutUnit = basispositionService.countUnit(unit.getPositionno(),unit.getDistrictcode());
            unit.setCount(conutUnit);
        }
        mv.addObject("buliding",buliding);
        mv.addObject("positionno",positionno);
        mv.addObject("districtcode",districtcode);
        return mv ;
    }
    /**
     * 查询小区下单元信息
     * */
    @RequestMapping("/selectUnit")
    public ModelAndView selectUnit (String positionno,String districtcode) {
        ModelAndView mv = new ModelAndView("basisposition/unitView");
        List<Basisposition> unit = basispositionService.selectUntit( positionno,districtcode);
        for (Basisposition units : unit) {
            int counHouse = basispositionService.conutHouse(positionno,districtcode);
            units.setCount(counHouse);
        }
        mv.addObject("unit",unit);
        mv.addObject("positionno",positionno);
        mv.addObject("districtcode",districtcode);
        return mv ;
    }
    /**
     * 查询单元下住户信息
     * */
    @RequestMapping("/selectHouse")
    public ModelAndView selectHouse (String positionno,String districtcode) {
        ModelAndView mv = new ModelAndView("basisposition/houseView");
        List<Basisposition> house = basispositionService.selectHouse(positionno,districtcode);
        mv.addObject("house",house);
        mv.addObject("positionno",positionno);
        mv.addObject("districtcode",districtcode);
        return mv ;
    }

    /**
     * 新增小区楼栋单元住户信息
     * */
    @RequestMapping("/insert")
    public ModelAndView insert(Basisposition basisposition, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String positionno ;
        String districtcode ;
        //默认设置为活跃
        basisposition.setActiveflag(Byte.valueOf("1"));
        int count ;
        switch (String.valueOf(basisposition.getTypeflag())){

            //小区的情况
            case "1" : //E表示小区
                 count = basispositionService.countVillage();
                basisposition.setPositionno("E000"+(count+1));
                mv.setViewName("redirect:/basisposition/selectAll");
                break;
            case  "2": //B表示楼栋
                 positionno = request.getParameter("positionno");
                 districtcode = request.getParameter("districtcode");
                count = basispositionService.countBuilding(positionno, districtcode);
                basisposition.setPositionno(positionno);
                basisposition.setDistrictcode(districtcode);
                basisposition.setTypeflag(BasispositionConstant.BUILDING);
                basisposition.setPositionno(positionno+":B000"+(count+1));
                mv.setViewName("redirect:/basisposition/selectBuilding?positionno="+positionno+"&districtcode="+districtcode);
                break;
            case "3" : //P表示单元
                positionno = request.getParameter("positionno");
                districtcode = request.getParameter("districtcode");
                basisposition.setPositionno(positionno);
                basisposition.setDistrictcode(districtcode);
                basisposition.setTypeflag(BasispositionConstant.UNIT);
                count = basispositionService.countUnit(positionno,districtcode);
                basisposition.setPositionno(positionno+":U000"+(count+1));
                mv.setViewName("redirect:/basisposition/selectUnit?positionno="+positionno+"&districtcode="+districtcode);
                break;
            case  "4": //H表示房屋
                positionno = request.getParameter("positionno");
                districtcode = request.getParameter("districtcode");
                basisposition.setPositionno(positionno);
                basisposition.setDistrictcode(districtcode);
                basisposition.setTypeflag(BasispositionConstant.HOUSE);
                count = basispositionService.conutHouse(positionno,districtcode);
                basisposition.setPositionno(basisposition.getPositionno()+":H000"+(count+1));
                mv.setViewName("redirect:/basisposition/selectHouse?positionno="+positionno+"&districtcode="+districtcode);
                break;
        }
        int insert = basispositionService.insert(basisposition);
        mv.addObject("basisposition",basisposition);
        return mv ;
    }

    @RequestMapping(value = "/select" ,method = RequestMethod.GET)
    public ModelAndView select () {
        ModelAndView mv = new ModelAndView("money/moneyIndex");
        List<Basisposition> basispositions = basispositionService.selectAll();
        for (Basisposition building : basispositions) {
            //判断是否为楼栋
            int conutBuilding = basispositionService.countBuilding(building.getPositionno(),building.getDistrictcode());
            building.setCount(conutBuilding);
        }
        mv.addObject("basisposition",basispositions);
        return mv ;
    }


}



















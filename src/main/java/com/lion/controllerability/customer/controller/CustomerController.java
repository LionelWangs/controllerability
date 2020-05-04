package com.lion.controllerability.customer.controller;

import com.github.pagehelper.PageHelper;
import com.lion.controllerability.basisposition.data.Basisposition;
import com.lion.controllerability.basisposition.service.BasispositionService;
import com.lion.controllerability.customer.data.Customerbase;
import com.lion.controllerability.customer.data.CustomerbaseExample;
import com.lion.controllerability.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/03
 **/
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService ;
    @Autowired
    private BasispositionService basispositionService ;
    @RequestMapping("/customerInfo")
    public String customerInfo(){
        return "customer/customerInfo";
    }

    /**
     * 根据用户id查询用户
     * */
    @RequestMapping("/select")
    public ModelAndView select(Long id){
        ModelAndView mv = new ModelAndView("customer/customerSelect");
        Customerbase customerbase = customerService.selectCustomerByCustomerId(id);
        System.out.println(customerbase);
        mv.addObject("customer",customerbase);
        return mv ;
    }

    /**
     * 查询所有用户
     * */
    @RequestMapping("/customerList")
    public ModelAndView customerList(@RequestParam(required = false) String pageNum) {
        ModelAndView mv = new ModelAndView("customer/customerView");
//        加入分页功能
        if (pageNum == null){
            pageNum = "1";
        }
        Integer page = Integer.valueOf(pageNum);
        PageHelper.startPage(page ,10);
        List<Customerbase> customerbases = customerService.selectAllCustomer();
        mv.addObject("pageNum",page);
        mv.addObject("customerList",customerbases);
        return mv ;
    }

    /**
     * 更新用户信息
     * */
    @RequestMapping("/updata")
    public ModelAndView update(@RequestParam Customerbase customerbase) {
        ModelAndView mv = new ModelAndView("customerUpdate");
        CustomerbaseExample example = new CustomerbaseExample();
        CustomerbaseExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo("1865527294");
        customerService.update(customerbase,example);
        return mv;
    }

    /**
     * 插入用户信息
     *
     * */
    @RequestMapping("/insert")
    public ModelAndView insert(Customerbase customerbase, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        request.getParameter("positionname");
        //设置用户默认为正常状态
        customerbase.setStatusflag(Byte.valueOf("1"));
        //默认设置为未绑定住户
        customerbase.setTypeflag(Byte.valueOf("1"));
        int result = customerService.insert(customerbase);
        if (result > 0 ){
            mv.setViewName("redirect:customer/customerList");
        }
        return mv ;
    }

    /**
     * 删除用户信息 根据用户id
     * */
    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam String customerid) {
        ModelAndView mv = new ModelAndView();
        int result = customerService.delete(Long.valueOf(customerid));
        if (result > 0) {
            mv.setViewName("redirect:/customer/customerList");
        }
        return mv;
    }
    /**
     * 用户管理
     * */
    @RequestMapping("/imgIndex")
    public ModelAndView selectCustomer() {
        ModelAndView mv = new ModelAndView("customer/imgIndex");
        //查询所有用户
        List<Customerbase> customerbases = customerService.selectAllCustomer();
//        查询所有小区
        List<Basisposition> basispositions = basispositionService.selectAll();

        mv.addObject("customerList",customerbases);
        mv.addObject("basispositions",basispositions);
        return mv ;

    }

}

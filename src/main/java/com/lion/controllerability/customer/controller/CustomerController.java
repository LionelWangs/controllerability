package com.lion.controllerability.customer.controller;

import com.github.pagehelper.PageHelper;
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
    /**
     * 根据用户id查询用户
     * */
    @RequestMapping("/queryCustomer")
    public ModelAndView queryCustomer(Long id){
        id= Long.valueOf(2);
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
    public ModelAndView queryAllCustomer(@RequestParam(required = false) String pageNum) {
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
    @RequestMapping("/updataCustomer")
    public ModelAndView updateCustomer(@RequestParam Customerbase customerbase) {
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
    @RequestMapping("/insertCustomer")
    public ModelAndView insertCustomer(Customerbase customerbase) {
        ModelAndView mv = new ModelAndView();
        //设置用户默认为正常状态
        customerbase.setStatusflag(Byte.valueOf("1"));
        int result = customerService.insert(customerbase);
        if (result > 0 ){
            mv.setViewName("redirect:/customer/customerList");
        }
        return mv ;
    }

    /**
     * 删除用户信息 根据用户id
     * */
    @RequestMapping("/deleteCustomer")
    public ModelAndView delete(@RequestParam String id) {
        ModelAndView mv = new ModelAndView();
        int result = customerService.delete(Long.valueOf(id));
        if (result > 0) {
            mv.setViewName("redirect:/customer/customerList");
        }
        return mv;
    }


}

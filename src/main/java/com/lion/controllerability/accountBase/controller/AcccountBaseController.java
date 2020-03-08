package com.lion.controllerability.accountBase.controller;

import com.lion.controllerability.accountBase.data.Accountbase;
import com.lion.controllerability.accountBase.service.AccountBaseService;
import com.lion.controllerability.accountCustomer.data.Accountcustomer;
import com.lion.controllerability.accountCustomer.service.AccountCustomerService;
import com.lion.controllerability.basisposition.data.Basisposition;
import com.lion.controllerability.basisposition.service.BasispositionService;
import com.lion.controllerability.customer.data.Customerbase;
import com.lion.controllerability.customer.data.CustomerbaseExample;
import com.lion.controllerability.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/08
 **/
@Controller
@RequestMapping("/accountBase")
public class AcccountBaseController {
    @Autowired
    private AccountBaseService service;
    @Autowired
    private BasispositionService basispositionService ;
    @Autowired
    private CustomerService customerService ;
    @Autowired
    private AccountCustomerService accountCustomerService;

    @RequestMapping("/insert")
    public ModelAndView insert(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/index");
        Date date = new Date();
        String customerld = request.getParameter("customerld");
        String positionId = request.getParameter("positionId");
        Basisposition info = basispositionService.getInfo(positionId);
        Customerbase customerbase = customerService.selectCustomerByCustomerId(Long.valueOf(customerld));
        int result = service.insert(info,customerld);
        if (result != 0) {
            Accountcustomer accountcustomer = new Accountcustomer();
            accountcustomer.setAccountid(Long.valueOf(customerld));
            accountcustomer.setActiveflag(Byte.valueOf("1"));
            accountcustomer.setCustomerid(Long.valueOf(customerld));
            accountcustomer.setBindtime(date);
            accountCustomerService.insert(accountcustomer);
            info.setActiveflag(Byte.valueOf("2"));
            basispositionService.update(info);
            customerbase.setTypeflag(Byte.valueOf("2"));
            customerService.update(customerbase);
        }
        return mv ;

    }

    @RequestMapping("/choosePalace")
    public ModelAndView choosePalace(String positionId) {
        ModelAndView mv = new ModelAndView("/accountBase/accountBaseView");
        Basisposition basisposition = basispositionService.selectHouse(positionId);
        //所有未开户的用户
        Customerbase customerbase = new Customerbase();
        CustomerbaseExample example = new CustomerbaseExample();
        CustomerbaseExample.Criteria criteria = example.createCriteria();
        criteria.andTypeflagEqualTo(Byte.valueOf("1"));
        List<Customerbase> customerbases = customerService.selectCustomer(customerbase, example);
        mv.addObject("basisposition",basisposition);
        mv.addObject("customerbases",customerbases);
        return mv ;
    }

}

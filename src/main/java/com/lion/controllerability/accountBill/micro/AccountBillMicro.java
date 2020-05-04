package com.lion.controllerability.accountBill.micro;

import com.lion.controllerability.accountBase.data.Accountbase;
import com.lion.controllerability.accountBase.mapper.AccountbaseMapper;
import com.lion.controllerability.accountBill.data.AccountBillConstant;
import com.lion.controllerability.accountBill.data.Accountbill;
import com.lion.controllerability.accountBill.mapper.AccountbillMapper;
import com.lion.controllerability.accountBill.service.AccountBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Author wang.hongyu
 * @Version V1.02020/05/01
 **/
@ApiIgnore
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/accountBillMicro")
public class AccountBillMicro {
    @Autowired
    private AccountBillService service;
    @Autowired
    private AccountbaseMapper accountbaseMapper ;
    @RequestMapping("/insertBill")
    public void insertBill() {
        List<Accountbase> accountbases = accountbaseMapper.selectAll();
        for (Accountbase accountbase : accountbases) {
            service.creatCharge(accountbase.getAccountid(),accountbase.getPositionid());
        }
    }
    /**
     * 根据用户customerId查询账单
     * 查询指定月份账单
     * */
    @RequestMapping("/selectCharge")
    public Map selectCharge(Long customerId,String yearMonth){
        Map map = new HashMap();
        List accountList = new ArrayList();
        List<Accountbill> accountbills = service.selectChargeByCustomerId(customerId,yearMonth);
        for (Accountbill accountbill : accountbills) {
            accountbill.getYearmonth();
            accountbill.getShouldamount();
            accountbill.getYearmonth();
            accountbill.getCustomerbase().getCustomername();
            accountList.add(accountbill);
        }
        map.put("accountList",accountList);
        return map;
    }
    /**
     * 查询所有月份账单
     * */
    @RequestMapping("/selectAllCharge")
    public Map selectAllCharge(Long customerId) {
        Map map = new HashMap();

        List month = new ArrayList();
        List<Accountbill> accountbills = service.selectAllCharge(customerId);
        for (Accountbill accountbill :accountbills){
            String yearmonth = accountbill.getYearmonth();
            String strh = yearmonth.substring(yearmonth.length() -2,yearmonth.length());
            month.add(Integer.valueOf(strh));
            map.put("adress",accountbill.getBasisposition().getEstatebriefname() + "" + accountbill.getBasisposition().getPositionname());
        }
        map.put("month",month);
        map.put("accountbills",accountbills);
        return map;
    }
}

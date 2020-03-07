package com.lion.controllerability.accountCustomer.service;

import com.lion.controllerability.accountCustomer.data.Accountcustomer;
import com.lion.controllerability.accountCustomer.mapper.AccountcustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/03
 **/
@Service
public class AccountCustomerService {
    @Resource
    private AccountcustomerMapper mapper ;

    public int insert(Accountcustomer accountcustomer) {
        try {
            int result = mapper.insert(accountcustomer);
            return result ;
        } catch (Exception e){
            e.printStackTrace();
        }

        return 0;

    }

    public void select(){
        Accountcustomer accountcustomer = mapper.selectByPrimaryKey(Long.valueOf(1));
    }

}

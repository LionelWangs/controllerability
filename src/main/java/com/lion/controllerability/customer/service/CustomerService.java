package com.lion.controllerability.customer.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lion.controllerability.accountCustomer.data.Accountcustomer;
import com.lion.controllerability.accountCustomer.mapper.AccountcustomerMapper;
import com.lion.controllerability.accountCustomer.service.AccountCustomerService;
import com.lion.controllerability.customer.data.Customerbase;
import com.lion.controllerability.customer.data.CustomerbaseExample;
import com.lion.controllerability.customer.mapper.CustomerbaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/03
 **/
@Service
public class CustomerService {
    @Resource
    private CustomerbaseMapper mapper ;
    @Autowired
    private AccountCustomerService accountCustomerService ;
    public  Customerbase selectCustomerByCustomerId(Long id) {
        Customerbase customerbase = mapper.selectByPrimaryKey(id);
        return customerbase;

    }

    public List<Customerbase> selectCustomer(Customerbase customerbase , CustomerbaseExample example) {
        List<Customerbase> customerbases = mapper.selectByExample(example);
        return customerbases ;
    }
    /**
     * 查询全部用户信息
     * */
    public List<Customerbase> selectAllCustomer() {
        CustomerbaseExample customerExample = new CustomerbaseExample();
        CustomerbaseExample.Criteria criteria = customerExample.createCriteria();
        criteria.andIdnoIsNotNull();
        criteria.andMobileIsNotNull();
        List<Customerbase> customerbases = mapper.selectByExample(customerExample);
        return customerbases;
    }


    /**
     * 根据id跟用户信息
     *
     * */
    public int update(Customerbase customerbase,CustomerbaseExample customerExample) {
        int result = mapper.updateByExample(customerbase, customerExample);
        return result ;

    }

    /**
     * 跟新信息
     * */
    public void update(Customerbase customerbase) {
        mapper.updateByPrimaryKey(customerbase);
    }

    /**
     * 删除用户信息 根据id
     * */
    public int delete(Long id) {
        int result = mapper.deleteByPrimaryKey(id);
        return result;

    }

    /**
     * 新增用户信息
     * */

    public int insert(Customerbase customerbase) {
        Date date = new Date();
        customerbase.setRegisterdate(date);
        int result = mapper.insert(customerbase);
        return result ;
    }
}

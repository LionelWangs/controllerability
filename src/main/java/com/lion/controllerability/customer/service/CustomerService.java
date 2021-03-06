package com.lion.controllerability.customer.service;

import com.lion.controllerability.accountCustomer.service.AccountCustomerService;
import com.lion.controllerability.customer.data.Customerbase;
import com.lion.controllerability.customer.data.CustomerbaseExample;
import com.lion.controllerability.customer.mapper.CustomerbaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        List<Customerbase> customerbases = mapper.selectAllCustomer();
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
     * 根据openId查询
     * */
    public Customerbase isBind(String openId){
        Customerbase bind = mapper.isBind(openId);
        return bind;
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
    /**
     * 根据用户手机号查询
     * */
    public Customerbase selectByMobile(String mobile) {
        Customerbase customerbase = mapper.selectByMobile(mobile);
        return customerbase;
    }
    /**
     * 根据用户id查询所有有用信息
     * */
    public Customerbase getInfo(Long customerId){
        Customerbase customerbase = mapper.getInfo(customerId);
        return customerbase ;
    }
//    /**
//     * 根据手机号码查询账单
//     * */
//    public Customerbase charge(String mobile) {
//
//    }
}

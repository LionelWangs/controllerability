package com.lion.controllerability.customer.micro;

import com.lion.controllerability.customer.data.Customerbase;
import com.lion.controllerability.customer.data.CustomerbaseExample;
import com.lion.controllerability.customer.mapper.CustomerbaseMapper;
import com.lion.controllerability.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wang.hongyu
 * @Version V1.02020/04/16
 * 用户类接口
 **/
@ApiIgnore
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/customerMicro")
public class CustomerMicro {
    @Autowired
    private CustomerbaseMapper mapper;
    /**
     * 判断是否已存在手机号
     * */
    @RequestMapping("/check")
    public Map check(String mobile) {
        Map map = new HashMap();
        Customerbase customerbase = mapper.selectByMobile(mobile);
        if (customerbase != null) {
            //查询到已存在
            map.put("status",100);
            return map;
        }
        else
            map.put("status",500);
        return map;

    }
}

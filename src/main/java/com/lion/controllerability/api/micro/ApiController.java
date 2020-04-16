package com.lion.controllerability.api.micro;

import com.lion.controllerability.accountBase.data.Accountbase;
import com.lion.controllerability.accountBase.service.AccountBaseService;
import com.lion.controllerability.basisposition.data.Basisposition;
import com.lion.controllerability.basisposition.service.BasispositionService;
import com.lion.controllerability.customer.data.Customerbase;
import com.lion.controllerability.customer.data.Info;
import com.lion.controllerability.customer.service.CustomerService;
import com.lion.controllerability.meterBase.data.Meterbase;
import com.lion.controllerability.meterBase.service.MeterBaseService;
import com.lion.controllerability.moneyRecord.data.Moneyrecord;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/12
 **/
@ApiIgnore
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountBaseService accountBaseService;
    @Autowired
    private MeterBaseService meterBaseService;
    @Autowired
    private BasispositionService basispositionService ;

    private static final Logger log = Logger.getLogger(ApiController.class);

    @PostMapping("/sendValidationCode")
    public Map sendValidationCode(String mobile) {
        Map map = new HashMap();
        if (mobile != null) {
            log.info("xxxxx");
            map.put("statusCode",100);
        }
        return map ;
    }
    /**
     * 绑定用户信息
     * */
    @PostMapping("/bind")
    public Map bind(String mobile ,String code,String openId) {
        Map map = new HashMap();
        Customerbase customerbase = customerService.selectByMobile(mobile);
        if (customerbase != null && code.equals("123456")) {
            customerbase.setOpenid(openId);
            customerService.update(customerbase);
            map.put("statusCode" ,100);
            map.put("customerId",customerbase.getCustomerld());
            map.put("customerName",customerbase.getCustomername());
            //表示绑定成功
            return map;
        }

        map.put("statusCode",502);//绑定失败
        return map;
    }
    /**
     * 查询所有信息
     * */
    @GetMapping("/list")
    public Map list(String customerId) {
        Map map = new HashMap();
        if (customerId == null ) {
            map.put("message","用户id为null");
        }
        Accountbase account = new Accountbase();
        Customerbase customer = new Customerbase();
        customer.setCustomerld(Long.valueOf(customerId));
        List<Accountbase> accountList = new ArrayList<>();
        Accountbase accountbase = accountBaseService.selectByCustomerId(Long.valueOf(customerId));
        map.put("accountbase",accountbase);
        return map;
    }
    /**
     * 查询户主信息
     * */
    @GetMapping("/getInfo")
    public Map  getInfo(String customerId) {
        Map map = new HashMap();
        Info info = new Info();
        Customerbase customerbase = customerService.getInfo(Long.valueOf(customerId));
        info.setMobile(customerbase.getMobile());
        info.setRegisterdate(customerbase.getRegisterdate());
        info.setAccountType(customerbase.getAccountbase().getAccounttypecode());
        info.setAdress(customerbase.getBasisposition().getEstatebriefname()+customerbase.getBasisposition().getPositionname());
        info.setCustomreName(customerbase.getCustomername());
        info.setIdNo(customerbase.getIdno());
        info.setLeftMoney(customerbase.getAccountbase().getLeftmoney());
        map.put("info",info);
        return map ;
    }

    @RequestMapping("/isExit")
    public Map isExit(Meterbase meterbase) {
        Map map = new HashMap();
        if (meterBaseService.isExit(meterbase.getImei())) {
            map.put("result",true);
            return map;
        }
        map.put("result" , false);
        return map;
    }

    @RequestMapping("/chooseMeter")
    public Map chooseMeter(){
        Map map = new HashMap();
        List<Meterbase> meterbases = meterBaseService.selectAll();
        map.put("meterbases",meterbases);
        return map;
    }

}

package com.lion.controllerability.moneyRecord.micro;

import com.lion.controllerability.accountBase.data.Accountbase;
import com.lion.controllerability.accountBase.data.AccountbaseExample;
import com.lion.controllerability.accountBase.mapper.AccountbaseMapper;
import com.lion.controllerability.accountMeter.data.AccountmeterExample;
import com.lion.controllerability.moneyRecord.data.Moneyrecord;
import com.lion.controllerability.moneyRecord.mapper.MoneyrecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author wang.hongyu
 * @Version V1.02020/04/28
 **/
@ApiIgnore
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/moneyRecordMicro")
public class MoneyRecordMicro {
    @Autowired
    private AccountbaseMapper accountbaseMapper ;
    @Autowired
    private MoneyrecordMapper moneyrecordMapper ;

//    批量生成账单接口
    @RequestMapping("/addMoneyRecord")
    public void addMoneyRecord() {
        AccountbaseExample example = new AccountbaseExample();
        Date date = new Date();
        AccountbaseExample.Criteria criteria = example.createCriteria();
//        查询所有用户批量生成账单
        criteria.andAccountidIsNotNull();
        List<Accountbase> accountbases = accountbaseMapper.selectByExample(example);
        for (Accountbase accountbase : accountbases) {
            Moneyrecord moneyrecord = new Moneyrecord();
            Random r = new Random(100);
            moneyrecord.setRecordid(null);
            moneyrecord.setAccountid(accountbase.getAccountid());
            moneyrecord.setPositionid(accountbase.getPositionid());
            moneyrecord.setChangeamount(Long.valueOf(r.nextInt()));
            moneyrecord.setAfteramount(Long.valueOf(r.nextInt()));
            moneyrecord.setRecordtime(date);
            moneyrecordMapper.insert(moneyrecord);
        }

    }
}

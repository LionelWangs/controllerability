package com.lion.controllerability.accountBase.service;

import com.lion.controllerability.accountBase.data.Accountbase;
import com.lion.controllerability.accountBase.data.AccountbaseExample;
import com.lion.controllerability.accountBase.mapper.AccountbaseMapper;
import com.lion.controllerability.basisposition.data.Basisposition;
import com.lion.controllerability.basisposition.data.BasispositionExample;
import com.lion.controllerability.basisposition.mapper.BasispositionMapper;
import com.lion.controllerability.basisposition.service.BasispositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/08
 **/
@Service
public class AccountBaseService {
    @Autowired
    private AccountbaseMapper mapper ;
    @Autowired
    private BasispositionMapper basispositionMapper ;

    /**
     * 绑定用户住户信息
     * */
    public int insert(Basisposition basisposition,String customerId) {
        Accountbase accountbase = new Accountbase();
        Date date = new Date();
        int accountNo = (int) ((Math.random()*9+1) * 10000000);
        while (isExit(String.valueOf(accountNo))) {
            accountNo = (int) ((Math.random()*9+1) * 10000000);
        }
        accountbase.setAccountid(Long.valueOf(customerId));
        accountbase.setAccountno(String.valueOf(accountNo));
        accountbase.setPositionid(basisposition.getPositionid());
        accountbase.setPositiontypeflag(basisposition.getTypeflag());
        accountbase.setCreatetime(date);
        accountbase.setActiveflag(Byte.valueOf("1"));
        accountbase.setLeftmoney(Long.valueOf("0"));
        accountbase.setAccounttypecode("w");
        int result = mapper.insert(accountbase);
        return result;
    }

    /**
     * 判断账户是否已经存在
     * */
    public boolean isExit(String accountNo) {
        AccountbaseExample example = new AccountbaseExample();
        AccountbaseExample.Criteria criteria = example.createCriteria();
        criteria.andAccountnoEqualTo(accountNo);
        List<Accountbase> accountbaseList = mapper.selectByExample(example);
        if (accountbaseList.size() != 0){
            return true;
        }
        return false;
    }

    /**
     * 根据Posionid进行查询
     * */
    public Accountbase query(String posionid) {
        Accountbase accountbase = mapper.selectByPosionId(Long.valueOf(posionid));
        return accountbase;
    }
    /**
     * 根据customerId查询
     * */
    public Accountbase selectByCustomerId(Long customerId) {
        Accountbase accountbase = mapper.selectByCustomerId(customerId);
        return accountbase;
    }

}

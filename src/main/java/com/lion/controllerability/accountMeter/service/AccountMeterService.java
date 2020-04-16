package com.lion.controllerability.accountMeter.service;

import com.lion.controllerability.accountBase.mapper.AccountbaseMapper;
import com.lion.controllerability.accountMeter.data.Accountmeter;
import com.lion.controllerability.accountMeter.data.AccountmeterExample;
import com.lion.controllerability.accountMeter.mapper.AccountmeterMapper;
import com.lion.controllerability.basisposition.data.Basisposition;
import com.lion.controllerability.basisposition.service.BasispositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/29
 **/
@Service
public class AccountMeterService {
    @Autowired
    private AccountmeterMapper mapper ;

    /**
     *
     *  新建表具
     *
     * */
    public int insert(Accountmeter accountmeter) {
        return mapper.insert(accountmeter);
    }


}

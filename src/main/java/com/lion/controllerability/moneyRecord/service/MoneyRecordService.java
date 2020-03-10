package com.lion.controllerability.moneyRecord.service;

import com.lion.controllerability.moneyRecord.data.Moneyrecord;
import com.lion.controllerability.moneyRecord.data.MoneyrecordExample;
import com.lion.controllerability.moneyRecord.mapper.MoneyrecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/10
 **/
@Service
public class MoneyRecordService {
    @Autowired
    private MoneyrecordMapper mapper ;

    /**
     * 根据小区查询账单
     * */
    public List<Moneyrecord> selectByUnit(Long positionId) {
        List<Moneyrecord> moneyrecords = mapper.selectAllByPositionId(positionId);
        return moneyrecords;
    }
}

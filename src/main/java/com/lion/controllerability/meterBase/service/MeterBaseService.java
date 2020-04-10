package com.lion.controllerability.meterBase.service;

import com.lion.controllerability.meterBase.data.Meterbase;
import com.lion.controllerability.meterBase.data.MeterbaseExample;
import com.lion.controllerability.meterBase.mapper.MeterbaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/18
 **/
@Service
public class MeterBaseService {
    @Autowired
    private MeterbaseMapper mapper ;
    /**
     * 新增表具
     *
     * */
    public int add(Meterbase meterbase) {
        int result = mapper.insert(meterbase);
        return result ;
    }
    /**
     * 查询所有表具 判断水表类型
     * */
    public List<Meterbase> selectAll(){
        MeterbaseExample example = new MeterbaseExample();
        MeterbaseExample.Criteria criteria = example.createCriteria();
        criteria.andMeteridIsNotNull();
//    //        判断水表状态
//        criteria.andStatusflagEqualTo(statusFlag);
//        criteria.andUsetypecodeEqualTo(useTyepCode);
        List<Meterbase> meterbases = mapper.selectByExample(example);
        return  meterbases;
    }
    /**
     * 判断表具是否已经存在
     * */
    public boolean isExit(String imei) {
        MeterbaseExample example = new MeterbaseExample();
        MeterbaseExample.Criteria criteria = example.createCriteria();
        criteria.andImeiEqualTo(imei);
        List<Meterbase> meterbases = mapper.selectByExample(example);
        if (meterbases.size() == 0) {
            return true;
        }
        return false;
    }
    /**
     * 选择表具
     *
     * */
    public List<Meterbase> seleAllMeter(){
        MeterbaseExample example = new MeterbaseExample();
        MeterbaseExample.Criteria criteria = example.createCriteria();
        criteria.andMeteridIsNotNull();
        //绑定前流量为空
        criteria.andVolumeIsNull();
        List<Meterbase> meterbases = mapper.selectByExample(example);
        return meterbases;
    }
}

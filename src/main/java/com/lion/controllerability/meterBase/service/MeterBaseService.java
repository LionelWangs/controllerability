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
        criteria.andVolumeIsNull();
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
        Meterbase meterbase = mapper.selectByImei(imei);
        if (meterbase != null) {
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
    /**
     * 修改表具绑定
     * */
    public void update(Long meterId){
        Meterbase meterbase = mapper.selectByPrimaryKey(meterId);
        //修改初始流量 初始流量不为null为绑定
        meterbase.setVolume(0.01);
        mapper.updateByPrimaryKey(meterbase);
    }
    /**
     * 通过imei查询meterID
     * */
    public Meterbase selectByImei(String imei){
        Meterbase meterbase = mapper.selectByImei(imei);
        return meterbase;
    }
    /**
     * 根据meterID查询
     * */
    public Meterbase selectByMeterId(Long meterId) {
       return mapper.selectByPrimaryKey(meterId);
    }
}

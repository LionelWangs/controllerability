package com.lion.controllerability.accountBill.service;

import com.lion.controllerability.accountBase.mapper.AccountbaseMapper;
import com.lion.controllerability.accountBill.data.AccountBillConstant;
import com.lion.controllerability.accountBill.data.Accountbill;
import com.lion.controllerability.accountBill.data.AccountbillExample;
import com.lion.controllerability.accountBill.mapper.AccountbillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author wang.hongyu
 * @Version V1.02020/05/01
 **/
@Service
public class AccountBillService {
    @Autowired
    private  AccountbillMapper mapper;
    /**
     * 账单生成
     * */
    public int creatCharge(Long accountId ,Long positionId) {
        //判断是否存在账单
        if (isExitCharge(accountId,positionId)){
            return 0;
        }


        Accountbill bill = new Accountbill();
        Random random = new Random();//默认构造方法
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH )+1;
        bill.setAccountid(accountId);
        bill.setPositionid(positionId);
        //设置期初流量 默认为0.00
        bill.setBeginvolume(0.00);
        DecimalFormat df = new DecimalFormat("#.00");
        double endVolume = Double.parseDouble(df.format(random.nextDouble() * 20));
        bill.setEndvolume(endVolume);
        //判断阶梯用水价格 计算账单
//            阶梯用水量小于1立方
        long fee = 0 ;
        if (endVolume < 16){
            //小于 1是直接进行运算
           double money =  endVolume * AccountBillConstant.FristWater;
           bill.setBillamount(new Double(money * 100).longValue());
           bill.setShouldamount(new Double(money * 100).longValue());
        }
        //二阶用水
        if ( 16.00 <= endVolume && endVolume <= 24.00 ) {
            //一节用水
            double v1 = 16.00;
            double moneyV1 =  v1 * AccountBillConstant.FristWater;
            //二阶用水
            double v2 = endVolume - v1;
            double moneyV2 =  v2 * AccountBillConstant.SecondWater;
            bill.setBillamount(new Double((moneyV1 + moneyV2) * 100).longValue());
            bill.setShouldamount(new Double((moneyV1 + moneyV2) * 100).longValue());
        }
        //三阶用水
        if (endVolume > 24.00) {
            double v1 = 16.00;
            double moneyV1 =  v1 * AccountBillConstant.FristWater;
            double v2 = 8.00;
            double moneyV2 =  v2 * AccountBillConstant.SecondWater;
            double v3 = endVolume - (v1+v2);
            double moneyV3 =  v3 * AccountBillConstant.ThirdWater;
            bill.setBillamount(new Double((moneyV1 + moneyV2 + moneyV3) * 100).longValue());
            bill.setShouldamount(new Double((moneyV1 + moneyV2 + moneyV3) * 100).longValue());
        }
        if (month < 10) {
            bill.setYearmonth(year+"0"+month);
        }
        else
        bill.setYearmonth(year+""+month);
        bill.setSettlementdate(date);
        return mapper.insert(bill);
    }
    /**
     * 判断是否已经存在账单
     * */
    public  boolean isExitCharge(Long accountId ,Long positionId) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH )+1;
        AccountbillExample example = new AccountbillExample();
        AccountbillExample.Criteria criteria = example.createCriteria();
        criteria.andYearmonthEqualTo(year+""+month);
        criteria.andAccountidEqualTo(accountId);
        criteria.andPositionidEqualTo(positionId);
        List<Accountbill> accountbills = mapper.selectByExample(example);
        if (accountbills.size() == 0) {
            return false;
        }
        return true;

    }
    /**
     * 根据customerId查询账单
     * */
    public List<Accountbill> selectChargeByCustomerId(Long customerId,String yearMonth) {
        List<Accountbill> accountbills = mapper.selectChargeByCustomerId(customerId,yearMonth);
        return accountbills;
    }
    /**
     * 根据customerId查询账单
     * */
    public List<Accountbill> selectAllCharge(Long customerId) {
        List<Accountbill> accountbills = mapper.selectAllCharge(customerId);
        return accountbills;
    }
}

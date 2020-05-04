package com.lion.controllerability.accountBill.data;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author wang.hongyu
 * @Version V1.02020/05/01
 **/
//阶梯用水价格
public class AccountBillConstant {
    public final static double FristWater = 2.56;
    public final static double SecondWater = 3.2;
    public final static double ThirdWater = 3.84;
}

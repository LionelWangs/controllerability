package com.lion.controllerability.accountBill;

import com.lion.controllerability.accountBill.data.AccountBillConstant;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * @Author wang.hongyu
 * @Version V1.02020/05/01
 **/
public class Test {
    @org.junit.Test
    public void test(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH )+1;
        System.out.println(year+""+month);

    }
}

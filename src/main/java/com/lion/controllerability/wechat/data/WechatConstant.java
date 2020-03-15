package com.lion.controllerability.wechat.data;

/**
 * @ClassName WechatConstant
 * @Author wang.hongyu
 * @Date 2019-12-30
 * @Version V1.0
 **/
public class WechatConstant {
    /**
     * 1.待支付
     * */
    public static final Integer STATUS_FLAG_PAID = 1;

    /**
     * 2.支付成功
     * */
    public static final Integer STATUS_FLAG_SUCCESS = 2;

    /**
     * 3.支付失败
     * */
    public static final Integer STATUS_FLAG_FAIL = 3;

    /**
     * 1.银联
     * */
    public static final Integer TypeFlag_UnionPay = 1;
    /**
     * 2.支付宝
     * */
    public static final Integer TypeFlag_AILIPAY= 2;
    /**
     * 3.微信
     * */
    public static final Integer TypeFlag_WECHATPAY = 3;

}

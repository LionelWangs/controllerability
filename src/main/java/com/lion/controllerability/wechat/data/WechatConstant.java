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

    public static final String TOKEN = "why";

    public static final String APPKEY = "8a698a7a6882b88e102ec0b11613bf57";

    public static final String APPID = "wx1b075537c82c1bca";

    public static final String APPSERCET = "5e4ae6319350b519a76240c0aaf8c35e";

    public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSERCET;

}

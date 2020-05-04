package com.lion.controllerability.wechat.data;

import com.lion.controllerability.wechat.service.WxService;
import com.lion.controllerability.wechat.util.Util;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author wang.hongyu
 * @Version V1.02020/05/04
 **/
public class TemplateMessage {

    /**
     * 设置行业
     */
    @Test
    public void set() {
        String at = WxService.getAccessToken();
        String url="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+at;
        String data="{\n" +
                "          \"industry_id1\":\"1\",\n" +
                "          \"industry_id2\":\"4\"\n" +
                "       }";
        String result = Util.post(url, data);
        System.out.println(result);
    }

    /**
     * 获取行业
     */
    @Test
    public void get() {
        String at = WxService.getAccessToken();
        String url="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token="+at;
        String result = Util.get(url);
        System.out.println(result);
    }

    /**
     * 发送模板消息
     * by 罗召勇 Q群193557337
     */
    @Test
    public void sendTemplateMessage() {
        String openId = "obPTLvmdvuoRyZ56uCzV8y5Ze0Z4";
        String money = "100";
        String at = WxService.getAccessToken();
        Date date = new Date();
        String str = "yyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+at;
        String data="{\n" +
                "           \"touser\":\""+openId+"\",\n" +
                "           \"template_id\":\"GKarWzEM9kQMy_b2TtljWuie5y20IV8kq_9ngXl5Mdg\",         \n" +
                "           \"url\":\"http://www.baidu.com\",         \n" +

                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"您有一条新的信息！\",\n" +
                "                       \"color\":\"#00CD00\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"马鞍山水务\",\n" +
                "                       \"color\":\"#fff000\"\n" +
                "                   },\n" +
                "                   \"keyword2\": {\n" +
                "                       \"value\":\""+String.valueOf(sdf.format(date))+"\",\n" +
                "                       \"color\":\"#1f1f1f\"\n" +
                "                   },\n" +
                "                   \"keyword3\": {\n" +
                "                       \"value\":\"需要缴费"+money+"元"+"\",\n" +
                "                       \"color\":\"#FF4040\"\n" +
                "                   },\n" +
                "                   \"keyword4\": {\n" +
                "                       \"value\":\"需要缴费"+money+"元"+"\",\n" +
                "                       \"color\":\"#FF4040\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"请及时缴费！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        String result = Util.post(url, data);
        System.out.println(result);
    }

}

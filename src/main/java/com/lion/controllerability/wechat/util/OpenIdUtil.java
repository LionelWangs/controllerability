package com.lion.controllerability.wechat.util;
import net.sf.json.JSONObject;
public class OpenIdUtil
{
    /**
     * 获取微信用户信息
     * @param code 微信端的CODE
     * @param classify 用户标示
     * @return
     */
    public static String oauth2GetOpenid(String code) {
        String appid = "wxcb17b0bd3caea01c";
        String appsecret = "be5ed889e616b166ecb52cc820f1c67b";
        //授权（必填）
        String grant_type = "authorization_code";
        //URL
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        //请求参数
        String params = "appid=" + appid + "&secret=" + appsecret + "&code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String data = HttpSendUtil.sendGet(requestUrl, params);
        //解析相应内容（转换成json对象）
        JSONObject  json = JSONObject.fromObject(data);
        //用户的唯一标识（openid）
        String openId = String.valueOf(json.get("openid"));
        return openId;
    }
}
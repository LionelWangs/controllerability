package com.lion.controllerability.wechat.controller;

import com.lion.controllerability.wechat.service.WxService;
import com.lion.controllerability.wechat.util.Util;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wang.hongyu
 * @Version V1.02020/04/18
 * 微信公众号模块
 **/
@RequestMapping("/wechat")
@Controller
public class WechatController {
    @GetMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        //校验证签名
        if (WxService.check(timestamp, nonce, signature)) {
            System.out.println("接入成功");
            PrintWriter out = response.getWriter();
            //原样返回echostr参数
            out.print(echostr);
            out.flush();
            out.close();
        } else {
            System.out.println("接入失败");
        }
    }

    @PostMapping("/test")
    public void getMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        //获取tokenAbstractButon
        //Button
        //ClickButton
        //SubButton
        //ViewButton
//        WxService.getAccessToken();
        //处理消息和事件推送
        Map<String, String> requestMap = WxService.parseRequest(request.getInputStream());
        System.out.println(requestMap);
        //准备回复的数据包
        String respXml = WxService.getRespose(requestMap);
        System.out.println(respXml);
        PrintWriter out = response.getWriter();
        out.print(respXml);
        out.flush();
        out.close();
    }

    @RequestMapping("/GetUserInfo")
    public void GetUserInfo(HttpServletRequest request, HttpServletResponse response) {
        Map map = new HashMap();
        //获取code
        String code = request.getParameter("code");
        //换取accesstoken的地址
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        url = url.replace("APPID", "wx1b075537c82c1bca").replace("SECRET", "5e4ae6319350b519a76240c0aaf8c35e").replace("CODE", code);
        String result = Util.get(url);
        String at = JSONObject.fromObject(result).getString("access_token");
        //获取openId
        String openid = JSONObject.fromObject(result).getString("openid");
        map.put("openId", openid);
        //拉取用户的基本信息
        url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESS_TOKEN", at).replace("OPENID", openid);
        result = Util.get(url);
        try {
            response.sendRedirect("http://101.37.33.94:81/dist/index.html?openId="+openid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
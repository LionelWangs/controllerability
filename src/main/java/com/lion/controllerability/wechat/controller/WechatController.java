package com.lion.controllerability.wechat.controller;

import com.lion.controllerability.wechat.service.WxService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
        if(WxService.check(timestamp,nonce,signature)) {
            System.out.println("接入成功");
            PrintWriter out = response.getWriter();
            //原样返回echostr参数
            out.print(echostr);
            out.flush();
            out.close();
        }else {
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
}

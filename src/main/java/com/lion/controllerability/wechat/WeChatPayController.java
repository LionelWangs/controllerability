package com.lion.controllerability.wechat;

import com.google.common.collect.Maps;
import com.lion.controllerability.wechat.service.WxService;
import com.lion.controllerability.wechat.util.WXPayUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/weChatPayController")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@SuppressWarnings({"rawtypes", "unchecked"})
public class WeChatPayController {

    /**
     * 充值
     * openId 用户标识
     * needPayMoney 需要支付的金额
     * orderNo 订单编号
     * attach 付款行为判断
     *
     * @return
     * @author LSJ
     */
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @RequestMapping(value = "/orderPay")
    public Map orderPay(HttpServletRequest request, HttpServletResponse response,
                        String openId, String needPayMoney){
            Map map = WxService.orderPay(request, response, openId, needPayMoney);
        return null;
    }


//    /**
//     * 支付成功后的回调
//     *
//     * @param request
//     * @throws IOException
//     * @author LSJ
//     */
//    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
//    @RequestMapping(value = "payCallBack")
//    public void payCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Map<String, String> resultMap = new HashMap<>();
//        try {
////            System.err.println("支付结果回调...");
//            String s = "支付结果回调";
//            //利用WXPayUtil工具类里的方法将XML读取出来
//            String xmlStr = WXPayUtil.reciverWx(request);
//            //并转换成MAP    这样就可以拿到xml里想要的数据了
//
//            Map map = WXPayUtil.xmlToMap(xmlStr);
//            if (map.get("return_code").equals("SUCCESS")) {
//                String sign = WXPayUtil.generateSignature(map, "abcde09876efghi54321jklmn2468088");
//                String sign1 = (String) map.get("sign");
//                //进行验签
//                if (sign.equals(sign1)) {
//                    //验签成功
//                    String attach = (String) map.get("attach");
//                    String[] splitAddress = attach.split("_");
//                    String judge = splitAddress[0];
//                    if ("1".equals(judge)) {
//                        //回调缴费方法
//                        boolean Boolean = PayCallBackService.pay(map);
//                        logger.log(StatusCode.LOG_INFO, "........................................进入支付回调");
//                        if (Boolean) {
//
//                            resultMap.put("return_code", "SUCCESS");
//                            resultMap.put("return_msg", "OK");
//
//                        }
//                        else {
//                            resultMap.put("return_code", "FAIL");
//                            resultMap.put("return_msg", "添加缴费记录错误");
//                        }
//                    }
//                    else if ("2".equals(judge)) {
//                        //回调充值方法
//                        boolean Boolean = PayCallBackService.payment(map);
//                        if (Boolean) {
//
//                            resultMap.put("return_code", "SUCCESS");
//                            resultMap.put("return_msg", "OK");
//
//                        } else {
//                            resultMap.put("return_code", "FAIL");
//                            resultMap.put("return_msg", "添加缴费记录错误");
//                        }
//                    }
//                }
//                else {
//                    resultMap.put("return_code", "FAIL");
//                    resultMap.put("return_msg", "签名不正确");
//                }
//            }
////            resultMap = WXPayUtil.xmlToMap(xmlStr);
//        }
//        catch (KeyManagementException | NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
//            resultMap = Maps.newHashMap();
//            resultMap.put("return_code", "FAIL");
//            resultMap.put("return_msg", "服务器报错");
//        }
//        catch (Exception e) {
////            e.printStackTrace();
//            logger.log(StatusCode.LOG_INFO, e);
//        }
//
//        PrintWriter out = response.getWriter();
//        out.write(WXPayUtil.getRequestXml(resultMap));
//        out.flush();
//    }
//    /**
//     * 退款给个人
//     * payWay：转账方式
//     * thirdId ：第三方用户id
//     * money：退款金额
//     * */
//    @RequestMapping("/refund")
//    public void refund(javax.servlet.http.HttpServletRequest request, String payWay, String thirdId, String money) throws Exception {
//        Customer customer = new Customer();
//        //判断是否为微信转账
//        if (StringUtils.isEmpty(payWay) || (PayConstant.WECHAT.equals(payWay))) {
//
//            WxService.refund(request, thirdId, money);
//        }
//        else {
//            customer.setUserId(thirdId);
//            Customer getMerchantNo = CustomerService.getObject(customer, null);
//            String merchantNo = getMerchantNo.getMerchantNo();
//            AliPayTransferService.aliPayTransfer(merchantNo, thirdId, money);
//        }
//
//    }

}
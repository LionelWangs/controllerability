package com.lion.controllerability.wechat.service;

import com.lion.controllerability.wechat.data.AccessToken;
import com.lion.controllerability.wechat.util.*;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WxService
 * @Author wang.hongyu
 * @Date 2019-12-17
 * @Version V1.0
 **/
@Service
public class WxService {
    private static final String SIGN = "abcde09876efghi54321jklmn2468088";
    private static final String APPID = "wxcb17b0bd3caea01c";
    private static final String MCHID = "1526260411";
    private static final String GET_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //微信公众号
    //用于存储token
    private static AccessToken at;
    /**
     * 申请退款
     *
     * @param  //包含商户订单号、商户退款单号、订单金额、退款金额
     * @return
     */
    public static String refund(Map<String, String> paraMap) throws Exception {
        WXPay wxPay = new WXPay();
        String xmlStr = "";
        String resultXml = "";
        paraMap.put("appid", APPID);
        paraMap.put("mch_id", MCHID);
        paraMap.put("nonce_str", WXPayUtil.generateNonceStr());
        String sign = WXPayUtil.generateSignature(paraMap, SIGN);
        paraMap.put("sign", sign);
        String url = "https://api.mch.weixin.qq.com/secapi/pay/refund";
        Map<String, String> resp = null;
        try {
            // 调用工具类，将Map集合转化为带签名sign的XML格式字符串
        xmlStr = WXPayUtil.generateSignedXml(paraMap, SIGN);
        // 调用微信退款接口地址
        // 调用双向证书，返回xml格式状态码
        resultXml = ClientCustomSSL.doRefund(url, xmlStr);
        // 将返回结果转换成Map集合
        resp = WXPayUtil.xmlToMap(resultXml);

        }
        catch (Exception e) {
        }
//        System.err.println(resp);
        String return_code = resp.get("return_code");   //返回状态码
        String return_msg = resp.get("return_msg");     //返回信息

        String resultReturn = null;
        if ("SUCCESS".equals(return_code)) {
            String result_code = resp.get("result_code");       //业务结果
            String err_code_des = resp.get("err_code_des");     //错误代码描述
            if ("SUCCESS".equals(result_code)) {
                //表示退款申请接受成功，结果通过退款查询接口查询
                //修改用户订单状态为退款申请中（暂时未写）
                resultReturn = "退款申请成功";
            }
            else {
//                logger.info("订单号:{}错误信息:{}", err_code_des);
                resultReturn = err_code_des;
            }
        }
        else {
//            logger.info("订单号:{}错误信息:{}", return_msg);
            resultReturn = return_msg;
        }
        return JSON.toJSONString(resultReturn);
    }

//    /**
//     * 微信转账到个人
//     *
//     * openId 退款人的id
//     * money  退款金额
//     * */
//    public static ResponseBase refund(HttpServletRequest request, String openId, String money) throws Exception {
//        final SortedMap<String, String> packageParams = new TreeMap<>();
//        DTOResponse<Map> dto = new DTOResponse<Map>();
//        Map<String, Object> dd = new HashMap();
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        if (ip.indexOf(",") != -1) {
//            String[] ips = ip.split(",");
//            ip = ips[0].trim();
//        }
//        packageParams.put("mch_appid", APPID);
//        packageParams.put("mchid", MCHID);
//        packageParams.put("partner_trade_no", String.valueOf(WXPayUtil.getCurrentTimestamp())); //生成订单号 唯一性
//        packageParams.put("openid", openId);
//        packageParams.put("check_name", "NO_CHECK");
//        packageParams.put("amount", money);
//        packageParams.put("desc", "test");
//        packageParams.put("spbill_create_ip", ip);
//        packageParams.put("nonce_str", WXPayUtil.generateNonceStr());
//        String sign = WXPayUtil.generateSignature(packageParams, SIGN);
////        System.out.println(sign)
//        packageParams.put("sign", sign);
//        String s = WXPayUtil.mapToXml(packageParams);
//        Map<String, String> map = WXPayUtil.wxSSLPost(s, MCHID, "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers");
//        if (map.get("return_code") == null || !"SUCCESS".equals(map.get("return_code")) || "FAIL".equals(map.get("result_code"))) {
//            //添加退款记录
//            Customerbase customer = new Customerbase();
//            customer.setOpenId(openId);
//            Customer customer1 = CustomerService.getObject(customer, null);
//            if (customer1 == null) {
//                dto.setMessage("用户不存在");
//                dto.setStatusCode(StatusCode.ERROR_DATABASE);
//                logger.log(StatusCode.LOG_INFO, "WxService.refund... 用户不存在");
//                dd.put("money", money);
//                dto.setDto(dd);
//                return dto;
//            }
//            Long customerId = customer1.getId();
//            AccountCustomer accountCustomer = new AccountCustomer();
//            accountCustomer.setCustomerId(customerId);
//            AccountCustomer accountGet = AccountCustomerService.getObject(accountCustomer, null);
//            Long accountId = accountGet.getAccountId();
//
////            account.setCustomerId(customerId);
//            Account account1 = AccountService.getObject(accountId);
//            String merchantNo = account1.getMerchantNo();
//            Long meterId = AccountMeterService.getMeterId(accountId);
//            RefundApply refundApply = new RefundApply();
//            refundApply.setMeterId(meterId);
//            refundApply.setResultRemark("微信转账");
//            refundApply.setRefundAmount(Long.valueOf(money));
//            refundApply.setMerchantNo(merchantNo);
//            java.util.Date  date = new java.util.Date();
//            //生成系统时间
//            java.sql.Date  data1 = new java.sql.Date(date.getTime());
//            refundApply.setApplyTime(data1);
//            refundApply.setStatusFlag(WechatConstant.STATUS_FLAG_FAIL);
//            RefundApplyService.insert(refundApply);
//            dto.setMessage("转账失败");
//            dto.setStatusCode(StatusCode.ERROR_DATABASE);
//            logger.log(StatusCode.LOG_INFO, "WxService.refund..." + JSON.toJSONString(map));
//            dd.put("money", money);
//            dto.setDto(dd);
//            return dto;
//        }
//        //添加退款记录
//        Customer customer = new Customer();
//        customer.setOpenId(openId);
//        Customer customer1 = CustomerService.getObject(customer, null);
//        Long customerId = customer1.getId();
//        Account account = new Account();
////        account.setCustomerId(customerId);
//        Account account1 = AccountService.getObject(account, null);
//        Long accountId = account1.getId();
//        int leftMoney = account1.getLeftMoney();
//        //转账成功后余额进行变动
//        AccountService.updateleftAmount(leftMoney - Integer.parseInt(money), accountId);
//        Long meterId = AccountMeterService.getMeterId(accountId);
//        RefundApply refundApply = new RefundApply();
//        refundApply.setMeterId(meterId);
//        refundApply.setRefundAmount(Long.valueOf(money));
//        java.util.Date  date = new java.util.Date();
//        //生成系统时间
//        java.sql.Date  data1 = new java.sql.Date(date.getTime());
//        refundApply.setApplyTime(data1);
//        refundApply.setStatusFlag(WechatConstant.STATUS_FLAG_SUCCESS);
//        RefundApplyService.insert(refundApply);
//        logger.log(StatusCode.LOG_INFO, "WxService.refund..." + JSON.toJSONString(map));
//        dd.put("money", money);
//        dto.setDto(dd);
//        dto.setMessage("转账成功");
//        dto.setStatusCode(StatusCode.SUCCESS);
//        return dto;
//    }

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
    public static Map orderPay(HttpServletRequest request, HttpServletResponse response, String openId, String needPayMoney) {

        String out_trade_no = WXPayUtil.generateNonceStr(); //随机生成32个字符内  （唯一）
        try {
            //拼接统一下单地址参数
            Map<String, String> paraMap = new HashMap<String, String>();
            //获取请求ip地址
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if (ip.indexOf(",") != -1) {
                String[] ips = ip.split(",");
                ip = ips[0].trim();
            }
//        获取用户信息 使用_分割 下划线前为AccountId
//            String accountId = StringUtils.substringBeforeLast(orderNo, "_");
//            生成缴费记录
//            Fee fee = new Fee();
//            fee.setFeeName("微信缴费");
//            fee.setFeeAmount(Long.valueOf(needPayMoney));
//            fee.setStatusFlag(WechatConstant.STATUS_FLAG_PAID);
//            fee.setAccountId(Long.valueOf(accountId));
//            Account account = AccountService.getObject(Long.valueOf(accountId));
//            fee.setMerchantNo(account.getMerchantNo());
//            FeeService.insert(fee);
            //微信支付时所需要的key-value
            paraMap.put("appid", APPID);
            paraMap.put("body", "测试商品");
            paraMap.put("mch_id", MCHID);
            paraMap.put("nonce_str", WXPayUtil.generateNonceStr()); //随机字符串
            paraMap.put("out_trade_no", "" + out_trade_no); //订单号
            paraMap.put("spbill_create_ip", ip);  //用户IP  上面已获取
            paraMap.put("total_fee", needPayMoney); //支付金额  （单位分）
//            if (recordId != null)
//            {
//                paraMap.put("attach", attach + "_" + recordId); //判断付款行为
//            }
//            else {
//                paraMap.put("attach", attach + "_");
//            }
            paraMap.put("openid", openId);
            paraMap.put("trade_type", "JSAPI");  //支付类型
            paraMap.put("notify_url", "http://water.fuhong.biz:81/weChatPayController/payCallBack"); // 此路径是微信服务器调用支付结果通知路径
            //利用WXPayUtil工具类生成签名
            String sign = WXPayUtil.generateSignature(paraMap, SIGN);
            paraMap.put("sign", sign);
            String xml = WXPayUtil.mapToXml(paraMap); //利用WXPayUtil工具类将所有参数(map)转xml格式
            String xmlStr = HttpSendUtil.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", xml); //发送post请求"统一下单接口"返回预支付id:prepay_id
            System.err.println(xmlStr);
            //以下内容是返回前端页面的json数据
            String prepay_id = ""; //预支付id
            if (xmlStr.indexOf("SUCCESS") != -1) {
//                System.out.println(xmlStr);
                Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);
                prepay_id = (String) map.get("prepay_id");
            }
            //返回给前端数据 唤起支付
            Map<String, String> payMap = new HashMap<String, String>();
            payMap.put("appId", APPID);
            payMap.put("timeStamp", WXPayUtil.getCurrentTimestamp() + ""); //时间戳
            payMap.put("nonceStr", WXPayUtil.generateNonceStr());  //随机字符串
            payMap.put("signType", "MD5");  //签名类型
            payMap.put("package", "prepay_id=" + prepay_id);
            String paySign = WXPayUtil.generateSignature(payMap, SIGN);
            payMap.put("paySign", paySign);
            System.out.println(payMap);
            return payMap;
        }
        catch (Exception e) {
        }
        return null;
    }

//
//    /**
//     * 获取token
//     */
//    private static void getToken() {
//        String url = GET_TOKEN_URL.replace("APPID", APPIDS).replace("APPSECRET", APPSECRET);
//        String tokenStr = Util.get(url);
//        JSONObject jsonObject = JSONObject.fromObject(tokenStr);
//        String token = jsonObject.getString("access_token");
//        String expireIn = jsonObject.getString("expires_in");
//        //创建token对象,并存起来。
//        at = new AccessToken(token, expireIn);
//    }
//
//    /**
//     * 向处暴露的获取token的方法
//     */
//    public static String getAccessToken() {
//        if(at==null||at.isExpired()) {
//            getToken();
//        }
//        return at.getAccessToken();
//    }



}

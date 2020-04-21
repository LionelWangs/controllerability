package com.lion.controllerability.wechat.service;

import com.lion.controllerability.wechat.data.AccessToken;
import com.lion.controllerability.wechat.data.Article;
import com.lion.controllerability.wechat.data.BaseMessage;
import com.lion.controllerability.wechat.data.NewsMessage;
import com.lion.controllerability.wechat.data.TextMessage;
import com.lion.controllerability.wechat.data.WechatConstant;
import com.lion.controllerability.wechat.util.*;
import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

    /**
     * 验证签名
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    public static boolean check(String timestamp, String nonce, String signature) {
        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] strs = new String[] {WechatConstant.TOKEN,timestamp,nonce};
        Arrays.sort(strs);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        String str = strs[0]+strs[1]+strs[2];
        String mysig = sha1(str);
        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        return mysig.equalsIgnoreCase(signature);
    }

    /**
     * 进行sha1加密
     * @return
     */
    private static String sha1(String src) {
        try {
            //获取一个加密对象
            MessageDigest md = MessageDigest.getInstance("sha1");
            //加密
            byte[] digest = md.digest(src.getBytes());
            char[] chars= {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuilder sb = new StringBuilder();
            //处理加密结果
            for(byte b:digest) {
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 解析xml数据包
     * @param is
     * @return
     */
    public static Map<String, String> parseRequest(InputStream is) {
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        try {
            //读取输入流，获取文档对象
            Document document = reader.read(is);
            //根据文档对象获取根节点
            Element root = document.getRootElement();
            //获取根节点的所有的子节点
            List<Element> elements = root.elements();
            for(Element e:elements) {
                map.put(e.getName(), e.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 用于处理所有的事件和消息的回复
     * @param requestMap
     * @return 返回的是xml数据包
     */
    public static String getRespose(Map<String, String> requestMap) {
        BaseMessage msg=null;
        String msgType = requestMap.get("MsgType");
        switch (msgType) {
            //处理文本消息
            case "text":
                msg=dealTextMessage(requestMap);
                break;
            case "image":
                break;
            case "voice":

                break;
            case "video":

                break;
            case "shortvideo":

                break;
            case "location":

                break;
            case "link":

                break;
            case "event":
                break;
            default:
                break;
        }
        //把消息对象处理为xml数据包
        if(msg!=null) {
            return beanToXml(msg);
        }
        return null;
    }
    /**
     * 处理文本消息
     * @param requestMap
     * @return
     */
    private static BaseMessage dealTextMessage(Map<String, String> requestMap) {
        //用户发来的内容
        String msg = requestMap.get("Content");
        if(msg.equals("简介")) {
            List<Article> articles = new ArrayList<>();
            articles.add(new Article("简介", "王宏宇的毕业设计", "http://img2.imgtn.bdimg.com/it/u=2703360051,1559664881&fm=26&gp=0.jpg", "http://www.baidu.com"));
            NewsMessage nm = new NewsMessage(requestMap, articles);
            return nm;
        }
        if(msg.equals("登录") || msg.equals("登陆")) {
            String url="http://101.37.33.94:81/dist/index.html";
            TextMessage tm = new TextMessage(requestMap, "点击<a href=\""+url+"\">这里</a>登录");
            return tm;
        }
        //调用方法返回聊天的内容
        String resp = chat(msg);
        TextMessage tm = new TextMessage(requestMap, resp);
        return tm;
    }

    /**
     * 调用图灵机器人聊天
     * @param msg 	发送的消息
     * @return
     */
    private static String chat(String msg) {
        String result =null;
        String url ="http://op.juhe.cn/iRobot/code";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",WechatConstant.APPKEY);//您申请到的本接口专用的APPKEY
        params.put("info",msg);//要发送给机器人的内容，不要超过30个字符
        params.put("dtype","");//返回的数据的格式，json或xml，默认为json
        params.put("loc","");//地点，如北京中关村
        params.put("lon","");//经度，东经116.234632（小数点后保留6位），需要写为116234632
        params.put("lat","");//纬度，北纬40.234632（小数点后保留6位），需要写为40234632
        params.put("userid","");//1~32位，此userid针对您自己的每一个用户，用于上下文的关联
        try {
            result =Util.net(url, params, "GET");
            //解析json
            JSONObject jsonObject = JSONObject.fromObject(result);
            //取出error_code
            int code = jsonObject.getInt("error_code");
            if(code!=0) {
                return null;
            }
            //取出返回的消息的内容
            String resp = jsonObject.getJSONObject("result").getString("text");
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把消息对象处理为xml数据包
     * @param msg
     * @return
     */
    private static String beanToXml(BaseMessage msg) {
        XStream stream = new XStream();
        //设置需要处理XStreamAlias("xml")注释的类
        stream.processAnnotations(TextMessage.class);
//        stream.processAnnotations(ImageMessage.class);
//        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(NewsMessage.class);
//        stream.processAnnotations(VideoMessage.class);
//        stream.processAnnotations(VoiceMessage.class);
        String xml = stream.toXML(msg);
        return xml;
    }
    /**
     * 获取token
     */
    private static void getToken() {
        String url =WechatConstant.GET_TOKEN_URL;
        String tokenStr = Util.get(url);
        JSONObject jsonObject = JSONObject.fromObject(tokenStr);
        String token = jsonObject.getString("access_token");
        String expireIn = jsonObject.getString("expires_in");
        //创建token对象,并存起来。
        at = new AccessToken(token, expireIn);
        System.out.println(at.getAccessToken());
    }

    /**
     * 向处暴露的获取token的方法
     * @return
     */
    public static String getAccessToken() {
        if(at==null||at.isExpired()) {
            getToken();
        }
        return at.getAccessToken();
    }



}

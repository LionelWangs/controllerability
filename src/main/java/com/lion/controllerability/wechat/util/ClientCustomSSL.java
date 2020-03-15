package com.lion.controllerability.wechat.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;



/**
 * 微信退款
 * 创建一个自定义的SSLContext安全连接
 * 
 */
public class ClientCustomSSL {
//    private static String path = "/opt/apiclient_cert.p12";
    public static String doRefund(String url,String data) throws Exception {
        //指定读取证书格式为PKCS12(注意PKCS12证书 是从微信商户平台-》账户设置-》 API安全 中下载的)
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
   String path ="/cert/apiclient_cert.p12"; //文件名
        // 指定证书路径
//   String path = "/apiclient_cert.p12";
        //读取本机存放的PKCS12证书文件 
        FileInputStream instream = new FileInputStream(new File(path));

        //比如安装在D:/pkcs12/apiclient_cert.p12情况下，就可以写成如下语句
        //FileInputStream instream = new FileInputStream(new File("D:/pkcs12/apiclient_cert.p12")); 
        try {
            //指定PKCS12的密码(商户ID) 
            keyStore.load(instream,"1526260411".toCharArray());
        } finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, "1526260411".toCharArray()).build();
        //指定TLS版本 
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory( sslcontext,new String[] {"TLSv1" },null,SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        //设置httpclient的SSLSocketFactory 
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {
            HttpPost httpost = new HttpPost(url);// 设置响应头信息
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data,"UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();
                String jsonStr = EntityUtils.toString(response.getEntity(),"UTF-8");
                EntityUtils.consume(entity);
                return jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
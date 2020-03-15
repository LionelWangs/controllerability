package com.lion.controllerability.wechat.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public class Util {
	public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	// 配置您申请的KEY
	public static final String APPKEY = "*************************";
	
	/**
	 * 向指定的地址发送一个post请求，带着data数据
	 * 
	 * @param url
	 * @param data
	 */
	public static String post(String url, String data) {
		try {
			URL urlObj = new URL(url);
			URLConnection connection = urlObj.openConnection();
			// 要发送数据出去，必须要设置为可发送数据状态
			connection.setDoOutput(true);
			// 获取输出流
			OutputStream os = connection.getOutputStream();
			// 写出数据
			os.write(data.getBytes());
			os.close();
			// 获取输入流
			InputStream is = connection.getInputStream();
			byte[] b = new byte[1024];
			int len;
			StringBuilder sb = new StringBuilder();
			while ((len = is.read(b)) != -1) {
				sb.append(new String(b, 0, len));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 向指定的地址发送get请求
	 *
	 * @param url
	 */
	public static String get(String url) {
		try {
			URL urlObj = new URL(url);
			// 开连接
			URLConnection connection = urlObj.openConnection();
			InputStream is = connection.getInputStream();
			byte[] b = new byte[1024];
			int len;
			StringBuilder sb = new StringBuilder();
			while ((len = is.read(b)) != -1) {
				sb.append(new String(b, 0, len));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}

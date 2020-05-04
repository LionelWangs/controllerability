package com.lion.controllerability.wechat.util;

import com.lion.controllerability.wechat.data.Button;
import com.lion.controllerability.wechat.data.ClickButton;
import com.lion.controllerability.wechat.data.SubButton;
import com.lion.controllerability.wechat.data.ViewButton;
import com.lion.controllerability.wechat.service.WxService;
import net.sf.json.JSONObject;

public class CreateMenu {

	public static void main(String[] args) {
		//菜单对象
		Button btn = new Button();
		//第一个一级菜单
		btn.getButton().add(new ViewButton("水费查询", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1b075537c82c1bca&redirect_uri=http://101.37.33.94/wechat/GetUserInfo&response_type=code&scope=snsapi_userinfo#wechat_redirect"));
		//转为json9
		JSONObject jsonObject = JSONObject.fromObject(btn);
		//准备url
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		url = url.replace("ACCESS_TOKEN", WxService.getAccessToken());
		//发送请求
		String result = Util.post(url, jsonObject.toString());
		System.out.println(result);
		
	}
	
}

package com.lion.controllerability.wechat.data;
/**
 * @ClassName AccessToken
 * @Author wang.hongyu
 * @Date 2019-12-17
 * @Version V1.0
 * 获取AccessToken
 **/
public class AccessToken {

	private String accessToken;
	private long expireTime;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	public AccessToken(String accessToken, String expireIn) {
		super();
		this.accessToken = accessToken;
		expireTime = System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
	}

    public AccessToken() {
    }

    /**
	 * 判断token是否过期
	 * @return
	 */
	public boolean isExpired() {
		return System.currentTimeMillis()>expireTime;
	}

}

package com.lion.controllerability.customer.data;

import java.util.Date;

/**
 * @Author wang.hongyu
 * @Version V1.02020/03/13
 *  用来传给前端数据
 * **/
public class Info {
    private String customreName ;

    private String adress ;

    private String mobile ;

    private String idNo ;

    private Date registerdate ;

    private Long leftMoney ;

    private String accountType ;

    public String getCustomreName() {
        return customreName;
    }

    public void setCustomreName(String customreName) {
        this.customreName = customreName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Date getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public Long getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(Long leftMoney) {
        this.leftMoney = leftMoney;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}

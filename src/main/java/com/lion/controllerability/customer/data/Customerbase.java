package com.lion.controllerability.customer.data;

import com.lion.controllerability.accountBase.data.Accountbase;
import com.lion.controllerability.accountCustomer.data.Accountcustomer;
import com.lion.controllerability.basisposition.data.Basisposition;
import com.lion.controllerability.moneyRecord.data.Moneyrecord;

import java.util.Date;

public class Customerbase {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CustomerBase.Customerld
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    private Long customerld;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CustomerBase.IdNo
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    private String idno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CustomerBase.CustomerName
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    private String customername;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CustomerBase.Address
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CustomerBase.RegisterDate
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    private Date registerdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CustomerBase.Mobile
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CustomerBase.Phone
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CustomerBase.StatusFlag
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    private Byte statusflag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CustomerBase.TypeFlag
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    private Byte typeflag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CustomerBase.OpenId
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    private String openid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CustomerBase.UserId
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    private String userid;

    private Accountbase accountbase ;

    private Basisposition basisposition ;

    private Accountcustomer accountcustomer ;

    private Moneyrecord moneyrecord ;

    public Accountbase getAccountbase() {
        return accountbase;
    }

    public void setAccountbase(Accountbase accountbase) {
        this.accountbase = accountbase;
    }

    public Basisposition getBasisposition() {
        return basisposition;
    }

    public void setBasisposition(Basisposition basisposition) {
        this.basisposition = basisposition;
    }

    public Accountcustomer getAccountcustomer() {
        return accountcustomer;
    }

    public void setAccountcustomer(Accountcustomer accountcustomer) {
        this.accountcustomer = accountcustomer;
    }

    public Moneyrecord getMoneyrecord() {
        return moneyrecord;
    }

    public void setMoneyrecord(Moneyrecord moneyrecord) {
        this.moneyrecord = moneyrecord;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CustomerBase.Customerld
     *
     * @return the value of CustomerBase.Customerld
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public Long getCustomerld() {
        return customerld;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CustomerBase.Customerld
     *
     * @param customerld the value for CustomerBase.Customerld
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public void setCustomerld(Long customerld) {
        this.customerld = customerld;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CustomerBase.IdNo
     *
     * @return the value of CustomerBase.IdNo
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public String getIdno() {
        return idno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CustomerBase.IdNo
     *
     * @param idno the value for CustomerBase.IdNo
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public void setIdno(String idno) {
        this.idno = idno == null ? null : idno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CustomerBase.CustomerName
     *
     * @return the value of CustomerBase.CustomerName
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public String getCustomername() {
        return customername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CustomerBase.CustomerName
     *
     * @param customername the value for CustomerBase.CustomerName
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public void setCustomername(String customername) {
        this.customername = customername == null ? null : customername.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CustomerBase.Address
     *
     * @return the value of CustomerBase.Address
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CustomerBase.Address
     *
     * @param address the value for CustomerBase.Address
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CustomerBase.RegisterDate
     *
     * @return the value of CustomerBase.RegisterDate
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public Date getRegisterdate() {
        return registerdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CustomerBase.RegisterDate
     *
     * @param registerdate the value for CustomerBase.RegisterDate
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CustomerBase.Mobile
     *
     * @return the value of CustomerBase.Mobile
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CustomerBase.Mobile
     *
     * @param mobile the value for CustomerBase.Mobile
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CustomerBase.Phone
     *
     * @return the value of CustomerBase.Phone
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CustomerBase.Phone
     *
     * @param phone the value for CustomerBase.Phone
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CustomerBase.StatusFlag
     *
     * @return the value of CustomerBase.StatusFlag
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public Byte getStatusflag() {
        return statusflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CustomerBase.StatusFlag
     *
     * @param statusflag the value for CustomerBase.StatusFlag
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public void setStatusflag(Byte statusflag) {
        this.statusflag = statusflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CustomerBase.TypeFlag
     *
     * @return the value of CustomerBase.TypeFlag
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public Byte getTypeflag() {
        return typeflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CustomerBase.TypeFlag
     *
     * @param typeflag the value for CustomerBase.TypeFlag
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public void setTypeflag(Byte typeflag) {
        this.typeflag = typeflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CustomerBase.OpenId
     *
     * @return the value of CustomerBase.OpenId
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CustomerBase.OpenId
     *
     * @param openid the value for CustomerBase.OpenId
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CustomerBase.UserId
     *
     * @return the value of CustomerBase.UserId
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CustomerBase.UserId
     *
     * @param userid the value for CustomerBase.UserId
     *
     * @mbggenerated Mon Mar 02 21:39:47 CST 2020
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }
}
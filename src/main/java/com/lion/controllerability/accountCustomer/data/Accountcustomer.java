package com.lion.controllerability.accountCustomer.data;

import java.util.Date;

public class Accountcustomer {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column AccountCustomer.Id
     *
     * @mbggenerated Tue Mar 03 20:40:12 CST 2020
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column AccountCustomer.AccountId
     *
     * @mbggenerated Tue Mar 03 20:40:12 CST 2020
     */
    private Long accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column AccountCustomer.CustomerId
     *
     * @mbggenerated Tue Mar 03 20:40:12 CST 2020
     */
    private Long customerid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column AccountCustomer.BindTime
     *
     * @mbggenerated Tue Mar 03 20:40:12 CST 2020
     */
    private Date bindtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column AccountCustomer.ActiveFlag
     *
     * @mbggenerated Tue Mar 03 20:40:12 CST 2020
     */
    private Byte activeflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column AccountCustomer.Id
     *
     * @return the value of AccountCustomer.Id
     *
     * @mbggenerated Tue Mar 03 20:40:12 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column AccountCustomer.Id
     *
     * @param id the value for AccountCustomer.Id
     *
     * @mbggenerated Tue Mar 03 20:40:12 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column AccountCustomer.AccountId
     *
     * @return the value of AccountCustomer.AccountId
     *
     * @mbggenerated Tue Mar 03 20:40:12 CST 2020
     */
    public Long getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column AccountCustomer.AccountId
     *
     * @param accountid the value for AccountCustomer.AccountId
     *
     * @mbggenerated Tue Mar 03 20:40:12 CST 2020
     */
    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column AccountCustomer.CustomerId
     *
     * @return the value of AccountCustomer.CustomerId
     *
     * @mbggenerated Tue Mar 03 20:40:12 CST 2020
     */
    public Long getCustomerid() {
        return customerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column AccountCustomer.CustomerId
     *
     * @param customerid the value for AccountCustomer.CustomerId
     *
     * @mbggenerated Tue Mar 03 20:40:12 CST 2020
     */
    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column AccountCustomer.BindTime
     *
     * @return the value of AccountCustomer.BindTime
     *
     * @mbggenerated Tue Mar 03 20:40:12 CST 2020
     */
    public Date getBindtime() {
        return bindtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column AccountCustomer.BindTime
     *
     * @param bindtime the value for AccountCustomer.BindTime
     *
     * @mbggenerated Tue Mar 03 20:40:12 CST 2020
     */
    public void setBindtime(Date bindtime) {
        this.bindtime = bindtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column AccountCustomer.ActiveFlag
     *
     * @return the value of AccountCustomer.ActiveFlag
     *
     * @mbggenerated Tue Mar 03 20:40:12 CST 2020
     */
    public Byte getActiveflag() {
        return activeflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column AccountCustomer.ActiveFlag
     *
     * @param activeflag the value for AccountCustomer.ActiveFlag
     *
     * @mbggenerated Tue Mar 03 20:40:12 CST 2020
     */
    public void setActiveflag(Byte activeflag) {
        this.activeflag = activeflag;
    }
}
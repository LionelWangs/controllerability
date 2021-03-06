package com.lion.controllerability.meterBase.data;

import java.util.Date;

public class Meterbase {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MeterBase.MeterId
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    private Long meterid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MeterBase.TypeId
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    private String typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MeterBase.MeterNo
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    private String meterno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MeterBase.Imei
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    private String imei;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MeterBase.Imsi
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    private String imsi;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MeterBase.StatusFlag
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    private Byte statusflag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MeterBase.SyncFlag
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    private Byte syncflag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MeterBase.LastCommuniationTime
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    private Date lastcommuniationtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MeterBase.Volume
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    private Double volume;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MeterBase.Voltage
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    private Double voltage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MeterBase.DeviceId
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    private String deviceid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MeterBase.UseTypeCode
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    private String usetypecode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MeterBase.MeterId
     *
     * @return the value of MeterBase.MeterId
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public Long getMeterid() {
        return meterid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MeterBase.MeterId
     *
     * @param meterid the value for MeterBase.MeterId
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public void setMeterid(Long meterid) {
        this.meterid = meterid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MeterBase.TypeId
     *
     * @return the value of MeterBase.TypeId
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public String getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MeterBase.TypeId
     *
     * @param typeid the value for MeterBase.TypeId
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MeterBase.MeterNo
     *
     * @return the value of MeterBase.MeterNo
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public String getMeterno() {
        return meterno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MeterBase.MeterNo
     *
     * @param meterno the value for MeterBase.MeterNo
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public void setMeterno(String meterno) {
        this.meterno = meterno == null ? null : meterno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MeterBase.Imei
     *
     * @return the value of MeterBase.Imei
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public String getImei() {
        return imei;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MeterBase.Imei
     *
     * @param imei the value for MeterBase.Imei
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MeterBase.Imsi
     *
     * @return the value of MeterBase.Imsi
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MeterBase.Imsi
     *
     * @param imsi the value for MeterBase.Imsi
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public void setImsi(String imsi) {
        this.imsi = imsi == null ? null : imsi.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MeterBase.StatusFlag
     *
     * @return the value of MeterBase.StatusFlag
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public Byte getStatusflag() {
        return statusflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MeterBase.StatusFlag
     *
     * @param statusflag the value for MeterBase.StatusFlag
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public void setStatusflag(Byte statusflag) {
        this.statusflag = statusflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MeterBase.SyncFlag
     *
     * @return the value of MeterBase.SyncFlag
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public Byte getSyncflag() {
        return syncflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MeterBase.SyncFlag
     *
     * @param syncflag the value for MeterBase.SyncFlag
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public void setSyncflag(Byte syncflag) {
        this.syncflag = syncflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MeterBase.LastCommuniationTime
     *
     * @return the value of MeterBase.LastCommuniationTime
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public Date getLastcommuniationtime() {
        return lastcommuniationtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MeterBase.LastCommuniationTime
     *
     * @param lastcommuniationtime the value for MeterBase.LastCommuniationTime
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public void setLastcommuniationtime(Date lastcommuniationtime) {
        this.lastcommuniationtime = lastcommuniationtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MeterBase.Volume
     *
     * @return the value of MeterBase.Volume
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public Double getVolume() {
        return volume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MeterBase.Volume
     *
     * @param volume the value for MeterBase.Volume
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public void setVolume(Double volume) {
        this.volume = volume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MeterBase.Voltage
     *
     * @return the value of MeterBase.Voltage
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public Double getVoltage() {
        return voltage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MeterBase.Voltage
     *
     * @param voltage the value for MeterBase.Voltage
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MeterBase.DeviceId
     *
     * @return the value of MeterBase.DeviceId
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public String getDeviceid() {
        return deviceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MeterBase.DeviceId
     *
     * @param deviceid the value for MeterBase.DeviceId
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MeterBase.UseTypeCode
     *
     * @return the value of MeterBase.UseTypeCode
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public String getUsetypecode() {
        return usetypecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MeterBase.UseTypeCode
     *
     * @param usetypecode the value for MeterBase.UseTypeCode
     *
     * @mbggenerated Wed Mar 18 16:12:54 CST 2020
     */
    public void setUsetypecode(String usetypecode) {
        this.usetypecode = usetypecode == null ? null : usetypecode.trim();
    }
}
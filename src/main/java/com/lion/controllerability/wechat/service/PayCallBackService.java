//package com.lion.controllerability.wechat.service;
//
//import org.springframework.stereotype.Service;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Map;
//import java.util.UUID;
//
///**
// * @ClassName PayCallBackService
// * @Author wang.hongyu
// * @Date 2019-12-30
// * @Version V1.0
// **/
//@Service
//public class PayCallBackService {
//
//    /**
//     * 回调充值方法
//     *
//     * @param map
//     * @return true:成功 false:失败
//     * @throws ParseException
//     */
//    public static boolean payment(Map map) throws ParseException {
//        AccountMeter accountMeter = new AccountMeter();
//        MoneyRecord moneyRecord = new MoneyRecord();
//        Account account = new Account();
//        String out_trade_no = (String) map.get("out_trade_no");
//        long feeId = Long.parseLong(out_trade_no);
//        if (FeeService.getObject(feeId) == null || FeeService.getObject(feeId).getStatusFlag() == WechatConstant.STATUS_FLAG_SUCCESS) {
//            return true;
//        } else {
//            long accountId = FeeService.getObject(feeId).getAccountId();
//
//            //应结订单金额
//            String total_fee = (String) map.get("total_fee");
//            int settlement_total_fee = Integer.parseInt(total_fee);
//            Account account1 = AccountService.getObject(accountId);
//            int leftMoney = account1.getLeftMoney() + settlement_total_fee;
//            account1.getPositionId();
//            account1.setLeftMoney(leftMoney);
//            AccountService.updateleftAmount(leftMoney, accountId);
//            //支付时间
//            String s = (String) map.get("time_end");
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
////            System.out.println(s);
//            logger.log(StatusCode.LOG_INFO, s);
//            Date PayTime = formatter.parse(s);
//            //绑定的表具编号
//            accountMeter.setAccountId(accountId);
//            Long meterId = AccountMeterService.getObject(accountMeter, null).getMeterId();
//            //支付账单流水号
//            String TransNo = (String) map.get("transaction_id");
//            logger.log(StatusCode.LOG_INFO, "WeChatPayController.payment..." + "获取feeId：" + feeId);
//            Fee fee = FeeService.getObject(feeId);
//            fee.setId(feeId);
//            fee.setStatusFlag(WechatConstant.STATUS_FLAG_SUCCESS);
//            fee.setTypeFlag(WechatConstant.TypeFlag_WECHATPAY);
//            fee.setMeterId(meterId);
//            fee.setFeeName("微信充值");
//            logger.log(StatusCode.LOG_INFO, "WeChatPayController.payment..." + "金额：" + Long.parseLong(String.valueOf(settlement_total_fee)));
//            fee.setFeeAmount(Long.parseLong(String.valueOf(settlement_total_fee)));
//            fee.setPayTime(PayTime);
//            fee.setTransNo(TransNo);
//            int i = FeeService.update(fee, null, fee);
//            if (i == 1) {
//                moneyRecord.setAccountId(accountId);
//                moneyRecord.setRemark("微信充值");
//                moneyRecord.setBillNo(out_trade_no);
//                moneyRecord.setChangeAmount(settlement_total_fee);
//                moneyRecord.setAfterAmount(leftMoney);
//                moneyRecord.setRecordTime(PayTime);
//                moneyRecord.setPositionId(account1.getPositionId());
//                moneyRecord.setMerchantNo(account1.getMerchantNo());
//                MoneyRecordService.insert(moneyRecord);
//
//                //获取当前时间
//                Date now = new Date();
//                //根据accountId获取到 AccountCustomer
//                AccountCustomer accountCustomer = AccountCustomerService.getCustomerByAccountId(accountId);
//                //根据 accountCustomer 获取 customer
//                Customer customer = CustomerService.getObject(accountCustomer.getCustomerId());
//
//                Account accounts = AccountService.getObject(accountId);
//
//                Long positionId = accounts.getPositionId();
//
//                EstatePosition estatePosition = EstatePositionService.getObject(positionId);
//
//                String positionNo = estatePosition.getPositionNo();
//                //生成告警信息
//                Alarm alarm = new Alarm();
//                alarm.setMobiles(customer.getMobile());
//                alarm.setOpenIds(customer.getOpenId());
//                alarm.setAlarmTime(now);
//                alarm.setOpenIds(customer.getOpenId());
//                alarm.setPositionNo(positionNo);
//                //告警类型 余额变动记录
//                alarm.setAlarmTypeFlag(AlarmConstant.ALARM_TYPE_SUPPLY);
//                //告警类型 未处理
//                alarm.setStatusFlag(AlarmConstant.ALARM_STATUS_UNTREATED);
//                //插入一条alarm
//                String dateStr = DateCommonUtils.formatString(now, "yyyy-MM-dd");
//                if (StringUtils.isNotEmpty(customer.getOpenId()))
//                {
//                    alarm.setRemark("手机号：" + customer.getMobile() + "；微信识别标识：" + customer.getOpenId() + "；通知时间：" + dateStr);
//                }
//                else
//                {
//                    alarm.setRemark("手机号：" + customer.getMobile() + "；微信识别标识：无"  + "；通知时间：" + dateStr);
//                }
//                AlarmService.insert(alarm);
//
//                // 将告警信息存入mq中
//                Message<SMSMessage> message = new Message<>();
//                message.setId(UUID.randomUUID().toString());
//                message.setSendTime(new Date().getTime());
//                message.setSender(Config.getProperty("mq.product"));
//                SMSMessage smsMessage = new SMSMessage();
//                smsMessage.setType(SMSMessageConstant.STATUS_FLAG_ALARM);
//                smsMessage.setAlarm(alarm);
//                smsMessage.setCustomer(customer);
//                smsMessage.setMoneyRecord(moneyRecord);
//                message.setData(smsMessage);
//                publisher.send(message);
//
//
//                return true;
//            } else {
//                return false;
//            }
//        }
//    }
//
//    /**
//     * 回调缴费方法
//     *
//     * @param map
//     * @return true:成功 false:失败
//     * @throws ParseException
//     */
//
//    public static boolean pay(Map map) throws ParseException {
//        //账单编号
//        String recordId2 = (String) map.get("attach");
//        logger.log(StatusCode.LOG_INFO, "..................................传进来的：获取到的attach" + recordId2);
//        String[] splitAddress = recordId2.split("_");
//
//        String recordId1 = splitAddress[1];
//        //缴费记录id
//        String out_trade_no = (String) map.get("out_trade_no");
////        String out_trade_no="123";
//        long feeId = Long.parseLong(out_trade_no);
//
//        Fee fee = FeeService.getObject(feeId);
//        //账单ID
//        Long recordId = Long.valueOf(recordId1);
//        AccountMeter accountMeter = new AccountMeter();
//        MoneyRecord moneyRecord = new MoneyRecord();
//        Bill bill = BillService.getObject(recordId);
//        //判断
//        if (fee == null || fee.getStatusFlag() == WechatConstant.STATUS_FLAG_SUCCESS || bill == null || bill.getStatusFlag() == WechatConstant.STATUS_FLAG_SUCCESS) {
//
//            logger.log(StatusCode.LOG_INFO, "........................................判断为空");
//            return true;
//        } else {
//            //支付时间
//            String s = (String) map.get("time_end");
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
////            System.out.println(s);
//            logger.log(StatusCode.LOG_INFO, s);
//            Date PayTime = formatter.parse(s);
//            //账户编号
//            Long accountId = bill.getAccountId();
//            //位置编号
//            Long positionId = bill.getPositionId();
//            //账单应缴金额
//            int shouldAmount = bill.getShouldAmount();
//            //订单金额
//            String total_fee = (String) map.get("total_fee");
//            int settlement_total_fee = Integer.parseInt(total_fee);
//            //绑定的表具编号
//            accountMeter.setAccountId(accountId);
//            Long meterId = AccountMeterService.getObject(accountMeter, null).getMeterId();
//            //订单流水号
//            String TransNo = (String) map.get("transaction_id");
//            //向缴费记录中插入数据
//            Fee fee1 = FeeService.getObject(feeId);
//            fee1.setId(feeId);
//            fee1.setStatusFlag(WechatConstant.STATUS_FLAG_SUCCESS);
//            fee1.setTypeFlag(WechatConstant.TypeFlag_WECHATPAY);
//            fee1.setMeterId(meterId);
//            fee1.setFeeName("微信缴费");
//            fee1.setFeeAmount(Long.parseLong(String.valueOf(shouldAmount)));
//            fee1.setPayTime(PayTime);
//            fee1.setTransNo(TransNo);
//
//            Account account = AccountService.getObject(accountId);
//            int i = FeeService.update(fee1, null, fee1);
//            if (i == 1) {
//                if (shouldAmount == settlement_total_fee) {
//                    //用户选择微信支付
//                } else if (shouldAmount > settlement_total_fee) {
//                    //账单需支付金额 > 账户余额
//                    //因账单需支付金额>账户余额 故将账户余额清零
//                    int leftMoney = 0;
//                    AccountService.updateleftAmount(leftMoney, accountId);
//                    //新增账户余额变动记录
//                    moneyRecord.setAccountId(accountId);
//                    moneyRecord.setRemark("微信缴费");
//                    moneyRecord.setBillNo(out_trade_no);
//                    moneyRecord.setChangeAmount(settlement_total_fee - shouldAmount);
//                    moneyRecord.setAfterAmount(leftMoney);
//                    moneyRecord.setRecordTime(PayTime);
//                    moneyRecord.setPositionId(account.getPositionId());
//                    moneyRecord.setMerchantNo(account.getMerchantNo());
//                    MoneyRecordService.insert(moneyRecord);
//
//                    EstatePosition estatePosition = EstatePositionService.getObject(account.getPositionId());
//
//                    String positionNo = estatePosition.getPositionNo();
//
//                    //获取当前时间
//                    Date now = new Date();
//                    //根据accountId获取到 AccountCustomer
//                    AccountCustomer accountCustomer = AccountCustomerService.getCustomerByAccountId(accountId);
//                    //根据 accountCustomer 获取 customer
//                    Customer customer = CustomerService.getObject(accountCustomer.getCustomerId());
//                    //生成告警信息
//                    Alarm alarm = new Alarm();
//                    alarm.setMobiles(customer.getMobile());
//                    alarm.setOpenIds(customer.getOpenId());
//                    alarm.setAlarmTime(now);
//                    alarm.setOpenIds(customer.getOpenId());
//                    alarm.setPositionNo(positionNo);
//                    //告警类型 表具异常
//                    alarm.setAlarmTypeFlag(AlarmConstant.ALARM_TYPE_SUPPLY);
//                    //告警类型 未处理
//                    alarm.setStatusFlag(AlarmConstant.ALARM_STATUS_UNTREATED);
//                    String dateStr = DateCommonUtils.formatString(now, "yyyy-MM-dd");
//                    if (StringUtils.isNotEmpty(customer.getOpenId()))
//                    {
//                        alarm.setRemark("手机号：" + customer.getMobile() + "；微信识别标识：" + customer.getOpenId() + "；通知时间：" + dateStr);
//                    }
//                    else
//                    {
//                        alarm.setRemark("手机号：" + customer.getMobile() + "；微信识别标识：无"  + "；通知时间：" + dateStr);
//                    }
//                    //插入一条alarm
//                    AlarmService.insert(alarm);
//
//
//                    // 将告警信息存入mq中
//                    Message<SMSMessage> message = new Message<>();
//                    message.setId(UUID.randomUUID().toString());
//                    message.setSendTime(new Date().getTime());
//                    message.setSender(Config.getProperty("mq.product"));
//                    SMSMessage smsMessage = new SMSMessage();
//                    smsMessage.setType(SMSMessageConstant.STATUS_FLAG_ALARM);
//                    smsMessage.setAlarm(alarm);
//                    smsMessage.setCustomer(customer);
//                    smsMessage.setMoneyRecord(moneyRecord);
//                    message.setData(smsMessage);
//                    publisher.send(message);
//
//
//                }
//                //修改账单记录
//                bill.setId(recordId);
//                bill.setStatusFlag(WechatConstant.STATUS_FLAG_SUCCESS);
//                bill.setActualAmount(shouldAmount);
//                bill.setPayTime(PayTime);
//                BillService.update(bill, null, bill);
//                return true;
//            } else {
//                return false;
//            }
//        }
//    }
//
//
//}

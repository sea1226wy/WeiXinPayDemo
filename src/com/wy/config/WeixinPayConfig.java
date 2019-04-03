package com.wy.config;

/**
 * @auther Seay
 * @date 2019/3/18 16:58
 * 微信支付配置文件
 */
public class WeixinPayConfig {
    public static final String appid="wx5e97e532d120a6bf";//公众号id 企业申请，会以邮件的形式发送*
    public static final String mch_id="1500625891";//商户号  企业申请，会以邮件的形式发送*
    public static final String device_info="WEB";//设备号
    public static final String url="https://api.mch.weixin.qq.com/pay/unifiedorder";//支付请求地址
    public static final String notify_url="http://pay2.wy.com/notifyUrl";//微信通知地址
    public static final String key="123456";// 商户的key【API密钥】  企业申请，会以邮件的形式发送*

}
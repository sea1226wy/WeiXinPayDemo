package com.wy.test;

import com.wy.config.WeixinPayConfig;
import com.wy.util.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther Seay
 * @date 2019/4/3 10:48
 * 测试退款申请
 * 微信接口文档 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
 */
public class TestRefund {
    private static final String url = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    public static void main(String[] args) throws IOException {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("appid", WeixinPayConfig.appid); // 公众账号ID
        map.put("mch_id", WeixinPayConfig.mch_id); // 商户号
        map.put("transaction_id", "4200000094201804192059258077"); // 微信订单号
        //map.put("out_trade_no", "20180419105343760"); // 商户订单号
        map.put("nonce_str", StringUtil.getRandomString(30)); // 随机字符串
        map.put("out_refund_no", DateUtil.getCurrentDateStr()); // 商户退款单号
        map.put("total_fee", 100); // 订单金额
        map.put("refund_fee", 10); // 退款金额
        map.put("sign", WeiXinUtil.getSign(map)); // 签名
        String xml= XmlUtil.genXml(map);
        System.out.println(xml);
        InputStream in= HttpClientUtil.sendXMLDataByHttpsPost(url, xml).getEntity().getContent(); // 发现xml消息
        WeiXinUtil.getElementValue(in);
    }
}
package com.wy.test;

import com.wy.config.WeixinPayConfig;
import com.wy.util.HttpClientUtil;
import com.wy.util.StringUtil;
import com.wy.util.WeiXinUtil;
import com.wy.util.XmlUtil;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 下载对账单测试
 * @author Administrator
 * 微信接口文档 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
 */
public class TestDownLoadBill {

	private static String url="https://api.mch.weixin.qq.com/pay/downloadbill";
	
	public static void main(String[] args) throws UnsupportedOperationException, ClientProtocolException, IOException {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("appid", WeixinPayConfig.appid); // 公众账号ID
		map.put("mch_id", WeixinPayConfig.mch_id); // 商户号
		map.put("nonce_str", StringUtil.getRandomString(30)); // 随机字符串
		map.put("bill_date", "20180419"); // 对账单日期
		map.put("bill_type", "ALL"); // 账单类型
		map.put("sign", WeiXinUtil.getSign(map)); // 签名
		String xml= XmlUtil.genXml(map);
		System.out.println(xml);
		InputStream in= HttpClientUtil.sendXMLDataByPost(url, xml).getEntity().getContent(); // 发现xml消息

		/*实际开发业务中，让流以文件的形式回显页面，然后下载*/
		/*这里测试，就直接输出了*/
		StringBuffer out=new StringBuffer();
		byte []b=new byte[4096];
		for(int n;(n=in.read(b))!=-1;){
			out.append(new String(b,0,n));
		}
		System.out.println(out.toString());
	}
}

package com.wy.servlet;

import com.wy.config.WeixinPayConfig;
import com.wy.util.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WapPayServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderNo= DateUtil.getCurrentDateStr(); // 生成订单号
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("appid", WeixinPayConfig.appid); // 公众账号ID
		map.put("mch_id", WeixinPayConfig.mch_id); // 商户号
		map.put("device_info", WeixinPayConfig.device_info); // 设备号
		map.put("notify_url", WeixinPayConfig.notify_url); // 异步通知地址
		map.put("trade_type", "MWEB"); // 交易类型
		map.put("out_trade_no", orderNo); // 商户订单号
		map.put("body", "测试商品"); // 商品描述
		map.put("total_fee", 100); // 标价金额
		// map.put("spbill_create_ip", getRemortIP(req)); // 终端IP
		map.put("spbill_create_ip", "127.0.0.1"); // 终端IP
		map.put("nonce_str", StringUtil.getRandomString(30)); // 随机字符串
		map.put("sign", getSign(map)); // 签名
		String xml= XmlUtil.genXml(map);
		System.out.println(xml);
		InputStream in= HttpClientUtil.sendXMLDataByPost(WeixinPayConfig.url, xml).getEntity().getContent(); // 发现xml消息
		String mweb_url=getElementValue(in,"mweb_url"); // 支付跳转链接
		/*拼接参数 支付成功跳转链接*/
		mweb_url+="&redirect_url="+URLEncoder.encode("http://pay2.java1234.com/", "GBK");
		System.out.println(mweb_url);
		resp.sendRedirect(mweb_url);
	}
	
	/**
     * 获取本机IP地址
     * @return IP
     */
	private static String getRemortIP(HttpServletRequest request) {  
        if (request.getHeader("x-forwarded-for") == null) {  
            return request.getRemoteAddr();  
        }  
        return request.getHeader("x-forwarded-for");  
    }
	
	/**
     * 微信支付签名算法sign
     */
    private String getSign(Map<String,Object> map) {
        StringBuffer sb = new StringBuffer();
        String[] keyArr = (String[]) map.keySet().toArray(new String[map.keySet().size()]);//获取map中的key转为array
        Arrays.sort(keyArr);//对array排序
        for (int i = 0, size = keyArr.length; i < size; ++i) {
            if ("sign".equals(keyArr[i])) {
                continue;
            }
            sb.append(keyArr[i] + "=" + map.get(keyArr[i]) + "&");
        }
        sb.append("key=" + WeixinPayConfig.key);
        String sign = Md5Util.string2MD5(sb.toString());
        return sign;
    }
    
    /**
	 * 通过返回IO流获取支付地址
	 * @param in
	 * @return
	 */
	private String getElementValue(InputStream in,String key){
		SAXReader reader = new SAXReader();
        Document document=null;
		try {
			document = reader.read(in);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();
        for (Element child : childElements) {
        	System.out.println(child.getName()+":"+child.getStringValue());
        	if(key.equals(child.getName())){
        		return child.getStringValue();
        	}
        }
        return null;
	}

}

package com.wy.util;

import com.wy.config.WeixinPayConfig;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @auther Seay
 * @date 2019/4/3 10:22
 */
public class WeiXinUtil {
    /**
     * 通过返回IO流获取支付地址
     * @param in
     * @return
     */
    public static void getElementValue(InputStream in){
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
        }
    }

    /**
     * 微信支付签名算法sign
     */
    public static String getSign(Map<String,Object> map) {
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
}
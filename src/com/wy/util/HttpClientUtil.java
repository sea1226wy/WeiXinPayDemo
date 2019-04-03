package com.wy.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * HttpClient工具类
 * @author Administrator
 *
 */
public class HttpClientUtil {

	/**
	 * 发送xml数据
	 * @param url
	 * @param xmlData
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static HttpResponse sendXMLDataByPost(String url, String xmlData)
            throws ClientProtocolException, IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        StringEntity entity = new StringEntity(xmlData);
        httppost.setEntity(entity);
        httppost.setHeader("Content-Type", "text/xml;charset=UTF-8");
        HttpResponse response = httpClient.execute(httppost);
        return response;
    }
	/**
	 * 发送xml数据 https
	 * @param url
	 * @param xmlData
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static HttpResponse sendXMLDataByHttpsPost(String url, String xmlData)
			throws ClientProtocolException, IOException {
		HttpPost httppost = new HttpPost(url);
		StringEntity entity = new StringEntity(xmlData);
		httppost.setEntity(entity);
		httppost.setHeader("Content-Type", "text/xml;charset=UTF-8");
		HttpResponse response=null;
		try {
			response = HttpClients.custom().setSSLSocketFactory(CertUtil.initCert()).build().execute(httppost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}

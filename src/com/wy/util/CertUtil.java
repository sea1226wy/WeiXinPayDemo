package com.wy.util;

import com.wy.config.WeixinPayConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * 加载证书的类
 * @author Administrator
 *
 */
public class CertUtil {

	/**
     * 加载证书
     */
    public static SSLConnectionSocketFactory initCert() throws Exception {
        FileInputStream instream = null;
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        /*实际开发中这个文件的由来在微信商家管理里面，必须保密。线上开发应该放位置文件里面，不可以放web下面*/
        instream = new FileInputStream(new File("c:/apiclient_cert.p12"));
        keyStore.load(instream, WeixinPayConfig.mch_id.toCharArray());

        if (null != instream) {
            instream.close();
        }

        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore,WeixinPayConfig.mch_id.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        return sslsf;
    }
}

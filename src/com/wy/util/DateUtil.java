package com.wy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther Seay
 * @date 2019/3/18 16:44
 */
public class DateUtil {
    public static String getCurrentDateStr(){
        SimpleDateFormat adf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        return adf.format(new Date());
    }

    public static void main(String[] args) {
        System.out.print(getCurrentDateStr());
    }
}
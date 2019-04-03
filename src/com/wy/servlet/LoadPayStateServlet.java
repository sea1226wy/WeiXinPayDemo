package com.wy.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @auther Seay
 * @date 2019/4/3 9:25
 */
public class LoadPayStateServlet extends HttpServlet {

    private int i = 0;
    private int result = 0;//未支付

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        i++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(i==10){
            result = 1;
        }
        System.out.println("i="+i);
        System.out.println("result="+result);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(result);
        writer.flush();
        writer.close();
    }
}
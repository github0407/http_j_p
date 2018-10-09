//package com.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("执行get方法");
        String string = req.getParameter("account");

        System.err.println("接收到的数据为：" + string);
        resp.setContentType("text/xml;charSet=utf-8");

        resp.getWriter().write("<rot>rr</rot>");
        resp.flushBuffer();
    }

    @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("请求的IP为："+req.getRemoteAddr());
        String string = req.getParameter("account");
        System.err.println("接收到的数据为：" + string);
        String xmlData = null;
        ServletInputStream sis = null;
        try {
            // 取HTTP请求流
            sis = req.getInputStream();
            // 取HTTP请求流长度
            int size = req.getContentLength();
            // 用于缓存每次读取的数据
            byte[] buffer = new byte[size];
            // 用于存放结果的数组
            byte[] xmldataByte = new byte[size];
            int count = 0;
            int rbyte = 0;
            // 循环读取
            while (count < size) {
                // 每次实际读取长度存于rbyte中
                rbyte = sis.read(buffer);
                for (int i = 0; i < rbyte; i++) {
                    xmldataByte[count + i] = buffer[i];
                }
                count += rbyte;
            }

            xmlData = new String(xmldataByte, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(xmlData);
        resp.setContentType("text/xml;charSet=utf-8");

        resp.getWriter().write("OK 200");
        resp.flushBuffer();
    }

}
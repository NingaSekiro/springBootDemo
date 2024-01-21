package com.example.springdemo.demos.web.servlet;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet("/myservlet")
public class MyServlet extends HttpServlet {
    private Logger logger = getLogger(MyServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解析文件
        if (req.getContentType().contains("multipart/form-data")) {
            //解析文件
            String fileName = req.getParameter("fileName");
            resp.getWriter().write(fileName);
        }
    }
}

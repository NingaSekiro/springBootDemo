package com.example.springdemo.demos.web.servlet;

import com.example.springdemo.demos.web.service.TestService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(name="WebServlet",urlPatterns="/myservlet")
public class MyServlet extends HttpServlet {

    @Autowired
    private TestService testService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private Logger logger = getLogger(MyServlet.class);
    public static final String world = "world";

//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        // 父类不初始化会导致getServletContext()空指针异常
//        super.init(config);
//        // 将当前this 的 Servlet交给Spring托管，因此该Servlet可以用@Autowired
//        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        testService.test();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        resp.getWriter().write("sadas");
    }
}

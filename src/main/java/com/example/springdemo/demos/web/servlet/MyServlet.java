package com.example.springdemo.demos.web.servlet;

import com.example.springdemo.demos.web.service.TestService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(name = "WebServlet", urlPatterns = "/myservlet")
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
        // 设置响应的内容类型为应用程序八进制流，这是文件下载常用的Content-Type
        resp.setContentType("application/octet-stream");
        // 设置响应头，指示浏览器以附件形式下载文件，而不是直接打开
        String fileName = "1.txt"; // 文件名，根据实际情况替换
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        String resourceName = "1.txt"; // 资源名称（无需路径前缀）
        try (InputStream in = getClass().getResourceAsStream("/" + resourceName); // 使用类加载器获取资源
             OutputStream out = resp.getOutputStream()) {
            // 使用缓冲区读取文件并写入输出流
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            // 刷新输出流确保所有数据被写入
            out.flush();
        } catch (IOException e) {
            // 处理文件读取或写入时发生的异常
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Failed to download the file.");
            e.printStackTrace();
        }
    }

}

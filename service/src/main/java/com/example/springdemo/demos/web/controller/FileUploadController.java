package com.example.springdemo.demos.web.controller;

import com.example.springdemo.demos.web.servlet.MyServlet;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class FileUploadController {
    private Logger logger = getLogger(MyServlet.class);

//    @RequestMapping("/")
//    public String index() {
//        return "uploadForm"; // 这里返回HTML页面的名称，假设它在templates目录下
//    }

    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        logger.info("post请求");
        if (file.isEmpty()) {
            return "请选择文件上传.";
        }

        // 在实际应用中，你可能需要将文件保存到服务器上的特定目录
        // 这里只是一个简单的演示
        // 你可以使用 file.transferTo(new File("你的文件路径")) 来保存文件

        return "文件上传成功!";
    }
}
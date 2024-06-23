package com.example.springdemo;

import com.example.springdemo.demos.web.controller.BasicController;
import com.example.springdemo.demos.web.model.SysUser;
import com.example.springdemo.demos.web.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
@AutoConfigureMockMvc
public class SpringXmlTests {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    private BasicController basicController;

    @Mock
    private SysUserService sysUserService;


    @Test
    public void test() throws InterruptedException {
        basicController=(BasicController) context.getBean("basicController");
        ReflectionTestUtils.setField(basicController, "sysUserService", sysUserService);
        PowerMockito.when(sysUserService.saveUser(any())).thenReturn(2);
        SysUser sysUser=new SysUser();
        sysUser.setId(1L);
        basicController.saveUser(sysUser);
    }
}


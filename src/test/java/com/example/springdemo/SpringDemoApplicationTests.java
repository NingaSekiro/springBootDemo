package com.example.springdemo;

import com.example.springdemo.demos.web.controller.BasicController;
import com.example.springdemo.demos.web.service.SysUserService;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@ActiveProfiles(value="test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PowerMockIgnore("javax.management.*")
public class SpringDemoApplicationTests {

    @Autowired
    private BasicController basicController;

    @Mock
    private SysUserService sysUserService;


//    @Test
//    public void test() throws InterruptedException {
//        ReflectionTestUtils.setField(basicController, "sysUserService", sysUserService);
//        PowerMockito.when(sysUserService.saveUser(any())).thenReturn(2);
//        SysUser sysUser=new SysUser();
//        sysUser.setId(1L);
//        basicController.saveUser(sysUser);
//    }


}


package com.example.demo.transaction;


import com.example.demo.dynamictxdemo.DynamicTxDemoApplication;
import com.example.demo.dynamictxdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@SpringBootTest(classes = DynamicTxDemoApplication.class)
class TestTxTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test
    void test() {
        log.info("实际的事务管理器类是: " + transactionManager.getClass());
        userService.txFail();
    }

}

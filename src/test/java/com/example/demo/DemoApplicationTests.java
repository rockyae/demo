package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void verify_behaviour(){
        //模拟创建一个List对象
        List mock = mock(List.class);
        //使用mock的对象
        mock.add(1);
        mock.clear();
        //验证add(1)和clear()行为是否发生
        verify(mock).add(1);
        verify(mock).clear();
    }

}

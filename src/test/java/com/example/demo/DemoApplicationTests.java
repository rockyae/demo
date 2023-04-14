package com.example.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.example.demo.easyexcel.DemoData;
import com.example.demo.easyexcel.DemoDataListener;
import com.example.demo.util.TestFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
@Slf4j
class DemoApplicationTests {


    @Test
    public void simpleRead(){
        log.info("内部");
        String fileName = "/excel/demo.xlsx";
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        EasyExcel.read(inputStream, DemoData.class,new DemoDataListener(
        dataList-> {
            for (DemoData demoData : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }
        })).sheet().doRead();
    }

}

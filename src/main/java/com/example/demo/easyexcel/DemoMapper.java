package com.example.demo.easyexcel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface DemoMapper {
    public void batchInsert(List<DemoData> list);
}

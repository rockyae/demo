package com.example.demo.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class DemoDataListener implements ReadListener<DemoData> {

    private static final int BATCH_COUNT = 100;

    private List<DemoData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        cachedDataList.add(data);
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    public DemoDataListener(DemoMapper demoMapper) {
        this.demoMapper = demoMapper;
    }
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        demoMapper.batchInsert(cachedDataList);
        log.info("存储数据库成功！");
    }

}

package com.example.demo.dynamictxdemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.ds1")
    public DataSource db1() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @ConfigurationProperties("spring.datasource.druid.ds2")
    @Bean
    public DataSource db2() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Primary
    @Bean
    public DataSource dynamicDataSource(@Qualifier("db1") DataSource db1,
                                          @Qualifier("db2") DataSource db2) {
        DynamicDataSource ds = new DynamicDataSource();
        Map<Object, Object> map = new HashMap<>();
        map.put("db1", db1);
        map.put("db2", db2);

        ds.setTargetDataSources(map);
        ds.setDefaultTargetDataSource(db1);
        return ds;
    }

//    @Bean
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("dynamicDataSource") DataSource ds) {
//        return new DataSourceTransactionManager(ds);
//    }



}

package com.example.demo;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "test")
@Data
public class KeyConfig {
    private List<String> keys;
}

package com.example.demo.module;

public class ConverterImpl {
    public static String convertColumnNameToPropertyName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (int i = 0; i < camels.length; i++) {
            if (i == 0) {
                continue;
            }
            if (i == 1) {
                result.append(camels[i]);
            } else {
                result.append(camels[i].substring(0, 1).toUpperCase());
                result.append(camels[i].substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
}


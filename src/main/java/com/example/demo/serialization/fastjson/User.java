package com.example.demo.serialization.fastjson;

/**
 * @Title: User
 * @Author rockyae
 * @Package com.example.demo.serialization.fastjson
 * @Date 2025/10/12 11:26
 * @description:
 */
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.lang.reflect.Type;
import java.util.*;

public class User {
    @JSONField()
    private List<String> tags;

    // 构造函数
    public User() {}

    // Getter和Setter
    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    // 便利方法
    public boolean hasTag(String tagName) {
        return tags != null && tags.contains(tagName);
    }

    public String getFirstTag() {
        return (tags != null && !tags.isEmpty()) ? tags.get(0) : null;
    }
}

// 自定义反序列化器
class FlexibleListDeserializer implements ObjectDeserializer {

    @Override
    public List<String> deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Object value = parser.parse();
        List<String> result = new ArrayList<>();

        if (value == null) {
            return result;
        }

        if (value instanceof String) {
            // 单个字符串
            result.add((String) value);
        } else if (value instanceof JSONArray) {
            // JSON数组
            JSONArray array = (JSONArray) value;
            for (Object element : array) {
                if (element instanceof String) {
                    result.add((String) element);
                } else if (element instanceof JSONObject) {
                    JSONObject obj = (JSONObject) element;
                    // 提取name字段，没有就用整个对象
                    if (obj.containsKey("name")) {
                        result.add(obj.getString("name"));
                    } else {
                        result.add(obj.toString());
                    }
                } else {
                    result.add(element.toString());
                }
            }
        } else if (value instanceof JSONObject) {
            // 单个JSON对象
            JSONObject obj = (JSONObject) value;
            if (obj.containsKey("name")) {
                result.add(obj.getString("name"));
            } else {
                result.add(obj.toString());
            }
        } else {
            // 其他类型转换为字符串
            result.add(value.toString());
        }

        return result;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
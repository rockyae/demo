package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        //指定redis key,value的序列化方式
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //Jackson2JsonRedisSerializer序列化器反序列为对象时会出现map不能转为对象的异常
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(getObjectMapper());
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer =  new GenericJackson2JsonRedisSerializer();
        template.setConnectionFactory(factory);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        om.registerModule(javaTimeModule);
        om.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        om.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        om.isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        om.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.isEnabled(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 此项必须配置，否则会报java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to XXX
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        om.disable(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS);
        return om;
    }

    /**
     * redisTemplate存储对象时会浪费存储空间，可以手动实现序列化来避免存储对象的类模板
     * 手动序列化统一使用StringRedisSerializer,序列化和反序列化时手动处理.
     *
     * @param factory
     * @return
     */
    @Bean(name = "redisForString")
    public RedisTemplate<String, String> redisForString(RedisConnectionFactory factory) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}

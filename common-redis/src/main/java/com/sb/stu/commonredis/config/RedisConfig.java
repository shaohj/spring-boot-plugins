package com.sb.stu.commonredis.config;

import com.sb.stu.commonredis.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 编  号：
 * 名  称：RedisConfig
 * 描  述：
 * 完成日期：2018/8/4 15:30
 * @author：felix.shao
 */
@Configuration
public class RedisConfig {

    /**
     * 默认的redis模板,注意设置为默认的模板,否则改方法名等操作会导致注入时,多个相匹配的模板不知道使用哪个,导致报错
     * @Title: redisTemplate 
     * @param redisFactory
     * @return
     */
    @Bean
    @Primary
    public RedisTemplate<String, ?> objectRedisTemplate(RedisConnectionFactory redisFactory) {
        RedisTemplate<String, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisFactory);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new RedisObjectSerializer());
        return redisTemplate;
    }
    
    /**
     * 自定义UserRedis模板
     * @Title: userRedisTemplate 
     * @param redisFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, User> userRedisTemplate(RedisConnectionFactory redisFactory) {
        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(redisFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

}

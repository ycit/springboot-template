package com.vastio.basic.service.impl;

import com.vastio.basic.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * redis 相关业务
 *
 * @author xlch
 * @Date 2018-02-24 18:00
 */
@Service
public class RedisService implements CacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisService.class);

    private StringRedisTemplate stringRedisTemplate;

    @Resource
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void updateByKey(String key, String value) {
        LOGGER.debug(" update redis cache, key is ==========={}", key);
        String originalVal = stringRedisTemplate.opsForValue().get(key);
        LOGGER.debug(" update redis cache, original value is ==========={}", originalVal);
        stringRedisTemplate.opsForValue().set(key, value);
    }

}

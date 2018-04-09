package com.vastio.basic.controller.base;

import com.vastio.basic.common.model.Org;
import com.vastio.basic.common.model.User;
import com.vastio.basic.common.service.IOrgService;
import com.vastio.basic.dao.UserCoreMapper;
import com.vastio.basic.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private IOrgService orgService;

    @Autowired
    private UserCoreMapper mapper;

    private StringRedisTemplate stringRedisTemplate;

    @Resource
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private RedisTemplate redisTemplate;

    @Resource
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/{id}")
    public Org getOrg(@PathVariable("id") Long id) {

        return orgService.selectById(id);
    }

    @GetMapping(value = "/redis")
    public void redisTest() {
        String key = "name";
        String value = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isBlank(value)) {
            value = "pps2";
            LOGGER.debug("find key from db ===========");
            stringRedisTemplate.opsForValue().set(key, value);
        } else {
            LOGGER.debug("find key from redis ===========value is {}", value);
        }
    }

    @GetMapping(value = "/redis/obj")
    public void redisObjTest() {
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        user.setCreateTime(new Date());
        String key = "user:" + user.getId();
        Object user1 = redisTemplate.opsForValue().get(key);
        if (user1 == null) {
            LOGGER.debug("find key from db ===========");
            redisTemplate.opsForValue().set(key, user);
        } else {
            LOGGER.debug("find key from redis ===========object is {}", user1);
        }
        redisTemplate.convertAndSend("pps3", "world");
    }

    public void restTemplateDefault() {

    }

    public static void main(String[] args) {
        String str = CommonUtil.encoderByMD5("dsj@dpj287");
        LOGGER.debug(str);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://www.baidu.com";
        String result = restTemplate.getForObject(url, String.class);
        LOGGER.debug(result);

    }
}

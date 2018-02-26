package com.vastio.basic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * 消息统一接收
 *
 * @author xlch
 * @Date 2018-02-24 16:43
 */
@Component
public class MsgReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MsgReceiver.class);

    private CountDownLatch countDownLatch;

    @Resource
    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void receiveMsg(String msg) {
        LOGGER.debug("receive msg is :========== {}", msg);
        countDownLatch.countDown();
    }
}

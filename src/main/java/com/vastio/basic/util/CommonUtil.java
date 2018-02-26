package com.vastio.basic.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class CommonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

    private CommonUtil() {
    }

    public static String encoderByMD5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes("utf-8"));
            return convertByteToHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("MD5实例创建失败");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("MD5加密失败");
        }
        return "";
    }

    private static String convertByteToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int temp = bytes[i] & 0xff;
            String tempHex = Integer.toHexString(temp);
            if (tempHex.length() < 2) {
                stringBuilder.append("0");
                stringBuilder.append(tempHex);
            } else {
                stringBuilder.append(tempHex);
            }
        }
        return stringBuilder.toString();
    }
}

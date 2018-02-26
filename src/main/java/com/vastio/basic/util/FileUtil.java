package com.vastio.basic.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public final class FileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void saveFile(String content, String path) {
        File file = new File(path);
        try (
                FileOutputStream fop = new FileOutputStream(file)
        ) {
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static String getFileContent(String path) {
        File file = new File(path);
        String encoding = "UTF-8";
        String lineTxt = "";
        StringBuilder stringBuilder = new StringBuilder();
        if (file.isFile() && file.exists()) {
            try (InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                 BufferedReader bufferedReader = new BufferedReader(read)) {
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    stringBuilder.append(lineTxt);
                }
            } catch (Exception e) {
                LOGGER.error("读取文件内容出错");
            }
        } else {
            LOGGER.error("找不到指定的文件");
        }
        return stringBuilder.toString();
    }

}

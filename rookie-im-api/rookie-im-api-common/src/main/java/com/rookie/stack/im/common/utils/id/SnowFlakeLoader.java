package com.rookie.stack.im.common.utils.id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
public class SnowFlakeLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(SnowFlakeLoader.class);

    public static final String DATA_CENTER_ID = "data.center.id";
    public static final String MACHINE_ID = "machine.id";

    private volatile static Properties instance;
    static {
        InputStream in = SnowFlakeLoader.class.getClassLoader().getResourceAsStream("snowflake/snowflake.properties");
        instance = new Properties();
        try {
            instance.load(in);
        } catch (IOException e) {
            LOGGER.error("SnowFlakeLoader|加载properties文件失败|{}", e.getMessage());
            e.printStackTrace();
        }
    }

    private static String getStringValue(String key){
        if(instance == null) {
            return "";
        }
        return instance.getProperty(key, "");
    }

    private static Long getLongValue(String key){
        String v = getStringValue(key);
        return (v == null || v.trim().isEmpty()) ? 0 : Long.parseLong(v);
    }

    public static Long getDataCenterId() {
        return getLongValue(DATA_CENTER_ID);
    }

    public static Long getMachineId() {
        return getLongValue(MACHINE_ID);
    }
}

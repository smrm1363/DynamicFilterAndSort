package com.glovoapp.backender;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class is used to getting property from property files
 */
public class Util {
    /**
     * For getting propery from property file
     * @param filePath path of the file
     * @param key is the key of the property
     * @return the value of the property
     */
    public static String getProperty(String filePath,String key)
    {

        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appProps.getProperty(key);
    }

    /**
     * For getting property from application.properties file
     * @param key is the key of the property
     * @return the value of the property
     */
    public static String getProperty(String key)
    {

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "application.properties";
        return getProperty(appConfigPath,key);
    }
}

package com.basePackage.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Hafiz on 4/20/2016.
 */
public class ReadProperties {

    private final static Logger logger     =
            LoggerFactory.getLogger(ReadProperties.class);
    private static Properties properties = new Properties();
    public static Properties demoProperties(){
        try {
            InputStream inputStream = ReadProperties.class.getClassLoader()
                    .getResourceAsStream(AppConstant.DB_PROPERTIES_FILE);
            properties.load(inputStream);
            logger.info("reading properties...");
        } catch (IOException e) {

            logger.error("Error:"+e.getMessage());
        }

        return properties;
    }
}

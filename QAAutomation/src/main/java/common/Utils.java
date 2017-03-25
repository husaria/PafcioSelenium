package common;


import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class Utils {
    private String getProperty(String property) {
        try {
            Properties properties = new Properties();

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("App.properties");
            properties.load(inputStream);

            return properties.getProperty(property);

        } catch (Exception e) {
            System.out.println("Unable to read or find property: " + property + ". Error: " + e);
            return null;
        }
    }


    public String getTodayDateDefaultFormat() {
        java.util.Date juDate = new Date();
        DateTime dt = new DateTime(juDate);
        return dt.toLocalDate().toString();
    }


    public String getTodayDateUSFormat() {
        java.util.Date juDate = new Date();
        DateTime dt = new DateTime(juDate);
        dt.toLocalDate();
        return dt.toString(DateTimeFormat.shortDate());
    }


    public String generateRandomText() {
        return RandomStringUtils.randomAlphabetic(15);
    }
}

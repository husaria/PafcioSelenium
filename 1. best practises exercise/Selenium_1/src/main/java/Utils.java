import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    public String getPropertyValue(String propertyName) throws IOException {

        try {
            Properties properties = new Properties();
            String propertiesFileName = "data.properties";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
            properties.load(inputStream);

            return properties.getProperty(propertyName);

        } catch (Exception e) {
            System.out.println("Unable to read / find property: " + e);
            return null;
        }
    }
}

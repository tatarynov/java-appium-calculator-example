package utils.managers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    private final static Logger LOG = LoggerFactory.getLogger(PropertiesManager.class);

    private Properties properties = new Properties();
    private String deviceId;
    private String host;
    private String port;
    private static PropertiesManager instance;

    public final static String DEVICE_ID_PROPERTY = "id";
    public final static String HOST_PROPERTY = "host";
    public final static String PORT_PROPERTY = "port";


    private PropertiesManager() {
        // place for reading default properties
        // from config.properties file
        InputStream inputStream;
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
            properties.load(inputStream);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            LOG.error("Configuration file is not found!");

        }
    }

    public static synchronized PropertiesManager getInstance() {
        if (instance == null) instance = new PropertiesManager();
        return instance;
    }

    public String getDeviceId() {
        String sysProp = System.getProperty(DEVICE_ID_PROPERTY, "");
        deviceId = sysProp.isEmpty() ? properties.getProperty(DEVICE_ID_PROPERTY) : sysProp;

        if (deviceId.isEmpty()) {
            LOG.error("Please provide valid device ID or change default 'id' value in config.properties file!");
            LOG.error("Example command: mvn test -Did=12345");
            System.exit(1);
        }

        LOG.info("Device id is '{}'", deviceId);
        return deviceId;
    }

    public String getHost() {
        return host = System.getProperty(HOST_PROPERTY, "");
    }

    public String getPort() {
        return port = System.getProperty(PORT_PROPERTY, "");

    }
}

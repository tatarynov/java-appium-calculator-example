package utils.managers;

import exceptions.UnsupportedManufacturerException;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.server.AppiumServer;
import utils.values.PackageNames;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;
import static utils.values.Waits.DEFAULT_IMPLICIT_WAIT_TIMEOUT;
import static utils.values.Waits.NEW_COMMAND_TIMEOUT_SEC;

public class DriverManager {

    private static final Logger LOG = LoggerFactory.getLogger(DriverManager.class);

    private AppiumServer invokeAppiumServer = new AppiumServer();
    private AndroidDriver<MobileElement> driver;

    private static ThreadLocal<AppiumServer> appiumServerThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<AndroidDriver<MobileElement>> driverThreadLocal = new ThreadLocal<>();

    public enum AutomationName {
        APPIUM("Appium"),
        UIAUTOMATOR2("UiAutomator2"),
        ESSPRESSO("Esspresso");

        private String name;

        AutomationName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public synchronized AndroidDriver<MobileElement> setUp(AutomationName automationName) throws IOException, InterruptedException, UnsupportedManufacturerException {
        LOG.info("Setting Android driver");
        PropertiesManager propertiesManager = PropertiesManager.getInstance();
        String appiumUrl = propertiesManager.getHost().isEmpty() ? invokeAppiumServer.startAppiumServer()
                : "http://" + propertiesManager.getHost() + ":" + propertiesManager.getPort() + "/wd/hub";
        LOG.info(appiumUrl);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(PLATFORM_NAME, "Android");
        capabilities.setCapability(DEVICE_NAME, "Android Test Device");
        capabilities.setCapability(UDID, propertiesManager.getDeviceId());
        capabilities.setCapability(AUTOMATION_NAME, automationName.getName());
        capabilities.setCapability(APP_PACKAGE, PackageNames.Calculator.getPackageName());
        capabilities.setCapability(APP_ACTIVITY, PackageNames.Calculator.getStartActivityName());
        capabilities.setCapability(AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability(NEW_COMMAND_TIMEOUT, NEW_COMMAND_TIMEOUT_SEC);
        capabilities.setCapability(NO_RESET, true);

        driver = new AndroidDriver<>(new URL(appiumUrl), capabilities);

        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_TIMEOUT, TimeUnit.MILLISECONDS);
        driverThreadLocal.set(driver);
        appiumServerThreadLocal.set(invokeAppiumServer);

        return driver;
    }

    public AndroidDriver<MobileElement> getDriver() {
        return driverThreadLocal.get();
    }

    public AppiumServer getServer() {
        return appiumServerThreadLocal.get();
    }
}

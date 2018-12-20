package base;

import exceptions.UnsupportedManufacturerException;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.BeforeClass;
import utils.android.AdbController;
import utils.managers.DriverManager;

import java.io.IOException;

import static utils.values.PackageNames.Appium.*;

public class BaseTest {

    // managers
    protected static DriverManager driverManager = new DriverManager();
    protected static AdbController adbController = AdbController.getInstance();
    protected static AndroidDriver<MobileElement> driver;

    private static boolean initialized;

    @BeforeClass
    public static void setUp() throws IOException, InterruptedException, UnsupportedManufacturerException {
        if (!initialized) {
            adbController.uninstallPackage(APPIUM_IO_UNOCK_PACKAGE);
            adbController.uninstallPackage(APPIUM_SETTINGS_PACKAGE);
            adbController.uninstallPackage(APPIUM_SERVER);
            adbController.uninstallPackage(APPIUM_SERVER_TEST);

            driver = driverManager.setUp(DriverManager.AutomationName.UIAUTOMATOR2);
            driver.resetApp();

            initialized = true;
        }
    }

    @After
    public void launchApp() {
        // restarts session after each test method
        driver.launchApp();
    }
}

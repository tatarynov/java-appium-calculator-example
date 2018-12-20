package pageobjects.screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.android.AdbController;
import utils.android.GesturesController;
import utils.values.Waits;

import java.time.Duration;

import static utils.values.Waits.POLLING_TIMEOUT_MS;

public class BaseScreen {

    protected AndroidDriver<MobileElement> driver;
    protected GesturesController gesturesController;
    protected AdbController adbController;

    private static final Logger LOG = LoggerFactory.getLogger(BaseScreen.class);

    public BaseScreen(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.gesturesController = new GesturesController(driver);
        this.adbController = AdbController.getInstance();
    }

    public void goBack() {
        LOG.info("Navigating back");
        driver.navigate().back();
    }

    protected MobileElement waitForElement(By element, int seconds) {
        LOG.debug("Waiting for element {}", element);
        return (MobileElement) new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(POLLING_TIMEOUT_MS))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    protected MobileElement waitForElement(By element) {
        return waitForElement(element, Waits.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
    }
}

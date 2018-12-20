package pageobjects.screens.google;

import enums.Operation;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.screens.base.HistoryCalculatorScreen;
import pageobjects.screens.base.MainCalculatorScreen;
import pageobjects.screens.BaseScreen;
import utils.android.Direction;

public class MainScreen extends BaseScreen implements MainCalculatorScreen {

    private final By equals = By.id("eq");
    private final By advancedPad = By.id("pad_advanced");
    private final By mainPad = By.id("pad_pager");
    private final By result = By.id("result");

    private static final String BUTTON_LOCATOR_PATTERN = "//android.widget.Button[@text='%s']";

    private static final Logger LOG = LoggerFactory.getLogger(MainScreen.class);

    public MainScreen(AndroidDriver<MobileElement> driver) {
        super(driver);
        initializeScreen();
    }

    private void initializeScreen() {
        LOG.debug("Waiting for elements on screen");
        waitForElement(result);
        waitForElement(mainPad);
    }

    public MainScreen tapDigit(int digit) {
        LOG.info("Tap digit {}", digit);
        String[] digits = String.valueOf(digit).split("");
        for (String dig : digits) {
            driver.findElement(getXpathLocator(dig)).click();
        }
        return this;
    }

    public MainScreen equals() {
        LOG.info("Equals");
        driver.findElement(equals).click();
        return this;
    }

    public HistoryCalculatorScreen openHistory() {
        LOG.info("Open history");
        MobileElement resultPad = driver.findElement(result);
        gesturesController.swipeInsideElement(Direction.DOWN, resultPad);
        return new HistoryScreen(driver);
    }

    public MainScreen openAdvancedPad() {
        LOG.info("Open Advanced Pad");
        driver.findElement(advancedPad).click();
        return this;
    }

    public String getResult() {
        LOG.info("Getting result...");
        String text = driver.findElement(result).getText();
        LOG.info("Result is {}", text);
        return text;
    }

    public MainCalculatorScreen makeOperation(Operation operation) {
        LOG.info("Make operation {}", operation.toString());
        driver.findElement(By.id(operation.getId())).click();
        return this;
    }


    private By getXpathLocator(String elementText) {
        return By.xpath(String.format(BUTTON_LOCATOR_PATTERN, elementText));
    }
}

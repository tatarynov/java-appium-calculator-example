package pageobjects.screens.google;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.screens.base.HistoryCalculatorScreen;
import pageobjects.screens.BaseScreen;

import java.util.List;

public class HistoryScreen extends BaseScreen implements HistoryCalculatorScreen {

    private final By historyFormula = By.id("history_formula");
    private static final Logger LOG = LoggerFactory.getLogger(HistoryScreen.class);

    public HistoryScreen(AndroidDriver<MobileElement> driver) {
        super(driver);
    }

    public List<MobileElement> getHistory() {
        LOG.info("Getting history...");
        List<MobileElement> elements = driver.findElements(historyFormula);
        LOG.info("Found {} formulas", elements.size());
        return elements;
    }

}

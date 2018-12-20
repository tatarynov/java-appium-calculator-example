package pageobjects.screens.xiaomi;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import pageobjects.screens.base.HistoryCalculatorScreen;
import pageobjects.screens.BaseScreen;

import java.util.List;

public class HistoryScreen extends BaseScreen implements HistoryCalculatorScreen {

    public HistoryScreen(AndroidDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public List<MobileElement> getHistory() {
        //TODO: implement
        return null;
    }
}

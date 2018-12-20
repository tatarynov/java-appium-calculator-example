package pageobjects.screens.xiaomi;

import enums.Operation;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import pageobjects.screens.base.HistoryCalculatorScreen;
import pageobjects.screens.base.MainCalculatorScreen;
import pageobjects.screens.BaseScreen;

public class MainScreen extends BaseScreen implements MainCalculatorScreen {

    public MainScreen(AndroidDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public String getResult() {
        // TODO: implement
        return null;
    }

    @Override
    public MainCalculatorScreen tapDigit(int digit) {
        // TODO: implement
        return null;
    }

    @Override
    public MainCalculatorScreen equals() {
        // TODO: implement
        return null;
    }

    @Override
    public MainCalculatorScreen openAdvancedPad() {
        // TODO: implement
        return null;
    }

    @Override
    public MainCalculatorScreen makeOperation(Operation operation) {
        // TODO: implement
        return null;
    }

    @Override
    public HistoryCalculatorScreen openHistory() {
        // TODO: implement
        return null;
    }
}

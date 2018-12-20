package pageobjects.factory;

import enums.Manufacturer;
import exceptions.UnsupportedManufacturerException;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import pageobjects.screens.base.HistoryCalculatorScreen;
import pageobjects.screens.base.MainCalculatorScreen;
import pageobjects.screens.google.HistoryScreen;
import pageobjects.screens.samsung.MainScreen;
import utils.android.AdbController;

public class ScreenFactory {

    private static AdbController adbController = AdbController.getInstance();

    public static MainCalculatorScreen getMainScreen(AndroidDriver<MobileElement> driver) throws UnsupportedManufacturerException {
        if (adbController.isManufacturer(Manufacturer.SAMSUNG)) {
            return new MainScreen(driver);
        } else if (adbController.isManufacturer(Manufacturer.GOOGLE)) {
            return new pageobjects.screens.google.MainScreen(driver);
        } else if (adbController.isManufacturer(Manufacturer.XIAOMI)) {
            return new pageobjects.screens.xiaomi.MainScreen(driver);
        } else {
            throw new UnsupportedManufacturerException("Device of this manufacturer is not supported!");
        }
    }

    public static HistoryCalculatorScreen getHistoryScreen(AndroidDriver<MobileElement> driver) throws UnsupportedManufacturerException {
        if (adbController.isManufacturer(Manufacturer.SAMSUNG)) {
            return new HistoryScreen(driver);
        } else if (adbController.isManufacturer(Manufacturer.GOOGLE)) {
            return new pageobjects.screens.google.HistoryScreen(driver);
        } else if (adbController.isManufacturer(Manufacturer.XIAOMI)) {
            return new pageobjects.screens.xiaomi.HistoryScreen(driver);
        } else {
            throw new UnsupportedManufacturerException("Device of this manufacturer is not supported!");
        }
    }
}

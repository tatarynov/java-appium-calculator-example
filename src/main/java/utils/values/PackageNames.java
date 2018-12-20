package utils.values;


import enums.Manufacturer;
import exceptions.UnsupportedManufacturerException;
import utils.android.AdbController;

public final class PackageNames {

    public static final class Calculator {

        private static String googleCalculatorPackageName = "com.android.calculator2";
        private static String googleCalculatorStartActivity = "com.android.calculator2.Calculator";

        private static String samsungCalculatorPackageName = "com.sec.google.app.popupcalculator";
        private static String samsungCalculatorStartActivity = "com.sec.google.app.popupcalculator.Calculator";

        private static String xiaomiCalculatorPackageName = "com.sec.google.app.popupcalculator";
        private static String xiaomiCalculatorStartActivity = "com.sec.google.app.popupcalculator.Calculator";

        public static String getPackageName() throws UnsupportedManufacturerException {
            AdbController adbController = AdbController.getInstance();
            if (adbController.isManufacturer(Manufacturer.SAMSUNG)) {
                return samsungCalculatorPackageName;
            } else if (adbController.isManufacturer(Manufacturer.GOOGLE)) {
                return googleCalculatorPackageName;
            } else if (adbController.isManufacturer(Manufacturer.XIAOMI)) {
                return xiaomiCalculatorPackageName;
            } else {
                throw new UnsupportedManufacturerException("Device of this manufacturer is not supported!");
            }
        }

        public static String getStartActivityName() throws UnsupportedManufacturerException {
            AdbController adbController = AdbController.getInstance();
            if (adbController.isManufacturer(Manufacturer.SAMSUNG)) {
                return samsungCalculatorStartActivity;
            } else if (adbController.isManufacturer(Manufacturer.GOOGLE)) {
                return googleCalculatorStartActivity;
            } else if (adbController.isManufacturer(Manufacturer.XIAOMI)) {
                return xiaomiCalculatorStartActivity;
            } else {
                throw new UnsupportedManufacturerException("Device of this manufacturer is not supported!");
            }
        }
    }

    public static final class Appium {
        /**
         * Default Appium support packages
         * which are being installed with each session
         */
        public static final String APPIUM_SETTINGS_PACKAGE = "io.appium.settings";
        public static final String APPIUM_IO_UNOCK_PACKAGE = "io.appium.unlock";
        public static final String APPIUM_SERVER = "io.appium.uiautomator2.server";
        public static final String APPIUM_SERVER_TEST = "io.appium.uiautomator2.server.test";
    }


}

package tests;

import base.BaseTest;
import enums.AdvancedOperation;
import exceptions.UnsupportedManufacturerException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import pageobjects.screens.base.MainCalculatorScreen;
import pageobjects.factory.ScreenFactory;

import static enums.BasicOperation.DIVIDE;

public class SineTest extends BaseTest {

    private static final double EXPECTED_RESULT_SINE = 0.5;
    private static final int DIVIDER = 6; //   Pi / 6 == 30 degrees

    @Test
    public void testCSine() throws UnsupportedManufacturerException {
        MainCalculatorScreen mainScreen = ScreenFactory.getMainScreen(driver);
        mainScreen
                .openAdvancedPad()
                .makeOperation(AdvancedOperation.SINE)
                .makeOperation(AdvancedOperation.PI)
                .goBack();
        mainScreen
                .makeOperation(DIVIDE)
                .tapDigit(DIVIDER)
                .openAdvancedPad()
                .makeOperation(AdvancedOperation.RIGHT_PARENTHESES)
                .goBack();
        String result = mainScreen
                .equals()
                .getResult();

        Assertions.assertThat(Double.valueOf(result))
                .as("Result of sin(30) is equal to %1$,.1f", EXPECTED_RESULT_SINE)
                .isEqualTo(EXPECTED_RESULT_SINE);
    }
}

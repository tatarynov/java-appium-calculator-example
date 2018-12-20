package tests;

import base.BaseTest;
import enums.AdvancedOperation;
import exceptions.UnsupportedManufacturerException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import pageobjects.screens.base.MainCalculatorScreen;
import pageobjects.factory.ScreenFactory;

import static enums.BasicOperation.MINUS;
import static enums.BasicOperation.MULTIPLY;

public class ParenthesesTest extends BaseTest {

    // test data
    private static final int FIRST_INT = 10;
    private static final int SECOND_INT = 2;
    private static final int THIRD_INT = 2;
    private static final int EXPECTED_RESULT_NOT_EQUALS = 20;

    @Test
    public void testParentheses() throws UnsupportedManufacturerException {
        MainCalculatorScreen mainScreen = ScreenFactory.getMainScreen(driver);
        mainScreen
                .openAdvancedPad()
                .makeOperation(AdvancedOperation.LEFT_PARENTHESES)
                .goBack();
        mainScreen
                .tapDigit(FIRST_INT)
                .makeOperation(MINUS)
                .tapDigit(SECOND_INT)
                .openAdvancedPad()
                .makeOperation(AdvancedOperation.RIGHT_PARENTHESES)
                .goBack();
        String result = mainScreen
                .makeOperation(MULTIPLY)
                .tapDigit(THIRD_INT)
                .equals()
                .getResult();

        Assertions.assertThat(Integer.valueOf(result))
                .as("Result is not equal %d", EXPECTED_RESULT_NOT_EQUALS)
                .isNotEqualTo(EXPECTED_RESULT_NOT_EQUALS);
    }
}

package tests;

import base.BaseTest;
import enums.BasicOperation;
import exceptions.UnsupportedManufacturerException;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import pageobjects.factory.ScreenFactory;

import java.util.Arrays;
import java.util.Collection;

import static enums.BasicOperation.MINUS;
import static enums.BasicOperation.PLUS;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SimpleMathOperationsTest extends BaseTest {

    // test data for basic arithmetical operations test
    @Parameterized.Parameter()
    public int firstInt;

    @Parameterized.Parameter(1)
    public int secondInt;

    @Parameterized.Parameter(2)
    public int expectedResult;

    @Parameterized.Parameter(3)
    public BasicOperation operation;


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {2, 3, 5, PLUS},
                {10, 2, 8, MINUS}};
        return Arrays.asList(data);
    }

    @Test
    public void testASimpleMathOperation() throws UnsupportedManufacturerException {
        String result = ScreenFactory.getMainScreen(driver)
                .tapDigit(firstInt)
                .makeOperation(operation)
                .tapDigit(secondInt)
                .equals()
                .getResult();

        Assertions.assertThat(Integer.valueOf(result))
                .as("Check %s operation", operation.toString())
                .isEqualTo(expectedResult);
    }
}

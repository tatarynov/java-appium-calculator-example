package tests;

import base.BaseTest;
import exceptions.UnsupportedManufacturerException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import pageobjects.factory.ScreenFactory;

public class HistoryTest extends BaseTest {

    private final static int EXPECTED_HISTORY_SIZE = 4;

    @Test
    public void testHistory() throws UnsupportedManufacturerException {
        int historySize = ScreenFactory.getMainScreen(driver)
                .openHistory()
                .getHistory()
                .size();

        Assertions.assertThat(historySize)
                .as("There are 4 formulas in history")
                .isEqualTo(EXPECTED_HISTORY_SIZE);
    }
}

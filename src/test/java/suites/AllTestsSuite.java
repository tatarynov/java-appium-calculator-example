package suites;

import base.BaseTest;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.HistoryTest;
import tests.ParenthesesTest;
import tests.SimpleMathOperationsTest;
import tests.SineTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({SimpleMathOperationsTest.class, ParenthesesTest.class, SineTest.class, HistoryTest.class})
public class AllTestsSuite extends BaseTest {

    // running only one time after all classes in suite
    @AfterClass
    public static void tearDown() {
        if (driver != null) driver.quit();
        driverManager.getServer().killAppiumProcess();
    }
}

package sel1.kan.testcase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import sel1.kan.test.LoginTest;
import sel1.kan.test.OpenAllProductsTest;
import sel1.kan.wd.Actions;
import sel1.kan.wd.ChromeCreator;

/**
 * Verifies browser log messages.
 */
@RunWith(Suite.class)
@SuiteClasses({ LoginTest.class, OpenAllProductsTest.class })
public class BrowserLogTestcase {
    /**
     * Start URL.
     */
    private static final String URL = "http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1";

    /**
     * After testcase actions.
     */
    @AfterClass
    public static void aftertClass() {
        Actions.getActions().quit();
    }

    /**
     * Before testcase actions.
     */
    @BeforeClass
    public static void beforeClass() {
        Actions actions = Actions.getActions();
        actions.useWebDriver(new ChromeCreator().create());
        actions.get(BrowserLogTestcase.URL);
    }
}

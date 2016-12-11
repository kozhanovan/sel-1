package sel1.kan.testcase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import sel1.kan.test.LinksInNewWindowTest;
import sel1.kan.test.LoginTest;
import sel1.kan.test.SwitchToEditCountryTest;
import sel1.kan.wd.Actions;
import sel1.kan.wd.ChromeCreator;

/**
 * Verifies that links are opened in new window.
 */
@RunWith(Suite.class)
@SuiteClasses({ LoginTest.class, SwitchToEditCountryTest.class,
        LinksInNewWindowTest.class })
public class LinksInNewWindowTestcase {

    /**
     * Start URL.
     */
    private static final String URL = "http://localhost/litecart/admin/";

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
        actions.get(LinksInNewWindowTestcase.URL);
    }
}

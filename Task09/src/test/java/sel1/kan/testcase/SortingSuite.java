package sel1.kan.testcase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import sel1.kan.test.CountriesSortingVerifier;
import sel1.kan.test.Login;
import sel1.kan.test.ZonesVerificationRunner;
import sel1.kan.wd.Actions;
import sel1.kan.wd.ChromeCreator;

/**
 * Verifies countries and zones sorting. Actual verifications are implemented in
 * classed mentioned in 'SuiteClasses' annotation.
 */
@RunWith(Suite.class)
@SuiteClasses({ Login.class, CountriesSortingVerifier.class,
        ZonesVerificationRunner.class })
public class SortingSuite {

    /**
     * Countries URL.
     */
    private static final String COUNTRIES_URL = "http://localhost/litecart/admin/?app=countries&doc=countries";

    /**
     * After class actions.
     */
    @AfterClass
    public static void aftetClass() {
        Actions.getActions().quit();
    }

    /**
     * Before class actions.
     */
    @BeforeClass
    public static void beforeClass() {
        Actions.getActions().useWebDriver(new ChromeCreator().create());
        Actions.getActions().get(SortingSuite.COUNTRIES_URL);
    }
}

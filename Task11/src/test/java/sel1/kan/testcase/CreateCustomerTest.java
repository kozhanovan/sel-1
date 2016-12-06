package sel1.kan.testcase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import sel1.kan.test.ClickCreateNewAccount;
import sel1.kan.test.CreateAccount;
import sel1.kan.test.Login;
import sel1.kan.test.Logout;
import sel1.kan.wd.Actions;
import sel1.kan.wd.ChromeCreator;

/**
 * Verifies customer creation process.
 */
@RunWith(Suite.class)
@SuiteClasses({ ClickCreateNewAccount.class, CreateAccount.class, Logout.class,
        Login.class, Logout.class })
public class CreateCustomerTest {
    /**
     * After testcase actions.
     */
    @AfterClass
    public static void afterClass() {
        Actions.getActions().quit();
    }

    /**
     * Before testcase actions.
     */
    @BeforeClass
    public static void beforeClass() {
        Actions.getActions().useWebDriver(new ChromeCreator().create());
        Actions.getActions().get("http://localhost/litecart");
    }
}

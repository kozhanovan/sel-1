package sel1.kan.testcase;

import java.text.MessageFormat;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sel1.kan.wd.Actions;
import sel1.kan.wd.ChromeCreator;
import sel1.kan.wd.IWDCreator;

/**
 * Clicks on all sections in admin panel.
 */
public class AdminPanelChecker {

    /**
     * Start url.
     */
    private static final String START_URL          = "http://localhost/litecart/admin";

    /**
     * Menu item template.
     */
    private static final String MENU_ITEM_TEMPLATE = "ul#box-apps-menu li:nth-child({0})";

    /**
     * Header locator.
     */
    private static final String HEADER_LOCATOR     = "td#content h1";

    /**
     * Object responsible for {@link WebDriver} creation.
     */
    private final IWDCreator    creator            = new ChromeCreator();

    /**
     * {@link WebDriver} instance.
     */
    private WebDriver           driver;

    /**
     * {@link WebDriverWait} instance.
     */
    private WebDriverWait       wait;

    /**
     * Actions instance.
     */
    private Actions             actions;

    /**
     * After test actions.
     */
    @After
    public void afterTest() {
        this.driver.quit();
    }

    /**
     * Before test actions.
     */
    @Before
    public void beforeTest() {
        this.driver = this.creator.create();
        this.wait = new WebDriverWait(this.driver, 20L);
        this.actions = new Actions(this.driver);
    }

    /**
     * Runs actual test.
     */
    @Test
    public void runTest() {
        this.driver.get(AdminPanelChecker.START_URL);
        this.login("admin", "admin");
        int index = 1;
        boolean bContinue = true;
        while (bContinue) {
            By itemBy = By.cssSelector(MessageFormat
                    .format(AdminPanelChecker.MENU_ITEM_TEMPLATE, index++));
            bContinue = this.actions.findElemens(itemBy);

            if (bContinue) {
                this.actions.clickAndWait(itemBy);
                Assert.assertTrue("Header not found!", this.actions.findElemens(
                        By.cssSelector(AdminPanelChecker.HEADER_LOCATOR)));
            }
        }
    }

    /**
     * Signs in into admin panel.
     *
     * @param login
     *            login to use
     * @param password
     *            password to use
     */
    private void login(final String login, final String password) {
        this.actions.type(By.name("username"), login);
        this.actions.type(By.name("password"), password);
        By loginBy = By.name("login");
        this.driver.findElement(loginBy).click();
        this.actions.waitForAjax();
        By logoutBy = By.xpath("//a[@title='Logout']");
        this.wait
                .until(ExpectedConditions.visibilityOfElementLocated(logoutBy));
    }

}

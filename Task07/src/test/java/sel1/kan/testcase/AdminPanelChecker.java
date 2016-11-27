package sel1.kan.testcase;

import java.text.MessageFormat;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private static final String START_URL              = "http://localhost/litecart/admin";

    /**
     * Menu item template.
     */
    private static final String MENU_ITEM_TEMPLATE     = "ul#box-apps-menu li:nth-child({0})";

    /**
     * Sub menu item template.
     */
    private static final String SUB_MENU_ITEM_TEMPLATE = "li:nth-child({0})";

    /**
     * Header locator.
     */
    private static final String HEADER_LOCATOR         = "td#content h1";

    /**
     * Object responsible for {@link WebDriver} creation.
     */
    private final IWDCreator    creator                = new ChromeCreator();

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
        int mainIndex = 1;
        boolean bMainMenuItemPresent = true;
        while (bMainMenuItemPresent) {
            // Searching main menu items by index, until none found.
            By itemBy = By.cssSelector(MessageFormat
                    .format(AdminPanelChecker.MENU_ITEM_TEMPLATE, mainIndex++));
            bMainMenuItemPresent = this.actions.findElemens(itemBy);

            if (bMainMenuItemPresent) {
                // Click on main menu item.
                this.actions.clickAndWait(itemBy);
                boolean bSubMenuItemPresent = true;
                int subIndex = 1;

                while (bSubMenuItemPresent) {
                    // Searching for sub menu items, if any.
                    By subMenuItem = By.cssSelector(MessageFormat.format(
                            AdminPanelChecker.SUB_MENU_ITEM_TEMPLATE,
                            subIndex++));
                    WebElement mainMenuItem = this.actions.findElement(itemBy);
                    bSubMenuItemPresent = this.actions.findSubElement(mainMenuItem,
                            subMenuItem);

                    if (bSubMenuItemPresent) {
                        // if found - click on it and verify presence of header.
                        mainMenuItem.findElement(subMenuItem).click();
                        Assert.assertTrue("Header not found!",
                                this.actions.findElemens(By.cssSelector(
                                        AdminPanelChecker.HEADER_LOCATOR)));
                    }
                }
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

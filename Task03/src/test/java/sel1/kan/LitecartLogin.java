package sel1.kan;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

/**
 * Litecart login scenario.
 */
@RunWith(Parameterized.class)
public class LitecartLogin {
    /**
     * Timeout in seconds.
     */
    private static final long   TIMEOUT_SEC = 30L;
    /**
     * Sleep time in milliseconds.
     */
    private static final long   SLEEP_MS    = 500L;

    /**
     * Litecart URL.
     */
    private static final String URL         = "http://localhost/litecart/admin/";

    /**
     * Username.
     */
    @Parameter(0)
    public String               username;

    /**
     * Password.
     */
    @Parameter(1)
    public String               password;

    /**
     * {@link WebDriver} instance.
     */
    private WebDriver           driver;

    /**
     * {@link WebDriverWait} instance.
     */
    private WebDriverWait       wait;

    /**
     * Returns test data.
     *
     * @return test data
     */
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { "admin", "admin" } });
    }

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
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setCapability(FirefoxDriver.MARIONETTE, false);
        this.driver = new FirefoxDriver(caps);
        this.wait = new WebDriverWait(this.driver, LitecartLogin.TIMEOUT_SEC,
                LitecartLogin.SLEEP_MS);
    }

    /**
     * Test method.
     */
    @Test
    public void runTest() {
        this.driver.get(LitecartLogin.URL);
        this.type(By.name("username"), this.username);
        this.type(By.name("password"), this.password);
        By loginBy = By.name("login");
        this.driver.findElement(loginBy).click();
        this.waitForAjax();
        By logoutBy = By.xpath("//a[@title='Logout']");
        this.wait
                .until(ExpectedConditions.visibilityOfElementLocated(logoutBy));
        this.driver.findElement(logoutBy).click();
        this.waitForAjax();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(loginBy));
    }

    /**
     * Enters text to element specified by 'by' instance.
     *
     * @param by
     *            element by
     * @param text
     *            text to enter
     */
    private void type(final By by, final String text) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        this.driver.findElement(by).sendKeys(text);
    }

    /**
     * Waits for ajax calls to complete.
     */
    private void waitForAjax() {
        this.wait.until(new Predicate<WebDriver>() {

            @Override
            public boolean apply(final WebDriver input) {
                return (Boolean) ((JavascriptExecutor) input)
                        .executeScript("return jQuery.active == 0");
            }
        });
    }
}

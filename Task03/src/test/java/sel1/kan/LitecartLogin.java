package sel1.kan;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

/**
 * Litecart login scenario.
 */
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
     * {@link WebDriver} instance.
     */
    private WebDriver           driver;

    /**
     * {@link WebDriverWait} instance.
     */
    private WebDriverWait       wait;

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
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, LitecartLogin.TIMEOUT_SEC,
                LitecartLogin.SLEEP_MS);
    }

    /**
     * Test method.
     */
    @Test
    public void runTest() {
        this.driver.get(LitecartLogin.URL);
        this.type(By.name("username"), "admin");
        this.type(By.name("password"), "admin");
        By loginBy = By.name("login");
        this.driver.findElement(loginBy).click();
        this.waitForAjax();
        By logoutBy = By.xpath("//a[@title='Logout']");
        this.wait.until(ExpectedConditions.presenceOfElementLocated(logoutBy));
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

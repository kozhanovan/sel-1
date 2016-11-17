package sel1.kan;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * First test.
 */
public class SeleniumTest {

    /**
     * Sleep period.
     */
    private static final int    SLEEP_PERIOD = 1000;

    /**
     * Timeout.
     */
    private static final int    TIMEOUT      = 30000;

    /**
     * Search string.
     */
    private static final String SEARCH_STR   = "selenium";

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
        // System.setProperty("webdriver.gecko.driver",
        // "drivers/geckodriver.exe");
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, SeleniumTest.TIMEOUT,
                SeleniumTest.SLEEP_PERIOD);
    }

    /**
     * Test method.
     */
    @Test
    public void runTest() {
        this.driver.get("http://google.com");
        this.driver.findElement(By.id("lst-ib"))
                .sendKeys(SeleniumTest.SEARCH_STR);
        this.driver.findElement(By.name("btnG")).click();
        By result = By.className("g");
        this.wait.until(ExpectedConditions.presenceOfElementLocated(result));
        Assert.assertTrue("Search results do not contain search string",
                this.driver.findElement(result).getText()
                        .contains(SeleniumTest.SEARCH_STR));
    }
}

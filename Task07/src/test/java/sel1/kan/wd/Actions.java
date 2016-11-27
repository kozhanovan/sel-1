package sel1.kan.wd;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

/**
 * Contains typical actions on page.
 */
public class Actions {
    /**
     * {@link WebDriver} instance to use.
     */
    private WebDriver     driver;

    /**
     * {@link WebDriverWait} instance.
     */
    private WebDriverWait wait;

    /**
     * Default constructor.
     */
    public Actions() {

    }

    /**
     * Constructor.
     *
     * @param driver
     *            {@link WebDriver} instance to use
     */
    public Actions(final WebDriver driver) {
        this.useWebDriver(driver);
    }

    /**
     * Clicks on provided element and waits for page to finish loading.
     *
     * @param by
     *            element to click
     */
    public void clickAndWait(final By by) {
        this.driver.findElement(by).click();
        this.waitForAjax();
    }

    /**
     * Returns <code>true</code> if at least one element could be found on page
     * by provided locator.
     *
     * @param by
     *            elements to find
     * @return <code>true</code>/<code>false</code>
     */
    public boolean findElemens(final By by) {
        return this.driver.findElements(by).size() > 0;
    }

    /**
     * Returns web element found.
     * 
     * @param by
     *            locator
     * @return web element found
     */
    public WebElement findElement(final By by) {
        return this.driver.findElement(by);
    }

    /**
     * Returns <code>true</code> if at least one element could be found below
     * element by provided locator.
     *
     * @param element
     *            top level element
     * @param by
     *            elements to find
     * @return <code>true</code>/<code>false</code>
     */
    public boolean findSubElement(final WebElement element, final By by) {
        return element.findElements(by).size() > 0;
    }

    /**
     * Enters text to element specified by 'by' instance.
     *
     * @param by
     *            element by
     * @param text
     *            text to enter
     */
    public void type(final By by, final String text) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        this.driver.findElement(by).sendKeys(text);
    }

    /**
     * Sets {@link WebDriver} instance to use.
     *
     * @param driver
     *            {@link WebDriver} instance to use
     */
    public void useWebDriver(final WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10L);
    }

    /**
     * Waits for ajax calls to complete.
     */
    public void waitForAjax() {
        this.wait.until(new Predicate<WebDriver>() {

            @Override
            public boolean apply(final WebDriver input) {
                return (Boolean) ((JavascriptExecutor) input)
                        .executeScript("return jQuery.active == 0");
            }
        });
    }
}

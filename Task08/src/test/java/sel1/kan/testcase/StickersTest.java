package sel1.kan.testcase;

import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sel1.kan.wd.Actions;
import sel1.kan.wd.ChromeCreator;

/**
 * Verifies stickers for goods on the main page.
 */
public class StickersTest {

    /**
     * Application url.
     */
    private static final String URL = "http://localhost/litecart/";

    /**
     * Logger instance.
     */
    private static Logger       log = LoggerFactory
            .getLogger(StickersTest.class);

    /**
     * Page actions.
     */
    private final Actions       actions;

    /**
     * Default constructor.
     */
    public StickersTest() {
        this.actions = new Actions(new ChromeCreator().create());
    }

    /**
     * After test actions.
     */
    @After
    public void afterTest() {
        this.actions.quit();
    }

    /**
     * Before test actions.
     */
    @Before
    public void beforeTest() {
        this.actions.get(StickersTest.URL);
    }

    /**
     * Test method.
     */
    @Test
    public void runTest() {
        // Search for all products on main page
        List<WebElement> products = this.actions.getWebDriver()
                .findElements(By.cssSelector("li.product"));
        SoftAssertions soft = new SoftAssertions();

        for (WebElement product : products) {
            // For each product verify number of stickers
            String title = product.findElement(By.tagName("a"))
                    .getAttribute("title");
            StickersTest.log.info("Verifying stickers for {}", title);
            int numOfStickers = product
                    .findElements(By.cssSelector("div.sticker")).size();
            soft.assertThat(numOfStickers)
                    .withFailMessage(
                            "Number of stickers for %s should be 1 but is %d",
                            title, numOfStickers)
                    .isEqualTo(1);
        }

        soft.assertAll();
    }
}

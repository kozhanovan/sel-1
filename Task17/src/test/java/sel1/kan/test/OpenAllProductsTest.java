package sel1.kan.test;

import java.util.List;
import java.util.logging.Level;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import sel1.kan.wd.Actions;

/**
 * Sequentially opens all products.
 */
public class OpenAllProductsTest {
    /**
     * Products locator.
     */
    private static final String PRODUCT_LOCATOR = "table.dataTable td:nth-child(3) a[href*='edit_product']";

    /**
     * Logger instance.
     */
    private static final Logger LOG             = LogManager
            .getLogger(OpenAllProductsTest.class);

    /**
     * Opens all products.
     */
    @Test
    public void openAllProducts() {
        int index = 0;
        boolean bContinue = true;
        Actions actions = Actions.getActions();

        while (bContinue) {
            List<WebElement> products = actions.getWebDriver().findElements(
                    By.cssSelector(OpenAllProductsTest.PRODUCT_LOCATOR));
            try {
                WebElement product = products.get(index++);
                OpenAllProductsTest.LOG
                        .info("Switch to product " + product.getText());
                product.click();
                actions.waitForAjax();
                actions.getWebDriver().navigate().back();
                actions.waitForAjax();
                int numOfEntries = actions.getWebDriver().manage().logs()
                        .get("browser").filter(Level.ALL).size();
                OpenAllProductsTest.LOG.info("Verify browser log is empty");
                Assertions.assertThat(numOfEntries)
                        .withFailMessage("Browser logs are not empty!")
                        .isEqualTo(0);
            } catch (Throwable t) {
                bContinue = false;
            }
        }
    }
}

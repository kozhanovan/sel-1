package sel1.kan.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sel1.kan.wd.Actions;

/**
 * Removes product from cart.
 */
public class ProductFromCartRemover {

    /**
     * Remove button locator.
     */
    private static final String REMOVE_BUTTON_LOCATOR = "[name='remove_cart_item']";

    /**
     * Product item locator.
     */
    private static final String PRODUCT_ICON_LOCATOR  = "#box-checkout-cart a.inact";

    /**
     * Table rows locator.
     */
    private static final String TABLE_ROWS_LOCATOR    = "#order_confirmation-wrapper tr";

    /**
     * Logger instance.
     */
    private static final Logger LOG                   = LogManager
            .getLogger(ProductFromCartRemover.class);

    /**
     * Removes product from cart.
     */
    @Test
    public void removeProductFromCart() {
        ProductFromCartRemover.LOG.info("Remove first item from Cart");
        Actions actions = Actions.getActions();
        List<WebElement> productIcons = actions.getWebDriver().findElements(
                By.cssSelector(ProductFromCartRemover.PRODUCT_ICON_LOCATOR));

        if (productIcons.size() > 0) {
            productIcons.get(0).click();
        }

        List<WebElement> removeButtons = actions.getWebDriver().findElements(
                By.cssSelector(ProductFromCartRemover.REMOVE_BUTTON_LOCATOR));
        Assertions.assertThat(removeButtons.size())
                .withFailMessage("Nothing to remove").isGreaterThan(0);
        int numOfTableRows = actions.getWebDriver()
                .findElements(By
                        .cssSelector(ProductFromCartRemover.TABLE_ROWS_LOCATOR))
                .size();
        WebElement firstButton = removeButtons.get(0);
        actions.getWebDriverWait()
                .until(ExpectedConditions.visibilityOf(firstButton));
        firstButton.click();
        this.waitForTableRowsToDecrease(numOfTableRows);
    }

    /**
     * Waits for order table rows to decrease.
     *
     * @param numOfTableRows
     *            number of rows before remove operation
     */
    private void waitForTableRowsToDecrease(final int numOfTableRows) {
        ProductFromCartRemover.LOG.info("Wait for items table to update");
        Actions.getActions().getWebDriverWait().until((final WebDriver d) -> {
            int numOfRows = Actions.getActions().getWebDriver().findElements(
                    By.cssSelector(ProductFromCartRemover.TABLE_ROWS_LOCATOR))
                    .size();
            return numOfRows != numOfTableRows;
        });
    }
}

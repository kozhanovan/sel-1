package sel1.kan.page;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sel1.kan.wd.Actions;

/**
 * Cart page object class.
 */
public class CartPage {

    /**
     * Remove button locator.
     */
    private static final String REMOVE_BUTTON_LOCATOR = "[name='remove_cart_item']";

    /**
     * Product item locator.
     */
    private static final String PRODUCT_ICON_LOCATOR  = "#box-checkout-cart li>a";

    /**
     * Table rows locator.
     */
    private static final String TABLE_ROWS_LOCATOR    = "#order_confirmation-wrapper tr";

    /**
     * Logger instance.
     */
    private static final Logger LOG                   = LogManager
            .getLogger(CartPage.class);

    /**
     * Removes product from cart.
     *
     * @param index
     *            zero-based product number in cart
     */
    public void removeProductFromCart(final int index) {
        CartPage.LOG.info("Remove item " + index + " from Cart");
        Actions actions = Actions.getActions();
        List<WebElement> productIcons = actions.getWebDriver()
                .findElements(By.cssSelector(CartPage.PRODUCT_ICON_LOCATOR));
        int size = productIcons.size();

        if (size > 0) {
            if (index < size) {
                productIcons.get(index).click();
            } else {
                Assertions.fail("Cant't remove item " + index + " from cart");
            }
        }

        List<WebElement> removeButtons = actions.getWebDriver()
                .findElements(By.cssSelector(CartPage.REMOVE_BUTTON_LOCATOR));
        Assertions.assertThat(removeButtons.size())
                .withFailMessage("Nothing to remove").isGreaterThan(0);
        int numOfTableRows = actions.getWebDriver()
                .findElements(By.cssSelector(CartPage.TABLE_ROWS_LOCATOR))
                .size();
        WebElement removeButton = removeButtons.get(index);
        actions.getWebDriverWait()
                .until(ExpectedConditions.visibilityOf(removeButton));
        removeButton.click();
        this.waitForTableRowsToDecrease(numOfTableRows);
    }

    /**
     * Waits for order table rows to decrease.
     *
     * @param numOfTableRows
     *            number of rows before remove operation
     */
    private void waitForTableRowsToDecrease(final int numOfTableRows) {
        CartPage.LOG.info("Wait for items table to update");
        Actions.getActions().getWebDriverWait().until((final WebDriver d) -> {
            int numOfRows = Actions.getActions().getWebDriver()
                    .findElements(By.cssSelector(CartPage.TABLE_ROWS_LOCATOR))
                    .size();
            return numOfRows != numOfTableRows;
        });
    }
}

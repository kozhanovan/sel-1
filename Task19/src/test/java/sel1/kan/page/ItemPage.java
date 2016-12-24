package sel1.kan.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import sel1.kan.wd.Actions;

/**
 * Item page object class.
 */
public class ItemPage {

    /**
     * Locator of 'add to cart' button.
     */
    private static final String ADD_TO_CART_BUTTON_LOCATOR = "[name=add_cart_product]";

    /**
     * Locator of size selector.
     */
    private static final String SIZE_SELECTOR_LOCATOR      = "[name='options[Size]']";

    /**
     * Quantity locator.
     */
    private static final String QUANTITY_LOCATOR           = "#cart .quantity";

    /**
     * Adds product to cart.
     */
    public void addToCart() {
        Actions actions = Actions.getActions();
        actions.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(ItemPage.ADD_TO_CART_BUTTON_LOCATOR)));
        if (actions
                .findElemens(By.cssSelector(ItemPage.SIZE_SELECTOR_LOCATOR))) {
            Select sizeSelector = new Select(actions.getWebDriver().findElement(
                    By.cssSelector(ItemPage.SIZE_SELECTOR_LOCATOR)));
            sizeSelector.selectByIndex(1);
        }

        String oldQuantity = actions.getWebDriver()
                .findElement(By.cssSelector(ItemPage.QUANTITY_LOCATOR))
                .getText();
        actions.clickAndWait(
                By.cssSelector(ItemPage.ADD_TO_CART_BUTTON_LOCATOR));
        this.waitForQuantityToChange(oldQuantity);
    }

    /**
     * Waits for items in cart quantity to change.
     *
     * @param oldQuantity
     */
    private void waitForQuantityToChange(final String oldQuantity) {
        Actions.getActions().getWebDriverWait().until((final WebDriver d) -> {
            String quantity = Actions.getActions().getWebDriver()
                    .findElement(By.cssSelector(ItemPage.QUANTITY_LOCATOR))
                    .getText();
            return !oldQuantity.equals(quantity);
        });
    }
}

package sel1.kan.test;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.openqa.selenium.By;

import sel1.kan.wd.Actions;

/**
 * Opens cart.
 */
public class CartOpener {
    /**
     * 'Checkout' link locator.
     */
    private static final String CHECKOUT_LOCATOR = "#cart .link";

    /**
     * Clicks on 'checkout' link.
     */
    @Test
    public void checkout() {
        LogManager.getLogger(CartOpener.class).info("Go to Cart");
        Actions.getActions()
                .clickAndWait(By.cssSelector(CartOpener.CHECKOUT_LOCATOR));
    }
}

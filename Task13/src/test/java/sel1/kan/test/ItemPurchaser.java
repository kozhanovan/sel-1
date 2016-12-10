package sel1.kan.test;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.JUnitCore;

/**
 * Purchases random item and returns to main page.
 */
public class ItemPurchaser {

    /**
     * Purchases random item.
     */
    @Test
    public void purchaseItem() {
        LogManager.getLogger(ItemPurchaser.class).info("Purchase random item");
        JUnitCore junit = new JUnitCore();
        junit.run(ProductSelector.class, AdderToCart.class,
                MainPageOpener.class);
    }
}

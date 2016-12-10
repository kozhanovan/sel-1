package sel1.kan.testcase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import sel1.kan.core.Context;
import sel1.kan.core.ContextKey;
import sel1.kan.core.StringList;
import sel1.kan.test.CartOpener;
import sel1.kan.test.ItemPurchaser;
import sel1.kan.test.ProductFromCartRemover;
import sel1.kan.wd.Actions;
import sel1.kan.wd.ChromeCreator;

/**
 * Verifies work with cart.
 */
@RunWith(Suite.class)
// @SuiteClasses({ ProductSelector.class, AdderToCart.class,
// MainPageOpener.class })
@SuiteClasses({ ItemPurchaser.class, ItemPurchaser.class, ItemPurchaser.class,
        CartOpener.class, ProductFromCartRemover.class,
        ProductFromCartRemover.class, ProductFromCartRemover.class })
public class CartTest {

    /**
     * Litecart start URL.
     */
    private static final String URL = "http://localhost/litecart/";

    /**
     * After testcase actions.
     */
    @AfterClass
    public static void afterClass() {
        Actions.getActions().quit();
    }

    /**
     * Before testcase actions.
     */
    @BeforeClass
    public static void beforeClass() {
        Actions.getActions().useWebDriver(new ChromeCreator().create());
        Actions.getActions().get(CartTest.URL);
        StringList purchasedProducts = new StringList();
        Context.getContext().put(ContextKey.PURCHASED_PRODUCTS,
                purchasedProducts);
    }
}

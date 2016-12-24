package sel1.kan.testcase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sel1.kan.core.Context;
import sel1.kan.core.ContextKey;
import sel1.kan.core.StringList;
import sel1.kan.page.CartPage;
import sel1.kan.page.ItemPage;
import sel1.kan.page.MainPage;
import sel1.kan.wd.Actions;
import sel1.kan.wd.ChromeCreator;

/**
 * Verifies work with cart.
 */
public class CartTest {

    /**
     * Litecart start URL.
     */
    private static final String URL      = "http://localhost/litecart/";

    /**
     * Main page object.
     */
    private final MainPage      mainPage = new MainPage();

    /**
     * Item page object.
     */
    private final ItemPage      itemPage = new ItemPage();

    /**
     * Cart page object.
     */
    private final CartPage      cartPage = new CartPage();

    /**
     * After testcase actions.
     */
    @After
    public void afterTest() {
        Actions.getActions().quit();
    }

    /**
     * Before testcase actions.
     */
    @Before
    public void beforeTest() {
        Actions.getActions().useWebDriver(new ChromeCreator().create());
        Actions.getActions().get(CartTest.URL);
        StringList purchasedProducts = new StringList();
        Context.getContext().put(ContextKey.PURCHASED_PRODUCTS,
                purchasedProducts);
    }

    /**
     * Verifies cart.
     */
    @Test
    public void cartTest() {
        this.purchaseProduct();
        this.purchaseProduct();
        this.purchaseProduct();
        this.mainPage.openCart();
        this.cartPage.removeProductFromCart(2);
        this.cartPage.removeProductFromCart(0);
        this.cartPage.removeProductFromCart(0);
    }

    /**
     * Purchase single product.
     */
    private void purchaseProduct() {
        this.mainPage.selectProduct();
        this.itemPage.addToCart();
        this.mainPage.open();
    }
}

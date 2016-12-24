package sel1.kan.page;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sel1.kan.core.Context;
import sel1.kan.core.ContextKey;
import sel1.kan.core.StringList;
import sel1.kan.wd.Actions;

/**
 * Main page object class.
 */
public class MainPage {

    /**
     * 'Checkout' link locator.
     */
    private static final String CHECKOUT_LOCATOR = "#cart .link";

    /**
     * Product locator.
     */
    private static final String PRODUCT_LOCATOR  = "li.product";

    /**
     * Random instance.
     */
    private static Random       random           = new Random();

    /**
     * Opens main page.
     */
    public void open() {
        Actions.getActions()
                .clickAndWait(By.cssSelector("div#logotype-wrapper"));
    }

    /**
     * Opens cart.
     */
    public void openCart() {
        LogManager.getLogger(MainPage.class).info("Go to Cart");
        Actions.getActions()
                .clickAndWait(By.cssSelector(MainPage.CHECKOUT_LOCATOR));
    }

    /**
     * Selects random product.
     */
    public void selectProduct() {
        Actions actions = Actions.getActions();
        actions.getWebDriverWait()
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector(MainPage.PRODUCT_LOCATOR)));
        List<WebElement> products = actions.getWebDriver()
                .findElements(By.cssSelector(MainPage.PRODUCT_LOCATOR));
        int productsAmount = products.size();
        StringList purchasedProducts = (StringList) Context.getContext()
                .get(ContextKey.PURCHASED_PRODUCTS);
        String name = null;
        int index = -1;

        do {
            index = MainPage.random.nextInt(productsAmount);
            name = products.get(index).findElement(By.cssSelector("div.name"))
                    .getText();
        } while (purchasedProducts.contains(name));

        purchasedProducts.add(name);
        products.get(index).click();
        actions.waitForAjax();
    }
}

package sel1.kan.test;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sel1.kan.core.Context;
import sel1.kan.core.ContextKey;
import sel1.kan.core.StringList;
import sel1.kan.wd.Actions;

/**
 * Selects random product.
 */
public class ProductSelector {
    /**
     * Product locator.
     */
    private static final String PRODUCT_LOCATOR = "li.product";

    /**
     * Random instance.
     */
    private static Random       random          = new Random();

    /**
     * Purchases random product.
     */
    @Test
    public void selectProduct() {
        Actions actions = Actions.getActions();
        actions.getWebDriverWait()
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector(ProductSelector.PRODUCT_LOCATOR)));
        List<WebElement> products = actions.getWebDriver()
                .findElements(By.cssSelector(ProductSelector.PRODUCT_LOCATOR));
        int productsAmount = products.size();
        StringList purchasedProducts = (StringList) Context.getContext()
                .get(ContextKey.PURCHASED_PRODUCTS);
        String name = null;
        int index = -1;

        do {
            index = ProductSelector.random.nextInt(productsAmount);
            name = products.get(index).findElement(By.cssSelector("div.name"))
                    .getText();
        } while (purchasedProducts.contains(name));

        purchasedProducts.add(name);
        products.get(index).click();
        actions.waitForAjax();
    }
}

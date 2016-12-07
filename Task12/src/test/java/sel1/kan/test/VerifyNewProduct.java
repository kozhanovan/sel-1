package sel1.kan.test;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sel1.kan.core.Context;
import sel1.kan.core.ContextKey;
import sel1.kan.core.Product;
import sel1.kan.core.Product.Field;
import sel1.kan.wd.Actions;

/**
 * Verifies new product was actually added to the store.
 */
public class VerifyNewProduct {
    /**
     * Verifies new product actually added to the store.
     */
    @Test
    public void verifyNewProduct() {
        Actions actions = Actions.getActions();
        String productName = ((Product) Context.getContext()
                .get(ContextKey.PRODUCT)).get(Field.NAME);
        actions.getWebDriverWait()
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector(".dataTable tr")));
        List<WebElement> products = actions.getWebDriver()
                .findElements(By.cssSelector(".dataTable td:nth-child(3) a"));
        boolean found = false;

        for (WebElement product : products) {
            if (productName.equals(product.getText())) {
                found = true;
                break;
            }
        }

        Assertions.assertThat(found)
                .withFailMessage("Product '%s' not found", productName)
                .isEqualTo(true);
    }
}

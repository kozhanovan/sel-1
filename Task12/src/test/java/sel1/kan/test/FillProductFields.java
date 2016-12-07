package sel1.kan.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import sel1.kan.core.Context;
import sel1.kan.core.ContextKey;
import sel1.kan.core.Product;
import sel1.kan.core.Product.Field;
import sel1.kan.wd.Actions;

/**
 * Fills product fields.
 */
public class FillProductFields {
    /**
     * Fills product fields.
     */
    @Test
    public void fillProductFields() {
        Product product = (Product) Context.getContext()
                .get(ContextKey.PRODUCT);
        Actions actions = Actions.getActions();

        // Click on 'general' tab.
        actions.clickAndWait(By.cssSelector("a[href*=tab-general]"));
        actions.type(By.cssSelector("[name*=name]"), product.get(Field.NAME));
        actions.type(By.cssSelector("[name=code]"), product.get(Field.CODE));
        actions.type(By.cssSelector("[name=quantity]"),
                product.get(Field.QUANTITY));

        // Click on 'information' tab.
        actions.clickAndWait(By.cssSelector("a[href*=tab-information]"));
        actions.type(By.cssSelector("[name=keywords]"),
                product.get(Field.KEYWORDS));
        actions.type(By.cssSelector("[name*=short_description]"),
                product.get(Field.SHORT_DESCRIPTION));
        actions.type(By.cssSelector(".trumbowyg-editor"),
                product.get(Field.DESCRIPTION));
        actions.type(By.cssSelector("[name*=head_title]"),
                product.get(Field.HEAD_TITLE));
        actions.type(By.cssSelector("[name*=meta_description]"),
                product.get(Field.META));

        // Click on 'prices' tab.
        actions.clickAndWait(By.cssSelector("a[href*=tab-prices]"));
        actions.type(By.cssSelector("[name=purchase_price]"),
                product.get(Field.PURCHASE_PRICE));
        Select currency = new Select(actions.getWebDriver().findElement(
                By.cssSelector("[name=purchase_price_currency_code]")));
        currency.selectByIndex(1);
        actions.type(By.cssSelector("[name='prices[USD]']"),
                product.get(Field.PRICE));
    }
}

package sel1.kan.test;

import org.junit.Test;
import org.openqa.selenium.By;

import sel1.kan.wd.Actions;

/**
 * Clicks on 'add new product' button.
 */
public class ClickAddNewProduct {
    /**
     * Clicks on 'add new product' button.
     */
    @Test
    public void clickAddNewProduct() {
        Actions.getActions()
                .clickAndWait(By.cssSelector("#content div a:last-child"));
    }
}

package sel1.kan.test;

import org.junit.Test;
import org.openqa.selenium.By;

import sel1.kan.wd.Actions;

/**
 * Clicks on 'save product' button.
 */
public class ClickSaveProduct {
    /**
     * Clicks on 'save product' button.
     */
    @Test
    public void clickSaveProduct() {
        Actions.getActions().clickAndWait(By.cssSelector("[name=save]"));
    }
}

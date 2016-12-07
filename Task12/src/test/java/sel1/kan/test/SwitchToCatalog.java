package sel1.kan.test;

import org.junit.Test;
import org.openqa.selenium.By;

import sel1.kan.wd.Actions;

/**
 * Selects 'catalog' category.
 */
public class SwitchToCatalog {
    /**
     * Selects 'catalog' category.
     */
    @Test
    public void swithcToCatalog() {
        Actions.getActions().clickAndWait(
                By.cssSelector("#box-apps-menu li a[href*='doc=catalog']"));
    }
}

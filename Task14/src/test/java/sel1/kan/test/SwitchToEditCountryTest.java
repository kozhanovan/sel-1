package sel1.kan.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sel1.kan.wd.Actions;

/**
 * Switches to edit country page.
 */
public class SwitchToEditCountryTest {
    /**
     * Switches to edit country page.
     */
    @Test
    public void switchToCountryEdit() {
        Actions actions = Actions.getActions();
        actions.clickAndWait(By.cssSelector("a[href*=countries]"));
        actions.clickAndWait(
                By.cssSelector("tr.row:nth-child(2) td:nth-child(5) a"));
        actions.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(".fa-external-link")));
    }
}

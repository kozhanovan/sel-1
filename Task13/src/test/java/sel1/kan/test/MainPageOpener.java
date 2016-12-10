package sel1.kan.test;

import org.junit.Test;
import org.openqa.selenium.By;

import sel1.kan.wd.Actions;

/**
 * Opens main page.
 */
public class MainPageOpener {
    /**
     * Clicks to logotype to return to main page.
     */
    @Test
    public void goToMainPage() {
        Actions.getActions()
                .clickAndWait(By.cssSelector("div#logotype-wrapper"));
    }
}

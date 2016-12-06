package sel1.kan.test;

import org.junit.Test;
import org.openqa.selenium.By;

import sel1.kan.wd.Actions;

/**
 * Logs out of application.
 */
public class Logout {
    /**
     * Logs out of application
     */
    @Test
    public void logout() {
        Actions.getActions()
                .clickAndWait(By.cssSelector("#box-account li:last-child a"));
    }
}

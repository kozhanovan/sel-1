package sel1.kan.test;

import org.junit.Test;
import org.openqa.selenium.By;

import sel1.kan.wd.Actions;

/**
 * Clicks on create new account link.
 */
public class ClickCreateNewAccount {

    /**
     * Clicks on create new account link.
     */
    @Test
    public void createNewAccount() {
        Actions.getActions()
                .clickAndWait(By.cssSelector("form[name='login_form'] a"));
    }
}

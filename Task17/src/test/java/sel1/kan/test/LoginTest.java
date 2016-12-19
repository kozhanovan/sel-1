package sel1.kan.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sel1.kan.wd.Actions;

/**
 * Signs in int litecart admin.
 */
public class LoginTest {
    /**
     * Signs in into litecart admin.
     */
    @Test
    public void login() {
        this.login("admin", "admin");
    }

    /**
     * Signs in into admin panel.
     *
     * @param login
     *            login to use
     * @param password
     *            password to use
     */
    private void login(final String login, final String password) {
        Actions actions = Actions.getActions();
        actions.type(By.name("username"), login);
        actions.type(By.name("password"), password);
        By loginBy = By.name("login");
        actions.getWebDriver().findElement(loginBy).click();
        actions.waitForAjax();
        By logoutBy = By.xpath("//a[@title='Logout']");
        actions.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfElementLocated(logoutBy));
    }
}

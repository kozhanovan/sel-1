package sel1.kan.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sel1.kan.core.Context;
import sel1.kan.core.ContextKey;
import sel1.kan.wd.Actions;

/**
 * Signs in to litecart admin.
 */
public class Login {
    @Test
    public void login() {
        Actions actions = Actions.getActions();
        Context context = Context.getContext();
        actions.type(By.name("username"),
                (String) context.get(ContextKey.USERNAME));
        actions.type(By.name("password"),
                (String) context.get(ContextKey.PASSWORD));
        By loginBy = By.name("login");
        actions.clickAndWait(loginBy);
        By logoutBy = By.xpath("//a[@title='Logout']");
        actions.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfElementLocated(logoutBy));
    }
}

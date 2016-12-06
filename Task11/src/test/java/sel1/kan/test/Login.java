package sel1.kan.test;

import org.junit.Test;
import org.openqa.selenium.By;

import sel1.kan.core.Context;
import sel1.kan.core.ContextKey;
import sel1.kan.core.Customer;
import sel1.kan.core.Customer.Field;
import sel1.kan.wd.Actions;

/**
 * Signs in with provided account.
 */
public class Login {
    /**
     * Signs in to application.
     */
    @Test
    public void signIn() {
        Actions actions = Actions.getActions();
        Customer customer = (Customer) Context.getContext()
                .get(ContextKey.CUSTOMER);
        actions.type(By.cssSelector("[name='email']"),
                customer.get(Field.EMAIL));
        actions.type(By.cssSelector("[name='password']"),
                customer.get(Field.PASSWORD));
        actions.clickAndWait(By.cssSelector("[name='login']"));
    }
}

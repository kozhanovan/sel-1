package sel1.kan.test;

import org.junit.Test;
import org.openqa.selenium.By;

import sel1.kan.core.Context;
import sel1.kan.core.ContextKey;
import sel1.kan.core.Customer;
import sel1.kan.core.Customer.Field;
import sel1.kan.wd.Actions;

/**
 * Creates new customer.
 */
public class CreateAccount {
    /**
     * Creates new customer.
     */
    @Test
    public void createCustomer() {
        Customer customer = new Customer();
        customer.generateRandom();

        Actions actions = Actions.getActions();
        actions.type(By.cssSelector("input[name='firstname']"),
                customer.get(Field.FIRST_NAME));
        actions.type(By.cssSelector("input[name='lastname']"),
                customer.get(Field.LAST_NAME));
        actions.type(By.cssSelector("input[name='address1']"),
                customer.get(Field.ADDRESS1));
        actions.type(By.cssSelector("input[name='postcode']"),
                customer.get(Field.POSTCODE));
        actions.type(By.cssSelector("input[name='city']"),
                customer.get(Field.CITY));

        this.selectCountry(customer.get(Field.COUNTRY));
        this.selectState(customer.get(Field.STATE));

        actions.type(By.cssSelector("input[name='email']"),
                customer.get(Field.EMAIL));
        actions.type(By.cssSelector("input[name='phone']"),
                customer.get(Field.PHONE));

        // actions.type(By.cssSelector("input[name='firstname']"),
        // customer.get(Field.FIRST_NAME));

        actions.type(By.cssSelector("input[name='password']"),
                customer.get(Field.PASSWORD));
        actions.type(By.cssSelector("input[name='confirmed_password']"),
                customer.get(Field.PASSWORD));
        actions.clickAndWait(By.cssSelector("button[name='create_account']"));
        // actions.waitForAjax();

        Context.getContext().put(ContextKey.CUSTOMER, customer);
    }

    /**
     * Selects country.
     *
     * @param country
     *            country
     */
    private void selectCountry(final String country) {
        Actions actions = Actions.getActions();
        actions.clickAndWait(
                By.xpath("//select[@name='country_code']/../span[2]"));
        actions.type(By.xpath("//html/body/span/span/span[1]/input"), country);
        actions.clickAndWait(
                By.cssSelector(".select2-results__options li:first-child"));
    }

    /**
     * Selects state.
     *
     * @param state
     *            state
     */
    private void selectState(final String state) {
        if (null == state) {
            return;
        }
    }
}

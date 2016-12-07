package sel1.kan.testcase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import sel1.kan.core.Context;
import sel1.kan.core.ContextKey;
import sel1.kan.core.Product;
import sel1.kan.test.ClickAddNewProduct;
import sel1.kan.test.ClickSaveProduct;
import sel1.kan.test.FillProductFields;
import sel1.kan.test.Login;
import sel1.kan.test.SwitchToCatalog;
import sel1.kan.test.VerifyNewProduct;
import sel1.kan.wd.Actions;
import sel1.kan.wd.ChromeCreator;

/**
 * Adds new product to store.
 */
@RunWith(Suite.class)
@SuiteClasses({ Login.class, SwitchToCatalog.class, ClickAddNewProduct.class,
        FillProductFields.class, ClickSaveProduct.class, SwitchToCatalog.class,
        VerifyNewProduct.class })
public class AddProductTest {
    private static final String URL = "http://localhost/litecart/admin";

    /**
     * After testcase actions.
     */
    @AfterClass
    public static void afterClass() {
        Actions.getActions().quit();
    }

    /**
     * Before testcase actions.
     */
    @BeforeClass
    public static void beforeClass() {
        Actions actions = Actions.getActions();
        actions.useWebDriver(new ChromeCreator().create());
        actions.get(AddProductTest.URL);
        Product product = new Product();
        product.generate();
        Context.getContext().put(ContextKey.USERNAME, "admin");
        Context.getContext().put(ContextKey.PASSWORD, "admin");
        Context.getContext().put(ContextKey.PRODUCT, product);
    }
}

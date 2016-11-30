package sel1.kan.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sel1.kan.core.Context;
import sel1.kan.core.ContextKey;
import sel1.kan.wd.Actions;

/**
 * Verifies zones sorting.
 */
@RunWith(Parameterized.class)
public class ZonesSortingVerifier {

    /**
     * Rows locator.
     */
    private static final String DATA_TABLE_ROWS = ".dataTable tr";

    /**
     * Logger instance.
     */
    private static Logger       log             = LoggerFactory
            .getLogger(ZonesSortingVerifier.class);

    /**
     * Locator of country with zones.
     */
    @Parameter
    public String               locator;

    /**
     * Returns locators of countries with zones.
     *
     * @return locators of countries with zones
     */
    @Parameters
    public static Collection<Object[]> locators() {
        String[] locators = (String[]) Context.getContext()
                .get(ContextKey.ZONES_LOCATORS);
        List<Object[]> result = new ArrayList<>();
        Arrays.stream(locators).forEach(s -> result.add(new Object[] { s }));
        return result;
    }

    /**
     * Verifies zones sorting.
     */
    @Test
    public void zonesSortingTest() {
        Actions actions = Actions.getActions();

        By countryBy = By.cssSelector(this.locator);
        actions.getWebDriverWait().until(
                ExpectedConditions.visibilityOfElementLocated(countryBy));
        actions.clickAndWait(countryBy);
        actions.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(ZonesSortingVerifier.DATA_TABLE_ROWS)));
        List<WebElement> rows = actions.getWebDriver().findElements(
                By.cssSelector(ZonesSortingVerifier.DATA_TABLE_ROWS));
        int last = rows.size() - 1;

        for (int i = 1; i < last; i++) {

        }

        actions.getWebDriver().navigate().back();
    }
}

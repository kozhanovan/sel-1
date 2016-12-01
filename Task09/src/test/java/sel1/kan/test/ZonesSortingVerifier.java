package sel1.kan.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
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
        ZonesSortingVerifier.log.info("Waiting for zones table to fully load");
        actions.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(ZonesSortingVerifier.DATA_TABLE_ROWS)));
        List<WebElement> rows = actions.getWebDriver().findElements(
                By.cssSelector(ZonesSortingVerifier.DATA_TABLE_ROWS));
        int last = rows.size() - 1;
        String prev = "";
        SoftAssertions soft = new SoftAssertions();

        for (int i = 1; i < last; i++) {
            WebElement row = rows.get(i);
            String next = row.findElement(By.cssSelector("td:nth-child(3)"))
                    .getText();
            ZonesSortingVerifier.log.info("Verifying order of " + next);
            soft.assertThat(next.compareTo(prev))
                    .withFailMessage("'%s' should be before '%s'", prev, next)
                    .isGreaterThanOrEqualTo(0);
            prev = next;
        }

        ZonesSortingVerifier.log.info("Go back to countries list");
        actions.getWebDriver().navigate().back();
        soft.assertAll();
    }
}

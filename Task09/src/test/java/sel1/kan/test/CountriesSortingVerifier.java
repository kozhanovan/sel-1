package sel1.kan.test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sel1.kan.core.Context;
import sel1.kan.core.ContextKey;
import sel1.kan.wd.Actions;

/**
 * Verifies countries sorting.
 */
public class CountriesSortingVerifier {

    /**
     * Table row locator.
     */
    private static final String COUNTRY_ROW_LOCATOR      = "table.dataTable tr.row";

    /**
     * Country locator template.
     */
    private static final String COUNTRY_LOCATOR_TEMPLATE = "tr.row:nth-child({0}) td:nth-child(5) a";

    /**
     * Logger instance.
     */
    private static Logger       log                      = LoggerFactory
            .getLogger(CountriesSortingVerifier.class);

    /**
     * Countries sorting.
     */
    @Test
    public void countriesSorting() {
        Actions actions = Actions.getActions();
        CountriesSortingVerifier.log.info("Waiting for countries table to fully load");
        actions.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(CountriesSortingVerifier.COUNTRY_ROW_LOCATOR)));
        List<WebElement> rows = actions.getWebDriver().findElements(
                By.cssSelector(CountriesSortingVerifier.COUNTRY_ROW_LOCATOR));
        String prev = "";
        SoftAssertions soft = new SoftAssertions();
        List<String> withZones = new ArrayList<>();
        int index = 2;

        for (WebElement row : rows) {
            String next = row.findElement(By.cssSelector("td:nth-child(5)"))
                    .getText();
            String zones = row.findElement(By.cssSelector("td:nth-child(6)"))
                    .getText();
            CountriesSortingVerifier.log.info("Verifying order of " + next);

            if (!"0".equals(zones)) {
                withZones.add(MessageFormat.format(
                        CountriesSortingVerifier.COUNTRY_LOCATOR_TEMPLATE, index));
            }

            soft.assertThat(next.compareTo(prev))
                    .withFailMessage("'%s' should be before '%s'", prev, next)
                    .isGreaterThanOrEqualTo(0);
            prev = next;
            index++;
        }

        Context.getContext().put(ContextKey.ZONES_LOCATORS,
                withZones.toArray(new String[withZones.size()]));
        soft.assertAll();
    }
}

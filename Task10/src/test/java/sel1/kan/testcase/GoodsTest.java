package sel1.kan.testcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sel1.kan.wd.Actions;
import sel1.kan.wd.ChromeCreator;

/**
 * Verifies correct good display.
 */
public class GoodsTest {
    /**
     * Inner class to store good verification data.
     */
    private class Data {
        /**
         * Name.
         */
        public String name;

        /**
         * Regular price.
         */
        public String regularPrice;

        /**
         * Campaign price.
         */
        public String campaignPrice;

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            Data other = (Data) obj;
            if (!this.getOuterType().equals(other.getOuterType())) {
                return false;
            }
            if (this.campaignPrice == null) {
                if (other.campaignPrice != null) {
                    return false;
                }
            } else if (!this.campaignPrice.equals(other.campaignPrice)) {
                return false;
            }
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (this.regularPrice == null) {
                if (other.regularPrice != null) {
                    return false;
                }
            } else if (!this.regularPrice.equals(other.regularPrice)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = (prime * result) + this.getOuterType().hashCode();
            result = (prime * result) + ((this.campaignPrice == null) ? 0
                    : this.campaignPrice.hashCode());
            result = (prime * result)
                    + ((this.name == null) ? 0 : this.name.hashCode());
            result = (prime * result) + ((this.regularPrice == null) ? 0
                    : this.regularPrice.hashCode());
            return result;
        }

        @Override
        public String toString() {
            return "Data [name=" + this.name + ", regularPrice="
                    + this.regularPrice + ", campaignPrice="
                    + this.campaignPrice + "]";
        }

        /**
         * Returns outer type instance.
         *
         * @return outer type instance
         */
        private GoodsTest getOuterType() {
            return GoodsTest.this;
        }
    }

    /**
     * Campaign price not found error.
     */
    private static final String CAMPAIGN_PRICE_NOT_FOUND = "Campaign price not found";

    /**
     * Regular price not found error.
     */
    private static final String REGULAR_PRICE_NOT_FOUND  = "Regular price not found";

    /**
     * Litecart start URL.
     */
    private static final String URL                      = "http://localhost/litecart/en/";

    /**
     * Locator of first good in 'Campaign' section.
     */
    private static final String GOOD_LOCATOR             = "#box-campaigns li.product";

    /**
     * Logger instance.
     */
    private static Logger       log                      = LogManager
            .getLogger(GoodsTest.class);

    /**
     * Campaign price By.
     */
    private final By            campaignPriceBy          = By
            .cssSelector(".campaign-price");

    /**
     * Regular price By.
     */
    private final By            regularPriceBy           = By
            .cssSelector(".regular-price");

    /**
     * After test actions.
     */
    @After
    public void afterTest() {
        Actions.getActions().quit();
    }

    /**
     * Before test actions.
     */
    @Before
    public void beforeTest() {
        Actions.getActions().useWebDriver(new ChromeCreator().create());
        GoodsTest.log.info("Open litecart main page");
        Actions.getActions().get(GoodsTest.URL);
    }

    /**
     * The test.
     */
    @Test
    public void testGood() {
        Actions actions = Actions.getActions();

        // Search for first good in 'campaigns' category.
        GoodsTest.log.info("Search for first good in 'Campaigns' category");
        By goodBy = By.cssSelector(GoodsTest.GOOD_LOCATOR);
        actions.getWebDriverWait()
                .until(ExpectedConditions.visibilityOfElementLocated(goodBy));
        WebElement good = actions.findElement(goodBy);
        // Verify price styles.
        this.verifyStyles(good);

        // Collect good data
        Data expected = new Data();
        // -- name
        expected.name = good.findElement(By.cssSelector("div.name")).getText();

        // -- campaign price
        expected.campaignPrice = good.findElement(this.campaignPriceBy)
                .getText();

        // -- regular price
        expected.regularPrice = good.findElement(this.regularPriceBy).getText();
        GoodsTest.log.info("  Found expected: " + expected);

        // Click on good
        GoodsTest.log.info("Click on good found");
        actions.clickAndWait(goodBy);

        WebElement priceWrapper = actions.getWebDriver()
                .findElement(By.cssSelector("#box-product .price-wrapper"));
        // Verify price styles.
        this.verifyStyles(priceWrapper);

        // Collect good data from page
        Data actual = new Data();
        // -- name
        actual.name = actions.getWebDriver()
                .findElement(By.cssSelector("#box-product h1")).getText();
        // -- campaign price
        actual.campaignPrice = priceWrapper
                .findElement(By.cssSelector(".campaign-price")).getText();

        // -- regular price
        actual.regularPrice = priceWrapper
                .findElement(By.cssSelector(".regular-price")).getText();
        GoodsTest.log.info("  Found actual  : " + actual);
        GoodsTest.log.info("Verify if expected = actual");
        Assert.assertEquals("Wrong good page is opened", expected, actual);
    }

    /**
     * Verifies price styles.
     *
     * @param root
     *            element which contains prices
     */
    private void verifyStyles(final WebElement root) {
        Actions actions = Actions.getActions();
        // verify campaign price style
        GoodsTest.log.info("Verify presence of big red price");
        Assert.assertTrue(GoodsTest.CAMPAIGN_PRICE_NOT_FOUND,
                actions.findSubElements(root, this.campaignPriceBy));
        GoodsTest.log.info("Verify presence of small gray price");
        // verify regular price style
        Assert.assertTrue(GoodsTest.REGULAR_PRICE_NOT_FOUND,
                actions.findSubElements(root, this.regularPriceBy));
    }
}

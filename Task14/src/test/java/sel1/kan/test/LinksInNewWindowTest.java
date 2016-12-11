package sel1.kan.test;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import sel1.kan.wd.Actions;

/**
 * Verifies that links are opened in new window.
 */
public class LinksInNewWindowTest {

    /**
     * Logger instance.
     */
    private final Logger log = LogManager.getLogger(LinksInNewWindowTest.class);

    /**
     * Verifies links are opened in new window.
     */
    @Test
    public void openLinks() {
        Actions actions = Actions.getActions();
        List<WebElement> links = actions.getWebDriver()
                .findElements(By.cssSelector("#content a[target=_blank]"));
        String mainWindowId = actions.getWebDriver().getWindowHandle();
        this.log.info("Main window ID: " + mainWindowId);

        for (WebElement link : links) {
            String windowId = this.clickOnExternalLink(link);
            this.log.info("New window ID: " + windowId);
            actions.getWebDriver().switchTo().window(windowId);
            this.log.info("Close new window and switch back to main");
            actions.getWebDriver().close();
            actions.getWebDriver().switchTo().window(mainWindowId);
        }
    }

    /**
     * Clicks on link which opens new window and returns new window's id.
     *
     * @param link
     *            link to click
     * @return new window's id
     */
    private String clickOnExternalLink(final WebElement link) {
        this.log.info("Click on " + link + " and wait for new window to open");
        Actions actions = Actions.getActions();
        Set<String> oldWindows = actions.getWebDriver().getWindowHandles();
        link.click();
        return actions.getWebDriverWait().until((final WebDriver d) -> {
            Set<String> curWindows = actions.getWebDriver().getWindowHandles();
            curWindows.removeAll(oldWindows);

            if (curWindows.size() > 0) {
                return curWindows.iterator().next();
            }

            return null;
        });
    }
}

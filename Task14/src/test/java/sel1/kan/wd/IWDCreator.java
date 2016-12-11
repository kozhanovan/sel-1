package sel1.kan.wd;

import org.openqa.selenium.WebDriver;

/**
 * Encapsulates web driver creation.
 */
public interface IWDCreator {
    /**
     * Creates new {@link WebDriver} instance.
     * 
     * @return new {@link WebDriver} instance
     */
    WebDriver create();
}

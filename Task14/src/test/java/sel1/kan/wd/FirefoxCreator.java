package sel1.kan.wd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Creates firefox driver.
 */
public class FirefoxCreator implements IWDCreator {

    @Override
    public WebDriver create() {
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        return new FirefoxDriver(caps);
    }

}

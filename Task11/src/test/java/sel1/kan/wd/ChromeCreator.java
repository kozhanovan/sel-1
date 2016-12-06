package sel1.kan.wd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Creates chrome.
 */
public class ChromeCreator implements IWDCreator {

    @Override
    public WebDriver create() {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        return new ChromeDriver(caps);
    }

}

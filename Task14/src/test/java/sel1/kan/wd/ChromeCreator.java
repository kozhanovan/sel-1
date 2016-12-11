package sel1.kan.wd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.ChromeDriverManager;

/**
 * Creates chrome.
 */
public class ChromeCreator implements IWDCreator {

    @Override
    public WebDriver create() {
        ChromeDriverManager.getInstance().setup();
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        return new ChromeDriver(caps);
    }

}

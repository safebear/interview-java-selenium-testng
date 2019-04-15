package utils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Browser {

    // Get our WebApp URL from the Maven command or the CI pipeline
    protected static final String URL = System.getProperty("url", "http://toolslist.safebear.co.uk:8080/");

    // Get our browser from the Maven command or the CI pipeline
    private static final String BROWSERNAME = System.getProperty("browser", "chrome");
    private static final String OS = System.getProperty("os", "MAC");


    // Set up our empty driver.
    protected static RemoteWebDriver browser;

    /**
     * Getter for the URL
     * @return URL for the website under test.
     */
    protected static String getURL() {
        return URL;
    }

    /**
     * Initialise our browser
     * @throws MalformedURLException
     */
    protected static void initialiseBrowser() throws MalformedURLException {

        DesiredCapabilities cap = new DesiredCapabilities();
        Platform platform = Platform.fromString(OS.toUpperCase());
        String node;

        ChromeOptions chromeOptions;
        FirefoxProfile firefoxProfile;
        FirefoxOptions firefoxOptions;


        switch(BROWSERNAME.toUpperCase()) {

            case ("FIREFOX"):

                System.out.println(" Executing on FireFox");

                node = "http://localhost:5555/wd/hub";

                firefoxProfile = new FirefoxProfile();
                firefoxOptions = new FirefoxOptions();
                firefoxOptions.setProfile(firefoxProfile);
                cap.setCapability(FirefoxDriver.PROFILE, firefoxProfile);

                cap.setCapability("platform", platform);

                cap.setBrowserName("firefox");

                browser = new RemoteWebDriver(new URL(node), cap);

                // Puts an Implicit wait, Will wait for 10 seconds before throwing exception
                browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                break;


            case ("CHROME"):

                System.out.println(" Executing on CHROME");

                chromeOptions = new ChromeOptions();
                cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                cap.setBrowserName("chrome");

                node = "http://localhost:5556/wd/hub";

                browser = new RemoteWebDriver(new URL(node), cap);

                browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                break;


            case ("CHROME_HEADLESS"):

                System.out.println(" Executing on CHROME HEADLESS");

                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("headless");
                chromeOptions.addArguments("window-size=1920,1080");
                chromeOptions.addArguments("start-maximized");
                cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                cap.setBrowserName("chrome");

                node = "http://localhost:5556/wd/hub";

                browser = new RemoteWebDriver(new URL(node), cap);
                browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                break;

            case ("IE"):
                System.out.println(" Executing on IE");
                cap = DesiredCapabilities.chrome();
                cap.setBrowserName("ie");
                node = "http://localhost:5558/wd/hub";
                browser = new RemoteWebDriver(new URL(node), cap);
                browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                break;


            default:

                throw new IllegalArgumentException("The Browser Type is Undefined");

        }

    }

}

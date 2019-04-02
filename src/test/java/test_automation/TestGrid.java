package test_automation;
import Utils.Browser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.RemoteWebDriver;

public class TestGrid {

    Browser browser = new Browser();
    RemoteWebDriver driver;

//    WebDriver driver;
//    String baseURL, hubURL;
//
//    @Before
//    public void setUp() throws MalformedURLException {
//        String URL = "http://www.calculator.net";
//        String browser = "chrome";
//
//        if (browser.equalsIgnoreCase("firefox")) {
//            System.out.println(" Executing on FireFox");
//            String Node = "http://localhost:5555/wd/hub";
//            DesiredCapabilities cap = DesiredCapabilities.firefox();
//            cap.setBrowserName("firefox");
//
//            driver = new RemoteWebDriver(new URL(Node), cap);
//            // Puts an Implicit wait, Will wait for 10 seconds before throwing exception
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//            // Launch website
//            driver.navigate().to(URL);
//            driver.manage().window().maximize();
//        } else if (browser.equalsIgnoreCase("chrome")) {
//            System.out.println(" Executing on CHROME");
//            DesiredCapabilities cap = DesiredCapabilities.chrome();
//            cap.setBrowserName("chrome");
//            String Node = "http://localhost:5556/wd/hub";
//            driver = new RemoteWebDriver(new URL(Node), cap);
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//            // Launch website
//            driver.navigate().to(URL);
//            driver.manage().window().maximize();
//        } else if (browser.equalsIgnoreCase("ie")) {
//            System.out.println(" Executing on IE");
//            DesiredCapabilities cap = DesiredCapabilities.chrome();
//            cap.setBrowserName("ie");
//            String Node = "http://localhost:5558/wd/hub";
//            driver = new RemoteWebDriver(new URL(Node), cap);
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//            // Launch website
//            driver.navigate().to(URL);
//            driver.manage().window().maximize();
//        } else {
//            throw new IllegalArgumentException("The Browser Type is Undefined");
//        }
//    }

    @Before
    public void setUp(){

        try {
            driver = browser.getBrowser();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.navigate().to(Browser.getURL());

    }

    @After
    public void afterTest() {
        driver.quit();
    }

    @Test
    public void sampleTest() {

        if (driver.getTitle().contains("Calculator")) {
            Assert.assertTrue("Link Found",true );
        } else {
            Assert.assertTrue("Failed: Link not found", false );
        }

    }
}

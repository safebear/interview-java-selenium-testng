package test_automation.tests.testng;

import Utils.Waits;
import test_automation.pages.ExampleLoginPage;
import test_automation.pages.ExampleToolsPage;

import java.net.MalformedURLException;

public class ExampleBaseTest extends Waits {


    ExampleLoginPage loginPage = new ExampleLoginPage();
    ExampleToolsPage toolsPage = new ExampleToolsPage();

    // Uncomment this out if using TestNG
    // @BeforeTest
    public void  setUp(){

        try {
            initialiseBrowser();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // Uncomment this out if using TestNG
    // @BeforeMethod
    public void setUpTest(){
        browser.navigate().to(getURL());
    }

    // Uncomment this out if using TestNG
    // @AfterTest
    public void tearDown(){

        browser.quit();

    }

}

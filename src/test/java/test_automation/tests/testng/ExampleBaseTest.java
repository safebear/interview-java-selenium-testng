package test_automation.tests.testng;

import Utils.Waits;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.*;
import test_automation.pages.ExampleLoginPage;
import test_automation.pages.ExampleToolsPage;

import java.net.MalformedURLException;

public class ExampleBaseTest extends Waits {


    ExampleLoginPage loginPage = new ExampleLoginPage();
    ExampleToolsPage toolsPage = new ExampleToolsPage();

    @BeforeTest
    public void  setUp(){

        try {
            initialiseBrowser();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUpTest(){
        browser.navigate().to(getURL());
    }

    @AfterTest
    public void tearDown(){

        browser.quit();

    }

}

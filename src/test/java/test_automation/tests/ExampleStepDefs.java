package test_automation.tests;

import Utils.Browser;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import test_automation.pages.ExampleLoginPage;
import test_automation.pages.ExampleToolsPage;

import java.net.MalformedURLException;

public class ExampleStepDefs extends Browser {


    ExampleLoginPage loginPage;
    ExampleToolsPage toolsPage;


    @Before
    public void setUp(){
        try {
            initialiseBrowser();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        loginPage = new ExampleLoginPage();
        toolsPage = new ExampleToolsPage();
    }

    @After
    public void tearDown(){

        //This closes the browser after each section
        browser.quit();
    }

    @Given("I navigate to the login page")
    public void i_navigate_to_the_login_page() {
        //Step1 ACTION: Open our web application in the browser
        browser.navigate().to(getURL());
        Assert.assertEquals("Login Page", loginPage.getPageTitle());

        // List<list> data = table.raw();
    }

    @When("I enter the login details for a {string}")
    public void i_enter_the_login_details_for_a(String userType) {
        switch (userType)
        {
            case "invalidUser":
                loginPage.enterUsername("attacker");
                loginPage.enterPassword("dontletmein");
                loginPage.clickLoginButton();
                break;
            case "validUser":
                loginPage.enterUsername("tester");
                loginPage.enterPassword("letmein");
                loginPage.clickLoginButton();
                break;
            default:
                Assert.fail("The test data is wrong - the only values that can be accepted are 'validUser or 'invalidUser'");
                break;
        }
    }

    @Then("I can see the following message: {string}")
    public void i_can_see_the_following_message(String validationMessage) {
        switch(validationMessage)
        {
            case "Username or Password is incorrect":
                Assert.assertTrue(loginPage.checkForFailedLoginWarning().contains(validationMessage));
                break;
            case "Login Successful":
                Assert.assertTrue(toolsPage.checkForLoginSuccessfulMessage().contains(validationMessage));
                break;
            default:
                Assert.fail("The test data is wrong");
                break;
        }
    }



}

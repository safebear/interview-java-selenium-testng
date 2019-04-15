package test_automation.tests.testng;

import utils.StaticProvider;
import utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExampleTestNgTest extends ExampleBaseTest {

     @Test(dataProvider = "testProducts", dataProviderClass = StaticProvider.class)
    public void loginTest(TestData loginDetails){

        Assert.assertEquals("Login Page", loginPage.getPageTitle());

        loginPage.enterUsername(loginDetails.getUsername());
        loginPage.enterPassword(loginDetails.getPassword());
        loginPage.clickLoginButton();

        switch (loginDetails.getUsername()){

            case "attacker":

                Assert.assertTrue(loginPage.checkForFailedLoginWarning().contains(loginDetails.getValidationMessage()));

                break;

            case "tester":

                Assert.assertTrue(toolsPage.checkForLoginSuccessfulMessage().contains(loginDetails.getValidationMessage()));

                break;

            default:

                Assert.fail("bad data");
                break;

        }



    }



}

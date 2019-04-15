package test_automation.tests.testng;

import utils.ExcelDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExampleTestNgTestCsv extends ExampleBaseTest {

    @Test(dataProvider = "testProductsCsv", dataProviderClass = ExcelDataProvider.class)
    public void loginTest(String username, String password, String message){

        Assert.assertEquals("Login Page", loginPage.getPageTitle());

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        switch (username){

            case "attacker":

                Assert.assertTrue(loginPage.checkForFailedLoginWarning().contains(message));

                break;

            case "tester":

                Assert.assertTrue(toolsPage.checkForLoginSuccessfulMessage().contains(message));

                break;

            default:

                Assert.fail("bad data");
                break;

        }



    }



}

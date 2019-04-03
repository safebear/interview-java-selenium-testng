package test_automation.tests.testng;

import Utils.TestData;

public class ExampleTestNgTest extends ExampleBaseTest {

    // Uncomment this out for TestNG
    // @Test(dataProvider = "testProducts", dataProviderClass = StaticProvider.class)
    // @Test
    public void loginTest(TestData loginDetails){

        // Assert.assertEquals("Login Page", loginPage.getPageTitle());

        loginPage.enterUsername("attacker");
        loginPage.enterPassword("dontletmein");
        loginPage.clickLoginButton();

        // Assert.assertTrue(loginPage.checkForFailedLoginWarning().contains(validationMessage));




    }



}

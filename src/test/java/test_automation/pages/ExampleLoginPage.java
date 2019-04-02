package test_automation.pages;

import Utils.Waits;
import test_automation.pages.Locators.ExampleLoginPageLocators;

public class ExampleLoginPage extends Waits {

    ExampleLoginPageLocators locators = new ExampleLoginPageLocators();

    public String getPageTitle(){
        return browser.getTitle();
    }

    public void enterUsername(String username){
        waitForElement(locators.getUsernameLocator()).sendKeys(username);
    }

    public void enterPassword(String password){
        waitForElement(locators.getPasswordFieldLocator()).sendKeys(password);
    }

    public void clickLoginButton(){
        // driver.findElement(locators.getLoginButtonLocator()).click();
        waitForElement(locators.getLoginButtonLocator()).click();
    }

    public String checkForFailedLoginWarning(){
        // return driver.findElement(locators.getFailedLoginMessage()).getText();
        return waitForElement(locators.getFailedLoginMessage()).getText();

    }
}

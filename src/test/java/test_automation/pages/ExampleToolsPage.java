package test_automation.pages;

import Utils.Waits;
import test_automation.pages.Locators.ExampleToolsPageLocators;

public class ExampleToolsPage extends Waits{

    ExampleToolsPageLocators tpl = new ExampleToolsPageLocators();

    public String getPageTitle(){
        return browser.getTitle();
    }

    public String checkForLoginSuccessfulMessage(){
        return waitForElement(tpl.getSuccessfulLoginMessage()).getText();
    }
}

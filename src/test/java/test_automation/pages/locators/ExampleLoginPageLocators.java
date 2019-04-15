package test_automation.pages.locators;

import lombok.Data;
import org.openqa.selenium.By;

@Data
public class ExampleLoginPageLocators {
    //fields
    private By usernameLocator = By.id("username");
    private By passwordFieldLocator = By.id("password");

    //buttons
    private By loginButtonLocator = By.id("enter");

    //messages
    private By failedLoginMessage = By.xpath(".//p[@id= 'rejectLogin']/b");

}

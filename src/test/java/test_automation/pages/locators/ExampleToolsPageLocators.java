package test_automation.pages.locators;

import lombok.Data;
import org.openqa.selenium.By;

@Data
public class ExampleToolsPageLocators {
    //messages
    private By successfulLoginMessage = By.xpath(".//body/div[@class = 'container']/p/b");
}

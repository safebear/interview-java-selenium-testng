package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class Waits extends Browser {

    private static Screenshots screenshots = new Screenshots();

    private static final String WAIT = System.getProperty( "waitTime", "10");

    protected static WebElement waitForElement(By locator){

        WebElement element = null;


        try {

            element = (new WebDriverWait(browser, Integer.parseInt(WAIT)))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));

        }catch (TimeoutException e){

            e.printStackTrace();
            //captureScreenShot(browser,generateScreenShotFileName());
            Assert.fail("Timeout: The element couldn't be found in " + WAIT + " seconds!");

        }catch (Exception e){

            screenshots.capturescreenshot("timeout-finding-element-");
            e.printStackTrace();
            Assert.fail("Something went wrong!");

        }

        return element;

    }

    protected static WebElement waitForElementToBeEnabled(By locator){

        WebElement element = null;

        try {

            element = (new WebDriverWait(browser, Integer.parseInt(WAIT)))
                    .until(ExpectedConditions.elementToBeClickable(locator));

        }catch (TimeoutException e){

            screenshots.capturescreenshot("element-not-enabled-");
            e.printStackTrace();
            Assert.fail("The element wasn't enabled");
        }

        return element;


    }

    protected static List<WebElement> waitForElements (By locator) {
        //create a counter and assign it a 0 value
        int i = 0;
        //create a list of type webelement and call it elements and give it a value of null or empty
        List<WebElement> elements = new ArrayList<>();

        //loop -> while the list created above is empty and is smaller than 6 do the following below:
        while (elements.isEmpty() && i < (Integer.parseInt(WAIT) * 1000) ){
            //to the list called 'elements' call the webdriver.findelement with the locator holder
            elements = browser.findElements(locator);
            //increment counter by 1
            i++;
            //if the list is empty
            if (elements.isEmpty()){
                //sleep for 1 second
                try {
                    Thread.sleep(1);
                    //catch an error not quite sure what the catch is looking for here
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //otherwise return the list
            } else {

                return elements;
            }

        }

        screenshots.capturescreenshot("timeout-finding-elements-");
        Assert.fail("Timeout: Couldn't find the Elements on the page");

        return elements;

    }

    protected static List<WebElement> waitForElementsNoFail (By locator) {
        //create a counter and assign it a 0 value
        int i = 0;
        //create a list of type webelement and call it elements and give it a value of null or empty
        List<WebElement> elements = new ArrayList<>();

        //loop -> while the list created above is empty and is smaller than 6 do the following below:
        while (elements.isEmpty() && i < 6 ){
            //to the list called 'elements' call the webdriver.findelement with the locator holder
            elements = browser.findElements(locator);
            //increment counter by 1
            i++;
            //if the list is empty
            if (elements.isEmpty()){
                //sleep for 1 second
                try {
                    Thread.sleep(3000);
                    //catch an error not quite sure what the catch is looking for here
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //otherwise return the list
            } else {

                return elements;
            }

        }

        return elements;

    }


}

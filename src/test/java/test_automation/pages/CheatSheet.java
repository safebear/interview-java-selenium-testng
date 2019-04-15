package test_automation.pages;

import utils.Waits;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.net.URL;

public class CheatSheet extends Waits {


    /**
     * Drop-down selectors
     */
    public void dropDownSelectors() {

        /*********************************
         * Dropdown selectors example
         *********************************/
        Select drpCountry = new Select(browser.findElement(By.name("country")));
        drpCountry.selectByVisibleText("ANTARCTICA");

        /*********************************
         * Selecting Items in a Multiple SELECT elements
         *********************************/
        Select fruits = new Select(browser.findElement(By.id("fruits")));
        fruits.selectByVisibleText("Banana");
        fruits.selectByIndex(1);

    }


    /**
     * Mouse keys - e.g. hover
     */
    public void mouseKeys(){

        /*********************************
         * Using mousekeys with 'action' class
         *********************************/
        WebElement link_Home = browser.findElement(By.id("id"));
        WebElement td_Home = browser.findElement(By.id("id"));

        Actions builder = new Actions(browser);
        Action mouseOverHome = builder
                .moveToElement(link_Home)
                .build();

        String bgColor = td_Home.getCssValue("background-color");
        System.out.println("Before hover: " + bgColor);
        mouseOverHome.perform();
        bgColor = td_Home.getCssValue("background-color");
        System.out.println("After hover: " + bgColor);

    }

    /**
     * Alert message
     */
    public void alertMessage(){


        /*********************************
         * Alert Message handling
         *********************************/

        // Switching to Alert
        Alert alert = browser.switchTo().alert();

        // Capturing alert message.
        String alertMessage= browser.switchTo().alert().getText();

        // Displaying alert message
        System.out.println(alertMessage);

        // Accepting alert
        alert.accept();


    }

    /**
     * Pop-up windows
     */
    public void popUpWindows(){
        /*********************************
         * Pop-up windows
         *********************************/

        String MainWindow=browser.getWindowHandle();

        // To handle all new opened window.
        Set<String> s1=browser.getWindowHandles();
        Iterator<String> i1=s1.iterator();

        while(i1.hasNext())
        {
            String ChildWindow=i1.next();

            if(!MainWindow.equalsIgnoreCase(ChildWindow))
            {

                // Switching to Child window
                browser.switchTo().window(ChildWindow);
                browser.findElement(By.name("emailid"))
                        .sendKeys("gaurav.3n@gmail.com");

                browser.findElement(By.name("btnLogin")).click();

                // Closing the Child Window.
                browser.close();
            }
        }
        // Switching to Parent window i.e Main Window.
        browser.switchTo().window(MainWindow);

    }

    /**
     * Verify ToolTip
     */
    public void verifyToolTip(){

        /*************************
         * Verifying a ToolTip
         **************************/

        String expectedTooltip = "Github";

        // Find the Github icon at the top right of the header
        WebElement github = browser.findElement(By.xpath(".//*[@class='soc-ico show-round']/a[4]"));

        //get the value of the "title" attribute of the github icon
        String actualTooltip = github.getAttribute("title");

        //Assert the tooltip's value is as expected
        System.out.println("Actual Title of Tool Tip"+actualTooltip);
        if(actualTooltip.equals(expectedTooltip)) {
            System.out.println("Test Case Passed");
        }
    }

    /**
     * Check for Broken Links
     */
    public void checkForBrokenLinks(){
        String homePage = "http://www.zlti.com";
        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;

        List<WebElement> links = browser.findElements(By.tagName("a"));

        Iterator<WebElement> it = links.iterator();

        while(it.hasNext()){

            url = it.next().getAttribute("href");

            System.out.println(url);

            if(url == null || url.isEmpty()){
                System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }

            if(!url.startsWith(homePage)){
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }

            try {
                huc = (HttpURLConnection)(new URL(url).openConnection());

                huc.setRequestMethod("HEAD");

                huc.connect();

                respCode = huc.getResponseCode();

                if(respCode >= 400){
                    System.out.println(url+" is a broken link");
                }
                else{
                    System.out.println(url+" is a valid link");
                }

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Date Picker
     */
    public void datePicker() throws InterruptedException {


        //DAte and Time to be set in textbox

        String dateTime ="12/07/2014 2:00 PM";


        //button to open calendar

        WebElement selectDate = browser.findElement(By.xpath("//span[@aria-controls='datetimepicker_dateview']"));

        selectDate.click();

        //button to move next in calendar

        WebElement nextLink = browser.findElement(By.xpath("//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-next')]"));

        //button to click in center of calendar header

        WebElement midLink = browser.findElement(By.xpath("//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-fast')]"));

        //button to move previous month in calendar

        WebElement previousLink = browser.findElement(By.xpath("//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-prev')]"));

        //Split the date time to get only the date part

        String date_dd_MM_yyyy[] = (dateTime.split(" ")[0]).split("/");

        //get the year difference between current year and year to set in calander

        int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2])- Calendar.getInstance().get(Calendar.YEAR);

        midLink.click();

        if(yearDiff!=0){

            //if you have to move next year

            if(yearDiff>0){

                for(int i=0;i< yearDiff;i++){

                    System.out.println("Year Diff->"+i);

                    nextLink.click();

                }

            }

            //if you have to move previous year

            else if(yearDiff<0){

                for(int i=0;i< (yearDiff*(-1));i++){

                    System.out.println("Year Diff->"+i);

                    previousLink.click();

                }

            }

        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Get all months from calendar to select correct one

        List<WebElement> list_AllMonthToBook = browser.findElements(By.xpath("//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));

        list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1])-1).click();

        Thread.sleep(1000);

        //get all dates from calendar to select correct one

        List<WebElement> list_AllDateToBook = browser.findElements(By.xpath("//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));

        list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0])-1).click();

        ///FOR TIME

        WebElement selectTime = browser.findElement(By.xpath("//span[@aria-controls='datetimepicker_timeview']"));

        //click time picker button

        selectTime.click();

        //get list of times

        List<WebElement> allTime = browser.findElements(By.xpath("//div[@data-role='popup'][contains(@style,'display: block')]//ul//li[@role='option']"));

        dateTime = dateTime.split(" ")[1]+" "+dateTime.split(" ")[2];

        //select correct time

        for (WebElement webElement : allTime) {

            if(webElement.getText().equalsIgnoreCase(dateTime))

            {

                webElement.click();

            }

        }
    }

    /**
     * Switching frames
     */
    public void switchingFrames(){

        browser.switchTo().frame("a077aa5e"); //switching the frame by ID

        browser.switchTo().parentFrame(); // Switch to parent
        browser.switchTo().defaultContent(); // switch to main frame

        // Find number of iframes on the page:
        int size = browser.findElements(By.tagName("iframe")).size();

        // Set index for iframe
        int indexOfIFrame = 0;
        // Find the frame that contains our element:
        for(int i=0; i<=size; i++){
            browser.switchTo().frame(i);
            int total=browser.findElements(By.xpath("html/body/a/img")).size();
            if(total == 1){
                indexOfIFrame = i;
            };
            browser.switchTo().defaultContent();}

        browser.switchTo().frame(indexOfIFrame); //Switching to the frame
        browser.findElement(By.xpath("html/body/a/img")).click();



    }


    /**
     * Drag and Dripe
     * @param source WebElement
     * @param target WebElement
     * @throws IOException Reads from a JavaScript file
     */
    public void dragAndDrop(WebElement source, WebElement target) throws IOException {

        // Normal way
        Actions act=new Actions(browser);
        act.dragAndDrop(source, target);
        act.build().perform();

        // Alternative way with JavaScript, if it doesn't work
        String script = new String(Files.readAllBytes(Paths.get("misc/DragAndDrop.js")));
        script += "simulateHTML5DragAndDrop(arguments[0], arguments[1])";
        JavascriptExecutor executor = (JavascriptExecutor)browser;
        executor.executeScript(script, source, target);

    }

    /**
     * Scrolling
     */
    public void scrolling(WebElement element){

        JavascriptExecutor js = (JavascriptExecutor) browser;

        // Until Element is visible
        js.executeScript("arguments[0].scrollIntoView();",element );

        // To the bottom of the page
        //This will scroll the web page till end.
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

    }

    /**
     * Double and right click
     */
    public void doubleAndRightClick(){


        Actions actions = new Actions(browser);
        WebElement elementLocator = browser.findElement(By.id("ID"));

        // Double click
        actions.doubleClick(elementLocator).perform();

        // Right click
        actions.contextClick(elementLocator).perform();

    }

}

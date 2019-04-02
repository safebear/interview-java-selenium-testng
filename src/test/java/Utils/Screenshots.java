package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.openqa.selenium.io.FileHandler.copy;

public class Screenshots extends Browser {

    private String generateScreenShotFileName() {
        //create filename
        return new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date()) + ".png";
    }

    public void capturescreenshot( String filename ) {
        //take screenshot
        File scrFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);

        //Make sure that the "screenshots" directory exists
        File file = new File("target/screenshots");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Failed to create directory!");
            }
        }

        //copy file to filename and location we set before
        try {
            copy(scrFile, new File("target/screenshots/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package examples;

import java.time.Duration;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CaptureScreenshots {

	public static void main(String[] args) {
		 // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        
        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Navigate to the website
        driver.get("https://demo.nopcommerce.com");
        
        // Maximize the browser window
        driver.manage().window().maximize();
        
        // Take a full-page screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
    
        // Define the target file path
        File targetFile = new File(System.getProperty("user.dir") + "\\screenshots\\fullpage.png");
        // Copy the source file to the target location
        sourceFile.renameTo(targetFile);
        
        
        //2)capture the screenshot of specific section
        WebElement feturedproducts = driver.findElement(By.xpath("//div[@class='product-grid home-page-product-grid']"));
        
        File sourcef = feturedproducts.getScreenshotAs(OutputType.FILE);
        File targetf = new File(System.getProperty("use.dir")+"\\screenshots\\scr.png");
        sourcef.renameTo(targetf);
        // Close the browser
        driver.quit();

	}

}

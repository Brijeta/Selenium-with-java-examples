package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class HandlingSliders {
    public static void main(String[] args) throws InterruptedException {
        // Initialize WebDriver (Assuming the ChromeDriver is set up in the system path)
        WebDriver driver = new ChromeDriver();
        
        // Navigate to the jQuery UI slider demo page
        driver.get("https://jqueryui.com/slider/");
        driver.manage().window().maximize();
        
        // Locate the frame containing the slider and switch to it
        WebElement frame = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frame);
        
        // Initialize the Actions class for performing complex user interactions
        Actions act = new Actions(driver);
        
        // Locate the slider element using its XPath
        WebElement elemslider = driver.findElement(By.xpath("//span[@class='ui-slider-handle ui-corner-all ui-state-default']"));
        
        // Print the default location of the slider
        System.out.println("Default location of the slider: " + elemslider.getLocation());
        
        // Move the slider to the right by 100 pixels horizontally and 249 pixels vertically
        act.dragAndDropBy(elemslider, 100, 249).perform();
        
        // Pause execution for 2 seconds to observe the change
        Thread.sleep(2000);
        
        // Close the browser and quit the WebDriver
        driver.quit();
    }
}

package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptInterfaceExample {

	 public static void main(String[] args) throws InterruptedException {
	        // Initialize the Chrome WebDriver
	        WebDriver driver = new ChromeDriver();

	        // Navigate to the test automation practice website
	        driver.get("https://testautomationpractice.blogspot.com/");

	        // Locate the text input element and set its value using JavaScript
	       WebElement ele = driver.findElement(By.xpath("//input[@id='name']"));
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].setAttribute('value','briju')", ele);

	        // Locate the radio button and click it using JavaScript
	        WebElement radioButton = driver.findElement(By.xpath("//input[@id='female']"));
	        js.executeScript("arguments[0].click();", radioButton);
	       
	        //1) scroll down page by pixel number
	        js.executeScript("window.scrollBy(0,1000)", "");
	        
	        //2) scroll the page till element is visible
	        WebElement visele = driver.findElement(By.xpath("//div[@class='feed-links']"));
	        js.executeScript("argument[0].scrollIntoView();", visele);
	        System.out.println(js.executeScript("return window.pageYOffset;"));
	        
	        //3)  scroll the page end of the page
	        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	        System.out.println(js.executeScript("return window.pageYOffset;"));
	        Thread.sleep(2000);
	        driver.quit();
	    }
	

}

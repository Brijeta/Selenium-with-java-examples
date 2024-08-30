package examples;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingFrames {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://ui.vision/demo/webtest/frames/");
		driver.manage().window().maximize();
		//Example of : switch to outer frame and interact with element
		WebElement fram3 = driver.findElement(By.xpath("//frame[@src='frame_3.html']"));
		driver.switchTo().frame(fram3); //switch by web element
		
		//Example of : switch to inner/nested iframe
		
		driver.switchTo().frame(0);// switching by index number of iframe
		
		//Example of :switch to default frame of web page
		driver.switchTo().defaultContent();//switch back to default frame
		

		
		//Example of : form that has fields inside an iframe
		// Switch to the iframe containing the form
		driver.switchTo().frame(fram3); //outer frame
		driver.switchTo().frame(0); //inner frame
		// Fill out the form fields
		WebElement textField = driver.findElement(By.xpath("//input[@type='text']"));
		textField.sendKeys("Test Input");
		WebElement nextButton = driver.findElement(By.xpath("//div[@role='button']"));
		nextButton.click();
	

		
		//Example of : handle a form that is dynamically loaded or updated with in iframe.
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("textFieldName")));
		
		
		// Switch back to the default content
		driver.switchTo().defaultContent();
		driver.close();
		
		

	}

}

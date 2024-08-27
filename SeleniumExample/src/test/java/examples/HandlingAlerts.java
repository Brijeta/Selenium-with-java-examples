package examples;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import org.openqa.selenium.*;

public class HandlingAlerts {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		driver.manage().window().maximize();
		
		//Normal alert with OK button
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='jsAlert()']"))).click();
		Alert myalert = driver.switchTo().alert();
		System.out.println(myalert.getText());
		myalert.accept(); //accept() method use to accept alert and close popup
		
		
		//confirmation alert - ok and cancel
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='jsConfirm()']"))).click();
		driver.switchTo().alert().dismiss(); //closed popup by click on cancel button.
		//driver.switchTo().alert().accept();//closed pop up by click on ok button on alert.
		
		//prompt alert -input box
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='jsPrompt()']"))).click();
		Alert al = driver.switchTo().alert();
		al.sendKeys("hello");
		al.accept();
		
		//handle alert without switch command, using explicit wait
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='jsPrompt()']"))).click();
		Alert myalt= wait.until(ExpectedConditions.alertIsPresent()); //capture alert window
		myalt.accept();
		Thread.sleep(5000); // to see action
		driver.quit();
		
		//Authentication Alert
//		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
//		Thread.sleep(2000);
//		driver.quit();
		
		
		
	}

}

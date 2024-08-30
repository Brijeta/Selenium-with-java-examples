package examples;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HandleMouseAction {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	/*	driver.get("https://demo.opencart.com/");
		driver.manage().window().maximize();
		
		WebElement desktops = wait.until(ExpectedConditions.presenceOfElementLocated
				(By.xpath("//a[normalize-space()='Desktops']")));
		WebElement mac=  wait.until(ExpectedConditions.presenceOfElementLocated
				(By.xpath("//a[normalize-space()='Mac (1)']")));
		
		Actions act= new Actions(driver);
		
		//Mouse hover action
		act.moveToElement(desktops).moveToElement(mac).click().build().perform();
		driver.quit();*/
		
		//right click action -context menu
	/*	driver.get("https://swisnl.github.io/jQuery-contextMenu/3.x/demo.html");
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated
				(By.xpath("//span[@class=\'context-menu-one btn btn-neutral\']")));
		Actions act= new Actions(driver);
		act.contextClick(ele).perform();
		Thread.sleep(2000);
		driver.quit();*/
		
		//double click action
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_ev_ondblclick");
		driver.switchTo().frame("iframeResult");
		WebElement dbele = wait.until(ExpectedConditions.presenceOfElementLocated
				(By.xpath("//button[normalize-space()='Double-click me']")));
		Actions act =new Actions(driver);
		act.doubleClick(dbele).perform();
		Thread.sleep(2000);
		driver.quit();
		
	}

}

package examples;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingCheckboxes {

	public static void main(String[] args) {
		WebDriver driver =new ChromeDriver();
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));

		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		
		//Example 1 : select specific checkbox
		wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//input[@id='sunday']")))).click();
		
		//Example 2 : select all the chcekboxes
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@class='form-check-input' and @type='checkbox']"));
//		for (WebElement checkbox : checkboxes) {
//			if(!checkbox.isSelected()){
//				checkbox.click();
//			}
//		}
		
		//Example 3 :Select last 3 checkboxes
		for(int i = checkboxes.size()-3;i<checkboxes.size();i++){
			if(!checkboxes.get(i).isSelected()) {
				checkboxes.get(i).click();
			}
		}
		
		
		//Example 4 : unselect checkboxes if they are selected
		for(WebElement checkbox : checkboxes) {
			if(checkbox.isSelected()) {
				checkbox.click();
			}
		}
		
		//close browser window
		driver.close();

	}

}

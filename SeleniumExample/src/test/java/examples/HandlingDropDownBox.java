package examples;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingDropDownBox {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver =new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
//		driver.get("https://testautomationpractice.blogspot.com/");
//		driver.manage().window().maximize();
//		
//		//Example of : Select dropdown
//		WebElement dropdowncountry = driver.findElement(By.id("country"));
//		Select select =new Select(dropdowncountry);
//		// Select by visible text(text on UI)
//        select.selectByVisibleText("Canada"); 
//        // Select by value
//        select.selectByValue("uk");
//        // Select by index
//        select.selectByIndex(3);
//		
//        
//        //Example of :count how many options we have
//		List<WebElement> options = select.getOptions();
//		System.out.println(options.size());
//		for(int i=0;i<options.size();i++) {
//			System.out.println(options.get(i).getText());
//		}
//		
//		
//		
//		//Example of : select multiple options (works with bootstrap drop down)
//		// Select multiple options by visible text
//        select.selectByVisibleText("Canada");
//        select.selectByVisibleText("Japan");
//
//        // Deselect multiple options by visible text
//        //select.deselectByVisibleText("China");
//
//        // Get all selected options
//        List<WebElement> selectedOptions = select.getAllSelectedOptions();
//        for (WebElement option : selectedOptions) {
//            System.out.println(option.getText());
//        }
//        Thread.sleep(5000);
//		driver.close();
//		
		
		//Example of  : bootstrap drop down --> select single option 
//		driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0_2");
//		driver.manage().window().maximize();
//		driver.findElement(By.xpath("//button[contains(@class,'multiselect')]")).click();//open drop down option
//		//1) capture single option
//		driver.findElement(By.xpath("//input[@value='Java']")).click();
//		//2) capture all the options and find out size
//		List<WebElement> optionsbt = driver.findElements(By.xpath("//ul[contains(@class,'multiselect')]//label"));
//		System.out.println(optionsbt.size());
//		//3) printing options from dropdown
//		for(WebElement op:optionsbt) {
//			System.out.println(op.getText());
//		}
//		//4) select multiple options
//		for(WebElement op: optionsbt) {
//			if(op.getText().equals("Java") || op.getText().equals("C#") || op.getText().equals("Python")){
//				op.click();
//			}
//		}
//		driver.quit();
		
		
		
		//Example of : hidden drop down
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
//		//login steps
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username"))).sendKeys("Admin");
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password"))).sendKeys("admin123");
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Login']"))).click();
//		//clicking on PIM
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='PIM']"))).click();
//		
//		//clicked on drpodown
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[6]//div[1]//div[2]//div[1]//div[1]//div[1]"))).click();
//		// 1) click on single element from hidden drop down
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Financial Analyst']"))).click();
//		//2 ) count the number of total options
//		List<WebElement> optionshd = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']//span")));
//		for (WebElement opt : optionshd) {
//			System.out.println(opt.getText());
//		}
//		
//		
		//Example of : handle dynamic options --> auto-suggest dropdown
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class=\"gLFyf\"]"))).sendKeys("selenium");
		List<WebElement> optdynamic = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@role='listbox']//li//div[@role='option']")));
		
		for(WebElement opt: optdynamic) {
			System.out.println(opt.getText());
		}
		Thread.sleep(5000);
		driver.quit();
		
		
		
		
		
		

	}

}

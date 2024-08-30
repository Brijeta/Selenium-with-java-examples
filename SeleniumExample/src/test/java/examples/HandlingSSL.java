package examples;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HandlingSSL {

	public static void main(String[] args) throws InterruptedException   {
		
		//Example of SSL
		ChromeOptions option= new ChromeOptions();
		option.setAcceptInsecureCerts(true);
		
		WebDriver driver= new ChromeDriver(option);
		
		driver.get("https://expired.badssl.com/");
		System.out.println("Title page : "+driver.getTitle());
		
		driver.quit();

	}

}

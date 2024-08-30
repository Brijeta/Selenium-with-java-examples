package examples;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EnableExtensionAtRunTime {

	public static void main(String[] args) {
		WebDriver driver= new ChromeDriver();
		driver.get("https://text-compare.com/");
		driver.quit();

	}

}

package examples;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandleingDynamicWebTable{

	public static void main(String[] args) throws InterruptedException {
		// Initialize WebDriver and WebDriverWait
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open the URL and maximize the browser window
        driver.get("https://testautomationpractice.blogspot.com/");
        driver.manage().window().maximize();

        // Locate the table header row and the first column
        List<WebElement> headerRowElements = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@id='productTable']//tr[1]//td")));
        List<WebElement> firstColumnElements = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@id='productTable']//tr//td[1]")));
        
        // Locate all pagination(data) elements
        List<WebElement> paginationElements = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@class='pagination']//li")));

        System.out.println("Total pages of data: " + paginationElements.size());  // Print the total number of pages

        // Iterate through each page
        for (WebElement pageElement : paginationElements) {
           pageElement.click();   // Click on the pagination element
            for (int rowIndex = 1; rowIndex <= headerRowElements.size(); rowIndex++) {  // Iterate through each row
                for (int colIndex = 1; colIndex <= firstColumnElements.size(); colIndex++) { // Iterate through each column
                    // Locate the cell value
                    WebElement cellValue = wait.until(
                      ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='productTable']//tr[" + rowIndex + "]//td[" + colIndex + "]")));
                   System.out.print(cellValue.getText() + " \t\t | \t"); // Print the cell value
                }
               System.out.println();// Print a new line after each row
            }
           System.out.println("Next page: " + pageElement.getText()); // Print the current page number
        }
        // Pause execution for 2 seconds
        Thread.sleep(2000);

	}

}

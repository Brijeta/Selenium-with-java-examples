
//This assignment is the example of static web table from the blazedemo website find out the cheapest flight details.

package Assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment1 {

	public static void main(String[] args) {
//---------------------Assignment :https://blazedemo.com/ --> find the cheap price flight from the table---------------------
				WebDriver driver=new ChromeDriver();
				WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
				driver.get("https://blazedemo.com/");
				WebElement drp1= wait.until(ExpectedConditions.elementToBeClickable(By.name("fromPort")));
				Select opt1= new Select(drp1);
				opt1.selectByIndex(2);
				WebElement  drp2= wait.until(ExpectedConditions.elementToBeClickable(By.name("toPort")));
				Select opt2 =new Select(drp2);
				opt2.selectByIndex(1);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']"))).click();
				
//------------------------approach 1 : -----------------------------------------------------------------------------
				/*// Locate all price elements in the table
		        List<WebElement> priceElements = wait.until(
		                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@class='table']//tr//td[6]"))
		            );
		            // Initialize variable to store the minimum price, starting with the maximum possible value
		            double minimumPrice = Double.MAX_VALUE;
		            // Iterate through each price element to find the cheapest price
		            for (WebElement priceElement : priceElements) {
		                // Extract and parse the price value from the element's text
		                double price = Double.parseDouble(priceElement.getText().replace("$", "").trim());
		                
		                // Update minimum price if the current price is lower
		                if (price < minimumPrice) {
		                    minimumPrice = price;
		                }
		            }

		            // Print the cheapest price found
		            System.out.println("Cheapest price: $" + minimumPrice);*/

				
//----------------------------approach 2 with same example-------------------------------------------------------------
				 // Locate all price elements in the table
		        List<WebElement> priceElements = wait.until(
		            ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@class='table']//tr//td[6]"))
		        );
		        // Locate all table rows (or cells) to match the minimum price later
		        List<WebElement> tableRows = wait.until(
		            ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@class='table']//tr"))
		        );
		        // Initialize a list to store parsed prices
		        List<Double> prices = new ArrayList<>();
		        // Extract and parse prices from the table elements
		        for (WebElement priceElement : priceElements) {
		            try {
		                String priceText = priceElement.getText().replace("$", "").trim();
		                prices.add(Double.parseDouble(priceText));
		            } catch (NumberFormatException e) {
		                System.out.println("Error parsing price: " + priceElement.getText());
		            }
		        }
		        // Sort the prices in ascending order
		        Collections.sort(prices);
		        // Identify the minimum price
		        if (!prices.isEmpty()) {
		            double minPrice = prices.get(0);
		            // Search for the row containing the minimum price
		            for (WebElement row : tableRows) {
		                if (row.getText().contains("$" + minPrice)) {
		                    System.out.println("Flight details with the minimum price: \n" + row.getText());
		                    break; // Assuming prices are unique, exit loop after finding the minimum price
		                }
		            }
		        } else {
		            System.out.println("No valid prices found.");
		        }
				
				driver.close();


	}

}

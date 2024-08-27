package examples;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class HandlingWebTable {

public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		String data="";
		//Example of : returns all values from static web table of book
		//1) find the numbers of rows in web table 
		List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@name='BookTable']//tr")));
		//2) find the number of column in web table
		List<WebElement> col = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@name='BookTable']//th")));
		System.out.println("Number of row:  "+rows.size());
		System.out.println("Number of col:  "+col.size());
		//3) read data from specific row and column(ex . 5th row and col 1st)
		String bookname =driver.findElement(By.xpath("//table[@name='BookTable']//tr[5]//td[1]")).getText();
		//4) read data from all the rows and columns(first row is th tag header)
		for(int r=2; r<=rows.size();r++) {
			for(int c=1;c<=col.size();c++) {
				 data =driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]/td["+c+"]")).getText();
				System.out.print(data+"\t\t\t");
				}
			System.out.println();
		}
		//5) print book name whos autohr is mukesh---> condition based data read
		for(int r=2; r<=rows.size();r++) {
			
				String author= driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]//td[2]")).getText();
				//System.out.println(author);
			if(author.equals("Mukesh")) {
					String book=driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]//td[1]")).getText();
					System.out.println("Author : "+author +"\t Book name : " +book);
				}
			
		}
		//6) Find the total price of all the books
	int total=0;
		for(int r=2;r<rows.size();r++) {
			String price=driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]//td[4]")).getText();
			
			total= total+Integer.parseInt(price);
		}
		System.out.println("Total of all book price: \t"+total);
		
	driver.close();
		
		
		
		
	}

}

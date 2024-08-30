package examples;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingDataPicker {

	private WebDriver driver;
    private WebDriverWait wait;
    public HandlingDataPicker() {
        // Initialize WebDriver, WebDriverWait, and JavaScriptExecutor
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void selectDate(String month, String year, String day) {
        // Switch to iframe and locate the date input field
        driver.switchTo().frame(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@class='demo-frame']"))));
        WebElement dateInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")));

        // Open the date picker calendar
        dateInput.click();

        // Loop to select the desired month and year
        while (true) {
            String currentMonth = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='ui-datepicker-month']"))).getText();
            String currentYear = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='ui-datepicker-year']"))).getText();

            if (currentMonth.equals(month) && currentYear.equals(year)) {
                break;
            }
            // Click the "Next" button to navigate to the next month
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Next']")));
            nextButton.click();
        }
        // Select the desired day
        List<WebElement> allDays = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//tbody//tr//td"));
        for (WebElement dayElement : allDays) {
            if (dayElement.getText().equals(day)) {
                dayElement.click();
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
    	HandlingDataPicker automation = new HandlingDataPicker();
        try {
            // Open URL and maximize the browser window
            automation.driver.get("https://jqueryui.com/datepicker/");
            automation.driver.manage().window().maximize();

            // Set the desired date
            automation.selectDate("May", "2026", "2");

            // Allow some time to observe the result
            Thread.sleep(2000);
        } finally {
            // Close the browser
            automation.driver.quit();
        }
    }
}

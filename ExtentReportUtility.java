package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.val;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import org.apache.commons.*;

import javax.print.DocFlavor;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportUtility implements ITestListener {
  // Step 1: Declare the required instances for generating reports.
  // These classes are from AventStack ExtentReports library dependencies.
  public ExtentSparkReporter sparkReporter; // Responsible for creating the UI of the report.
  public ExtentReports extent;             // Manages information for the reports, such as system and environment details.
  public ExtentTest test;                  // Represents the individual test case in the report.
  String repName;                          // Dynamic name for the report file.

  //this implementation of ITestLister Interface will execute after each test case passed
  public void onStart(ITestContext testContext) {
    // Step 2: Generate a dynamic report name using a timestamp.
    // The SimpleDateFormat class formats the date and time dynamically.
    // Example: "Test-Report-2024.11.25.14.32.45.html"
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    repName = "Test-Report-" + timeStamp + ".html"; // Create a unique report file name.

    // Step 3: Initialize the ExtentSparkReporter instance.
    // Set configurations for the report such as document title, report name, and theme.
    sparkReporter = new ExtentSparkReporter(".//reports/" + repName); // Specify the report file location.
    sparkReporter.config().setDocumentTitle("Banking Portal Automation Report"); // Title for the document.
    sparkReporter.config().setReportName("Banking Portal Functional Testing");  // Name of the report.
    sparkReporter.config().setTheme(Theme.DARK); // Set theme to DARK for a visually distinct report.

    // Step 4: Initialize the ExtentReports instance.
    // Attach the SparkReporter and set additional system-related information for the report.
    extent = new ExtentReports();
    extent.attachReporter(sparkReporter);

    // Add system-related details to the report.
    extent.setSystemInfo("Application", "Banking portal");              // Name of the application under test.
    extent.setSystemInfo("Module", "Admin");                            // Module or feature being tested.
    extent.setSystemInfo("User Name", System.getProperty("user.name")); // System's logged-in user.
    extent.setSystemInfo("Environment", "QA");                          // Testing environment (e.g., QA, Prod).
    extent.setSystemInfo("Operating System", testContext.getCurrentXmlTest().getParameter("OS")); // OS used for testing.
    extent.setSystemInfo("Browser", testContext.getCurrentXmlTest().getParameter("browser"));     // Browser used for testing.

    // Step 5: Include executed groups in the report (if applicable).
    // This shows which test groups were included in the execution.
    List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups(); // Get included groups from the test context.
    if (!includedGroups.isEmpty()) { // If there are included groups, add them to the report.
      extent.setSystemInfo("Groups", includedGroups.toString());
    }
  }

  //this implementation of ITestLister Interface will execute after each test case passed
  public void onTestSuccess(ITestResult result) {
    // Step 1: Create a new test in the report for the successfully executed test case.
    // The name of the test is set to the class name where the test case resides.
    test = extent.createTest(result.getTestClass().getName());
    // Step 2: Assign categories (test groups) to the test case in the report.
    // This makes it easier to organize and filter test cases by their groups.
    test.assignCategory(result.getMethod().getGroups());
    // Step 3: Log the successful execution of the test case.
    // Add a log entry in the report indicating that the test case passed.
    // Example: "testMethodName got successfully executed"
    test.log(Status.PASS, result.getName() + " got successfully executed");
  }

  //this implementation of ITestLister Interface will execute after each test case Failed
  public void onTestFailure(ITestResult result) {
    // Step 1: Create a new test in the report for the failed test case.
    // The name of the test in the report is set to the name of the test class where the test case resides.
    test = extent.createTest(result.getTestClass().getName());

    // Step 2: Assign categories (test groups) to the failed test case in the report.
    // This helps in organizing and filtering test cases by their associated groups.
    test.assignCategory(result.getMethod().getGroups());

    // Step 3: Log the failure of the test case in the report.
    // Add a log entry indicating that the test case failed, along with its method name.
    // Example: "testMethodName got failed"
    test.log(Status.FAIL, result.getName() + " got failed");

    // Step 4: Log additional details about the failure.
    // This logs the exception or error message thrown during the test case execution.
    test.log(Status.INFO, result.getThrowable().getMessage());

    // Step 5: Capture a screenshot of the failure (if applicable) and attach it to the report.
    try {
      // The `captureScreen` method captures a screenshot and returns the image path.
      // This assumes that `captureScreen` is defined in the `BaseClass`.
      String imagePath = new BaseClass().captureScreen(result.getName());

      // Attach the screenshot to the test report for better debugging and visualization.
      test.addScreenCaptureFromPath(imagePath);
    } catch (Exception e) {
      // If an exception occurs while capturing the screenshot, print the stack trace.
      e.printStackTrace();
    }

  }


  public void onTestSkipped(ITestResult result) {
    test=extent.createTest(result.getTestClass().getName());
    test.assignCategory(result.getMethod().getGroups());
    test.log(Status.SKIP,result.getName()+" got skipped");
    test.log(Status.INFO,result.getThrowable().getMessage());
  }


  /// This implementation of the ITestListener interface will execute after all tests in the test suite are completed.
  @Override
  public void onFinish(ITestContext context) {
    // Step 1: Flush the ExtentReports instance.
    // This saves all the logs, details, and results collected during the test execution into the report.
    extent.flush();

    // Step 2: Define the path of the generated Extent report.
    // This uses the report name (`repName`) created earlier and the project's base directory.
    String pathOfExtentReport = System.getProperty("user.dir") + "//reports//" + repName;

    // Step 3: Create a File object for the report file.
    File extentReport = new File(pathOfExtentReport);

    // Step 4: Automatically open the report in the default web browser.
    // The Desktop class is used to interact with the system's desktop (e.g., to open files or browse URLs).
    try {
      Desktop.getDesktop().browse(extentReport.toURI()); // Converts the file path to a URI and opens it in the browser.
    } catch (IOException e) {
      // Step 5: Handle exceptions that may occur while opening the report.
      // For example, if the file cannot be found or if the browser fails to open.
      e.printStackTrace();

      //send report by email
    /*  try {
    // Construct the file path for the report
    URL url = new URL("file:" + System.getProperty("user.dir") + "\\reports\\" + repName);

    // Create the email object with HTML content support
    ImageHtmlEmail email = new ImageHtmlEmail();

    // Set the resolver for handling inline images
    email.setDataSourceResolver(new DataSourceUrlResolver(url));

    // Configure SMTP server settings
    email.setHostName("smtp.googlemail.com"); // SMTP host
    email.setSmtpPort(465);                  // SMTP port
    email.setAuthenticator(new DefaultAuthenticator(
        "pavanoltraining@gmail.com",        // Sender's email
        "password"                          // Sender's password
    ));
    email.setSSLOnConnect(true);             // Enable SSL for secure connection

    // Set the sender's email address
    email.setFrom("brijeta88@gmail.com");

    // Set the email subject
    email.setSubject("Test Results");

    // Set the email body message
    email.setMsg("Please find the attached report...");

    // Add the recipient's email address
    email.addTo("pavankumar.busyqa@gmail.com");

    // Attach the report file to the email
    email.attach(url, "Extent Report", "Please check the attached report.");

    // Send the email
    email.send();

} catch (Exception e) {
    // Print the exception stack trace for debugging
    e.printStackTrace();
}*/

    }
  }
}

package practice.test;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.protobuf.ByteString.Output;

public class SampleReportTest {
	public ExtentReports report;
	@BeforeSuite
	public void configBS() {
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/report1.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//add Env information & create test
		 report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
		
	}
	@AfterSuite
	public void configAS() {
		report.flush();
	}
	
@Test
public void createContactWithOrg() {
WebDriver driver=new ChromeDriver();
driver.get("http://49.249.28.218:8888/");
TakesScreenshot ts=(TakesScreenshot)driver;
String filepath=ts.getScreenshotAs(OutputType.BASE64);
	
	ExtentTest test=report.createTest("createContactWithorg");
	
	test.log(Status.INFO,"login to app");
	test.log(Status.INFO,"navigate to contact page");
	
	test.log(Status.INFO,"create contact");
	if("HDFC".equals("HDFC")) 
		test.log(Status.PASS,"contact is created");
	else
		test.log(Status.FAIL,"contact is not created");
	test.addScreenCaptureFromBase64String(filepath, "ErrorFile");
	driver.close();

}

/*@Test
public void createContactWithphno() {

	
	ExtentTest test=report.createTest("createContactWithphno");
	
	test.log(Status.INFO,"login to app");
	test.log(Status.INFO,"navigate to contact page");
	
	test.log(Status.INFO,"create contact");
	if("HDFC".equals("HDFC")) 
		test.log(Status.PASS,"contact is created");
	else
		test.log(Status.FAIL,"contact is not created");
	

}*/
}

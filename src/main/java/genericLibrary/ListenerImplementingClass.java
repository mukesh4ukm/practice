package genericLibrary;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementingClass implements ITestListener {

	ExtentReports report;
	ExtentTest test;
	@Override
	public void onTestStart(ITestResult result) {
		
		String mName = result.getMethod().getMethodName();
		test = report.createTest(mName);
		Reporter.log(mName+"test execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String mName = result.getMethod().getMethodName();
		test.log(Status.PASS, mName);
		Reporter.log(mName+"test execution success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		EventFiringWebDriver driver = new EventFiringWebDriver(BaseClass.sdriver);
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screen/shot.png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.log(Status.FAIL, result.getThrowable());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
		String mName = result.getMethod().getMethodName();
		test.log(Status.SKIP, mName);
		Reporter.log(mName+" test execution skip");
	}

	@Override
	public void onStart(ITestContext context) {
		
		ExtentSparkReporter htmlreport = new ExtentSparkReporter("./ExtendReport/report.html");
		htmlreport.config().setDocumentTitle("TEST YANTRA");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setReportName("AllenSolly");
		
		report = new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("Base browser", "chrome");
		report.setSystemInfo("os", "windows");
		report.setSystemInfo("Base url", "https://allensolly.abfrl.in/");
		report.setSystemInfo("Reporter ", "waris");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

	
}

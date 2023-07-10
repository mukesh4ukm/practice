package script;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Script {

	public static void main(String[] args) {
		
		
		WebDriver driver = WebDriverManager.chromedriver().create();
		driver.get("https://allensolly.abfrl.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement ele = (WebElement) js.executeScript(
					"return document.querySelector(\"#wzrkImageOnlyDiv > ct-web-popup-imageonly\").shadowRoot.querySelector(\"#close\")");
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(ele));
			js.executeScript("arguments[0].click()", ele);
			}catch(Exception e) {
				
			}
		
		driver.findElement(By.xpath("//span[.='stores']")).click();
		
		driver.findElement(By.xpath("//input[@placeholder=\"Enter Pincode/City/Area\"]")).sendKeys("560085");
		
		WebElement java = driver.findElement(By.xpath("//input[@placeholder=\"Enter Pincode/City/Area\"]/ancestor::div[@class=\"containerGrid\"]/descendant::div[@class=\"StoreLocator_locationContainer__XUTQj null\"]"));
	    
		System.out.println(java.getText());
		
		System.out.println("hello");
	
	}

}

package script;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericLibrary.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(genericLibrary.ListenerImplementingClass.class)
public class VerifyCategoryIsDisplay extends BaseClass {

	
	@Test
	public void verifyElementIsPresentOrNot() {

//		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement ele = (WebElement) js.executeScript(
				"return document.querySelector(\"#wzrkImageOnlyDiv > ct-web-popup-imageonly\").shadowRoot.querySelector(\"#close\")");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(ele));
		js.executeScript("arguments[0].click()", ele);
//		}catch(Exception e) {
//			
//		}

		List<String> list = new ArrayList<String>();
		list.add("MEN");
		list.add("WOMEN");
		list.add("BOYS");
		list.add("GIRLS");
		list.add("NEW ARRIVALS");
		
		for (int i = 0; i < list.size(); i++) {

			WebElement cat = driver.findElement(By.xpath("//h6/child::a[@title=\"" + list.get(i) + "\"]"));

			Actions a = new Actions(driver);
			a.moveToElement(cat).perform();

			List<WebElement> text = driver.findElements(By.xpath("//a[@title=\"" + list.get(i)
					+ "\"]/parent::h6/following-sibling::div/descendant::div[contains(@class,\"MuiGrid-root MuiGrid-container D\")]/descendant::a"));

			for (int j = 0; j < text.size(); j++) {

				System.out.println(text.get(j).getText());
				boolean flag = text.get(j).isDisplayed();
				Assert.assertTrue(flag);
			}
		}
	}
}

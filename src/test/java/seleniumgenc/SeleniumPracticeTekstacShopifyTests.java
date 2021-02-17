package seleniumgenc;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumPracticeTekstacShopifyTests {

	private final String BASE_URL = "https://webapps.tekstac.com/Shopify";
	private WebDriver driver;
	
	@BeforeTest
	public void beforeTest(){
		WebDriverManager.chromedriver().setup();
	}
	
	@Test
	public void shopifyRegisterFormContainsAllTextboxes(){
		//Arrange
		driver = new ChromeDriver();
		driver.get(BASE_URL);
		
		//Act
		WebElement firstNameTextBox = driver.findElement(By.id("firstname"));
		
		//Assert
		Assert.assertTrue(firstNameTextBox.isDisplayed());
	}
	
	@AfterTest
	public void afterTest(){
		driver.quit();
	}
}

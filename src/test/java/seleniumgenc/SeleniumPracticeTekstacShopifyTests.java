package seleniumgenc;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

import seleniumgenc.Utils.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumgenc.pages.ShopifyRegisterPage;
import seleniumgenc.pages.ShopifyRegisterPageBuilder;

public class SeleniumPracticeTekstacShopifyTests {

	private ShopifyRegisterPageBuilder shopifyRegisterPageBuilder;
	private WebDriver driver;
	
	@BeforeTest
	public void beforeTest(){
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeMethod
	public void beforeMethod(){
		shopifyRegisterPageBuilder = new ShopifyRegisterPageBuilder(new ShopifyRegisterPage(new ChromeDriver()));
	}
	
	@Test
	public void shopifyRegisterFormContainsAllTextboxes(){
		
		Assert.assertTrue(shopifyRegisterPageBuilder.build().getFirstNameTextBox().isDisplayed());
		Assert.assertTrue(shopifyRegisterPageBuilder.build().getLastNameTextBox().isDisplayed());
		Assert.assertTrue(shopifyRegisterPageBuilder.build().getUserNameTextBox().isDisplayed());
		Assert.assertTrue(shopifyRegisterPageBuilder.build().getPasswordNameTextBox().isDisplayed());
		
	}
	
	@Test
	public void submittingFormAddsInfoToBottomTable(){
		
		//Arrange/Act
		ShopifyRegisterPage page = shopifyRegisterPageBuilder
				.withFirstName("Jason")
				.withLastName("Monroe")
				.withUserName("JMoney")
				.withCity(City.valueOf("Coimbatore"))
				.withGender(Gender.valueOf("Male"))
				.withPassword("password-1").build();
		
		page.clickRegisterButton();
		
		//find table and view rows
		WebElement table = page.getTable();
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		
		String expected = "Jason Monroe JMoney Coimbatore";
		String actual = rows.get(rows.size()-1).getText();
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void incomplete_form_issues_error(){
		
		//Arrange/Act
		ShopifyRegisterPage page = shopifyRegisterPageBuilder
				.withFirstName("Jason")
				.withLastName("Monroe")
				.withUserName("JMoney")
				.withCity(City.valueOf("Coimbatore"))
				.withGender(Gender.valueOf("Male")).build();
		
		page.clickRegisterButton();
		
		String expectedError = "Passwd can't be blank";
		String actualError = page.getErrorMessages();
		Assert.assertEquals(actualError, expectedError);
		
	}
	
	
	@AfterMethod
	public void afterTest(){
		shopifyRegisterPageBuilder.quitDriver();
	}
}

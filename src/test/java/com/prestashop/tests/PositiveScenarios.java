package com.prestashop.tests;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PositiveScenarios {

	WebDriver driver;
	Faker faker;
	Random random;
	String email;
	String firstName;
	String lastName;
	String password;
	String adress;
	String city;
	String zipcode;
	String phone;
	Select day;
	Select month;
	Select year;
	Select state;

	@BeforeMethod
	public void setUpMethod() {

		faker = new Faker();
		random = new Random();
		firstName = faker.name().firstName();
		lastName = faker.name().lastName();
		email = faker.internet().emailAddress();
		password = faker.internet().password();
		adress = faker.address().streetAddress();
		city = faker.address().city();
		zipcode = faker.address().zipCode().substring(0, 5);
		phone = faker.phoneNumber().cellPhone();
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com");
		driver.findElement(By.linkText("Sign in")).click();
	}

	@Test
	public void loginTest() {

		driver.findElement(By.id("email_create")).sendKeys(email);
		driver.findElement(By.id("SubmitCreate")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("id_gender" + (random.nextInt(2) + 1))).click();

		driver.findElement(By.id("customer_firstname")).sendKeys(firstName);
		driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
		driver.findElement(By.id("passwd")).sendKeys(password);

		day = new Select(driver.findElement(By.id("days")));
		day.selectByIndex((random.nextInt(27) + 1));

		month = new Select(driver.findElement(By.id("months")));
		month.selectByIndex((random.nextInt(12) + 1));

		year = new Select(driver.findElement(By.id("years")));
		year.selectByIndex((random.nextInt(118) + 1));

		driver.findElement(By.id("address1")).sendKeys(adress);
		driver.findElement(By.id("city")).sendKeys(city);

		state = new Select(driver.findElement(By.id("id_state")));
		state.selectByIndex((random.nextInt(50) + 1));

		driver.findElement(By.id("postcode")).sendKeys(zipcode);
		driver.findElement(By.id("phone_mobile")).sendKeys(phone);
		driver.findElement(By.id("submitAccount")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@class = 'logout']")).click();

		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("passwd")).sendKeys(password);
		driver.findElement(By.id("SubmitLogin")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("a[title='View my customer account']>span")).getText(),
				firstName + " " + lastName);

	}

	@AfterMethod
	public void tearDownMethod() {
		driver.close();
	}
	
}

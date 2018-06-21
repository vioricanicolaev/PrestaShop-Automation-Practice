package com.prestashop.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NegativScenarios {

	WebDriver driver;

	@BeforeMethod
	public void setUpMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com");
		driver.findElement(By.linkText("Sign in")).click();
	}

	@Test
	public void wrongCredentialsTest() {
		String expected = "Authentication failed.";

		driver.findElement(By.id("email")).sendKeys("john.smith@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("12345678");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//i[@class='icon-lock left']")).click();
		String actualErorMessage = driver.findElement(By.xpath("//li[.='Authentication failed.']")).getText();
		Assert.assertEquals(actualErorMessage, expected);

	}

	@Test
	public void invalidEmailTest() {
		String expected = "Invalid email address.";

		driver.findElement(By.id("email")).sendKeys("john.smith@gmail");
		driver.findElement(By.id("passwd")).sendKeys("12345678");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//i[@class='icon-lock left']")).click();
		String actualErorMessage = driver.findElement(By.xpath("//li[.='Invalid email address.']")).getText();
		Assert.assertEquals(actualErorMessage, expected);

	}

	@Test
	public void blankEmailTest() {
		String expected = "An email address required.";

		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("passwd")).sendKeys("12345678");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//i[@class='icon-lock left']")).click();
		String actualErorMessage = driver.findElement(By.xpath("//li[.='An email address required.']")).getText();
		Assert.assertEquals(actualErorMessage, expected);

	}

	@Test
	public void blankPasswordTest() {
		String expected = "Password is required.";

		driver.findElement(By.id("email")).sendKeys("john.smith@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//i[@class='icon-lock left']")).click();
		String actualErorMessage = driver.findElement(By.xpath("//li[.='Password is required.']")).getText();
		Assert.assertEquals(actualErorMessage, expected);

	}

	@AfterMethod
	public void tearDownMethod() {
		driver.close();
	}

}

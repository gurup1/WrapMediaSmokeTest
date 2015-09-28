package com.gurpreet.project;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class App 
{
	public static WebDriver driver =new FirefoxDriver();
	WebDriverWait dynamicwait=new WebDriverWait(driver, 15);
	String email=randomString()+"@gmail.com";
	
	
	@BeforeMethod
	public void opensite()
	{	String baseurl="https://home-x1.wrap.co/index/";
	driver.manage().window().maximize();
	driver.get(baseurl);
	}

	@Test
	public void open() throws InterruptedException{ 
		WebElement earlyaccess=driver.findElement(By.xpath(".//a[contains(@class,'btn btn-default btn-l')]"));
		dynamicwait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[contains(@class,'btn btn-default btn-l')]")));
		earlyaccess.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement smallbusiness=driver.findElement(By.xpath("(//a[@class='ng-isolate-scope'])[2]"));
		dynamicwait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='ng-isolate-scope'])[2]")));
		smallbusiness.click();
		driver.findElement(By.id("email")).sendKeys(email);
		WebElement signUp=driver.findElement(By.xpath(".//button[@ng-click='startEmailSignUp()']"));
		signUp.click();
		Assert.assertTrue(validateemailregistered(),"Failed to Register");
		driver.findElement(By.id("username")).sendKeys(email.substring(0,10));
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.xpath("//button[starts-with(.,'Create Account')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[starts-with(.,'Create Account')]")).click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("FirstName");
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("LastName");
		driver.findElement(By.xpath("//input[@name='company']")).sendKeys("CompanyName");
		driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("9"+randomInteger());
		driver.findElement(By.xpath("//input[@ng-model='complete.certified']")).click();
		driver.findElement(By.xpath("//button[starts-with(.,'Next')]")).click();
		
		Thread.sleep(8000);
		Assert.assertTrue(driver.getCurrentUrl().contains("authoring"),"Failed to Sign UP");
	}

	public String randomString(){
		String charset="qwertyuioasdfghjklzxcvbnmp";
		StringBuilder sb=new StringBuilder();
		Random ran=new Random();
		for (int i = 0; i < charset.length(); i++) {
			int pos=ran.nextInt(charset.length());
			sb.append(charset.charAt(pos));
		}

		return sb.toString();
	}

	public boolean validateemailregistered(){
		dynamicwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'email ng-binding')]")));
		String emailtext=driver.findElement(By.xpath("//div[contains(@class,'email ng-binding')]")).getText();
		if(emailtext.contains(email)){
			return true;
		}
		else {
			return false;
		}
	}
	
	public String randomInteger(){
		String charset="1234567890";
		StringBuilder sb=new StringBuilder();
		Random ran=new Random(9);
		for (int i = 0; i <9; i++) {
			int pos=ran.nextInt(charset.length());
			sb.append(charset.charAt(pos));
		}

		return sb.toString();
	}
	
	@AfterMethod
	public void quit()
	{
		driver.quit();
	}
}

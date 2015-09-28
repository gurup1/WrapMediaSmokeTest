
/*Test 1 (launchSiteAndLogin)
1. Go to https://home-x1.wrap.co
2. Click on Early access Button.

Test 2 (Login with small business Plan)
1. Click on the Try Free Plan
2. Enter  the Username field
3. Enter the Password filed
4. Click on the Login button
5. Verify that the user loggedin successfully.

Test (Create Wrap)
1. Click on the Create button
2. Verify that Logout button displayed.
*/




package TestNgPack;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Wrap_Test {
	private WebDriver browser;
	private String baseurl = ("http://home-x1.wrap.co");
	

	private void sleep () {
	try{
		  Thread.sleep(5000);
	  }
	  catch(Exception e){
		  System.out.println(e);
	  }
	}
	  
	 
	 /*@Test (priority = 0)
	 public void openbrowser() { 
	  browser.manage().window().maximize();
	  browser.get("http://home-x1.wrap.co");
	  this.sleep();
	  browser.findElement(By.xpath("//*[@id='hero-carousel']/div/div[1]/div/a")).click();
	  this.sleep();
}*/
	 
	@Test (priority = 0)
	public void Login() {
		browser.findElement(By.xpath("//*[@id='pricingModel']/div/div[1]/div/div[4]/div[2]/a")).click();
		browser.findElement(By.xpath("//*[@id='wrap-auth']/div[2]/div/ng-include/div[4]/a")).click();
		//baseurl.findElement(By.xpath("//*[@id='pricingModel']/div/div[1]/div/div[4]/div[2]/a")).click();
		//baseurl.findElement(By.xpath("//*[@id='wrap-auth']/div[2]/div/ng-include/div[4]/a")).click();
		//baseurl.findElement(By.xpath("//*[@id='wrap-auth']/div[2]/div/ng-include/div[4]/a")).click();
		browser.findElement(By.xpath("//*[@id='email']")).sendKeys("gurpreet@sd2labs.com");
		this.sleep();
		browser.findElement(By.xpath("//*[@id='password']")).sendKeys("gurpreet@sd2labs");
		this.sleep();
		browser.findElement(By.xpath("//*[@id='wrap-auth']/div[2]/div[3]/form/button")).click();
		this.sleep();
		System.out.print("\nUser is able to Login");
	}

//BeforeClass annotation defines this method has to run Before every @Test methods
	@BeforeClass
	public void openbrowser()  {
		browser=new FirefoxDriver ();			
		browser.get(baseurl);
		this.sleep();
		browser.findElement(By.xpath("//*[@id='hero-carousel']/div/div[1]/div/a")).click();
		this.sleep();
		System.out.print("\nBrowser Open");
		      
		   try {		      
		    browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		     } catch (Exception e) {
		    throw new IllegalStateException("Can't start Web Driver", e);
		    }
		    
		  }
//AfterClass annotation defines this method has to run after every @Test methods
	@AfterClass 
	public void createwrap() {
		browser.findElement(By.xpath("//html/body/div[2]/div/div[1]/nav[1]/ul[1]/li[2]/a")).click();
		String expectedText = "Logout";
		String actualText = "Login";
// This assert will break the code and raise a failure. Result will be 1 Failure/
		Assert.assertEquals (actualText, expectedText);
/*//This assert will Continue the code and not raise a failure. Result will be Passed and the below message will be displayed/
		Assert.assertNotEquals(actualText, expectedText);
		System.out.print("\nExpected and Actual value or not matching");
*/
		browser.close();
		browser.quit();
	}
  }

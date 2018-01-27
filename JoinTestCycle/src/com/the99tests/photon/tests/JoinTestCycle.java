/*
 * Copyright (c) 2017, Preenos Crowd Technologies Private Limited
 * 
 * Please read instructions below for writing your tests
 */

package com.the99tests.photon.tests;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.the99tests.photon.PhotonSession;
import com.the99tests.photon.PhotonSession.PhotonSuite.PhotonTestEnvironment;
import com.the99tests.photon.platforms.UnsupportedConfigException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

// change RemoteWebDriver to any other driver if needed(Android/iOS)
public class JoinTestCycle extends PhotonSession.PhotonSuite<RemoteWebDriver> {
	/*
	 * Configuration block - please edit carefully
	 * 
	 * Do not change the structure of the code or functions in this block
	 */
	@Override
	protected RemoteWebDriver setupLocalWebDriver() throws Exception {
		
		// TODO local driver setup
		
		DesiredCapabilities capability=DesiredCapabilities.chrome();
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, Level.ALL);
		capability.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		capability.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		System.setProperty("webdriver.chrome.driver", "//Users//99tests//Downloads//chromedriver");
		driver=new ChromeDriver(capability);
		driver.switchTo().window(driver.getWindowHandle());
		Dimension dimension = new Dimension(2560,1700);
		driver.manage().window().setSize(dimension);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
	}

	@Override
	protected RemoteWebDriver setupPlaygroundWebDriver() throws Exception {
		// TODO test playground setup
		// more info at https://hackmd.io/GwUwTOAMCMYLQEMQIMZwCwM3ARgdgFY84Z1ZJ1IBmAEwDMCg
		 DesiredCapabilities capability=DesiredCapabilities.chrome();
		 LoggingPreferences logPrefs = new LoggingPreferences();
		 logPrefs.enable(LogType.BROWSER, Level.ALL);
		 capability.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		 capability.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		 RemoteWebDriver driver = new RemoteWebDriver(new URL("http://52.66.6.164:4444/wd/hub"),capability);
		 Dimension dimension = new Dimension(2560,1700);
			driver.manage().window().setSize(dimension);
			driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 return driver;
		
	} 
	
	@Override
	protected HashMap<String, Object> getAPICredentials() {
		HashMap<String, Object> apiInfo=new HashMap<String, Object>();
		apiInfo.put("email", "shashiranjansharma@live.in");
		apiInfo.put("apiKey", "TesterHZuM8W4a9hfkPOqMrh2o5w");
		return apiInfo;
	}
	
	@Override
	protected HashMap<String, Object> getWorkflowDetails() {
		HashMap<String, Object> workflowInfo=new HashMap<String, Object>();
		workflowInfo.put("workflow", "84"); // 15
		workflowInfo.put("enterpriseCycle", "777");
		return workflowInfo;
	}
	
	@Override
	protected HashMap<String, Object> getAutomationDeviceInfo() {
		HashMap<String, Object> deviceInfo=new HashMap<String, Object>();
		deviceInfo.put("automationDeviceId", "b59907");
		deviceInfo.put("logType", LogType.BROWSER);
		return deviceInfo;
	}
	
	@Override
	protected HashMap<String, Object> getPhotonSessionSettings() {
		HashMap<String, Object> settings=new HashMap<String, Object>();
		settings.put("checkpointVerification", "on"); // set to off to disable checkpoint verification while developing tests, only works in LOCAL environments
		settings.put("testEnvironment", PhotonTestEnvironment.LOCAL); 
		// for test playground - settings.put("testEnvironment", PhotonTestEnvironment.PLAYGROUND); 
		// for RDA submissions - settings.put("testEnvironment", PhotonTestEnvironment.RDA_SUBMISSION);
		//settings.put("testEnvironment", PhotonTestEnvironment.PLAYGROUND); 
		return settings;
	}	
	
	@Override
	protected PhotonTestEnvironment getEnvironment() {
		// TODO change this to PhotonTestEnvironment.PLAYGROUND 
		// to test on the 99tests test playground
		return PhotonTestEnvironment.LOCAL;
		//return PhotonTestEnvironment.PLAYGROUND;
	}
	
	/*
	 * Tests block - please write your tests below
	 */
	public static String baseUrl = "https://99tests.com";
	 //Open a new Tab/Window and switch to it.
    public void openNewTab()
	{   
		
		String CH = driver.getWindowHandle();
		
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("window.open();");
		
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		for(String t: tabs)
		{
			if(t.equals(CH))
			{
				int Cix= tabs.indexOf(CH);
				
				driver.switchTo().window(tabs.get(Cix+1));
				break;
			}
		}
	   }
    
  //Switch to a tab by its index.
    public void switchToTab(int p )
	{
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(p-1));
	}
	
	/*
	 * Tests block - please write your tests below
	 */
	public void OnboardLoginCorrect() throws Exception
	{
		String CUserName = "shashiranjansharma@live.in";
		String CPassword = "sadaf@14";
		driver.get(baseUrl);
		//session.checkpoint("CorrectLoginOnboarding1");
		driver.findElement(By.xpath("/html/body/header/div/nav/div[2]/div[2]/ul/li[6]/a")).click();
		//session.checkpoint("CorrectLoginOnboarding2");
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(CUserName);
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys(CPassword);
		driver.findElement(By.id("signin_btn")).click();
		//session.checkpoint("CorrectLoginOnboarding3");
		
	}
	@Test(priority = 2)
	public void ActiveProject1() throws Exception{
		OnboardLoginCorrect();
		WebElement ActiveProject = driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul/li[3]/a"));
		Actions act = new Actions(driver);
		act.moveToElement(ActiveProject).build().perform();
		
		session.checkpoint("84_133_9695_45626_1");
		ActiveProject.click();
		
		session.checkpoint("84_133_9695_45627_2");
		
	}
	
	@Test(priority = 3)
	public void ActiveProject6() throws Exception{
		//((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
		Thread.sleep(2000);
		session.checkpoint("84_133_9700_45634_9");
		
	}
	
	@Test(priority = 4)
	public void ActiveProject8() throws Exception{

		if(driver.findElements(By.xpath("//*[@id='enterprisecycles']/div/div/div/table/tbody/tr[2]/td[3]/a")).size() != 0 )
		{
			System.out.println("Enterprise cycle is running");
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//*[@id='enterprisecycles']/div/div/div/table/tbody/tr[2]/td[3]/a"))).build().perform();
			Thread.sleep(2000);
			session.checkpoint("84_133_9702_45642_17");
			driver.findElement(By.xpath("//*[@id='enterprisecycles']/div/div/div/table/tbody/tr[2]/td[3]/a")).click();
			Thread.sleep(2000);
			session.checkpoint("84_133_9702_45643_18");
		}
		else
			System.out.println("No Enterprise cycle is running at this moment.");
	}
	
	@Test(priority = 5)
	public void ActiveProjectShowInterest() throws Exception{
		if(driver.findElements(By.xpath("//*[@id='enterprisecycles']/div/div/div/table/tbody/tr[2]/td[3]/a")).size() != 0 )
		{
		driver.findElement(By.xpath("//*[@id='main_container']/div/div[4]/div/div/a")).click();
		Thread.sleep(2000);
		WebElement acceptNDA = driver.findElement(By.xpath("//*[@id='acceptndainterested']/div/div/div[3]/a"));
		Actions act = new Actions(driver);
		act.moveToElement(acceptNDA).build().perform();
		Thread.sleep(2000);
		acceptNDA.click();
		Thread.sleep(1000);
		session.checkpoint("84_133_9706_45655_30");
		WebElement showInterest = driver.findElement(By.xpath("//*[@id='new_enterprisecycleplayer']/input[4]"));
		act.moveToElement(showInterest).build().perform();
		showInterest.click();
		Thread.sleep(2000);
		session.checkpoint("84_133_9706_45656_31");
		driver.findElement(By.xpath("//*[@id='main_container']/div/div[4]/div/div/a")).click();
		Thread.sleep(2000);
        act.moveToElement(driver.findElement(By.xpath("//*[@id='main_container']/div/div[6]/div/div/div[3]/a"))).build().perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='main_container']/div/div[6]/div/div/div[3]/a")).click();
		Thread.sleep(2000);
		session.checkpoint("84_133_9706_45657_32");
		driver.findElement(By.xpath("//*[@id='enterprisecycleplayer_enterprisecycledevice_ids_']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='showinterest']/div/div/div[2]/form/input[4]")).click();
		Thread.sleep(2000);
		session.checkpoint("84_133_9706_45658_33");
		}
		else
			System.out.println("No Enterprise cycle is running at this moment.");
	}
	
	
	
}


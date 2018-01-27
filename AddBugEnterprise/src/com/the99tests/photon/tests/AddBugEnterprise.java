/*
 * Copyright (c) 2017, Preenos Crowd Technologies Private Limited
 * 
 * Please read instructions below for writing your tests
 */

package com.the99tests.photon.tests;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

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
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
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
public class AddBugEnterprise extends PhotonSession.PhotonSuite<RemoteWebDriver> {
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
		 driver.setFileDetector(new LocalFileDetector());
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
		workflowInfo.put("workflow", "86"); // 15
		workflowInfo.put("enterpriseCycle", "777");
		return workflowInfo;
	}
	
	@Override
	protected HashMap<String, Object> getAutomationDeviceInfo() {
		HashMap<String, Object> deviceInfo=new HashMap<String, Object>();
		deviceInfo.put("automationDeviceId", "d19145");
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
		
		public void OnboardLoginCorrect() throws Exception
		{
			String CUserName = "arunkumaarts@gmail.com";//"shashiranjansharma@live.in";
			String CPassword = "1993DINGU@6"; //"sadaf@14";
			driver.get(baseUrl);
			driver.findElement(By.xpath("/html/body/header/div/nav/div[2]/div[2]/ul/li[6]/a")).click();
			driver.findElement(By.xpath("//*[@id='email']")).sendKeys(CUserName);
			driver.findElement(By.xpath("//*[@id='password']")).sendKeys(CPassword);
			driver.findElement(By.id("signin_btn")).click();
			
		}
		
		@Test
		public void addNewBug() throws Exception{
			OnboardLoginCorrect();
			WebElement ActiveProject = driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul/li[3]/a"));
			Actions act = new Actions(driver);
			act.moveToElement(ActiveProject).build().perform();
			ActiveProject.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='main_container']/div[3]/div/div/div[1]/ul/li[2]/a")).click();
			//WebElement ProjectTitle = driver.findElement(By.xpath("//*[@id='enterprisecycles']/div/div/div/table/tbody/tr[2]/td[3]/a"));
			WebElement ProjectTitle = driver.findElement(By.xpath("//*[@id='openbug']/table/tbody/tr/td/div/div[2]/div/div/div[1]/a"));
			
			if(ProjectTitle.isDisplayed())
			{
				System.out.println("Openbug cycle is running");
				act.moveToElement(ProjectTitle).build().perform();
				ProjectTitle.click();
			}
			Thread.sleep(2000);
			//driver.findElement(By.xpath("//*[@id='bottom-header']/ul/li[3]/a")).click();
			//Thread.sleep(2000);
			session.checkpoint("86_136_9727_45708_83");
			driver.findElement(By.xpath("//*[@id='attachment_']")).sendKeys(Paths.get(".").toAbsolutePath().normalize().toString()+"/testdata/imagerequired.png");
			System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
			Thread.sleep(20000);
			String Title = "Unable to updalod an image from Gdrive";
			driver.findElement(By.xpath("//*[@id='enterprisebug_title']")).sendKeys(Title);
			String Bug_Url = "https://admin.scim.mindtickle.com/907111748118844097/edit?series=906477650754702391#level/907111753520021063";
			driver.findElement(By.xpath("//*[@id='enterprisebug_url']")).sendKeys(Bug_Url);
			
			String Description = "1.Go to https://admin.scim.mindtickle.com \n"

                                  +"2.Login in to Content Expert USER NAME content.expert@yopmail.com AND PASSWORD Test1234 \n"

                                  +"3.Create a group \n"

                                  +"4.change the themes \n"

                                  +"5.Select upload custom Background \n"

                                  +"6.new page will open from that select google drive";
			WebElement description = driver.findElement(By.xpath("//*[@id='cke_1_contents']/iframe"));//
			driver.switchTo().frame(description);
			driver.findElement(By.xpath("/html/body")).sendKeys(Description);
			driver.switchTo().defaultContent();
			String Actual = "400. That’s an error shown";
			WebElement actualResult = driver.findElement(By.xpath("//*[@id='cke_2_contents']/iframe"));
			act.moveToElement(actualResult).build().perform();
			driver.switchTo().frame(actualResult);
			driver.findElement(By.xpath("/html/body")).sendKeys(Actual);
			driver.switchTo().defaultContent();
			String Expected = "User should be able add a Pic or video  from Google Drive or dropbox";
			WebElement expectedResult = driver.findElement(By.xpath("//*[@id='cke_3_contents']/iframe"));
			act.moveToElement(expectedResult).build().perform();
			driver.switchTo().frame(expectedResult);
			driver.findElement(By.xpath("/html/body")).sendKeys(Expected);
			driver.switchTo().defaultContent();
			Select priority = new Select(driver.findElement(By.id("enterprisebug_priority")));
			priority.selectByVisibleText("High");
			Select type = new Select(driver.findElement(By.id("enterprisebug_btype")));
			type.selectByVisibleText("Performance");
			Select component = new Select(driver.findElement(By.id("enterprisebug_enterprisecyclecomponent_id")));
			component.selectByVisibleText("Enablement Admin");
			Select devicename = new Select(driver.findElement(By.id("devicename")));
			devicename.selectByVisibleText("max");
			Thread.sleep(2000);
			Select browser_id = new Select(driver.findElement(By.id("devicetypebrowser_id")));
			browser_id.selectByVisibleText("Google Chrome");
			String browserversion = "1.012";
			driver.findElement(By.id("devicebrowserversion_id")).sendKeys(browserversion);
			Thread.sleep(2000);
			session.checkpoint("86_136_9728_45709_84");
			WebElement submitBug = driver.findElement(By.xpath("//*[@id='new_enterprisebug']/div[3]/div[2]/div/input"));
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			act.moveToElement(submitBug).build().perform();
			submitBug.click();
			Thread.sleep(2000);
			session.checkpoint("86_136_9728_45710_85");
			
		}
		
	}


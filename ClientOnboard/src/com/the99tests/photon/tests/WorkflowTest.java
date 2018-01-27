/*
 * Copyright (c) 2017, Preenos Crowd Technologies Private Limited
 * 
 * Please read instructions below for writing your tests
 */

package com.the99tests.photon.tests;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
public class WorkflowTest extends PhotonSession.PhotonSuite<RemoteWebDriver> {
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
		workflowInfo.put("workflow", "78"); // 15
		workflowInfo.put("enterpriseCycle", "772");
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
	
/*	@Test(priority = 1)
	public void ValidateClientOnboard() throws Exception
	{
		String AppUrl = "https://99tests.com/";
		driver.get(AppUrl);
		driver.findElement(By.xpath("/html/body/header/div/nav/div[2]/div[2]/ul/li[5]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("body > div.crisp-client > div.crisp-1 > div > a")).click();
		driver.findElement(By.cssSelector("body > div.crisp-client > div.crisp-1 > div > div > div.crisp-80 > div > span.crisp-109.crisp-33 > span")).click();
		Thread.sleep(2000);
		session.checkpoint("78_125_9353_44830_1");
		driver.findElement(By.id("product_name_onboard")).click();
		Thread.sleep(2000);
		session.checkpoint("78_125_9353_44831_2");
		driver.findElement(By.xpath("//*[@id='findClicked']/a[1]/i")).click();
		Thread.sleep(2000);
		session.checkpoint("78_125_9353_44832_3");
		WebElement Continue_Btn = driver.findElement(By.xpath("/html/body/div[2]/div/form/div/div/div[2]/button"));
		Assert.assertTrue(Continue_Btn.isEnabled()==false, "Continue button is disable");
		Thread.sleep(2000);
		session.checkpoint("78_125_9353_44833_4");
	}
	
	@Test(priority = 2)
	public void CopyTempEmail() throws Exception
	{
		String AppUrl = "https://99tests.com/";
		driver.get(AppUrl);
		driver.findElement(By.xpath("/html/body/header/div/nav/div[2]/div[2]/ul/li[5]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("body > div.crisp-client > div.crisp-1 > div > a")).click();
		driver.findElement(By.cssSelector("body > div.crisp-client > div.crisp-1 > div > div > div.crisp-80 > div > span.crisp-109.crisp-33 > span")).click();
		Thread.sleep(3000);
		session.checkpoint("78_125_9354_44834_5");
		driver.findElement(By.id("product_name_onboard")).click();
		Thread.sleep(2000);
		session.checkpoint("78_125_9354_44835_6");
		driver.findElement(By.id("product_name_onboard")).sendKeys("My APP");
		Thread.sleep(2000);
		session.checkpoint("78_125_9354_44836_7");
		driver.findElement(By.xpath("//*[@id='findClicked']/a[1]/i")).click();
		Thread.sleep(2000);
		session.checkpoint("78_125_9354_44837_8");
		driver.findElement(By.xpath("//*[@id='findClicked']/a[2]/i")).click();
		Thread.sleep(2000);
		session.checkpoint("78_125_9354_44838_9");
		driver.findElement(By.xpath("//*[@id='findClicked']/a[3]/i")).click();
		Thread.sleep(2000);
		session.checkpoint("78_125_9354_44839_10");
		driver.findElement(By.xpath("//*[@id='idForWebsiteOnboard']/a/i")).click();
		Thread.sleep(2000);
		session.checkpoint("78_125_9354_44840_11");
		
	}*/
	
	@Test(priority = 3)
	public void ValidateFormAttribute() throws Exception
	{
		String AppUrl = "https://99tests.com/";
		driver.get(AppUrl);
		driver.findElement(By.xpath("/html/body/header/div/nav/div[2]/div[2]/ul/li[5]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("body > div.crisp-client > div.crisp-1 > div > a")).click();
		driver.findElement(By.cssSelector("body > div.crisp-client > div.crisp-1 > div > div > div.crisp-80 > div > span.crisp-109.crisp-33 > span")).click();
		session.checkpoint("78_125_9355_44841_12");
		driver.findElement(By.id("product_name_onboard")).click();
		session.checkpoint("78_125_9355_44842_13");
		driver.findElement(By.id("product_name_onboard")).sendKeys("My APP");
		session.checkpoint("78_125_9355_44843_14");
		driver.findElement(By.xpath("//*[@id='findClicked']/a[1]/i")).click();
		session.checkpoint("78_125_9355_44844_15");
		driver.findElement(By.xpath("//*[@id='onboard_url']")).click();
		session.checkpoint("78_125_9355_44845_16");
		driver.findElement(By.xpath("/html/body/div[2]/div/form/div/div/div[1]/div[4]/div/div/button")).click();
		session.checkpoint("78_125_9355_44846_17");
		WebElement Continue_Btn = driver.findElement(By.xpath("/html/body/div[2]/div/form/div/div/div[2]/button"));
		Assert.assertTrue(Continue_Btn.isEnabled()==false, "Continue button is disable");
		session.checkpoint("78_125_9355_44847_18");
		driver.findElement(By.xpath("//*[@id='findClicked']/a[1]/i")).click();
		session.checkpoint("78_125_9355_44848_19");
		driver.findElement(By.xpath("//*[@id='onboard_url']")).sendKeys("99testscom");
		session.checkpoint("78_125_9355_44849_20");
		driver.findElement(By.xpath("//*[@id='findClicked']/a[1]/i")).click();
		session.checkpoint("78_125_9355_44850_21");
		driver.findElement(By.xpath("//*[@id='onboard_url']")).clear();
		driver.findElement(By.xpath("//*[@id='onboard_url']")).sendKeys("99tests.com");
		driver.findElement(By.xpath("/html/body/div[2]/div/form/div/div/div[1]/div[4]/div/div/button")).click();
		session.checkpoint("78_125_9355_44851_22");
		Continue_Btn.click();
		session.checkpoint("78_125_9355_44852_23");
		driver.findElement(By.xpath("/html/body/div[2]/div/form/div/div/div[1]/div[4]/div/div/button")).click();
		session.checkpoint("78_125_9355_44853_24");
		driver.findElement(By.xpath("/html/body/div[2]/div/form/div/div/div[1]/div[4]/div/div/ul/li[1]/a/label/input")).click();
		session.checkpoint("78_125_9355_44854_25");
		driver.findElement(By.xpath("/html/body/div[2]/div/form/div/div/div[1]/div[4]/div/div/ul/li[2]/a/label/input")).click();
		session.checkpoint("78_125_9355_44855_26");
		driver.findElement(By.xpath("/html/body/div[2]/div/form/div/div/div[1]/div[4]/div/div/ul/li[3]/a/label/input")).click();
		session.checkpoint("78_125_9355_44856_27");
		driver.findElement(By.xpath("/html/body/div[2]/div/form/div/div/div[1]/div[4]/div/div/ul/li[1]/a/label/input")).click();
		session.checkpoint("78_125_9355_44857_28");
		Continue_Btn.click();
		session.checkpoint("78_125_9355_44858_29");
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div[1]/div[3]/div[1]/a")).click();
		session.checkpoint("78_125_9355_44859_30");
		session.scrollToBottom();
		driver.findElement(By.xpath("//*[@id='back-to-requirement']")).click();
		session.checkpoint("78_125_9355_44860_31");
		Continue_Btn.click();
		session.checkpoint("78_125_9355_44861_32");
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div[2]/button[1]")).click();
		session.checkpoint("78_125_9355_44862_33");
		driver.findElement(By.id("person_name_onboard")).click();
		driver.findElement(By.id("email_id_onboard")).click();
		session.checkpoint("78_125_9355_44863_34");
		driver.findElement(By.id("person_name_onboard")).sendKeys("Shashi");
		session.checkpoint("78_125_9355_44864_35");
		driver.findElement(By.id("email_id_onboard")).click();
		driver.findElement(By.id("company_name_onboard")).click();
		session.checkpoint("78_125_9355_44865_36");
		driver.findElement(By.id("email_id_onboard")).sendKeys("shashi@xxx.com");
		session.checkpoint("78_125_9355_44866_37");
		driver.findElement(By.id("company_name_onboard")).click();
		driver.findElement(By.id("password_onboard")).click();
		session.checkpoint("78_125_9355_44867_38");
		String MyAppTest = getSaltString();
		driver.findElement(By.id("company_name_onboard")).sendKeys("99tests");
		session.checkpoint("78_125_9355_44868_39");
		driver.findElement(By.id("password_onboard")).click();
		WebElement Signup = driver.findElement(By.xpath("/html/body/div[4]/div/div/form/div[2]/div/div[3]/button"));
		Assert.assertTrue(Signup.isEnabled()==false, "Signup button is disable");
		session.checkpoint("78_125_9355_44869_40");
		driver.findElement(By.id("password_onboard")).sendKeys("3434465yuyuiuiyr");
		Signup.click();
		Thread.sleep(10000);
		session.checkpoint("78_125_9355_44870_41");
		
	}
	
	
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

	
	@Test(priority = 4 )
	public void TestFunctional() throws Exception
	{
		String AppUrl = "https://99tests.com/";
		driver.get("https://www.minuteinbox.com/");
		driver.findElement(By.xpath("/html/body/div/div[3]/div[2]/div[1]/a")).click();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String result = (String) clipboard.getData(DataFlavor.stringFlavor);
		session.openNewTab();
		System.out.println("Successfully opened new tab");
		driver.get(AppUrl);
		driver.findElement(By.xpath("/html/body/header/div/nav/div[2]/div[2]/ul/li[5]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("body > div.crisp-client > div.crisp-1 > div > a")).click();
		driver.findElement(By.cssSelector("body > div.crisp-client > div.crisp-1 > div > div > div.crisp-80 > div > span.crisp-109.crisp-33 > span")).click();
		Thread.sleep(1000);
	    session.checkpoint("78_126_9356_44871_42");
	    driver.findElement(By.id("product_name_onboard")).clear();
	    driver.findElement(By.id("product_name_onboard")).sendKeys("My APP");
	    session.checkpoint("78_126_9356_44872_43");
	    driver.findElement(By.xpath("//*[@id='findClicked']/a[1]/i")).click();
	    session.checkpoint("78_126_9356_44873_44");
	    driver.findElement(By.id("onboard_url")).sendKeys("https://99tests.net");
	    session.checkpoint("78_126_9356_44874_45");
	    driver.findElement(By.xpath("/html/body/div[2]/div/form/div/div/div[1]/div[4]/div/div/button")).click();
	    driver.findElement(By.xpath("/html/body/div[2]/div/form/div/div/div[1]/div[4]/div/div/ul/li[1]/a/label/input")).click();
	    session.checkpoint("78_126_9356_44875_46");
	    driver.findElement(By.xpath("/html/body/div[2]/div/form/div/div/div[2]/button")).click();
	    session.checkpoint("78_126_9356_44876_47"); 
	    driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div[1]/div[3]/div[1]/a")).click();
	    driver.findElement(By.xpath("//*[@id='couponCodeId']")).sendKeys("BugBash17");
	    session.scrollToBottom();
	    driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div[2]/button[1]")).click();
	    session.checkpoint("78_126_9356_44877_48");
	    driver.findElement(By.id("person_name_onboard")).sendKeys("Testing");
	    session.checkpoint("78_126_9356_44878_49");
	    driver.findElement(By.id("email_id_onboard")).sendKeys(result);
	    session.checkpoint("78_126_9356_44879_50");
	    String MyAppTest = getSaltString();
	    driver.findElement(By.id("company_name_onboard")).sendKeys(MyAppTest);
	    session.checkpoint("78_126_9356_44880_51");
	    driver.findElement(By.id("password_onboard")).sendKeys("password_onboard");
	    driver.findElement(By.xpath("/html/body/div[4]/div/div/form/div[2]/div/div[3]/button")).click();
	    session.checkpoint("78_126_9356_44881_52");
	    Thread.sleep(2000);
	    session.switchToTab(1);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id='schranka']/tr[1]")).click();
		Thread.sleep(5000);
	}
}


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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class AccountSettings extends PhotonSession.PhotonSuite<RemoteWebDriver> {
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
		workflowInfo.put("workflow", "88"); // 15
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
			String CUserName = "shashiranjansharma@live.in";
			String CPassword = "sadaf@14";
			driver.get(baseUrl);
			driver.findElement(By.xpath("/html/body/header/div/nav/div[2]/div[2]/ul/li[6]/a")).click();
			driver.findElement(By.xpath("//*[@id='email']")).sendKeys(CUserName);
			driver.findElement(By.xpath("//*[@id='password']")).sendKeys(CPassword);
			driver.findElement(By.id("signin_btn")).click();
			
		}
		
		//Below test case is for editing tester profile.
		@Test(priority = 7)
		// @Parameters({"DisplayName", "RealName", "AboutTester", "TeserExperienceYear", "TeserExperienceMonth"})
		public void AccountSettingProfile() throws Exception
		{
			String DisplayName = "Shashi Ranjan";
			String RealName = "Shashi Ranjan Kumar";
			String AboutTester = "I am a software tester with 2+ year of experience, Currently associated with 99tests";
			String TeserExperienceMonths = "9";
			String TeserExperienceYear = "1";
			OnboardLoginCorrect();
			driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul/li[8]/a")).click();
			driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul/li[8]/ul/div/div[1]/li[7]/a")).click();
			Thread.sleep(1000);
			session.checkpoint("87_137_9791_45868_243");
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(1000);
			session.checkpoint("87_137_9791_45869_244");
			driver.findElement(By.id("tester_display_name")).clear();
			driver.findElement(By.id("tester_real_name")).clear();
			driver.findElement(By.id("tester_bio")).clear();
			driver.findElement(By.id("tester_display_name")).sendKeys(DisplayName);
			driver.findElement(By.id("tester_real_name")).sendKeys(RealName);
			driver.findElement(By.id("tester_bio")).sendKeys(AboutTester);
			WebElement TEY = driver.findElement(By.id("yearexp"));
			Select selectYear = new Select(TEY);
			selectYear.selectByValue(TeserExperienceYear);
			WebElement TEM = driver.findElement(By.id("monthexp"));
			Select selectMonth = new Select(TEM);
			selectMonth.selectByValue(TeserExperienceMonths);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			driver.findElement(By.xpath("//*[@id='tester_edit_profile']/form/div[16]/input")).click();
			WebDriverWait WaitForMe = new WebDriverWait(driver, 5);
			WaitForMe.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
			driver.findElement(By.id("tester_edit_tab")).click();
			Thread.sleep(1000);
			session.checkpoint("87_137_9791_45870_245");
		
			//-----------------------------------------------------------------------------------------------------\\
			//Testers Bank account details
			
			String BankAccountNumber = "98765432100000";
			String BankBranchName = "Kormangala, Bangalore";
			String BankIFSC = "ICICI123";
			driver.findElement(By.id("tester_payment_tab")).click();
			Thread.sleep(2000);
			session.checkpoint("87_137_9791_45871_246");
			WebElement INDbank = driver.findElement(By.id("tester_dest_account_indian"));
			if(INDbank.isSelected())
			{
				System.out.println("Indian bank account details radio is already selected");
				session.checkpoint("87_137_9791_45872_247");
			}
			else
			{
				INDbank.click();
				Thread.sleep(1000);
				session.checkpoint("87_137_9791_45872_247");
			}
			driver.findElement(By.id("tester_bank")).clear();
			driver.findElement(By.id("tester_account_type")).clear();
			driver.findElement(By.id("tester_ifsc")).clear();
			driver.findElement(By.id("tester_bank")).sendKeys(BankAccountNumber);
			driver.findElement(By.id("tester_account_type")).sendKeys(BankBranchName);
			driver.findElement(By.id("tester_ifsc")).sendKeys(BankIFSC);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			driver.findElement(By.xpath("//*[@id='tester_payment_settings']/form/div[6]/input")).click();
			WaitForMe.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			session.checkpoint("87_137_9791_45873_248");
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
			driver.findElement(By.id("tester_payment_tab")).click();
			driver.findElement(By.id("tester_bank")).clear();
			driver.findElement(By.id("tester_account_type")).clear();
			driver.findElement(By.id("tester_ifsc")).clear();
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			driver.findElement(By.xpath("//*[@id='tester_payment_settings']/form/div[6]/input")).click();
			WaitForMe.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			session.checkpoint("87_137_9791_45874_249");
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
			driver.findElement(By.id("tester_payment_tab")).click();
			WebElement NonINDbank = driver.findElement(By.id("tester_dest_account_nonindian"));
			if(NonINDbank.isSelected())
			{
				System.out.println("Non Indian bank account details radio is already selected");
				session.checkpoint("87_137_9791_45875_250");
			}
			else
			{
				NonINDbank.click();
				Thread.sleep(1000);
				session.checkpoint("87_137_9791_45875_250");
			}
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			driver.findElement(By.xpath("//*[@id='tester_payment_settings']/form/div[6]/input")).click();
			Thread.sleep(2000);
			WaitForMe.until(ExpectedConditions.alertIsPresent());
			
			driver.switchTo().alert().accept();
			session.checkpoint("87_137_9791_45876_251");
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
			driver.findElement(By.id("tester_payment_tab")).click();
		   
		//---------------------------------------------------------------------------------------------------------------------------------\\
		// Testers Skills
			
			driver.findElement(By.id("tester_skill_tab")).click();
			Thread.sleep(2000);
			session.checkpoint("87_137_9791_45877_252");
			WebElement IMP = driver.findElement(By.xpath("//*[@id='tester_skill_settings']/form/div[1]/div[2]/span/input"));
			Boolean Improve = IMP.isSelected();
			if(Improve == true)
			{
				System.out.println("Improve is already slected");
				//IMP.click();
			}
			else
			{
				IMP.click();
			}
			WebElement REW = driver.findElement(By.xpath("//*[@id='tester_skill_settings']/form/div[1]/div[3]/span/input"));
			Boolean Reward = REW.isSelected();
			if(Reward == true)
			{
				System.out.println("Reward is already slected");
				//REW.click();
			}
			else
			{
				REW.click();
			}
			
			WebElement FUL = driver.findElement(By.xpath("//*[@id='tester_skill_settings']/form/div[1]/div[4]/span/input"));
			Boolean Fulltime = FUL.isSelected();
			if(Fulltime == true)
			{
			  System.out.println("FullTime is already slected");
			  //REW.click();
			}
			else
			{
				FUL.click();
			}
			WebElement QUI = driver.findElement(By.xpath("//*[@id='tester_skill_settings']/form/div[1]/div[5]/span/input"));
			Boolean QuickEarning = QUI.isSelected();
			if(QuickEarning == true)
			{
				System.out.println("QuickEarning is already slected");
				//QUI.click();
			}
			else
			{
				QUI.click();
			}
			WebElement MEE = driver.findElement(By.xpath("//*[@id='tester_skill_settings']/form/div[1]/div[6]/span/input"));
			Boolean MeetOther = MEE.isSelected();
			if(MeetOther == true)
			{
				System.out.println("MeetOther is already slected");
				//MEE.click();
			}
			else
			{
				MEE.click();
			}
			WebElement LOO = driver.findElement(By.xpath("//*[@id='tester_skill_settings']/form/div[1]/div[7]/span/input"));
			Boolean LookingJob = LOO.isSelected();
			if(LookingJob == true)
			{
				System.out.println("LookingJob is already slected");
				//LOO.click();
			}
			else
			{
				LOO.click();
			}
			
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			driver.findElement(By.xpath("//*[@id='tester_skill_settings']/form/div[7]/input")).click();
			WaitForMe.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
			driver.findElement(By.id("tester_skill_tab")).click();
			Thread.sleep(2000);
			session.checkpoint("87_137_9791_45878_253");
			//--------------------------------------------------------------------------------------------------------------------\\
			//Add a device in tester device list.
			
			String DeviceName = "MacBook Pro";
			driver.navigate().refresh();
			driver.findElement(By.id("tester_devices_tab")).click();
			Thread.sleep(2000);
			session.checkpoint("87_137_9791_45879_254");
			WebElement DType = driver.findElement(By.id("testerdevice_device_id"));
			Thread.sleep(2000);
			session.checkpoint("87_137_9791_45880_255");
			Select DeviceType = new Select(DType);
			DeviceType.selectByVisibleText("Personal Computer");
			driver.findElement(By.id("testerdevice_device_name")).sendKeys(DeviceName);
			WebElement OS = driver.findElement(By.id("testerdevice_devicetype_id"));
			Select OperatingSystem = new Select(OS);
			OperatingSystem.selectByVisibleText("MAC OS");
			WebElement OSV = driver.findElement(By.id("testerdevice_devicetypeosversion_id"));
			Select OperatingSystemVersion = new Select(OSV);
			OperatingSystemVersion.selectByVisibleText("OS X v10.6 (Snow Leopard)");
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			driver.findElement(By.xpath("//*[@id='browsers']/div[10]/input")).click();
			driver.findElement(By.xpath("//*[@id='browsers']/div[14]/input")).click();
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			driver.findElement(By.xpath("//*[@id='new_testerdevice']/div/div[9]/input")).click();
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
			driver.navigate().refresh();
			driver.findElement(By.id("tester_devices_tab")).click();
			Thread.sleep(2000);
			session.checkpoint("87_137_9791_45879_256");
		//-----------------------------------------------------------------------------------------------------------------------\\
			//My Notification
			
			driver.navigate().refresh();
			WebElement Notification = driver.findElement(By.id("tester_notification_tab"));
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(Notification));
			Notification.click();
			Thread.sleep(2000);
			session.checkpoint("87_137_9791_45882_257");
			WebElement NewTest = driver.findElement(By.id("notification1"));
			if(NewTest.isSelected())
			{
				System.out.println("Email me whenever any new test cycle is launched");
				NewTest.click();
			}
			else
			{
				//NewTest.click();
				System.out.println("Not Required");
			}
			
			WebElement MatchMySkill = driver.findElement(By.id("notification2"));
			if(MatchMySkill.isSelected())
			{
				System.out.println("Email me only for those test cycle, which match my skills");
				MatchMySkill.click();
			}
			else
			{
				//MatchMySkill.click();
			}
			WebElement MyCycle = driver.findElement(By.id("notification3"));
			if(MyCycle.isSelected())
			{
				System.out.println("Email me only for those test cycle, which match my skills");
				MyCycle.click();
			}
			else
			{
				//MyCycle.click();
			}
			
			WebElement News = driver.findElement(By.id("notification4"));
			if(News.isSelected())
			{
				System.out.println("Subscribe for news letter");
				News.click();
			}
			else
			{
				//News.click();
			}
			
			WebElement Dont = driver.findElement(By.id("notification5"));
			if(Dont.isSelected())
			{
				System.out.println("Don't send any email");
				Dont.click();
			}
			else
			{
				//Dont.click();
			}
			Thread.sleep(2000);
			session.checkpoint("87_137_9791_45883_258");
			NewTest.click();
			MyCycle.click();
			Thread.sleep(2000);
			session.checkpoint("87_137_9791_45884_259");
			driver.findElement(By.xpath("//*[@id='tester_notification_settings']/div[3]/form/input[2]")).click();
			Thread.sleep(1000);
			driver.navigate().refresh();
			Thread.sleep(2000);
			driver.findElement(By.id("tester_notification_tab")).click();
			Thread.sleep(2000);
			session.checkpoint("87_137_9791_45885_260");
		//-----------------------------------------------------------------------------------------------------------------\\
			//My Job Profile
		
			String Job_Email = "shashiranjansharma@live.in";
			String Job_Name = "Shashi";
			String Job_Cover = "Software tester, with 5 years of experience";
			driver.navigate().refresh();
			WebElement JobProfile = driver.findElement(By.xpath("//*[@id='launch_tab']"));
			wait.until(ExpectedConditions.elementToBeClickable(JobProfile));
			JobProfile.click();
			Thread.sleep(2000);
			session.checkpoint("87_137_9791_45886_261");
			WebElement Job = driver.findElement(By.id("jobprofile_seeker"));
			Select Jobtype = new Select(Job);
			Jobtype.selectByVisibleText("Yes");
			driver.findElement(By.id("jobprofile_email")).clear();
			driver.findElement(By.id("jobprofile_name")).clear();
			driver.findElement(By.id("jobprofile_body")).clear();
			driver.findElement(By.id("jobprofile_email")).sendKeys(Job_Email);
			driver.findElement(By.id("jobprofile_name")).sendKeys(Job_Name);
			driver.findElement(By.id("jobprofile_body")).sendKeys(Job_Cover);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			driver.findElement(By.xpath("//*[@id='tester_jobprofile_settings']/form/div[6]/input")).click();
			driver.navigate().refresh();
			driver.findElement(By.xpath("//*[@id='launch_tab']")).click();
			Thread.sleep(2000);
			session.checkpoint("87_137_9791_45887_262");
			driver.findElement(By.xpath("//*[@id='upload_file']")).sendKeys(Paths.get(".").toAbsolutePath().normalize().toString()+"/testdata/Scanshashi.pdf");
			Thread.sleep(3000);
			driver.navigate().refresh();
			driver.findElement(By.xpath("//*[@id='launch_tab']")).click();
			session.checkpoint("87_137_9791_45888_263");
		//---------------------------------------------------------------------------------------------------------------------------\\
			//Change your password
			
			driver.navigate().refresh();
			WebElement ChangePassword = driver.findElement(By.xpath("//*[@id='tester_jobprofile_tab']"));
			wait.until(ExpectedConditions.elementToBeClickable(ChangePassword));
			ChangePassword.click();
			session.checkpoint("87_137_9791_45889_264");
			String currentPassword = "sadaf@14";
			String newPassword = "ranjan1234";
			driver.findElement(By.id("tester_old_password")).clear();
			driver.findElement(By.id("tester_old_password")).sendKeys(currentPassword);
			driver.findElement(By.id("tester_password")).clear();
			driver.findElement(By.id("tester_password")).sendKeys(newPassword);
			driver.findElement(By.id("tester_password_confirmation")).clear();
			driver.findElement(By.id("tester_password_confirmation")).sendKeys(newPassword);
			driver.findElement(By.xpath("//*[@id='change_password']/div[4]/input")).click();
			Thread.sleep(2000);
			session.checkpoint("87_137_9791_45890_265");
			driver.findElement(By.xpath("//*[@id='email']")).sendKeys("shashiranjansharma@live.in");
			driver.findElement(By.xpath("//*[@id='password']")).sendKeys(newPassword);
			driver.findElement(By.id("signin_btn")).click();
			
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='tester_jobprofile_tab']")).click();
			driver.findElement(By.id("tester_old_password")).clear();
			driver.findElement(By.id("tester_old_password")).sendKeys(currentPassword);
			driver.findElement(By.id("tester_password")).clear();
			driver.findElement(By.id("tester_password")).sendKeys(newPassword);
			driver.findElement(By.id("tester_password_confirmation")).clear();
			driver.findElement(By.id("tester_password_confirmation")).sendKeys(newPassword);
			driver.findElement(By.xpath("//*[@id='change_password']/div[4]/input")).click();
			Thread.sleep(2000);
			session.checkpoint("87_137_9791_45891_266");
			
			driver.findElement(By.id("tester_old_password")).clear();
			driver.findElement(By.id("tester_old_password")).sendKeys(newPassword);
			driver.findElement(By.id("tester_password")).clear();
			driver.findElement(By.id("tester_password")).sendKeys(currentPassword);
			driver.findElement(By.id("tester_password_confirmation")).clear();
			driver.findElement(By.id("tester_password_confirmation")).sendKeys(currentPassword);
			driver.findElement(By.xpath("//*[@id='change_password']/div[4]/input")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='email']")).sendKeys("shashiranjansharma@live.in");
			driver.findElement(By.xpath("//*[@id='password']")).sendKeys(currentPassword);
			driver.findElement(By.id("signin_btn")).click();
			
			driver.navigate().refresh();
			driver.findElement(By.id("tester_changeemail_tab")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='change_password']/div[4]/div[1]/button")).click();
			WebElement radioWillBack = driver.findElement(By.xpath("//*[@id='reason_2']"));
			if(radioWillBack.isSelected())
			{
				
			}
			else
			{
				radioWillBack.click();
			}
			
			driver.findElement(By.xpath("//*[@id='message']")).sendKeys("I will come back soon");
			driver.findElement(By.xpath("//*[@id='new-account-deactivation-request']/div/div/form/div/div/div[3]/div[6]/div/input")).click();
			Thread.sleep(2000);
			session.checkpoint("87_137_9791_45892_267");
			Thread.sleep(10000);
			session.checkpoint("88_138_9791_45893_268");
			
			//-------------------------------------------------------------------------------------------------------------\\
			//Remove added device.
			driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul/li[8]/a")).click();
			driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul/li[8]/ul/div/div[1]/li[7]/a")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("tester_devices_tab")).click();
			if(driver.findElement(By.xpath("//*[@id='addeddevices']/div[2]/div[2]")).getText().contains(DeviceName))
			{
				System.out.println("AirBook found, Removing it");
				driver.findElement(By.xpath("//*[@id='addeddevices']/div[2]/div[2]/div/div/a[2]/span")).click();
				WaitForMe.until(ExpectedConditions.alertIsPresent());
				driver.switchTo().alert().accept();
				
			}
			else
			{
				System.out.println("AirBook is not in your device list");
			}
			Thread.sleep(3000);
			session.checkpoint("88_138_9791_45990_268");
			
			//Activate account
			
			driver.navigate().refresh();
			driver.findElement(By.id("tester_changeemail_tab")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='change_password']/div[4]/div[1]/a")).click();
			Thread.sleep(2000);
			
			
			//Remove added device.
			driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul/li[8]/a")).click();
			driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul/li[8]/ul/div/div[1]/li[7]/a")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("tester_devices_tab")).click();
			if(driver.findElement(By.xpath("//*[@id='addeddevices']/div[2]/div[2]")).getText().contains(DeviceName))
			{
				System.out.println("AirBook found, Removing it");
				driver.findElement(By.xpath("//*[@id='addeddevices']/div[2]/div[2]/div/div/a[2]/span")).click();
				WaitForMe.until(ExpectedConditions.alertIsPresent());
				driver.switchTo().alert().accept();
				
			}
			else
			{
				System.out.println("AirBook is not in your device list");
			}
			Thread.sleep(3000);
	
			
		}

		
	}



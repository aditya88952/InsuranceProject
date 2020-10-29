package com_hackethon_base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com_hackethon_utils.DateUtils;
import com_hackethon_utils.ExtentReportManager;

public class BaseUI {
	public RemoteWebDriver driver;;
	public Properties prop;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;

	// ************************************************INVOKE THE
	// BROWSER************************************************************//
	public void invokeBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("Chrome")) {

				//System.setProperty("webdriver.chrome.driver",
						//System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
				//driver = new ChromeDriver();
				 DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				 driver = new RemoteWebDriver(new URL("http://192.168.1.101:5566/wd/hub"), capabilities);
				
			} else if (browserName.equalsIgnoreCase("Firefox")) {

				//System.setProperty("webdriver.gecko.driver",
						//System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\geckodriver.exe");
				//driver = new FirefoxDriver();
				 DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				 driver = new RemoteWebDriver(new URL("http://192.168.1.101:5567/wd/hub"), capabilities);
			}

			else if (browserName.equalsIgnoreCase("Opera")) {

				//System.setProperty("webdriver.opera.driver",
						//System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\operadriver.exe");
				//driver = new OperaDriver();
				 DesiredCapabilities capabilities = DesiredCapabilities.opera();
				 driver = new RemoteWebDriver(new URL("http://192.168.1.101:5568/wd/hub"), capabilities);
				
			} else {
				System.out.println(
						"Please Invoke the Correct Browser,i.e : Chrome, Opera or Firefox. Thank you! Have a nice day ");
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

	}

	public void invokeproperties() {

		if (prop == null) {
			prop = new Properties();

			try {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\objectRepository\\projectConfig.properties");
				prop.load(file);

			} catch (Exception e) {
				reportFail(e.getMessage());
				e.printStackTrace();
			}

		}
	}

	// ********************************************** OPEN
	// URL**************************************************************************//
	public void openURL(String websiteURLKey) {
		try {
			driver.get(prop.getProperty(websiteURLKey));
			reportPass(websiteURLKey + " Identified Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// ************************************************TEAR DOWN(CLOSING TABS OF
	// BROWSER)**********************************************************//
	public void tearDown() {

		driver.close();
	}

	// **************************************************QUIT
	// BROWSER*****************************************************************************//
	public void quitBrowser() {

		driver.quit();
	}

	// ***********************************************************************************************
	public void navigateTohome() {
		try {
			driver.navigate().to(prop.getProperty("websiteURL"));
			reportPass("Navigated to Home Page Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// ****************************************************ENTER
	// TEXT************************************************************************//

	public void enterText(String xpathKey, String data) {
		try {
			getElement(xpathKey).sendKeys(data);
			reportPass(data + " - Entered successfully in locator Element : " + xpathKey);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// *************************************CLICK
	// ELEMENT*************************************************************************//

	public void clickElement(String xpathKey) {
		try {
			getElement(xpathKey).click();
			reportPass(xpathKey + " : Element Clicked Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// *************************************************MOUSE
	// HOVER****************************************************************************//

	public void howerMouse(String xpathKey) {

		try {
			Actions actions = new Actions(driver);
			WebElement targetElement = getElement(xpathKey);
			actions.moveToElement(targetElement).perform();
			reportPass(xpathKey + " : Mouse Hower Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// ***********************************************MOUSE HOVER AND CLICK AT
	// SAME TIME********************************************************//

	public void howerAndClick(String xpathKey) {

		try {
			Actions actions = new Actions(driver);
			WebElement targetElement = getElement(xpathKey);
			actions.moveToElement(targetElement).click().perform();
			reportPass(xpathKey + " : Mouse Hower and Click Successfully");

		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	// *********************************************************************************************************

	public void validationError(String locatorKey, String locatorkey1) {
		try{
			if (getElement(locatorKey).isDisplayed()) {
				String error_text=getElement(locatorKey).getText();
				System.out.println(error_text);
				reportPass(error_text);
				waitLoad(1);
				
				
			}
			else{
				System.out.println("No error in mail  ");
			}
			
			if (getElement(locatorkey1).isDisplayed()) {
				String error_text=getElement(locatorkey1).getText();
				System.out.println(error_text);
				reportPass(error_text);
				waitLoad(1);
			}
			else{
				System.out.println("No error in phone ");
			}}
			catch (Exception e){
				reportFail(e.getMessage());
			}
			finally{
				waitLoad(2);
				
				navigateTohome();
				waitLoad(2);
			}
	}

	// *********************************************PRESS SPECIAL
	// KEYS*****************************************************************************//
	public void pressKeys(String xpathKey, String keysValue) {

		try {
			if (keysValue.equalsIgnoreCase("Enter")) {
				getElement(xpathKey).sendKeys(Keys.ENTER);
			} else if (keysValue.equalsIgnoreCase("Up")) {
				getElement(xpathKey).sendKeys(Keys.ARROW_UP);
			} else if (keysValue.equalsIgnoreCase("Down")) {
				getElement(xpathKey).sendKeys(Keys.ARROW_DOWN);
			} else if (keysValue.equalsIgnoreCase("Left")) {
				getElement(xpathKey).sendKeys(Keys.ARROW_LEFT);
			} else if (keysValue.equalsIgnoreCase("Right")) {
				getElement(xpathKey).sendKeys(Keys.ARROW_RIGHT);
			}
			reportPass(xpathKey + " : Special Key Pressed Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// ************************************************CALENDER
	// HANDELING***********************************************************************//
	public static void setDateUsingJavaScriptInCalendar(WebDriver driver, String strDate, WebElement calLocator) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String script = "arguments[0].setAttribute('value','" + strDate + "');";
		jse.executeScript(script, calLocator);
	}

	public void setCalenderDate(String xpathKey, String xpath1Key) {
		try {
			LocalDate today = LocalDate.now();
			System.out.println(today);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
			String strDate = formatter.format(today);
			WebElement departCal = getElement(xpathKey);
			BaseUI.setDateUsingJavaScriptInCalendar(driver, strDate, departCal);
			reportPass(xpathKey + " : Start date fed Successfully");
			LocalDate fina = today.plusDays(10);
			System.out.println(fina);
			formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
			String endDate = formatter.format(fina);
			WebElement Cal = getElement(xpath1Key);
			BaseUI.setDateUsingJavaScriptInCalendar(driver, endDate, Cal);
			reportPass(xpath1Key + " : End date fed Successfully");

		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// *****************************************************************************************************//
	public void waiting(String xpathKey) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty(xpathKey))));
			reportPass(xpathKey + " is visible in the WebPage");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// ****************************************************************************************************//
	public void SelectElementInList(String locatorXpath, String Value) {
		try {
			Select preference = new Select(getElement(locatorXpath));
			preference.selectByVisibleText(Value);
			reportPass(locatorXpath + " is visible in the WebPage" + " and " + Value + " is selected");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// *************************************************************************************************************
	public void SelectSubMenuFromMainMenu(String locatorKey) {
		try {
			List<WebElement> myElements = (List<WebElement>) driver
					.findElements(By.xpath(prop.getProperty(locatorKey)));
			waitLoad(3);

			for (WebElement e : myElements) {
				String name_menu = e.getText();
				logger.log(Status.INFO, "SubMenu Identified : " + name_menu);
				System.out.println(name_menu);

			}
			reportPass("All SubMenu Retrive");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// ***********************************************************************************************************

	public void getAttribute(String locatorKey) {
		try {
			String company = getElement(locatorKey).getAttribute("title");
			reportPass(" The Company name is:" + company);
			System.out.println(company);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// *************************************************************************************************************
	public void getText(String locatorKey) {
		try {
			String amount = getElement(locatorKey).getText();
			reportPass(" The amount is: Rs." + amount);
			System.out.println(amount);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	// ************************************************************************************************

	public void waitLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void implicitWait(int i) {
		try {
			driver.manage().timeouts().implicitlyWait(i, TimeUnit.SECONDS);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// **********************************************DYANAMIC LOCATOR FUNCTION
	// ********************************************************

	public WebElement getElement(String locatorKey) {
		WebElement element = null;

		try {
			if (locatorKey.endsWith("_id")) {
				element = driver.findElement(By.id(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_xpath")) {
				element = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_className")) {
				element = driver.findElement(By.className(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_cssSelector")) {
				element = driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_LinkText")) {
				element = driver.findElement(By.linkText(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_PartialLinkText")) {
				element = driver.findElement(By.partialLinkText(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_Name")) {
				element = driver.findElement(By.name(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else {
				reportFail("Failing the Testcase, Invalid Locator " + locatorKey);
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		return element;
	}

	// *******************************************REPORTING
	// FUNCTION******************************************************************************

	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.fail(reportString);
	}

	// *********************************************************************************************
	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	// *********************************************************************************************
	public void takeScreenShotOnFailure() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(
				System.getProperty("user.dir") + "//ScreenShots//" + DateUtils.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "//ScreenShots//" + DateUtils.getTimeStamp() + ".png");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	// ****************************************************************************************************************

}

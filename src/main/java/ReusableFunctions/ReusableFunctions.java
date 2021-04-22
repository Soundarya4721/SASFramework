package ReusableFunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.xml.sax.SAXException;


import com.jayway.restassured.path.xml.XmlPath;
import com.opera.core.systems.scope.services.Exec;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Test;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import SupportLibraries.*;
import SupportLibraries.Report.Status;
import freemarker.template.utility.Execute;
import global.Global;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class ReusableFunctions{
	
	private static final String JavaScriptLibrary = null;
	public long timeout = 30000;
	public static int riwMonthlyBenefitAmount = 0;
	public String keyword = "";
	public String objectName = "";
	public String objectID = "";
	public String methodName = "";
	Util utilObject =new Util();
	public String environment = utilObject.getValueFromConfigProperties("EnvironmentName");

	/**
	 * Method Name: Login
	 * Description: This method is to Login to the Application
	 * Parameters: Nothing
	 * Return Type: Nothing
	 * @throws Exception
	 */
	
	public void navigateToIESURL(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		//Util utilObject = new Util();
		//driver.close();
				
		//ExecuteScripts e1 = new ExecuteScripts();
		
		//StandaloneDriver.driver = e1.SelectDriver(browser, "");
		//ExecuteScripts.driver = StandaloneDriver.driver;
		
		//driver = ExecuteScripts.driver;
		
		
		try {
			driver.navigate().to(utilObject.getValueFromConfigProperties(environment+"_URL"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void navigateToIESURLIE(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();

		caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
		caps.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
		caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		// caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);
		caps.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
		caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		
		
			// When Grid Setup is No in Config.properties file
			try {
				System.setProperty("webdriver.ie.driver",
						new File(".").getCanonicalPath() + "\\lib\\IEDriverServer.exe");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if(driver!=null) {
				driver.quit();
				driver=null;
				driver = new InternetExplorerDriver(caps);
				Global.driver=driver;
			}else {
				driver = new InternetExplorerDriver(caps);
				Global.driver=driver;
			}
		
		
		try {
			driver.navigate().to(utilObject.getValueFromConfigProperties(environment+"_URL"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void handleMCITaskMessagge(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws InterruptedException{
		KeywordLibrary k1 = new KeywordLibrary();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if(driver.getPageSource().contains("Please confirm that the information you have provided below is correct,")){
			k1.captureScreenshot(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
			driver.findElement(By.xpath(".//*[@id='common_fourteen']")).click();
			
		}
	}
	
	public void EsignCheckboxes(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws InterruptedException{
		
		WebElement chk1 = driver.findElement(By.xpath(".//*[@id='ApplicationESign']/div/div[1]/fieldset[2]/div/div[1]/label"));	
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		js1.executeScript("arguments[0].click();", chk1);
		
		WebElement ClosingPopup1 = driver.findElement(By.xpath(".//*[@id='shareDataConsentModal']/div[3]/div/div/div/button"));
		if(ClosingPopup1.isDisplayed()){
			ClosingPopup1.click();	
		}
		Thread.sleep(2000);
		WebElement chk2 = driver.findElement(By.xpath(".//*[@id='ApplicationESign']/div/div[1]/fieldset[2]/div/div[1]/p[2]/label"));	
		JavascriptExecutor js2 = (JavascriptExecutor)driver;
		js2.executeScript("arguments[0].click();", chk2);
		WebElement ClosingPopup2 = driver.findElement(By.xpath(".//*[@id='incomeDataConsentModal']/div[3]/div/div/div/button"));
		if(ClosingPopup1.isDisplayed()){
			ClosingPopup2.click();
			Thread.sleep(2000);
		}
	}
	
	

public void navigateThroughHIXScreensMCITask(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws Exception{
	
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	
	Util utilObject = new Util();
	Report reportObject = new Report();
			
	String MCIFlag = utilObject.getDataINI(utilObject.getData("SectionName", driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder), "MCI_Task");
	
	if(MCIFlag.equalsIgnoreCase("true")){
		
			if(utilObject.getDataINI(scenario+"_"+testCase, "SEP_Date_PreviousMonth").equalsIgnoreCase("true")){
				
				driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
				
				if(driver.findElement(By.xpath("//div/h1[contains(text(),'Application Review')]")).isDisplayed()){
					//Change
					driver.findElement(By.xpath(".//*[@id='common_one']")).click();
					driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
				}
				
				if(driver.findElements(By.xpath("//label[@for='lostInsuranceYES']")).size()!=0){
					driver.findElement(By.xpath("//label[@for='lostInsuranceYES']")).click();
					specialconditionsDate(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
					driver.findElement(By.xpath("//label[@for='gainedDependentMarriageNO']")).click();
					driver.findElement(By.xpath("//label[@for='gainedDependentBirthNO']")).click();
					driver.findElement(By.xpath("//label[@for='gainedDependentAdoptionNO']")).click();
					driver.findElement(By.xpath("//label[@for='gainedDependentCourtOrderNO']")).click();
					driver.findElement(By.xpath("//label[@for='movedtoRINO']")).click();
					driver.findElement(By.xpath("//label[@for='statusChangeNO']")).click();
					driver.findElement(By.xpath("//label[@for='statusChangedDeathNO']")).click();
					driver.findElement(By.xpath("//label[@for='anotherStatusChangeNO']")).click();
					
					driver.findElement(By.xpath(".//*[@id='common_one']")).click();
					
					if(driver.findElement(By.xpath("//form/div/div/h1[contains(text(),'Choose Coverage Effective Date')]")).isDisplayed()){
						Select startCoverageOnField = new Select(driver.findElement(By.xpath(".//*[@id='lastCovered']")));
						startCoverageOnField.selectByIndex(1);
						driver.findElement(By.xpath("//label[@for='disclaimer']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath(".//*[@id='common_eight']")).click();
						driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
						
						if(driver.findElement(By.xpath("//div/div/h1[contains(text(),'eSignature')]")).isDisplayed()){
							driver.findElement(By.xpath(".//*[@id='nextButton']")).click();
							Thread.sleep(2000);
							driver.findElement(By.xpath("//div[@id='submitApplicationModal']//a[@class='btn btn-primary modal-button' and @id='YesButton']")).click();
							Thread.sleep(2000);
							driver.findElement(By.xpath("//a[@id='appSubmitted']")).click();
							driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
							Thread.sleep(3000);
							
							driver.findElement(By.xpath("//button[@id='common_thirteen']")).click();
							Thread.sleep(3000);
							
							//New screen
							if(driver.getPageSource().contains("See Health Coverage Eligibility and Enroll in a Plan")){
								reportObject.Log("Verifying the screen See Health Coverage Eligibility and Enroll in a Plan is displayed or not", "The screen See Health Coverage Eligibility and Enroll in a Plan is displayed", Status.pass, driver, testCase, scenario, browser, "Yes", browserFolder);
								
								driver.findElement(By.xpath("//a[contains(@id,'eligibilityDeterPage') and contains(text(),'Next')]")).click();
								Thread.sleep(5000);	
							}else{
								reportObject.Log("Verifying the screen See Health Coverage Eligibility and Enroll in a Plan is displayed or not", "The screen See Health Coverage Eligibility and Enroll in a Plan is not displayed", Status.FAIL, driver, testCase, scenario, browser, "Yes", browserFolder);
							}
						}
					}
				}
			}
			else{
				if(driver.findElements(By.xpath("//button[@id='common_thirteen']")).size()!=0){
					driver.findElement(By.xpath("//button[@id='common_thirteen']")).click();
					Thread.sleep(3000);
					if(driver.getPageSource().contains("See Health Coverage Eligibility and Enroll in a Plan")){
						reportObject.Log("Verifying the screen See Health Coverage Eligibility and Enroll in a Plan is displayed or not", "The screen See Health Coverage Eligibility and Enroll in a Plan is displayed", Status.pass, driver, testCase, scenario, browser, "Yes", browserFolder);
					
						driver.findElement(By.xpath("//a[contains(@id,'eligibilityDeterPage') and contains(text(),'Next')]")).click();
						Thread.sleep(5000);
					}else{
						reportObject.Log("Verifying the screen See Health Coverage Eligibility and Enroll in a Plan is displayed or not", "The screen See Health Coverage Eligibility and Enroll in a Plan is not displayed", Status.FAIL, driver, testCase, scenario, browser, "Yes", browserFolder);
					}
				}
				
			}
	}else{
				
		
		if(utilObject.getDataINI(scenario+"_"+testCase, "SEP_Date_PreviousMonth").equalsIgnoreCase("true")){
			
			driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
						
			if(driver.findElement(By.xpath("//div/h1[contains(text(),'Application Review')]")).isDisplayed()){
				//Change
				driver.findElement(By.xpath(".//*[@id='common_one']")).click();
				driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
			}
			
			if(driver.findElements(By.xpath("//label[@for='lostInsuranceYES']")).size()!=0){
				driver.findElement(By.xpath("//label[@for='lostInsuranceYES']")).click();
				specialconditionsDate(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
				driver.findElement(By.xpath("//label[@for='gainedDependentMarriageNO']")).click();
				driver.findElement(By.xpath("//label[@for='gainedDependentBirthNO']")).click();
				driver.findElement(By.xpath("//label[@for='gainedDependentAdoptionNO']")).click();
				driver.findElement(By.xpath("//label[@for='gainedDependentCourtOrderNO']")).click();
				driver.findElement(By.xpath("//label[@for='movedtoRINO']")).click();
				driver.findElement(By.xpath("//label[@for='statusChangeNO']")).click();
				driver.findElement(By.xpath("//label[@for='statusChangedDeathNO']")).click();
				driver.findElement(By.xpath("//label[@for='anotherStatusChangeNO']")).click();
				
				driver.findElement(By.xpath(".//*[@id='common_one']")).click();
				
				if(driver.findElement(By.xpath("//form/div/div/h1[contains(text(),'Choose Coverage Effective Date')]")).isDisplayed()){
					Select startCoverageOnField = new Select(driver.findElement(By.xpath(".//*[@id='lastCovered']")));
					startCoverageOnField.selectByIndex(1);
					driver.findElement(By.xpath("//label[@for='disclaimer']")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath(".//*[@id='common_eight']")).click();
					driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
					
					if(driver.findElement(By.xpath("//div/div/h1[contains(text(),'eSignature')]")).isDisplayed()){
						driver.findElement(By.xpath(".//*[@id='nextButton']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//div[@id='submitApplicationModal']//a[@class='btn btn-primary modal-button' and @id='YesButton']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//a[@id='appSubmitted']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//button[@id='common_thirteen' and @value='Submit']")).click();
						driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
						Thread.sleep(3000);
						
						
					}
				}
			}
		}
		
		if(utilObject.getDataINI(scenario+"_"+testCase, "SEP_Date_PreviousMonth").equalsIgnoreCase("false")){
			
			if(driver.findElements(By.xpath("//button[@id='common_thirteen']")).size()!=0){
				driver.findElement(By.xpath("//button[@id='common_thirteen']")).click();
				Thread.sleep(3000);
				
				if(driver.getPageSource().contains("See Health Coverage Eligibility and Enroll in a Plan")){
					reportObject.Log("Verifying the screen See Health Coverage Eligibility and Enroll in a Plan is displayed or not", "The screen See Health Coverage Eligibility and Enroll in a Plan is displayed", Status.pass, driver, testCase, scenario, browser, "Yes", browserFolder);
				
					driver.findElement(By.xpath("//a[contains(@id,'eligibilityDeterPage') and contains(text(),'Next')]")).click();
					Thread.sleep(5000);
				}else{
					reportObject.Log("Verifying the screen See Health Coverage Eligibility and Enroll in a Plan is displayed or not", "The screen See Health Coverage Eligibility and Enroll in a Plan is not displayed", Status.FAIL, driver, testCase, scenario, browser, "Yes", browserFolder);
				}
			}else{
				driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
				if(driver.getPageSource().contains("Application Review")){
					//Chan
					driver.findElement(By.xpath(".//*[@id='common_one']")).click();
					driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
				
					if(driver.findElements(By.xpath("//label[@for='lostInsuranceYES']")).size()!=0){
						driver.findElement(By.xpath("//label[@for='lostInsuranceYES']")).click();
						specialconditionsDate(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
						driver.findElement(By.xpath("//label[@for='gainedDependentMarriageNO']")).click();
						driver.findElement(By.xpath("//label[@for='gainedDependentBirthNO']")).click();
						driver.findElement(By.xpath("//label[@for='gainedDependentAdoptionNO']")).click();
						driver.findElement(By.xpath("//label[@for='gainedDependentCourtOrderNO']")).click();
						driver.findElement(By.xpath("//label[@for='movedtoRINO']")).click();
						driver.findElement(By.xpath("//label[@for='statusChangeNO']")).click();
						driver.findElement(By.xpath("//label[@for='statusChangedDeathNO']")).click();
						driver.findElement(By.xpath("//label[@for='anotherStatusChangeNO']")).click();
						
						driver.findElement(By.xpath(".//*[@id='common_one']")).click();
						
						if(driver.findElement(By.xpath("//form/div/div/h1[contains(text(),'Choose Coverage Effective Date')]")).isDisplayed()){
							Select startCoverageOnField = new Select(driver.findElement(By.xpath(".//*[@id='lastCovered']")));
							startCoverageOnField.selectByIndex(1);
							driver.findElement(By.xpath("//label[@for='disclaimer']")).click();
							Thread.sleep(2000);
							driver.findElement(By.xpath(".//*[@id='common_eight']")).click();
							driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
							
							if(driver.findElement(By.xpath("//div/div/h1[contains(text(),'eSignature')]")).isDisplayed()){
								driver.findElement(By.xpath(".//*[@id='nextButton']")).click();
								Thread.sleep(2000);
								driver.findElement(By.xpath("//div[@id='submitApplicationModal']//a[@class='btn btn-primary modal-button' and @id='YesButton']")).click();
								Thread.sleep(2000);
								driver.findElement(By.xpath("//a[@id='appSubmitted']")).click();
								driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
								Thread.sleep(3000);
								
								driver.findElement(By.xpath("//button[@id='common_thirteen']")).click();
								Thread.sleep(3000);
								
								//New screen
								if(driver.getPageSource().contains("See Health Coverage Eligibility and Enroll in a Plan")){
									reportObject.Log("Verifying the screen See Health Coverage Eligibility and Enroll in a Plan is displayed or not", "The screen See Health Coverage Eligibility and Enroll in a Plan is displayed", Status.pass, driver, testCase, scenario, browser, "Yes", browserFolder);
									
									driver.findElement(By.xpath("//a[contains(@id,'eligibilityDeterPage') and contains(text(),'Next')]")).click();
									Thread.sleep(5000);	
								}else{
									reportObject.Log("Verifying the screen See Health Coverage Eligibility and Enroll in a Plan is displayed or not", "The screen See Health Coverage Eligibility and Enroll in a Plan is not displayed", Status.FAIL, driver, testCase, scenario, browser, "Yes", browserFolder);
								}
							}
						}
					}
				}
			}			
		}		
	}
	
}


public void verifyEnrollmentSuccessMessage(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, 
		String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws Exception {
	Report reportObject = new Report();
	
	try {		 
		if (driver.getPageSource().contains("has completed an enrollment application but will not be enrolled until a premium payment has been made")) {
			reportObject.Log("Verify that enrollment confirmation message is displayed", "Enrollment Confirmation Message was displayed",
					Status.pass, driver, testCase, scenario, browser, "YES", browserFolder);
		} else {
			reportObject.Log("Verify that enrollment confirmation message is displayed", "Enrollment Confirmation Message was NOT displayed",
					Status.pass, driver, testCase, scenario, browser, "YES", browserFolder);
		}
	} catch (Exception e) {
		reportObject.Log("Verify that enrollment confirmation message is displayed", "Enrollment Confirmation Message was NOT displayed",
				Status.pass, driver, testCase, scenario, browser, "YES", browserFolder);
		e.printStackTrace();
	}
}

	public void checkForMCITaskPopup(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws Exception{
		Util utilObject = new Util();
		KeywordLibrary k1 = new KeywordLibrary();
		Report reportObject = new Report();
		utilObject.setDataINI("MCI_Task", "false", testCase, scenario);
		k1.waitForPageToLoad(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		//if(!(driver.getPageSource().contains("Additional Information Required"))){
		//if(!(driver.getPageSource().contains("Please verify the following information"))){
		
		if((!(driver.getPageSource().contains("Please verify the following information"))) &&
				driver.getPageSource().contains("In order for your health insurance coverage to begin on the 1st of the next month")){
			utilObject.setDataINI("MCI_Task", "true", testCase, scenario);
			reportObject.Log("Set MCI_Task to true", "Info", Status.done, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			WebElement closeIcon = driver.findElement(By.xpath(".//*[@id='mcipartialmatch']/div[3]/div/div/div[1]/a"));
			//if (nextButton.isEnabled() && nextButton.isDisplayed()) {
			//System.out.println("Clicking on element with using java script click");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeIcon);
			
			try{
				Thread.sleep(5000);
			}catch(Exception e){
				
			}
			
			//k1.verifyDisplayedPage(homePath, testCase, scenario, browser, objectId, "Account Home", "Account Home", "The Account Home page is displayed", "The Account Home page is not displayed", driver, passScreenshot, currentIteration, error, browserFolder);
			
			driver.findElement(By.xpath("//a[@id='LogoutButton' and text()='Log Out']")).click();
			
		}else{
			//Change
			Thread.sleep(20000);
			k1.waitForXPath(homePath, testCase, scenario, browser, "xpath://button[@id='common_thirteen' and @value='Submit']", "ADDITIONAL_DOCUMENTATION_REQUIRED_NEXT_BUTTON", dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
			//k1.waitForXPath(homePath, testCase, scenario, browser, "xpath://a[@id='common_thirteen']", "ADDITIONAL_DOCUMENTATION_REQUIRED_NEXT_BUTTON", dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
			
			//driver.findElement(By.xpath("//a[@id='common_thirteen']")).click();
			driver.findElement(By.xpath("//button[@id='common_thirteen' and @value='Submit']")).click();
			Thread.sleep(9000);
			k1.waitForPageToLoad(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
			//New screens
			k1.verifyDisplayedPage(homePath, testCase, scenario, browser, objectId, "See Health Coverage Eligibility and Enroll in a Plan", "See Health Coverage Eligibility and Enroll in a Plan", "The See Health Coverage Eligibility and Enroll in a Plan page is displayed", "The See Health Coverage Eligibility and Enroll in a Plan page is not displayed", driver, passScreenshot, currentIteration, error, browserFolder);
			driver.findElement(By.xpath("//div[@id='healthCoverageHixNavigatorScreen']/following::a[@class='btn btn-primary'][1]")).click();
			//
			Thread.sleep(10000);
			k1.waitForPageToLoad(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
			k1.verifyDisplayedPage(homePath, testCase, scenario, browser, objectId, "Results", "Results", "The Eligibility Determination Results page is displayed", "The Eligibility Determination Results page is not displayed", driver, passScreenshot, currentIteration, error, browserFolder);
			
			k1.assertElementPresent(homePath, testCase, scenario, browser, "xpath:.//*[@id='currentYearEligResults']/fieldset/div[3]/div[2]/p", "HIX_PARENT_QHP", dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
			
			k1.verifyElementPartialText(homePath, testCase, scenario, browser, "xpath:.//*[@id='currentYearEligResults']/fieldset/div[3]/div[2]/p", "HIX_PARENT_QHP", "Conditionally QHP Eligible", "Parent is QHP Conditionally Eligible", "Parent is NOT QHP Conditionally Eligible", driver, "Yes", currentIteration, error, browserFolder);
			k1.verifyElementPartialText(homePath, testCase, scenario, browser, "xpath:.//*[@id='currentYearEligResults']/fieldset/div[4]/div[2]/p", "HIX_PARENT_CSR", "CSR", "Parent is CSR Conditionally Eligible", "Parent is NOT CSR Conditionally Eligible", driver, passScreenshot, currentIteration, error, browserFolder);
			k1.verifyElementPartialText(homePath, testCase, scenario, browser, "xpath:.//*[@id='currentYearEligResults']/fieldset/div[5]/div[2]/p", "HIX_PARENT_APTC", "APTC", "Parent is APTC Conditionally Eligible", "Parent is NOT APTC Conditionally Eligible", driver, passScreenshot, currentIteration, error, browserFolder);
			
			k1.verifyElementPartialText(homePath, testCase, scenario, browser, "xpath:.//*[@id='currentYearEligResults']/fieldset/div[9]/div[2]/p", "HIX_CHILD_MA", "Medicaid Pending", "Child is MA Pending", "Child is NOT MA Pending", driver, passScreenshot, currentIteration, error, browserFolder);
			
			driver.findElement(By.xpath("//button[@id='common_twelve' and @value='Submit']")).click();

			try{
				Thread.sleep(5000);
			}catch(Exception e){
				
			}
			
			//k1.verifyDisplayedPage(homePath, testCase, scenario, browser, objectId, "Special Enrollment Eligibility", "Special Enrollment Eligibility", "The Special Enrollment Eligibility page is displayed", "The Special Enrollment Eligibility page is not displayed", driver, passScreenshot, currentIteration, error, browserFolder);
			driver.findElement(By.xpath("//a[@id='LogoutButton' and text()='Log Out']")).click();
			
		}
	}
	
	
	public void specialconditionsDate(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Calendar cal = Calendar.getInstance();
		Util utilObject = new Util();
		
		String mm = "", dd="", yyyy="";
		String overrideDBDate = "";
		overrideDBDate = utilObject.getValueFromConfigProperties("OverrideDBAppDate").trim();
		
		String value = "";
		if(overrideDBDate.equalsIgnoreCase("No")){
			try{
				homePath = new File (".").getCanonicalPath();
				File f = new File(homePath+"\\SupportLibraries\\ApplicationDate.ini");
				
				Ini a = new Ini();
				a.load(f);
				value = a.get("EnvironmentDetails", "ApplicationDate");
			}catch(Exception e){
				e.printStackTrace();
			}
				
			mm = value.split("-")[0].trim();
			dd = value.split("-")[1].trim();
			yyyy = value.split("-")[2].trim();
		}		
		
		if(overrideDBDate.equalsIgnoreCase("No")){
			mm = value.split("-")[0].trim();
			dd = value.split("-")[1].trim();
			yyyy = value.split("-")[2].trim();
		}else if(overrideDBDate.equalsIgnoreCase("Yes")){
			mm = utilObject.getValueFromConfigProperties("appDate").split("-")[0].trim();
			dd = utilObject.getValueFromConfigProperties("appDate").split("-")[1].trim();
			yyyy = utilObject.getValueFromConfigProperties("appDate").split("-")[2].trim();
		}
		
		/*cal.set(Calendar.YEAR, Integer.parseInt(yyyy));
		cal.set(Calendar.MONTH, Integer.parseInt(mm));
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dd));*/
		cal.set(Integer.parseInt(yyyy), Integer.parseInt(mm), Integer.parseInt(dd));
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		if(mm.equalsIgnoreCase("12")){
			cal.add(Calendar.MONTH, -2);
			utilObject.setDataINI("SEP_Date_PreviousMonth", "true", testCase, scenario);
		}else{
			cal.add(Calendar.MONTH, -1);
			utilObject.setDataINI("SEP_Date_PreviousMonth", "false", testCase, scenario);
		}
		
		String finalDate = sdf.format(cal.getTime());
		driver.findElement(By.xpath(".//*[@id='dateLastCoverage']")).clear();
		driver.findElement(By.xpath(".//*[@id='dateLastCoverage']")).sendKeys(finalDate);
	}
	
	
public void navigateToHIXURLChrome(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		
	String port ="";
	if (browser.equalsIgnoreCase("Chrome")) {
		// When Grid Setup is Yes in Config.properties file
		if (utilObject.getValueFromConfigProperties("GridSetup").equalsIgnoreCase("Yes")) {
			DesiredCapabilities caps = DesiredCapabilities.chrome();

			if (utilObject.getValueFromConfigProperties("ADA_TESTING_WAVE_TOOL").equalsIgnoreCase("Yes")) {
				ChromeOptions options = new ChromeOptions ();
				
				if(Global.gblAdaWaveValidations.isEmpty())
            Global.gblAdaWaveValidations=new LinkedHashMap<String, List<Integer>>();
            
				options.addExtensions (new File(".//lib//WAVE-Evaluation-Tool_v1.0.9.crx"));


				caps.setCapability(ChromeOptions.CAPABILITY, options);

				
			}
			
			caps.setJavascriptEnabled(true);

			if (port != "") {
				caps.setCapability("NodeName", port);
			}

			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.get(utilObject.getValueFromConfigProperties(utilObject.getValueFromConfigProperties("EnvironmentName")+"_HIX_URL"));
		} else {
			try {
				System.setProperty("webdriver.chrome.driver",
						new File(".").getCanonicalPath() + "\\lib\\chromedriver.exe");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ChromeOptions options = new ChromeOptions ();
			options.addExtensions (new File(".//lib//WAVE-Evaluation-Tool_v1.0.9.crx"));

			DesiredCapabilities caps = DesiredCapabilities.chrome();
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			// ChromeOptions options = new ChromeOptions();
			// options.addArguments("test-type");
			driver = new ChromeDriver(caps);
			driver.get(utilObject.getValueFromConfigProperties(utilObject.getValueFromConfigProperties("EnvironmentName")+"_HIX_URL"));
			Global.driver=driver;
		}

			
		
	}
		try {
			driver.navigate().to(utilObject.getValueFromConfigProperties(environment+"_HIX_URL"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Login(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws Exception{
		Report reportObject = new Report();
		Util utilObject = new Util();
		KeywordLibrary k1 = new KeywordLibrary();
		Thread.sleep(5000);
		String userName = "", password = "";
		driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
		Util util=new Util();
		k1.waitForXPath(homePath, testCase, scenario, browser, "xpath:.//*[@id='username']", "USERNAME", dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
		if(driver.findElements(By.id("username")).size()!=0){
			reportObject.Log("Verification of Login Page","The Login page is displayed", Status.PASS, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		
			if(driver.getCurrentUrl().startsWith("https://")){
				userName = util.getValueFromConfigProperties(util.getValueFromConfigProperties("EnvironmentName")+"_ET_USERNAME");
				password = util.getValueFromConfigProperties(util.getValueFromConfigProperties("EnvironmentName")+"_ET_PASSWORD");
			}else if(driver.getCurrentUrl().startsWith("http://")){
				userName =  util.getValueFromConfigProperties(util.getValueFromConfigProperties("EnvironmentName")+"_ET_USERNAME");
				password = util.getValueFromConfigProperties(util.getValueFromConfigProperties("EnvironmentName")+"_ET_PASSWORD");
				}
			
			driver.findElement(By.id("username")).clear();
			driver.findElement(By.id("username")).sendKeys(userName);
			driver.findElement(By.id("password")).click();
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.xpath(".//*[@id='loginButtonrow']/button")).click();
		}else{
			reportObject.Log("Verification of Login Page","The Login page is not displayed", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}		
	}
			
	public boolean switchWindow(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws IOException {
		Report reportObject = new Report();
		String title = dataValue;
	    String currentWindow = driver.getWindowHandle();
	    Set <String> availableWindows = driver.getWindowHandles();
	    if (!availableWindows.isEmpty()) {
		    for (String windowId : availableWindows) {
			    if (driver.switchTo().window(windowId).getTitle().equals(title)) {
			    	driver.switchTo().window(windowId);
			    	reportObject.Log("Switching Window", "Switched the control to the Window with the title "+title, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			    	return true;
			    }else {
			        driver.switchTo().window(currentWindow);
			       
			    }
		    }
	    }
	    reportObject.Log("Switching Window", "Did not find a window with the title "+title+". Hence, staying in the current window itself", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	    return false;
	    }
	
	/**
	 * Method Name: Logout
	 * Description: This method is to Logout of the Application
	 * Parameters: Nothing
	 * Return Type: Nothing
	 */
	public void Logout(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		Util utilObject = new Util();
		
			if(driver.findElement(By.xpath("//a[contains(text(),'Log Out') and @title='Log Out']")).isDisplayed()){
				reportObject.Log("Verification of Presence of Logout Link","The Log out link is displayed", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
				/*driver.findElement(By.xpath("//a[contains(text(),'Log Out') and @title='Log Out']")).click();
				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
				}
				driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);*/
				driver.navigate().to(utilObject.getValueFromConfigProperties("URL"));
				if(driver.findElements(By.id("username")).size()!=0){
					reportObject.Log("Verification of Login Page","The Login page is displayed", Status.PASS, driver, testCase, scenario, browser, passScreenshot, browserFolder);
				}
			}else{
				reportObject.Log("Verification of Presence of Logout Link","The Log out link is not displayed", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
	}
	
	public void selectPhoneType(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		
		try {
			Util util = new Util();
			if(browser.equalsIgnoreCase("Firefox")){
				try {
					driver.findElement(By.cssSelector("#new_row_PhoneType")).click();
					driver.findElement(By.cssSelector("#new_row_PhoneType")).sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(2000);
					driver.findElement(By.cssSelector("#new_row_PhoneType")).sendKeys(Keys.TAB);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				driver.findElement(By.cssSelector("#new_row_PhoneType")).click();
				Select selectBox = new Select(driver.findElement(By.cssSelector("#new_row_PhoneType")));
				selectBox.selectByVisibleText(util.getData("PhoneType", driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void selectAllMissingVerification(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		
		try {
			
			
			
			if(driver.findElements(By.xpath("//table[@id='grid0']/tbody/tr")).size()!=0){
				for(int i=0;i<(driver.findElements(By.xpath("//table[@id='grid0']/tbody/tr")).size()-1);i++){
					Thread.sleep(5000);
					driver.findElement(By.xpath("//table[@id='grid0']/tbody/tr["+(i+2)+"]/td[9]/input[@type='checkbox']/following-sibling::label")).click();
					driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void writeCaseNumber(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		
	Util utilObject = new Util();
	String applicationNum = "";
	String caseNumber = "";
		try {
			applicationNum = utilObject.getDataINI(utilObject.getData("SectionName", driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder), "APPLICATION_NUMBER");
			caseNumber = applicationNum.substring(1,applicationNum.length());
			utilObject.setDataINI("CASE_NUMBER", caseNumber, testCase, scenario);
			System.out.println("The case number is "+caseNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void writeHIX_AFB_Number(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		
		Util utilObject = new Util();
		String accountNumber = "";
		String RMCTrackID = "";
		String finalAccountNumber = "";
		String finalRMCTrackID = "";
		
		try {
			if((driver.getPageSource().contains("Your tracking number for this change report is "))){
				RMCTrackID = driver.findElement(By.xpath(".//*[@id='wrapper_1']/div[6]/div[1]/fieldset/div/div/p[1]")).getText();
				RMCTrackID = RMCTrackID.split("Your tracking number for this change report is ")[1];
				finalRMCTrackID = RMCTrackID.split("\\.")[0];
				utilObject.setDataINI("RMC_TRACK_ID", finalRMCTrackID, testCase, scenario);
			}else if ((driver.getPageSource().contains("Your tracking number for this application is "))){
				accountNumber = driver.findElement(By.xpath(".//*[@id='wrapper_1']/div[5]/div[2]/fieldset/div/div/p[1]")).getText();
				accountNumber = accountNumber.split("Your tracking number for this application is ")[1];
				finalAccountNumber = accountNumber.split("\\.")[0];
				utilObject.setDataINI("AFB_NUMBER", finalAccountNumber, testCase, scenario);
				utilObject.setDataINI("Case_NUMBER", finalAccountNumber.substring(1, finalAccountNumber.length()), testCase, scenario);
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	public void writeHIX_AccountNumber(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
	
	Util utilObject = new Util();
	String accountNumber = "";
	
		try {
			//accountNumber = driver.findElement(By.xpath(".//*[@id='Messagestab1']/div/div/b")).getText();
			accountNumber = driver.findElement(By.xpath("//section[@class='dashboard-new']//span[@id='panel-id']")).getText().trim().split(" ")[0].trim();
			if (accountNumber.contains("#")) {
				accountNumber = accountNumber.replaceAll("\\#", "");
				System.out.println("Account Number Captured as"+accountNumber);
			}
			//accountNumber = accountNumber.split(" :: ")[1];
			utilObject.setDataINI("ACCOUNT_NUMBER", accountNumber, testCase, scenario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}


public void TAM_ON_PA_To_IES_Check(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
	Util util = new Util();
	KeywordLibrary keyword = new KeywordLibrary();
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	//WebDriverWait wait = new WebDriverWait(driver, 180);
	
	try{
		if(!(driver.getPageSource().contains("Application Overview"))){
			driver.findElement(By.xpath("//a[@id='LogoutButton']/../following-sibling::li[2]/a")).click();
			
			Thread.sleep(3000);
			
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			keyword.waitForXPath(homePath, testCase, scenario, browser, "xpath://input[@id='username']", "Username", dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
			
			driver.findElement(By.xpath("//input[@id='username']")).clear();
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys(util.getDataINI(util.getData("SectionName", driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder), "HIX_USER_ID"));
			
			driver.findElement(By.xpath("//input[@id='password']")).click();
			driver.findElement(By.xpath("//input[@id='password']")).clear();
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Deloitte.1");
			
			driver.findElement(By.xpath("//a[text()='Login']")).click();
			Thread.sleep(3000);
			
			keyword.waitForXPath(homePath, testCase, scenario, browser, "xpath://a[@id='ContinueModal' and contains(text(),'Continue')]", "Continue Link", dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
			
			driver.findElement(By.xpath("//a[@id='ContinueModal' and contains(text(),'Continue')]")).click();
			
			keyword.waitForXPath(homePath, testCase, scenario, browser, "xpath://a[@id='NextButton' and contains(text(),'Next')]", "Next button in pop-up", dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
			
			driver.findElement(By.xpath("//a[@id='NextButton' and contains(text(),'Next')]")).click();
			Thread.sleep(3000);
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}catch(Exception e){
		
	}
}

	   public String getDate() {
		   
		   DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	       Calendar cal = Calendar.getInstance();
	       Date date = cal.getTime();
	       String todaysdate = dateFormat.format(date);
	       
		return todaysdate;
		   
	   }
	   
	/**
	
	//New method to connect to DB and capture the application date
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: captureAppDate
	 * Return Type: Nothing
	 * Description: This method connects to DB and fetches the application date from IES schema
	 * @throws Exception
	 */
	public void captureAppDate(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws Exception{
		String dateFromDB = "";
		String currentIESDate = "";
		
		Util utilObject = new Util();
		
		if(utilObject.getValueFromConfigProperties("OverrideDBAppDate").equalsIgnoreCase("No")){
			try{
				dateFromDB = utilObject.getDataFromDB("select sysdate from dual", utilObject.getDBParametersINI(utilObject.getValueFromConfigProperties("EnvironmentName"), "IES_SCHEMA"));
				dateFromDB = dateFromDB.split(" ")[0].trim();
				DateFormat formatter = null;
				formatter  = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				Date date = null;
				try {
					date = formatter.parse(dateFromDB);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				currentIESDate = (sdf.format(date)).toString();
			}catch(Exception e){
				try{
					dateFromDB = utilObject.getValueFromConfigProperties("appDate").trim();
				}catch(Exception e1){
					throw new Exception();
				}				
			}
			
			try{
				homePath = new File (".").getCanonicalPath();
				File f = new File(homePath+"\\SupportLibraries\\ApplicationDate.ini");
				
				Wini a = new Wini(f);
				a.load(f);
				
				a.put("EnvironmentDetails", "ApplicationDate", currentIESDate);
				a.store();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
	}
	
	
	

	public String getObjectIDFromRepository(String objectName, String module){
		
		String actualObjectID = "";
		try {
		
		Util  utilObject = new Util();
		
		String objectID = "";
		
		objectID = utilObject.getObjectFromObjectMap(objectName, module);
		String object_arr[]=objectID.split(":");
		if(object_arr.length>2){
			for(int z=1;z<object_arr.length;z++){
				if(z!=1){
					actualObjectID = actualObjectID+":"+object_arr[z].trim();
				}else if (z==1){
					actualObjectID = actualObjectID+object_arr[z].trim();
				}
			}
		}else{
			actualObjectID = object_arr[1].trim();
		}
		
		return actualObjectID;
	}
	catch(Exception e) {
		return actualObjectID;
	}
	}
	


	public void inboxNewDesign
	(
		String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws InterruptedException {
		KeywordLibrary k1 = new KeywordLibrary();
		Util utilObject = new Util();
		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		cap.setCapability("ignoreProtectedModeSettings",1);
		cap.setCapability("IntroduceInstabilityByIgnoringProtectedModeSettings",true);
		cap.setCapability("nativeEvents",true);
		cap.setCapability("browserFocus",true);
		cap.setCapability("ignoreZoomSetting", true);
		cap.setCapability("requireWindowFocus","true");
		cap.setCapability("INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS", true);
	    try {
            String parentHandle = driver.getWindowHandle();
            
            for (String winHandle : driver.getWindowHandles()) {

                  if (!parentHandle.equals(winHandle)) {

                         driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                         driver.switchTo().window(winHandle);
                        // System.out.println("These are the winhandles: "+winHandle);
                  break;
                  }
                  
            }
            Thread.sleep(5000);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);

     } catch (Exception e) {
            e.printStackTrace();
     }
  
	    try{
			if((driver.getPageSource().contains("Continue to this website (not recommended)."))){
				//List<WebElement> buttons = driver.findElements(By.xpath("//a[@id='overridelink']"));
				//if(buttons.size()!=0){
				//WebElement addbutton = buttons.get(0);
		        //addbutton.click();
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
		        
				}
			//}
			else if((driver.getPageSource().contains("This site is not secure"))){
				WebElement element = driver.findElement(By.xpath("//*[@id='moreInfoContainer']/a"));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				
				List<WebElement> buttons1 = driver.findElements(By.xpath("//a[@id='overridelink']"));
				if(buttons1.size()!=0){
					WebElement addbutton = buttons1.get(0);
		        addbutton.click();
				}
				}
		}catch(Exception e){
			
		} 
  
  try {
            driver.switchTo().alert().accept();
     } catch (Exception e) {
            //e.printStackTrace();
     }


	}
	
	public void navigateToHIXURL(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		
		Util utilObject = new Util();
		
		
		//driver.close();
				
		//ExecuteScripts e1 = new ExecuteScripts();
		
		//StandaloneDriver.driver = e1.SelectDriver(browser, "");
		//ExecuteScripts.driver = StandaloneDriver.driver;
		
		//driver = ExecuteScripts.driver;
		
		try {
			driver.navigate().to(utilObject.getValueFromConfigProperties(environment+"_HIX_URL"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void overrideSecurityException(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try{
			if((driver.getPageSource().contains("Continue to this website (not recommended)."))){
				List<WebElement> buttons = driver.findElements(By.xpath("//a[@id='overridelink']"));
				if(buttons.size()!=0){
					WebElement addbutton = buttons.get(0);
		        addbutton.click();
				}        
			} else if((driver.getPageSource().contains("This site is not secure"))){
				WebElement element = driver.findElement(By.xpath("//*[@id='moreInfoContainer']/a"));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				
				List<WebElement> buttons1 = driver.findElements(By.xpath("//a[@id='overridelink']"));
				if(buttons1.size()!=0){
					WebElement addbutton = buttons1.get(0);
		        addbutton.click();
				}
			}
		}catch(Exception e){
			
		}			
	}		
	
	public void reusableKeywords(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder,int iteration) throws InterruptedException {
	
		

		ExecuteScripts executeScripts=new ExecuteScripts();
		POI_ReadExcel poiObject = new POI_ReadExcel();
		Report reportObject = new Report();
		
		String dataPath = homePath+"\\ModuleSheets\\"+ExecuteScripts.module+".xlsx";
		
		List<String> whereClause2 = new ArrayList<String>();
		whereClause2.add("TestScript::"+testCase);
		
		Map<String, List<String>> result2 = poiObject.fetchWithCondition(dataPath, objectName, whereClause2);
		
		//Loop for iterating through each step of the test case/test script
		for(int k=0; k<result2.get("TestScript").size(); k++){
			
			if(testCase.equalsIgnoreCase(result2.get("TestScript").get(k))){
				
				if(driver!=null) {
					
				}else {
					driver = ExecuteScripts.driverHandle.get(browser);
				}
				
				//Storing the value of the column "Keyword" from the sheet "TestSteps"
				executeScripts.keyword = result2.get("Keyword").get(k);
				
				//Storing the value of the column "ObjectID" from the sheet "TestSteps"
				Util utilObject = new Util();
				try{
					objectName = result2.get("ObjectID").get(k);
					//objectID = utilObject.getObjectFromObjectMap(objectName, testCase);
					objectID = utilObject.getObjectFromObjectMap(objectName, ExecuteScripts.module);
				}catch(Exception e){
					// TODO Auto-generated catch block
				}
				
			
				//Storing the value of the column "KeyInData" from the sheet "TestSteps"
				try {
					//Directly storing the value present in the column "KeyInData" for current step (hard coded data)
					dataValue = result2.get("KeyInData").get(k);
					//When the value in column starts with text "getData=", then connecting with sheet "TestData" and reading the data in mentioned column and storing that data returned in the variable
					//When the value in column starts with text "getDataINI=", then connecting with OutputData.INI file and reading the data in mentioned Section and Key and storing that data returned in the variable
					
					
					if(dataValue.startsWith("getData=")){
						
						if(dataValue.contains("DYNAMICTEXT") && iteration!=-1) {
						
						dataValue=dataValue.replace("DYNAMICTEXT", iteration+"");
						dataValue = utilObject.getData(dataValue.split("getData=")[1],driver, ExecuteScripts.module, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder);
						
						
					}else
					dataValue = utilObject.getData(dataValue.split("getData=")[1],driver, ExecuteScripts.module, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder);
					
					}else if(dataValue.startsWith("getDataINI=")){
						String Testcase="";
						if(dataValue.contains("DYNAMICTEXT") && iteration!=-1) {
							
							dataValue=dataValue.replace("DYNAMICTEXT", iteration+"");
							String parameters = dataValue.split("getDataINI=")[1].trim();
							dataValue = utilObject.getDataINI(parameters.split(";")[0].trim(),parameters.split(";")[1].trim());
						}else
						{
						String parameters = dataValue.split("getDataINI=")[1].trim();
						if(parameters.split(";").length<2) {
							Testcase=scenario+"_"+testCase;
						}else {
						Testcase=parameters.split(";")[1].trim();
						}
						dataValue = utilObject.getDataINI(Testcase,parameters.split(";")[0].trim());
						}
						}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
				}
				
				//Storing the value of pass screenshot column
				try{
					passScreenshot = result2.get("PassScreenshot").get(k);
					if(passScreenshot == null){
						passScreenshot="";
					}
				}catch(Exception e){
					// TODO Auto-generated catch block
					
				}
				
				//Storing the value of OnPassLogMsg column
				try {
					onPassLog = result2.get("OnPassLogMsg").get(k);
					if(onPassLog==null){
						onPassLog = "";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					onPassLog = "";
				}
				
				//Storing the value of OnFailLogMsg column
				try {
					onFailLog = result2.get("OnFailLogMsg").get(k);
					if(onFailLog==null){
						onFailLog = "";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					onFailLog = "";
				}
			
				
				//Checking if the error flag is False or it has been set to True by previous steps. Current step executed only if error flag is false
				if(ExecuteScripts.error.get(ExecuteScripts.module+"_"+testCase)==false){
					//Checking if the keyword value does not end with "#". If a keyword has "#" at end, that keyword is skipped
					if(!executeScripts.keyword.endsWith("#")){
						//Checking if keyword starts with "function=". If it starts with "function=", then framework searches for matching method in ReusableFunctions.ReusableFunctions.java and invokes that method
						if(executeScripts.keyword.startsWith("function=")){
							methodName = executeScripts.keyword.split("function=")[1].trim();
							executeScripts.ExecuteMethod(homePath, testCase, ExecuteScripts.module, browser, objectID, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, ExecuteScripts.error.get(ExecuteScripts.module+"_"+testCase), browserFolder);
						}else if(executeScripts.keyword.startsWith("reusable=")){
							methodName = executeScripts.keyword.split("reusable=")[1].trim();
							executeScripts.ExecuteMethod(homePath, testCase, ExecuteScripts.module, browser, objectID, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, ExecuteScripts.error.get(ExecuteScripts.module+"_"+testCase), browserFolder);
						}
						else if(executeScripts.keyword.startsWith("method=")){
							methodName = executeScripts.keyword.split("method=")[1].trim();
							executeScripts.ExecuteApplicationMethod(homePath, testCase, ExecuteScripts.module, browser, objectID, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, ExecuteScripts.error.get(ExecuteScripts.module+"_"+testCase), browserFolder);
						}
						else{
							//If keyword does not start with "function=", then framework searches for matching method in SupportLibraries.KeywordLibrary.java and invokes that method
							executeScripts.ExecuteKeyword(homePath, testCase, ExecuteScripts.module, browser, objectID, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, ExecuteScripts.error.get(ExecuteScripts.module+"_"+testCase), browserFolder);
						}
					}
				}
			}									
		}
		
	}
	
	
	public void runKeywordsFromAnotherSheet(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws InterruptedException {
		
		String sheetname=objectName;
		
		String datavalue_arr[]=sheetname.split(";");
		
		if(datavalue_arr.length>1) {
			
			for(int i=1;i<=Integer.parseInt(datavalue_arr[1]);i++) {
				try {
				reusableKeywords(homePath, testCase, scenario, browser, objectId, datavalue_arr[0], dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder,i);
				}
				catch (Exception e) {
					System.out.println("Exception occurred :"+e.getMessage());
					// TODO: handle exception
				}
			}
		}
		else 
			System.out.println("Please mention iteration count for runkeywordsfromanothersheet");
	}
	






// *Method to call Soap API Services
	
	public void SoapWebServicesAPI(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws InterruptedException, IOException {
		{
			
			ExecuteScripts executeScripts=new ExecuteScripts();
			POI_ReadExcel poiObject = new POI_ReadExcel();
			Report reportObject = new Report();
			
			String dataPath = homePath+"\\ModuleSheets\\"+ExecuteScripts.module+".xlsx";
			
			List<String> whereClause2 = new ArrayList<String>();
			whereClause2.add("TestScript::"+testCase);
			
			Map<String, List<String>> result2 = poiObject.fetchWithCondition(dataPath, "TestData", whereClause2);
			 
			List<String> apiScenarios=result2.get("TestScript");
			
			for(int i=0;i<apiScenarios.size();i++) {
				  
		     String apiRequestType="";
		      
		     apiRequestType=result2.get("MethodType").get(i);
		     String endPoint = result2.get("EndPoint").get(i);
		     String SqlQuery = result2.get("SQLQUERY").get(i);
		     String requestPath = result2.get("RequestPath").get(i);
		     String responsePath = result2.get("ResponsePath").get(i);
		     String sampleResponsePath = result2.get("SampleResponse").get(i);
		     String pathparams=result2.get("PATHPARAM").get(i);
		     String paramtype=result2.get("PATHPARAMTYPE").get(i);
		     String paramvalue=result2.get("PATHPARAMVALUE").get(i);
		     int Responsecode=Integer.parseInt(result2.get("EXPECTEDRESPONSECODE").get(i));
		    
	
	          if(!apiRequestType.isEmpty()) {
	        	  
	        	  try {
					TriggerAPIRequest(apiRequestType, endPoint, requestPath,pathparams,paramtype,paramvalue,Responsecode,testCase);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	  
	        	  
	          }
				
				
				
				
			}
			
			
			System.out.println(result2);
			
		
		
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	}






	public void reEnterHIXSSN(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Util utilObject = new Util();
		driver.findElement(By.xpath(".//*[@id='reenterssn']")).sendKeys(utilObject.getDataINI(scenario+"_"+testCase, "HIX_SSN"));
	}

	public void enterHIXDOB(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Util utilObject = new Util();
		
		String dob="";
		try {
			dob = utilObject.getData("HIX_DOB_1_MM", driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder)+"/"+utilObject.getData("HIX_DOB_1_DD", driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder)+"/"+utilObject.getData("HIX_DOB_1_YYYY", driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(".//*[@id='fullDateOfBirth-masked']")).sendKeys(dob.split("/")[0]);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath(".//*[@id='fullDateOfBirth-masked']")).sendKeys(dob.split("/")[1]);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath(".//*[@id='fullDateOfBirth-masked']")).sendKeys(dob.split("/")[2]);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		utilObject.setDataINI("HIX_DOB", dob, testCase, scenario);
	}

	public void reEnterHIXDOB(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Util utilObject = new Util();
		String dob=utilObject.getDataINI(scenario+"_"+testCase, "HIX_DOB");
		//driver.findElement(By.xpath(".//*[@id='reEnterDOB-masked']")).sendKeys(utilObject.getDataINI(scenario+"_"+testCase, "HIX_DOB"));
		driver.findElement(By.xpath(".//*[@id='reEnterDOB-masked']")).sendKeys(dob.split("/")[0]);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath(".//*[@id='reEnterDOB-masked']")).sendKeys(dob.split("/")[1]);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath(".//*[@id='reEnterDOB-masked']")).sendKeys(dob.split("/")[2]);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enterHIXCHILDDOB(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Util utilObject = new Util();
		
		String dob="";
		try {
			dob = utilObject.getData("HIX_DOB_1_MM", driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder)+"/"+utilObject.getData("HIX_DOB_1_DD", driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder)+"/"+utilObject.getData("HIX_DOB_CHILD_YYYY", driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(".//*[@id='dob2']")).sendKeys(dob.split("/")[0]);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(".//*[@id='dob2']")).sendKeys(dob.split("/")[1]);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(".//*[@id='dob2']")).sendKeys(dob.split("/")[2]);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		utilObject.setDataINI("HIX_CHILD_DOB", dob, testCase, scenario);
	}

	public void reenterHIXCHILDDOB(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Util utilObject = new Util();
		String dob=utilObject.getDataINI(scenario+"_"+testCase, "HIX_CHILD_DOB");
		//driver.findElement(By.xpath(".//*[@id='reEnterDOB2-masked']")).sendKeys(utilObject.getDataINI(scenario+"_"+testCase, "HIX_CHILD_DOB"));
		driver.findElement(By.xpath(".//*[@id='reEnterDOB2']")).sendKeys(dob.split("/")[0]);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(".//*[@id='reEnterDOB2']")).sendKeys(dob.split("/")[1]);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(".//*[@id='reEnterDOB2']")).sendKeys(dob.split("/")[2]);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void enterHIXSSNforChild(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Util utilObject = new Util();
		String ssnFirstPart = "5";
		/*Random rand=new Random();
		int randomNum = rand.nextInt((99999999 - 1000000) + 1) + 10000000;*/
		Random rand=new Random();
		Report reportObject = new Report();
		String firstField = "";
		String MiddleField = "";
		String lastField = "";
		int random = rand.nextInt((99 - 10) + 1) + 10;
		firstField=String.valueOf(random);
		//System.out.println(firstField);
		
		random = rand.nextInt((99 - 10) + 1) + 10;
		MiddleField=String.valueOf(random);
		//System.out.println(MiddleField);
		
		random = rand.nextInt((9999 - 1000) + 1) + 1000;
		lastField=String.valueOf(random);
		//System.out.println(lastField);
		
		String completeSSN = "";
		completeSSN = ssnFirstPart+firstField+"-"+MiddleField+"-"+lastField;
		
		driver.findElement(By.xpath(".//*[@id='ssn2']")).sendKeys(completeSSN);
		utilObject.setDataINI("HIX_SSN_CHILD", completeSSN, testCase, scenario);
	}

	public void reEnterHIXSSNforChild(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Util utilObject = new Util();
		driver.findElement(By.xpath(".//*[@id='reenterssn2']")).sendKeys(utilObject.getDataINI(scenario+"_"+testCase, "HIX_SSN_CHILD"));
	}

	public void enterHIXSSN(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Util utilObject = new Util();
		String ssnFirstPart = "4";
		/*Random rand=new Random();
		int randomNum = rand.nextInt((99999999 - 1000000) + 1) + 10000000;*/
		Random rand=new Random();
		Report reportObject = new Report();
		String firstField = "";
		String MiddleField = "";
		String lastField = "";
		int random = rand.nextInt((99 - 10) + 1) + 10;
		firstField=String.valueOf(random);
		//System.out.println(firstField);
		
		random = rand.nextInt((99 - 10) + 1) + 10;
		MiddleField=String.valueOf(random);
		//System.out.println(MiddleField);
		
		random = rand.nextInt((9999 - 1000) + 1) + 1000;
		lastField=String.valueOf(random);
		//System.out.println(lastField);
		
		String completeSSN = "";
		completeSSN = ssnFirstPart+firstField+"-"+MiddleField+"-"+lastField;
		driver.findElement(By.xpath(".//*[@id='ssn']")).sendKeys(completeSSN);
		utilObject.setDataINI("HIX_SSN", completeSSN, testCase, scenario);
	}

	public void TriggerAPIRequest(String apiRequestType,String endPoint,  String requestPath,String pathparams,String paramkey,String paramvalue ,int Responsecode,String testcase) throws IOException {
		
		 String paramType = null;
	     String webresponse="";
		 if(apiRequestType.toUpperCase().equalsIgnoreCase("GET"))
           {
			 
			if(pathparams.equalsIgnoreCase("YES")) {
			
				given().
				contentType(ContentType.ANY).
				when().
				pathParams(paramkey, paramvalue);
			    Response response=	get(endPoint);
				System.out.println("Response body for the request :"+response.asString());
				webresponse=response.asString();
				int statuscode=response.getStatusCode();
				System.out.println("Status code Returned :"+statuscode);
				Assert.assertEquals(Responsecode, statuscode);

			}else {
			
				given().
				contentType(ContentType.ANY);
				Response response=	get(endPoint);
				webresponse=response.asString();
				System.out.println("Response body for the request :"+response.asString());
				int statuscode=response.getStatusCode();
				System.out.println("Status code Returned :"+statuscode);
				Assert.assertEquals(Responsecode, statuscode);
			}
			
			
			File file=new File(".//output//"+testcase+".txt");
			
			if(!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
	        BufferedWriter output = null;
	        try {
	         
	            output = new BufferedWriter(new FileWriter(file));
	            output.write(webresponse);
	        } catch ( IOException e ) {
	            e.printStackTrace();
	        } finally {
	          if ( output != null ) {
	            output.close();
	          }
	        }
	    
           }
		if(apiRequestType.toUpperCase().equalsIgnoreCase("POST"))
           {
			
			if(pathparams.equalsIgnoreCase("YES")) {
				
				String myRequest = KeywordLibrary.generateStringFromResource(requestPath);

				
				RequestSpecification  request= RestAssured.given();
				request.contentType(ContentType.ANY);
				request.body(myRequest);
				request.pathParams(paramkey, paramvalue);
			    Response response=  request.post(endPoint);
			    System.out.println("Response body for the request :"+response.asString());
				webresponse=response.asString();
				int statuscode=response.getStatusCode();
				System.out.println("Status code Returned :"+statuscode);
				Assert.assertEquals(Responsecode, statuscode); 
			}
			
			else {
           
			String myRequest = KeywordLibrary.generateStringFromResource(requestPath);

			
			RequestSpecification  request= RestAssured.given();
			request.contentType(ContentType.ANY);
			request.body(myRequest);
			
			 Response response=  request.post(endPoint);
			 System.out.println("Response body for the request :"+response.asString());
				webresponse=response.asString();
				int statuscode=response.getStatusCode();
				System.out.println("Status code Returned :"+statuscode);
				Assert.assertEquals(Responsecode, statuscode);
			 
			     
			}
			

			File file=new File(".//output//"+testcase+".txt");
			
			if(!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
	        BufferedWriter output = null;
	        try {
	         
	            output = new BufferedWriter(new FileWriter(file));
	            output.write(webresponse);
	        } catch ( IOException e ) {
	            e.printStackTrace();
	        } finally {
	          if ( output != null ) {
	            output.close();
	          }
	        }
           
           }
			
		
		if(apiRequestType.toUpperCase().equalsIgnoreCase("PATCH"))
        {
			
			if(pathparams.equalsIgnoreCase("YES")) {
				
				String myRequest = KeywordLibrary.generateStringFromResource(requestPath);

				
				RequestSpecification  request= RestAssured.given();
				request.contentType(ContentType.ANY);
				request.body(myRequest);
				request.pathParams(paramkey, paramvalue);
			    Response response=  request.patch(endPoint);
			    System.out.println("Response body for the request :"+response.asString());
				webresponse=response.asString();
				int statuscode=response.getStatusCode();
				System.out.println("Status code Returned :"+statuscode);
				Assert.assertEquals(Responsecode, statuscode); 
			}
			
			else {
        
			String myRequest = KeywordLibrary.generateStringFromResource(requestPath);

			
			RequestSpecification  request= RestAssured.given();
			request.contentType(ContentType.ANY);
			request.body(myRequest);
			
			 Response response=  request.post(endPoint);
			 System.out.println("Response body for the request :"+response.asString());
				webresponse=response.asString();
				int statuscode=response.getStatusCode();
				System.out.println("Status code Returned :"+statuscode);
				Assert.assertEquals(Responsecode, statuscode);
			 
			     
			}
			

			File file=new File(".//output//"+testcase+".txt");
			
			if(!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
	        BufferedWriter output = null;
	        try {
	         
	            output = new BufferedWriter(new FileWriter(file));
	            output.write(webresponse);
	        } catch ( IOException e ) {
	            e.printStackTrace();
	        } finally {
	          if ( output != null ) {
	            output.close();
	          }
	        }
        
        }
		
		if(apiRequestType.toUpperCase().equalsIgnoreCase("DELETE"))
        {
			
			if(pathparams.equalsIgnoreCase("YES")) {
				
				String myRequest = KeywordLibrary.generateStringFromResource(requestPath);

				
				RequestSpecification  request= RestAssured.given();
				request.contentType(ContentType.ANY);
				request.body(myRequest);
				request.pathParams(paramkey, paramvalue);
			    Response response=  request.patch(endPoint);
			    System.out.println("Response body for the request :"+response.asString());
				webresponse=response.asString();
				int statuscode=response.getStatusCode();
				System.out.println("Status code Returned :"+statuscode);
				Assert.assertEquals(Responsecode, statuscode); 
			}
			
			else {
        
			String myRequest = KeywordLibrary.generateStringFromResource(requestPath);

			
			RequestSpecification  request= RestAssured.given();
			request.contentType(ContentType.ANY);
			request.body(myRequest);
			
			 Response response=  request.delete(endPoint);
			 System.out.println("Response body for the request :"+response.asString());
				webresponse=response.asString();
				int statuscode=response.getStatusCode();
				System.out.println("Status code Returned :"+statuscode);
				Assert.assertEquals(Responsecode, statuscode);
			 
			     
			}
			

			File file=new File(".//output//"+testcase+".txt");
			
			if(!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
	        BufferedWriter output = null;
	        try {
	         
	            output = new BufferedWriter(new FileWriter(file));
	            output.write(webresponse);
	        } catch ( IOException e ) {
	            e.printStackTrace();
	        } finally {
	          if ( output != null ) {
	            output.close();
	          }
	        }
        
        }
	}
	
	
		
	
	public void runKeywordsFromGivenSheetIfGivenPageAppears(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws InterruptedException 
	{		
		    System.out.println("@runKeywordsFromGivenSheetIfGivenPageAppears():objectName:"+objectName);
		    System.out.println("@runKeywordsFromGivenSheetIfGivenPageAppears():dataValu):"+dataValue);	
			
			if((driver.getPageSource().contains(dataValue)))
			{
				System.out.println("@runKeywordsFromGivenSheetIfGivenPageAppears():driver.getPageSource().contains(dataValue):"+dataValue);
				reusableKeywords(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder,1);
			}
			else
			{
				System.out.println("@runKeywordsFromGivenSheetIfGivenPageAppears:Given page is not opened.Skipping this step!");
			}
	}
	}	
			
			
			
			
			
	

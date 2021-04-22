package SupportLibraries;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.DateFormatter;

import org.ini4j.Ini;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import CoreTestFunctions.ScenarioData;
import ReusableFunctions.ReusableFunctions;
import SupportLibraries.Report.Status;


public class ApplicationFunctions {

	public long timeout = 30000;
	static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static boolean magi_short = false;

	/**
	 * Description: This method is to Login to the customer portal (Uses data
	 * from CaseIndividual Object)
	 * 
	 * @author sakjaiswal
	 * @throws Exception
	 */
	public void continueCPLogin(String homePath, String testCase, String scenario, String browser, String objectId,
			String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
			String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws Exception {
		String userName = null;
		Util utilObject = new Util();
		Report reportObject = new Report();
		WebDriverWait wait = new WebDriverWait(driver, 20, 100);
		if (driver.getTitle().contains("HealthSource RI | My DashBoard")){
			driver.findElement(By.xpath(".//*[@id='ri-human-service-program']/button")).click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@for='continueradioContinueApp']"))).click();
			//driver.findElement(By.xpath(".//*[@for='continueradioContinueApp']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//a[@class='btn btn-primary internal-button']")).click();
			
		}
	
			
			} 
	


public void verifyEligibillityResults(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
	try {
		
	
		List<String> proglist =new ArrayList<String>();
		
		try {
			String[] data_arr=dataValue.split("_");
			
		     if(data_arr.length>1) {
		    	 Arrays.asList(data_arr);
		    	 
		     }else {
		    	 proglist.add(dataValue);
		     }
		}catch (Exception e) {
			proglist.add(dataValue);
			// TODO: handle exception
		}
		
	
		
		   By BywebElement =KeywordLibrary.findElement(driver, objectName, scenario);
           if(KeywordLibrary.webElementExplict(driver, BywebElement)) {
        	   
        	   List<WebElement> eligbillityTable=driver.findElements(BywebElement);
        	   
        	   for(int i=0;i<eligbillityTable.size();i++) {
        	   
        	   List<WebElement> eligibillityData= eligbillityTable.get(i).findElements(By.xpath("td[2]"));
        	   
        	   
        	   for(int j=0;j<eligibillityData.size();j++) {
        		  
        		   try {
        			  String programdata="";
        			  programdata= eligibillityData.get(j).getText();
        			  if(proglist.contains(programdata)) {
        				  System.out.println("Program :"+programdata+": Matched with Eligbillity Results");
        			  }else {
        				  System.out.println("Program :"+programdata+" : Not Matched with Eligbillity Results");
        			  }
        			  
        		   }catch (Exception e) {
					// TODO: handle exception
				}
        		   
        		   
        		   
        	   }
        	   
        	   
        	   }
           }
		
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	

}


/**
	 * Method Name: Login Description: This method is to Login to the
	 * Application Parameters: Nothing Return Type: Nothing
	 * 
	 * @throws Exception
	 */
	public void Login(
			String homePath, String testCase, String scenario, String browser, String objectId,
			String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
			String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
					throws Exception {
		// Code to be written for the Login functionality
		// System.out.println("Login has started");
		Report reportObject = new Report();
		Util utilObject = new Util();
		ReusableFunctions r1 = new ReusableFunctions();
		String userName = "", password = "";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		r1.captureAppDate(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog,
				driver, passScreenshot, currentIteration, error, browserFolder);

		try {
			if ((driver.getPageSource().contains("Continue to this website (not recommended)."))) {
				List<WebElement> buttons = driver.findElements(By.xpath("//a[@id='overridelink']"));
				if (buttons.size() != 0) {
					WebElement addbutton = buttons.get(0);
					addbutton.click();
				}
			}
		} catch (Exception e) {

		}

		// Verifying for the Username field in the Login page
		if (driver.findElements(By.xpath(getObjectIDFromRepository("USERNAME_TXT_FIELD", scenario))).size() != 0) {
			reportObject.Log("Verification of Login page", "The Login page is displayed", Status.pass, driver, testCase,
					scenario, browser, passScreenshot, browserFolder);

			if (driver.getCurrentUrl().startsWith("https://")) {
				userName = utilObject.getData("Username", driver, scenario, testCase, homePath, currentIteration,
						browser, passScreenshot, browserFolder);
				password = utilObject.getData("Password", driver, scenario, testCase, homePath, currentIteration,
						browser, passScreenshot, browserFolder);
			} else if (driver.getCurrentUrl().startsWith("http://")) {
				userName = utilObject.getData("TAM_OFF_Username", driver, scenario, testCase, homePath,
						currentIteration, browser, passScreenshot, browserFolder);
				password = utilObject.getData("Password", driver, scenario, testCase, homePath, currentIteration,
						browser, passScreenshot, browserFolder);
			}

			// Entering Username
			// driver.findElement(By.xpath(getObjectIDFromRepository("USERNAME_TXT_FIELD",scenario))).click();
			// driver.findElement(By.xpath(getObjectIDFromRepository("USERNAME_TXT_FIELD",scenario))).sendKeys(userName);

			getObjectIDAndFindElement(driver, "USERNAME_TXT_FIELD", scenario).clear();
			// getObjectIDAndFindElement(driver, "USERNAME_TXT_FIELD",
			// scenario).sendKeys("Santosh");
			getObjectIDAndFindElement(driver, "USERNAME_TXT_FIELD", scenario).sendKeys(userName);

			// Entering password
			driver.findElement(By.xpath(getObjectIDFromRepository("PASSWORD_TXT_FIELD", scenario))).click();
			driver.findElement(By.xpath(getObjectIDFromRepository("PASSWORD_TXT_FIELD", scenario))).sendKeys(password);

			// Clicking Login button
			driver.findElement(By.xpath(getObjectIDFromRepository("LOGIN_BTN_FIELD", scenario))).click();
			
			
			
			
		} else {
			reportObject.Log("Verification of Login page", "The Login page is not displayed", Status.Blocker, driver,
					testCase, scenario, browser, passScreenshot, browserFolder);
		}
	}

	/**
	 * Method Name: ApplicationRegistration Description: This method is to
	 * navigate to Register Application menu and enter all the mandatory fields
	 * in the first page of AR flow Parameters: Nothing Return Type: Nothing
	 * Modified By: Shubham Chauhan/09-29-2017
	 * 
	 * @throws Exception
	 */


	/**
	 * Method Name: setAttributeValue Description: This method is to override
	 * the attribute of an element Parameters: Nothing Return Type: Nothing
	 * 
	 * @throws Exception
	 */
	public void setAttributeValue(WebDriver driver, WebElement element, String dataValue) {
		String attributeName = dataValue.split("=")[0];
		String attributeValue = dataValue.split("=")[1];
		JavascriptExecutor js = (JavascriptExecutor) driver;

		String scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";

		js.executeScript(scriptSetAttrValue, element, attributeName, attributeValue);
	}

	
	/**
	 * Method Name: RegisterIndividual Description: This method is for adding an
	 * individual in AR flow Parameters: Nothing Return Type: Nothing
	 * 
	 * @throws Exception
	 */



	public void Logout(String homePath, String testCase, String scenario, String browser, String objectId,
			String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
			String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) {
		Report reportObject = new Report();
		Util utilObject = new Util();

		if (driver.findElement(By.xpath("//a[contains(text(),'Log Out') and @title='Log Out']")).isDisplayed()) {
			reportObject.Log("Verification of Presence of Logout Link", "The Log out link is displayed", Status.pass,
					driver, testCase, scenario, browser, passScreenshot, browserFolder);

			driver.navigate().to(utilObject.getValueFromConfigProperties("URL"));
			if (driver.findElements(By.id("username")).size() != 0) {
				reportObject.Log("Verification of Login Page", "The Login page is displayed", Status.PASS, driver,
						testCase, scenario, browser, passScreenshot, browserFolder);
			}
		} else {
			reportObject.Log("Verification of Presence of Logout Link", "The Log out link is not displayed",
					Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
	}

	
	/**
	 * Author: Santosh Kumar Anupati Method Name: getObjectIDFromRepository
	 * Return Type: String Description: This method reads the
	 * <Module>.properties file from Object Repository and gets the xpath based
	 * on Reference name
	 */
	public String getObjectIDFromRepository(String objectName, String module) {
		Util utilObject = new Util();
		String actualObjectID = "";
		String objectID = "";
		// Method to connect to properties file and gets the object id
		objectID = utilObject.getObjectFromObjectMap(objectName, module);

		actualObjectID = objectID.split(":", 2)[1].trim();

		return actualObjectID;
	}
	
	

	/**
	 * Author: Santosh Kumar Anupati Method Name: getObjectIDAndFindElement
	 * Return Type: WebElement Description: This method finds the element based
	 * on the type of identifier given in the object ID preceding the actual
	 * object ID and returns a Web element
	 */
	public WebElement getObjectIDAndFindElement(WebDriver driver, String objectName, String module) {
		Util utilObject = new Util();
		WebElement object = null;
		String identifier, actualObjectID = "";
		String objectID = "";

		objectID = utilObject.getObjectFromObjectMap(objectName, module);
		identifier = objectID.split(":")[0].trim();
		
		actualObjectID = objectID.split(":", 2)[1].trim();

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		// Actual identification of the element using web-driver based on the
		// type of identification
		if (identifier.equalsIgnoreCase("id")) {
			object = driver.findElement(By.id(actualObjectID));
		} else if (identifier.equalsIgnoreCase("xpath")) {
			object = driver.findElement(By.xpath(actualObjectID));
		} else if (identifier.equalsIgnoreCase("className")) {
			object = driver.findElement(By.className(actualObjectID));
		} else if (identifier.equalsIgnoreCase("name")) {
			object = driver.findElement(By.name(actualObjectID));
		} else if (identifier.equalsIgnoreCase("css")) {
			object = driver.findElement(By.cssSelector(actualObjectID));
		} else if (identifier.equalsIgnoreCase("linkText")) {
			object = driver.findElement(By.linkText(actualObjectID));
		} else if (identifier.equalsIgnoreCase("partialLinkText")) {
			object = driver.findElement(By.partialLinkText(actualObjectID));
		}
		return object;
	}

	/**
	 * Author: Santosh Kumar Anupati Method Name: getObjectIDAndFindElements
	 * Return Type: List of Web elements Description: This method finds the
	 * element based on the type of identifier given in the object ID preceding
	 * the actual object ID and returns a Web element
	 */
	public List<WebElement> getObjectIDAndFindElements(WebDriver driver, String objectName, String module) {
		Util utilObject = new Util();
		
		String identifier, actualObjectID = "";
		String objectID = "";
		List<WebElement> object = null;

		objectID = utilObject.getObjectFromObjectMap(objectName, module);
		identifier = objectID.split(":")[0].trim();
		
		actualObjectID = objectID.split(":", 2)[1].trim();

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		// Actual identification of the element using web-driver based on the
		// type of identification
		if (identifier.equalsIgnoreCase("id")) {
			object = driver.findElements(By.id(actualObjectID));
		} else if (identifier.equalsIgnoreCase("xpath")) {
			object = driver.findElements(By.xpath(actualObjectID));
		} else if (identifier.equalsIgnoreCase("className")) {
			object = driver.findElements(By.className(actualObjectID));
		} else if (identifier.equalsIgnoreCase("name")) {
			object = driver.findElements(By.name(actualObjectID));
		} else if (identifier.equalsIgnoreCase("css")) {
			object = driver.findElements(By.cssSelector(actualObjectID));
		} else if (identifier.equalsIgnoreCase("linkText")) {
			object = driver.findElements(By.linkText(actualObjectID));
		} else if (identifier.equalsIgnoreCase("partialLinkText")) {
			object = driver.findElements(By.partialLinkText(actualObjectID));
		}
		return object;
	}

	/**
	 * Author: Santosh Kumar Anupati Method Name: enter_Custom_CalendarDate
	 * Return Type: Nothing Description: This method enters the date specified
	 * in the date fields in the application
	 */
	public void enter_Custom_CalendarDate(String homePath, String testCase, String scenario, String browser,
			String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
			String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) {
		Report reportObject = new Report();
		Util utilObject = new Util();

		String mm = "", dd = "", yyyy = "";

		// Reading the Configuration file to read the OverrideDBAppDate which
		// specifies whether to override DB date or not
		String overrideDBDate = "";
		overrideDBDate = utilObject.getValueFromConfigProperties("OverrideDBAppDate").trim();

		String value = "";
		// If we specify not to override the DB connection,
		// then read the Application date that is stored in ApplicationDate.INI
		// file
		// Else, read the Application date that is present in Config.properties
		// file
		if (overrideDBDate.equalsIgnoreCase("No")) {
			try {
				homePath = new File(".").getCanonicalPath();
				File f = new File(homePath + "\\SupportLibraries\\ApplicationDate.ini");

				Ini a = new Ini();
				a.load(f);
				value = a.get("EnvironmentDetails", "ApplicationDate");
				mm = value.split("-")[0].trim();
				dd = value.split("-")[1].trim();
				yyyy = value.split("-")[2].trim();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			mm = utilObject.getValueFromConfigProperties("appDate").split("-")[0].trim();
			dd = utilObject.getValueFromConfigProperties("appDate").split("-")[1].trim();
			yyyy = utilObject.getValueFromConfigProperties("appDate").split("-")[2].trim();
		}

		// Boolean to compare current date and application date. If its a match,
		// then appDateMatchWithCurrentDate will be set to true
		Boolean appDateMatchWithCurrentDate = false;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

		if (overrideDBDate.equalsIgnoreCase("No")) {
			if (sdf.format(cal.getTime()).toString().equalsIgnoreCase(value)) {
				appDateMatchWithCurrentDate = true;
			} else {
				appDateMatchWithCurrentDate = false;
			}
		} else {
			if (sdf.format(cal.getTime()).toString()
					.equalsIgnoreCase(utilObject.getValueFromConfigProperties("appDate").trim())) {
				appDateMatchWithCurrentDate = true;
			} else {
				appDateMatchWithCurrentDate = false;
			}
		}

		String actualObjectID = "";
		actualObjectID = getObjectIDFromRepository(objectName, scenario);
		// If the objectName is CSCD_DATE or REPORT_DATE or
		// CLIENT_BECAME_AWARE_DATE or VERIFICATION_STATUS_DATE
		// then, only if current date is different from application date, then,
		// the dates are entered
		if (objectName.equalsIgnoreCase("CSCD_DATE") || objectName.equalsIgnoreCase("REPORT_DATE")
				|| objectName.equalsIgnoreCase("CLIENT_BECAME_AWARE_DATE")
				|| objectName.equalsIgnoreCase("VERIFICATION_STATUS_DATE")) {
			if (!appDateMatchWithCurrentDate) {
				driver.findElement(
						By.xpath(actualObjectID + "/preceding-sibling::input[@type='text' and contains(@id,'month')]"))
						.clear();
				driver.findElement(
						By.xpath(actualObjectID + "/preceding-sibling::input[@type='text' and contains(@id,'month')]"))
						.sendKeys(mm);

				driver.findElement(
						By.xpath(actualObjectID + "/preceding-sibling::input[@type='text' and contains(@id,'date')]"))
						.clear();
				driver.findElement(
						By.xpath(actualObjectID + "/preceding-sibling::input[@type='text' and contains(@id,'date')]"))
						.sendKeys(dd);

				driver.findElement(
						By.xpath(actualObjectID + "/preceding-sibling::input[@type='text' and contains(@id,'year')]"))
						.clear();
				driver.findElement(
						By.xpath(actualObjectID + "/preceding-sibling::input[@type='text' and contains(@id,'year')]"))
						.sendKeys(yyyy);
			}
		} else {
			driver.findElement(
					By.xpath(actualObjectID + "/preceding-sibling::input[@type='text' and contains(@id,'month')]"))
					.clear();
			driver.findElement(
					By.xpath(actualObjectID + "/preceding-sibling::input[@type='text' and contains(@id,'month')]"))
					.sendKeys(mm);

			driver.findElement(
					By.xpath(actualObjectID + "/preceding-sibling::input[@type='text' and contains(@id,'date')]"))
					.clear();
			driver.findElement(
					By.xpath(actualObjectID + "/preceding-sibling::input[@type='text' and contains(@id,'date')]"))
					.sendKeys(dd);

			driver.findElement(
					By.xpath(actualObjectID + "/preceding-sibling::input[@type='text' and contains(@id,'year')]"))
					.clear();
			driver.findElement(
					By.xpath(actualObjectID + "/preceding-sibling::input[@type='text' and contains(@id,'year')]"))
					.sendKeys(yyyy);

			reportObject.Log("Entering the custom date",
					"Entered the date in calendar " + objectName + " as " + mm + "-" + dd + "-" + yyyy, Status.DONE,
					driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
	}

	
	/**
	 * Author: Santosh Kumar Anupati Method Name:
	 * generateRandomStringAndEnterText Return Type: Nothing Description: This
	 * method generates a random string of the specified length and enters the
	 * random string in the input field
	 */
	public String generateRandomStringAndEnterText(String homePath, String testCase, String scenario, String browser,
			String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
			String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) {
		Report reportObject = new Report();
		// Code to generate a random string of specified length
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < Integer.parseInt(dataValue); i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}

		// Entering the random generated string in the input field
		getObjectIDAndFindElement(driver, objectName, scenario).clear();
		getObjectIDAndFindElement(driver, objectName, scenario).sendKeys(randStr);
		reportObject.Log("Entering random generated text in the text box " + objectName,
				"Entered the random text " + randStr, Status.DONE, driver, testCase, scenario, browser, passScreenshot,
				browserFolder);

		return randStr.toString();

	}

	/**
	 * Author: Santosh Kumar Anupati Method Name: getRandomNumber Return Type:
	 * Nothing Description: This method generates a random number of the
	 * specified length and enters the random number in the input field
	 */
	private int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		// Generating the random number
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	/**
	 * Author: Santosh Kumar Anupati Method Name: WriteDataToOutputFile Return
	 * Type: Nothing Description: This method writes data to Output INI file
	 */
	public void WriteDataToOutputFile(String homePath, String testCase, String scenario, String browser,
			String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
			String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) {
		KeywordLibrary k1 = new KeywordLibrary();
		String columnValue = dataValue;
		String data = columnValue.split(";")[0].trim();
		String key = columnValue.split(";")[1].trim();
		Util utilObject = new Util();

		if (data.startsWith("getData=")) {
			try {
				data = utilObject.getData(data.split("getData=")[1].trim(), driver, scenario, testCase, homePath,
						currentIteration, browser, passScreenshot, browserFolder);
			} catch (Exception e) {
				e.printStackTrace();
			}
			utilObject.setDataINI(key, data, testCase, scenario);
		} else if (data.startsWith("ObjectText")) {
			try {
				data = k1.findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId,
						browser, passScreenshot, browserFolder).getText().toString();
				System.out.println("The app no" + data);
			} catch (Exception e) {
				e.printStackTrace();
			}
			utilObject.setDataINI(key, data, testCase, scenario);
		} else {
			utilObject.setDataINI(key, data, testCase, scenario);
		}
	}

	/**
	 * Method Name: genRandom Description: This method returns a 4 digit random
	 * number Parameters: Nothing Return Type: String
	 * 
	 * @throws Exception
	 */
	public String genRandom(String homePath, String testCase, String scenario, String browser, String objectId,
			String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
			String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws Exception {
		Random rand = new Random();
		Report reportObject = new Report();
		int randomNum = rand.nextInt((9999 - 1000) + 1) + 1000;
		dataValue = String.valueOf(randomNum);

		KeywordLibrary k1 = new KeywordLibrary();
		k1.findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser,
				passScreenshot, browserFolder).clear();
		k1.findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser,
				passScreenshot, browserFolder).sendKeys(dataValue);

		reportObject.Log("Entering text in the text box " + objectName, "Entered the text " + dataValue, Status.DONE,
				driver, testCase, scenario, browser, passScreenshot, browserFolder);
		return dataValue;
	}
	


	



	
	public void selectDataText(WebDriver  driver,String elementId, String dataValue, String scenarioData) throws Exception {
		Select selectField = new Select(
				getObjectIDAndFindElement(driver, elementId, scenarioData));
		selectField.selectByVisibleText(dataValue);
	}
	public void closePopUpWindow(WebDriver driver, String parentHandle) {
		try {

			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}

			String winHandle = driver.getWindowHandle();
			if (!parentHandle.equals(winHandle)) {
				driver.close();
			}
			// driver.close();
			driver.switchTo().window(parentHandle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static Date getSunday(Date today) {
        Calendar cal = Calendar.getInstance();
        
        cal.setTime(today);

        int dow = cal.get(Calendar.DAY_OF_WEEK);
        
        while (dow != Calendar.SUNDAY) {
            int date = cal.get(Calendar.DATE);
            
            if (date == 1) {
                int month = cal.get(Calendar.MONTH);
                
                if (month == Calendar.JANUARY) {
                    month = Calendar.DECEMBER;
                    
                    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
                } else {
                    month--;
                }
                
                cal.set(Calendar.MONTH, month);
                
                date = getMonthLastDate(month, cal.get(Calendar.YEAR));
            } else {
                date--;
            }
            
            cal.set(Calendar.DATE, date);
            
            dow = cal.get(Calendar.DAY_OF_WEEK);
        }
        
        return cal.getTime();
    }
	
	public static Date getSaturday(Date today) {
        Calendar cal = Calendar.getInstance();
        
        cal.setTime(today);

        int dow = cal.get(Calendar.DAY_OF_WEEK);
        
        while (dow != Calendar.SATURDAY) {
            int date = cal.get(Calendar.DATE);
            
            if (date == 1) {
                int month = cal.get(Calendar.MONTH);
                
                if (month == Calendar.JANUARY) {
                    month = Calendar.DECEMBER;
                    
                    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
                } else {
                    month--;
                }
                
                cal.set(Calendar.MONTH, month);
                
                date = getMonthLastDate(month, cal.get(Calendar.YEAR));
            } else {
                date--;
            }
            
            cal.set(Calendar.DATE, date);
            
            dow = cal.get(Calendar.DAY_OF_WEEK);
        }
        
        return cal.getTime();
    }
	
    private static int getMonthLastDate(int month, int year) {
        switch (month) {
            case Calendar.JANUARY:  
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.OCTOBER:
            case Calendar.DECEMBER:
                return 31;

            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                return 30;
                
            default:    //  Calendar.FEBRUARY
                return year % 4 == 0 ? 29 : 28;
        }
    }
    
    
    

	public WebElement findElementByType(WebDriver driver, String scenario, String testCase, String homePath, int currentIteration, String objectID, String browser, String passScreenshot, String browserFolder)
	{
		Util utilObject = new Util();
		WebElement object = null;
		String identifier,actualObjectID = "";
		identifier = objectID.split(":")[0].trim();
		/*if(objectID.split(":").length>2){
			for(int z=1;z<(objectID.split(":").length);z++){
				if(z!=1){
					actualObjectID = actualObjectID+":"+objectID.split(":")[z].trim();
				}else if (z==1){
					actualObjectID = actualObjectID+objectID.split(":")[z].trim();
				}
			}
		}else{
			actualObjectID = objectID.split(":")[1].trim();
		}*/
		actualObjectID = objectID.split(":",2)[1].trim();
				
		if(actualObjectID.contains("#getData=")){
			String[] splittedString = actualObjectID.split("#");
			String finalObjectID = "";
			for(int i=0;i<splittedString.length;i++){
				if(splittedString[i].startsWith("getData=")){
					try {
						splittedString[i] = utilObject.getData((splittedString[i]).split("getData=")[1], driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				finalObjectID = finalObjectID+splittedString[i];
			}
			actualObjectID = finalObjectID;
		}
		
		if(identifier.equalsIgnoreCase("id")){
			object = driver.findElement(By.id(actualObjectID));
		}else if(identifier.equalsIgnoreCase("xpath")){
			object = driver.findElement(By.xpath(actualObjectID));
		}else if(identifier.equalsIgnoreCase("className")){
			object = driver.findElement(By.className(actualObjectID));
		}else if(identifier.equalsIgnoreCase("name")){
			object = driver.findElement(By.name(actualObjectID));
		}else if(identifier.equalsIgnoreCase("css")){
			object = driver.findElement(By.cssSelector(actualObjectID));
		}else if(identifier.equalsIgnoreCase("linkText")){
			object = driver.findElement(By.linkText(actualObjectID));
		}else if(identifier.equalsIgnoreCase("partialLinkText")){
			object = driver.findElement(By.partialLinkText(actualObjectID));
		}
		return object;
	}
	
	public void Login_ASPEN( String homePath, String testCase, String scenario, String browser, String objectId,
            String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
            String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
                                         throws Exception {
ReusableFunctions r1 = new ReusableFunctions();

// Code to be written for the Login functionality
// System.out.println("Login has started");
Report reportObject = new Report();
Util utilObject = new Util();

String userName = "", password = "";
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

r1.captureAppDate( homePath,  testCase,  scenario,  browser,  objectId,  objectName,  dataValue,  onPassLog,  onFailLog,  driver,  passScreenshot,  currentIteration,  error,  browserFolder) ;
try {
            if ((driver.getPageSource().contains("Continue to this website (not recommended)."))) {
                           List<WebElement> buttons = driver.findElements(By.xpath("//a[@id='overridelink']"));
                           if (buttons.size() != 0) {
                                         WebElement addbutton = buttons.get(0);
                                         addbutton.click();
                           }
            }
} catch (Exception e) {

}

// Verifying for the Username field in the Login page
if (driver.findElements(By.xpath(getObjectIDFromRepository("USERNAME_TXT_FIELD", scenario))).size() != 0) {
            reportObject.Log("Verification of Login page", "The Login page is displayed", Status.pass, driver, testCase,
                                         scenario, browser, passScreenshot, browserFolder);

            if (driver.getCurrentUrl().startsWith("https://")) {
                           userName = utilObject.getData("Username", driver, scenario, testCase, homePath, currentIteration,
                                                       browser, passScreenshot, browserFolder);
                           password = utilObject.getData("Password", driver, scenario, testCase, homePath, currentIteration,
                                                       browser, passScreenshot, browserFolder);
            } else if (driver.getCurrentUrl().startsWith("http://")) {
                           userName = utilObject.getData("TAM_OFF_Username", driver, scenario, testCase, homePath,
                                                       currentIteration, browser, passScreenshot, browserFolder);
                           password = utilObject.getData("Password", driver, scenario, testCase, homePath, currentIteration,
                                                       browser, passScreenshot, browserFolder);
            }

            // Entering Username
            // driver.findElement(By.xpath(getObjectIDFromRepository("USERNAME_TXT_FIELD",scenario))).click();
            // driver.findElement(By.xpath(getObjectIDFromRepository("USERNAME_TXT_FIELD",scenario))).sendKeys(userName);

            getObjectIDAndFindElement(driver, "USERNAME_TXT_FIELD", scenario).clear();
            // getObjectIDAndFindElement(driver, "USERNAME_TXT_FIELD",
            // scenario).sendKeys("Santosh");
            getObjectIDAndFindElement(driver, "USERNAME_TXT_FIELD", scenario).sendKeys(userName);

            // Entering password
driver.findElement(By.xpath(getObjectIDFromRepository("PASSWORD_TXT_FIELD", scenario))).click();
driver.findElement(By.xpath(getObjectIDFromRepository("PASSWORD_TXT_FIELD", scenario))).sendKeys(password);

            // Clicking Login button
driver.findElement(By.xpath(getObjectIDFromRepository("LOGIN_BTN_FIELD", scenario))).click();
            
            
            
            
} else {
            reportObject.Log("Verification of Login page", "The Login page is not displayed", Status.Blocker, driver,
                                         testCase, scenario, browser, passScreenshot, browserFolder);
}
//To validate session error message.
String errorMessage           = "Your previous session did not end gracefully. Please login again.";
String errorMessageFromScreen = "";                   
String errorMessageXpath      = utilObject.getObjectFromObjectMap("LOGIN_SESSION_ERROR_XPATH",scenario);
if(errorMessageXpath != null)
{                           
errorMessageFromScreen        = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), errorMessageXpath, browser, passScreenshot, browserFolder).getText().trim();
System.out.println("#errorMessage:"+errorMessage);
System.out.println("#errorMessageFromScreen:"+errorMessageFromScreen);
if(errorMessage.equalsIgnoreCase(errorMessageFromScreen))
{
            Login(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
}                           
}
//session error message changes end 

}


}

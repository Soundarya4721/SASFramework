package SupportLibraries;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
/*import java.time.LocalDate;
import java.time.format.DateTimeFormatter;*/
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

import com.jayway.restassured.path.xml.XmlPath;

import ReusableFunctions.ReusableFunctions;

import org.openqa.selenium.Alert;


import SupportLibraries.Report.Status;
import cucumber.api.java.en_old.Ac;
import global.ExcelRead;
import global.Global;
import io.restassured.response.Response;

public class KeywordLibrary{
	
	public long timeout = 0;
	static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static final String CHAR_NUM_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	
	public static Map<String, List<String>> finalmap;
	
	public static void switchToLastWindow
	(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
    {
		try {
			
			if(WaitForPageLoad(driver)) {
                    
				Set<String> winhandles=driver.getWindowHandles();
				String lastwindow="";
				
				for(String winhandle: winhandles) {
					lastwindow=winhandle;
				}
						driver.switchTo().window(lastwindow);
						  
						if(driver.getTitle().contains("This site isn�t secure")) {
		                	   driver.navigate().to("javascript:document.getElementById('overridelink').click()"); 
		                   }
						
						
			}
					
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void switchToLastWindowcustom
	(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
    {
		try {
			
			if(WaitForPageLoad(driver)) {
                    
				Set<String> winhandles=driver.getWindowHandles();
				String lastwindow="";
				
				for(String winhandle: winhandles) {
					lastwindow=winhandle;
				}
						driver.switchTo().window(lastwindow);
						  
						if(driver.getTitle().contains("This site isn�t secure")) {
		                	   driver.navigate().to("javascript:document.getElementById('overridelink').click()"); 
		                   }
						
						
			}
					
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void switchToOtherWindow(WebDriver driver,String parentwindow) {
		try {
			
			if(WaitForPageLoad(driver)) {
                    
				Set<String> winhandles=driver.getWindowHandles();
				
				
				for(String winhandle: winhandles) {
					
					if(! winhandle.equalsIgnoreCase(parentwindow) ){
					
						driver.switchTo().window(winhandle);
						  if(driver.getTitle().contains("This site isn�t secure")) {
		                	   driver.navigate().to("javascript:document.getElementById('overridelink').click()"); 
		                   }
						
						
			}
					
			}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public void hixInsuranceScreen(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		List<WebElement> coveragedetails = driver.findElements(By.xpath("//label[text()='No']"));
		for(int i=0;i< coveragedetails.size();i++){
			coveragedetails.get(i).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: findElementByType
	 * Return Type: WebElement
	 * Description: This method finds the element based on the type of identifier given in the object ID preceding the actual object ID and returns a Web element
	 */
	
		public WebElement findElementByType(WebDriver driver, String scenario, String testCase, String homePath, int currentIteration, String objectID, String browser, String passScreenshot, String browserFolder){
		
		//	System.out.println("======");
			
			Util utilObject = new Util();
		WebElement object = null;
		String identifier,actualObjectID = "";
		
		identifier = objectID.split(":")[0].trim();
		
		
		String objectID_finalarr[]=objectID.split(":");
		
		if(objectID_finalarr.length>2){
			for(int z=1;z<objectID_finalarr.length;z++){
				if(z!=1){
					actualObjectID = actualObjectID+":"+objectID_finalarr[z].trim();
				}else if (z==1){
					actualObjectID = actualObjectID+objectID_finalarr[z].trim();
				}
			}
		}else{
			actualObjectID = objectID_finalarr[1].trim();
		}
				
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
		
		//System.out.println("Actual"+actualObjectID);
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
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: findElementsByType
	 * Return Type: List WebElement
	 * Description: This method finds the elements based on the type of identifier given in the object ID preceding the actual object ID and returns a list of Web element
	 */
	public static List findElementsByType
	(WebDriver driver, String scenario, String testCase, String homePath, int currentIteration, String objectID, String browser, String passScreenshot, String browserFolder){
		Util utilObject = new Util();
		List<WebElement> object = null;
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
			object = driver.findElements(By.id(actualObjectID));
		}else if(identifier.equalsIgnoreCase("xpath")){
			object = driver.findElements(By.xpath(actualObjectID));
		}else if(identifier.equalsIgnoreCase("className")){
			object = driver.findElements(By.className(actualObjectID));
		}else if(identifier.equalsIgnoreCase("name")){
			object = driver.findElements(By.name(actualObjectID));
		}else if(identifier.equalsIgnoreCase("css")){
			object = driver.findElements(By.cssSelector(actualObjectID));
		}else if(identifier.equalsIgnoreCase("linkText")){
			object = driver.findElements(By.linkText(actualObjectID));
		}else if(identifier.equalsIgnoreCase("partialLinkText")){
			object = driver.findElements(By.partialLinkText(actualObjectID));
		}
		return object;
	}

	public static boolean WaitForPageLoad(WebDriver driver) {
		boolean webelementcheck=false;
		try {
			
			ExpectedCondition<Boolean> expectation = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equalsIgnoreCase("complete");
	                    }};
	      
	            WebDriverWait wait =new WebDriverWait(driver, 200);
	           if(wait.until(expectation)) {
				webelementcheck=true;
				return webelementcheck;
				}else return webelementcheck;
			}
			catch(Exception e) {
			return webelementcheck;
		}
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: enter_text
	 * Return Type: Nothing
	 * Description: This method enters the text specified in KeyInData column of Test case
	 */
	public void enter_text(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);
		
		reportObject.Log("Entering text in the text box "+objectName, "Entered the text "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: enter_text
	 * Return Type: Nothing
	 * Description: This method enters the text specified in KeyInData column of Test case
	 */
	public void send_text(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);
		
		
			
			
			reportObject.Log("Entering text in the text box "+objectName, "Entered the text "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	
	/**
	 * Author: Jitin Prakash Kulshreshta
	 * Method Name: confirm_Message
	 * Return Type: Nothing
	 * Description: This method enters the text specified in KeyInData column of Test case
	 */
	public void confirm_Message(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		try{
			Report reportObject = new Report();
			Alert alert = driver.switchTo().alert();
			alert.accept();
			reportObject.Log("Confirm Delete Message "+objectName, "Delete Message Confirmed ", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}catch(Exception e){
			
		}
		
	}
	
	/**
	 * Author: Jitin Prakash Kulshreshta
	 * Method Name: confirm_Message
	 * Return Type: Nothing
	 * Description: This method enters the text specified in KeyInData column of Test case
	 */
	public void cancel_Message(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		try{
			Report reportObject = new Report();
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			reportObject.Log("Cancel Alert Message "+objectName, "Alert Message Cancelled", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}catch(Exception e){
			
		}
		
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: selectByVisibleText
	 * Return Type: Nothing
	 * Description: This method selects the visible text specified in KeyInData column of Test case in a select box
	 */
	public void selectByVisibleText(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		Select selectBox = new Select(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder));
		selectBox.selectByVisibleText(dataValue);
		reportObject.Log("Selecting by visible text in the dropdown "+objectName, "Selected the value "+dataValue+" in the select box ", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: selectByIndex
	 * Return Type: Nothing
	 * Description: This method selects the visible text specified in KeyInData column of Test case in a select box
	 */
	public void selectByIndex(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		System.out.println("Selecting value from drp");
		Select selectBox = new Select(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder));
		selectBox.selectByIndex((int)Float.parseFloat(dataValue));
		reportObject.Log("Selecting by index in the dropdown "+objectName, "Selected the index "+(int)Float.parseFloat(dataValue)+" in the select box", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: selectByValue
	 * Return Type: Nothing
	 * Description: This method selects the visible text specified in KeyInData column of Test case in a select box
	 */
	public  void selectByValue(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		Select selectBox = new Select(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder));
		selectBox.selectByValue(dataValue);
		reportObject.Log("Selecting by value in the dropdown "+objectName, "Selected the value "+dataValue+" in the select box", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: selectByoption
	 * Return Type: Nothing
	 * Description: This method clicks the option specified in KeyInData column of Test case in a select box
	 */
	public  void selectByoption(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		Select selectBox = new Select(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder));
		
		List<WebElement> options = selectBox.getOptions();
		for (WebElement option : options) {
		    if (option.getText().toString().equalsIgnoreCase(dataValue)) {
		    	option.click();
		    }
		}
		reportObject.Log("Selecting by option in the dropdown "+objectName, "Selected the option "+dataValue+" in the select box", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: click
	 * Return Type: Nothing
	 * Description: This method clicks on an object specified in the Object Repository and referred in the Object ID column
	 */
	public  void click(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
	
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).click();
	
		Report reportObject = new Report();
	
		reportObject.Log("Clicking on the Element "+objectName, "Clicked on the object", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		/*
		 * System.out.println("overriden method");
		 */
	}
	
	/**
	 * Author: Jitin Prakash Kulshreshta
	 * Method Name: sendkey
	 * Return Type: Nothing
	 * Description: This method clicks on an object specified in the Object Repository and referred in the Object ID column
	 */
	public void sendkey(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys("");
		Report reportObject = new Report();
		reportObject.Log("Clicking on the Element "+objectName, "Clicked on the object", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: doubleClick
	 * Return Type: Nothing
	 * Description: This method double clicks on an object specified in the Object Repository and referred in the Object ID column
	 */
	public void doubleClick(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Actions ac1 = new Actions(driver);
		ac1.doubleClick(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder)).perform();
		Report reportObject = new Report();
		reportObject.Log("Double Clicking on the Element "+objectName, "Double clicked on the object", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: clickByLinkText
	 * Return Type: Nothing
	 * Description: This method clicks on an object using the text of the link in the entire page
	 */
	public void clickByLinkText(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		driver.findElement(By.linkText(dataValue)).click();
		reportObject.Log("Clicking on the link with text "+dataValue, "Clicked on a link having text "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: waitForPageToLoad
	 * Return Type: Nothing
	 * Description: This method waits for the page to load by waiting for an implicit wait period
	 */
	public void waitForPageToLoad(String homePath, String testCase, String scenario, String browser,String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
try {
			
			ExpectedCondition<Boolean> expectation = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equalsIgnoreCase("complete");
	                    }};
	      
	            WebDriverWait wait =new WebDriverWait(driver, 200);
	           if(wait.until(expectation)) {
				
				}
			}
			catch(Exception e) {
			
		}
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: waitForXPath
	 * Return Type: Nothing
	 * Description: This method waits for the object to appear in the application by waiting for a certain period of time
	 */
	public void waitForXPath(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		try {
			Thread.sleep(1000);
			int count=1;
			while(findElementsByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).size()==0){
				count++;
				Thread.sleep(2000);
				if(count>3)
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: waitForLinkText
	 * Return Type: Nothing
	 * Description: This method waits for the link with the given text to appear
	 */
	public void waitForLinkText(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		try {
			int count=1;
			while(driver.findElements(By.linkText(dataValue)).size()==0){
				count++;
				Thread.sleep(2000);
				if(count==2)
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: delay
	 * Return Type: Nothings
	 * Description: This method inserts a sleep time of timeout value specified in the WebDriverHelper class
	 */
	public void delay(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		try {
			Thread.sleep(Long.parseLong(Integer.toString(((int)Float.parseFloat(dataValue)))));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Author: Gurdeep Arora
	 * Method Name: mouse_over
	 * Return Type: Nothing
	 * Description: Method to perform Mouse Hover action
	 */
	public void mouse_over(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		
		Report reportObject = new Report();
		Actions action1= new Actions(driver);
		action1.moveToElement(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder)).build().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reportObject.Log("Performing Mouse Hover action  "+objectName, "Mouse Hover Action done "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: WriteDataToOutputFile
	 * Return Type: Nothing
	 * Description: This method writes data to Output INI file
	 */	
	public void WriteDataToOutputFile(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		String columnValue = dataValue;
		String data = columnValue.split(";")[0].trim();
		String key = columnValue.split(";")[1].trim();
		Util utilObject = new Util();
		
		if(data.startsWith("getData=")){
			try {
				data = utilObject.getData(data.split("getData=")[1].trim(), driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			utilObject.setDataINI(key, data, testCase, scenario);
		}else if(data.startsWith("ObjectText")){
			try {
				data = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).getText().toString();
			System.out.println("The application number is " +data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			utilObject.setDataINI(key, data, testCase, scenario);
		} else{
			utilObject.setDataINI(key, data, testCase, scenario);
		}		
	}
	
	/**
	 * Author: Deepika Agnihotri
	 * Method Name: highlight
	 * Return Type: Nothing
	 * Description: This method highlight an object specified in the Object Repository and referred in the Object ID column
	 */
	public void highlight(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws InterruptedException{
		
	//Creating JavaScriptExecuter Interface
      JavascriptExecutor js = (JavascriptExecutor)driver;
      
      // Starting loop to highlight and then de-highlight the web element
	   for (int iCnt = 0; iCnt < 2; iCnt++) {
		   //Execute Javascript
		   if(iCnt == 0){
			   js.executeScript("arguments[0].setAttribute('style', arguments[1]);", findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder), "color: yellow; border: 10px solid yellow;");
			   Report reportObject = new Report();
			   reportObject.Log("Highilighting the Element "+objectName, "Highlighted successfully", Status.highlight, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			   Thread.sleep(3000);
		   }else{
			   js.executeScript("arguments[0].setAttribute('style', arguments[1]);", findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder), "");
		   }            
       }
	}
	
	/**
	 * Author: Deepika Agnihotri
	 * Method Name: captureScreenshot
	 * Return Type: Nothing
	 * Description: This method captures screenshot of the page
	 */
	public void captureScreenshot(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws InterruptedException{
	   Report reportObject = new Report();
	   reportObject.Log("Capturing screenshot", "Captured screenshot", Status.done, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	public void captureScreenShot(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws InterruptedException{
		   Report reportObject = new Report();
		   reportObject.Log("Capturing screenshot", "Captured screenshot", Status.custom, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
	//------------------------Verifying keywords
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: verifyElementPresent
	 * Return Type: Nothing
	 * Description: This method verifies for the existence of an object
	 */
	public void verifyElementPresent(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Report reportObject = new Report();
		if(findElementsByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).size()!=0){
			if(!onPassLog.equalsIgnoreCase("")){
				reportObject.Log("Verifying the Presence of Element "+objectName, onPassLog, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}else{
				reportObject.Log("Verifying the Presence of Element "+objectName, "The Element "+objectName+" is present", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
		}else{
			if(!onFailLog.equalsIgnoreCase("")){
				reportObject.Log("Verifying the Presence of Element "+objectName, onFailLog, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}else{
				reportObject.Log("Verifying the Presence of Element "+objectName, "The Element "+objectName+" is  not present", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
		}
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: verifyElementPresent
	 * Return Type: Nothing
	 * Description: This method verifies for the existence of an object
	 * @throws Exception 
	 */
	public void assertElementPresent(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws Exception{
		Util utilObject = new Util();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Report reportObject = new Report();
		if(findElementsByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).size()!=0){
			if(testCase.contains("PA_Flow")){
				utilObject.setDataINI("AFB_Submission", "Pass", testCase, scenario);
			}
			
			if(!onPassLog.equalsIgnoreCase("")){
				reportObject.Log("Verifying the Presence of Element "+objectName, onPassLog, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}else{
				reportObject.Log("Verifying the Presence of Element "+objectName, "The Element "+objectName+" is present", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
		}else{
			if(!onFailLog.equalsIgnoreCase("")){
				reportObject.Log("Verifying the Presence of Element "+objectName, onFailLog, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}else{
				reportObject.Log("Verifying the Presence of Element "+objectName, "The Element "+objectName+" is  not present", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
			if(testCase.contains("PA_Flow")){
				utilObject.setDataINI("AFB_Submission", "Fail", testCase, scenario);
			}
			throw new Exception();
		}
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: verifyElementNotPresent
	 * Return Type: Nothing
	 * Description: This method verifies for the non-existence of an object
	 */
	public void verifyElementNotPresent(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		if(findElementsByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).size()==0){
			if(!onPassLog.equalsIgnoreCase("")){
				reportObject.Log("Verifying that "+objectName+" Element is not present", onPassLog, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}else{
				reportObject.Log("Verifying that "+objectName+" Element is not present", "The Element "+objectName+" is not present", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
			
		}else{
			if(!onFailLog.equalsIgnoreCase("")){
				reportObject.Log("Verifying that "+objectName+" Element is not present", onFailLog, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}else{
				reportObject.Log("Verifying that "+objectName+" Element is not present", "The Element "+objectName+" is  present", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
		}
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: verifyElementText
	 * Return Type: Nothing
	 * Description: This method verifies for the text of the element
	 */
	public void verifyElementText(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		if(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).getText().equals(dataValue)){
			reportObject.Log("Verifying the Text of the object "+objectName, "The element "+objectName+" has the text "+dataValue, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}else{
			reportObject.Log("Verifying the Text of the object "+objectName, "The element "+objectName+" does not have the text "+dataValue, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}		
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: verifyElementDisabled
	 * Return Type: Nothing
	 * Description: This method verifies for the editable state of the object
	 */
	public void verifyElementDisabled(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		if(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).getAttribute("disabled").equals("true")){
			reportObject.Log("Verifying the State of the object "+objectName, "The element "+objectName+" is in disabled state", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}else{
			reportObject.Log("Verifying the State of the object "+objectName, "The element "+objectName+" is not in disabled state", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}		
	}
	
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: verifyCheckBoxChecked
	 * Return Type: Nothing
	 * Description: This method verifies whether a check box is checked
	 */
	public void verifyCheckBoxChecked(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		if(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).isSelected()){
			reportObject.Log("Verifying the State of the checkbox "+objectName, "The checkbox "+objectName+" is checked", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}else{
			reportObject.Log("Verifying the State of the checkbox "+objectName, "The checkbox "+objectName+" is not checked", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: verifyCheckBoxChecked
	 * Return Type: Nothing
	 * Description: This method verifies whether a check box is checked
	 */
	public void checkSelectionAndCheckBox(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		if(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).isSelected()){
			reportObject.Log("Verifying the State of the checkbox "+objectName, "The checkbox "+objectName+" is checked", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}else{
			findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).click();
		}
	}

	
	
	/**
	 * Method Name: CurrentMonth
	 * Return Type: Nothing
	 * Description: This method is to fetch the current month
	 */
	public void CurrentMonth(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	{   
		Calendar rightNow = Calendar.getInstance();
		java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM");
		dataValue=(df1.format(rightNow.getTime()));
		
		Report reportObject = new Report();
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);
		
		reportObject.Log("Entering text in the text box "+objectName, "Entered the text "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		
	}

	/**
	 * Method Name: CurrentYear
	 * Return Type: Nothing
	 * Description: This method is to fetch the current Year
	 */
	public void CurrentYear(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	{
		Report reportObject = new Report();
		Calendar rightNow = Calendar.getInstance();
		java.text.SimpleDateFormat df2 = new java.text.SimpleDateFormat("YYYY");
		dataValue=df2.format(rightNow.getTime());
		
			findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
			findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);
			
		
			reportObject.Log("Entering text in the text box "+objectName, "Entered the text "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	/**
	 * Method Name: custYear
	 * Return Type: Nothing
	 * Description: This method is to fetch the current Year
	 */
	public void custYear(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	{
		Report reportObject = new Report();
		Calendar prev3Year = Calendar.getInstance();
		int minusValue=Integer.valueOf(dataValue);
		  prev3Year.add(Calendar.YEAR, -minusValue);
		  dataValue=String.valueOf(prev3Year.get(Calendar.YEAR));
		  //System.out.println(dataValue);
		
			findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
			findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);
					
			reportObject.Log("Entering text in the text box "+objectName, "Entered the text "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	
	
	public void genRandom(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Random rand=new Random();
		Report reportObject = new Report();
		int randomNum = rand.nextInt((9999 - 1000) + 1) + 1000;
		dataValue=String.valueOf(randomNum);
				
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);
		
		Util utilObject = new Util();
		utilObject.setDataINI("RANDOM_SSN", dataValue, testCase, scenario);
		
		//WriteDataToOutputFile(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
		reportObject.Log("Entering text in the text box "+objectName, "Entered the text "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	
	public void generateRandomStringAndEnterText(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		StringBuffer randStr = new StringBuffer();
        for(int i=0; i<Integer.parseInt(dataValue); i++){
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        			
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(randStr);
		
		//WriteDataToOutputFile(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
		reportObject.Log("Entering random generated text in the text box "+objectName, "Entered the random text "+randStr, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	
	
    private static int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
	
	
	/**
	 * Method Name: checkbox_value
	 * Return Type: Nothing
	 * Description: This method checks the checkbox value
	 */
	public void checkbox_value(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		
		try{
			findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).click();
			reportObject.Log("selecting checkbox "+objectName, "The checkbox "+objectName+" is checked", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
	
	catch(Exception e){
	
		reportObject.Log("Verifying the State of the checkbox "+objectName, "The checkbox "+objectName+" is not checked", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
		}
	/**
	 
	 * Method Name: verifyDisplayedPage
	 * Return Type: Nothing
	 * Description: This method registers the title of the page and is a pass if it matches the expected
	 */
	public void verifyDisplayedPageEDM(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		try{
			
			if((driver.getPageSource().contains(objectName)) || (driver.getTitle().contains(dataValue))){
				reportObject.Log("Verifying the page that is opened", "The page "+objectName+" has successfully opened", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
			else{
				 reportObject.Log("Verifying the page that is opened", "The page "+objectName+" has not opened", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
		}catch(Exception e){
			reportObject.Log("Verifying the page that is opened", ""+objectName+" exception block", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
	}
	
	public void verifyDisplayedPage(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		try{
			if((driver.getPageSource().contains(objectName)) || (driver.getTitle().contains(dataValue))){
				reportObject.Log("Verifying the page that is opened", "The page "+objectName+" has successfully opened", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
			else{
				 reportObject.Log("Verifying the page that is opened", "The page "+objectName+" has not opened", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
		}catch(Exception e){
			reportObject.Log("Verifying the page that is opened", ""+objectName+" exception block", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
	}
	
	/**
	
	 * Method Name: ReadDataFromOutputFile
	 * Return Type: Nothing
	 * Description: This method reads the value from the output file and puts it in the data field
	 */
	public void ReadDataFromOutputFile(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
	
		Util utilObject = new Util();
		Report reportObject = new Report();
		String columnValue = dataValue;
		String sectionName = columnValue.split(";")[0].trim();
		String key = columnValue.split(";")[1].trim();
		
		dataValue=utilObject.getDataINI(sectionName, key);
		
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);
	}
	
	
	/**
	
	 * Method Name: pickFromDatePicker
	 * Return Type: Nothing
	 * Description: This method reads the value from the output file and puts it in the data field
	 */
	public void pickFromDatePicker(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
	
		findElementByType(driver, scenario, testCase, homePath, currentIteration, objectId, browser, passScreenshot, browserFolder).click();
		//driver.findElement(By.xpath(".//*[@id='dimg_applicationReceivedDate']")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		WebElement dateWidget = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
		
		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
	
		for (WebElement cell:columns){
			if (cell.getText().equals(dataValue)){
			cell.findElement(By.linkText(dataValue)).click();
			break;
			}
		}		
	}
	

	/**
	 * Method Name: pickPreviousDayFromDatePicker
	 * Return Type: Nothing
	 * Description: This method reads the value from the output file and puts it in the data field
	 */
	public void pickPreviousDayFromDatePicker(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
	
		findElementByType(driver, scenario, testCase, homePath, currentIteration, objectId, browser, passScreenshot, browserFolder).click();
		//driver.findElement(By.xpath(".//*[@id='dimg_applicationReceivedDate']")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		WebElement dateWidget = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
		
		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
	
		String date = "";
		Calendar rightNow = Calendar.getInstance();
		java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("dd");
		date=(df1.format(rightNow.getTime()));
		if(!(date.equalsIgnoreCase("01") || date.equalsIgnoreCase("1"))){
			date = String.valueOf(Integer.parseInt(date)-1);
			
			for (WebElement cell:columns){
				if (cell.getText().equals(date)){
				cell.findElement(By.linkText(date)).click();
				break;
				}
			}
		}else if(date.equalsIgnoreCase("01") || date.equalsIgnoreCase("1")){
			driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']/a[@title='Prev']/span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			columns=dateWidget.findElements(By.tagName("td"));
			
			for (WebElement cell:columns){
				if (cell.getText().equals("31")){
					cell.findElement(By.linkText("31")).click();
					break;
				}else{
					if (cell.getText().equals("30")){
						cell.findElement(By.linkText("30")).click();
						break;
					}
				}
			}	
			
		}
		
	}
	
/**
	
	 * Method Name: selectCurrentDateInCustomMonth
	 * Return Type: Nothing
	 * Description: This method selects current date from custom month (difference specified in Test Data column)
	 */
	public void selectCurrentDateInCustomMonth(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
	
		findElementByType(driver, scenario, testCase, homePath, currentIteration, objectId, browser, passScreenshot, browserFolder).click();
		//driver.findElement(By.xpath(".//*[@id='dimg_applicationReceivedDate']")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		int number = Integer.parseInt(dataValue);
		
		if(number<0){
			for(int i=number;i<0;i++){
				driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']/a[@title='Prev']/span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(number>0){
			for(int i=0;i<number;i++){
				driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']/a[@title='Next']/span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		WebElement dateWidget = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));
		
		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
	
		String date = "";
		Calendar rightNow = Calendar.getInstance();
		java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("dd");
		date=(df1.format(rightNow.getTime()));
		
		for (WebElement cell:columns){
			if (cell.getText().equals(date)){
			cell.findElement(By.linkText(date)).click();
			break;
			}
		}		
	}
	
	public void verifySelectedValue(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		   Report report = new Report();
			
		   Select selectBox = new Select(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder));
	      if(selectBox.getFirstSelectedOption().getText().equalsIgnoreCase(dataValue)){
	            if(!onPassLog.equalsIgnoreCase("")){
	                  report.Log("Verifying the selected value in the dropdown "+objectName, onPassLog, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	            }else{
	                  report.Log("Verifying the selected value in the dropdown "+objectName, "The dropdown "+objectName+" has the value "+dataValue+" selected", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	            }                 
	      }else{
	            if(!onFailLog.equalsIgnoreCase("")){
	                  report.Log("Verifying the selected value in the dropdown "+objectName, onFailLog, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	            }else{
	                  report.Log("Verifying the selected value in the dropdown "+objectName, "The dropdown "+objectName+" does not have the value "+dataValue+" selected", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	            }	            
	      }
	}
	
	public void verifyWindowDisplayed(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		  Report report = new Report();
		  
		  String currentWindow = driver.getWindowHandle();
		  
		  driver.findElement(By.xpath("//button[@id='button_Preview']")).click();
		  
		  WebDriverWait wait = new WebDriverWait(driver, 15, 100);
		  wait.until(numberOfWindowsToBe(2));
		  
	 	  Set <String> availableWindows = driver.getWindowHandles();
	 	  boolean flag = false;
	 	  
	 	  if (!availableWindows.isEmpty()) {
	 		    for (String windowId : availableWindows) {
	 			    if (driver.switchTo().window(windowId).getTitle().equalsIgnoreCase(dataValue)) {
	 			    	if(!onPassLog.equalsIgnoreCase("")){
			                  report.Log("Verifying the selected value in the dropdown "+objectName, onPassLog, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			            }else{
			                  report.Log("Verifying the selected value in the dropdown "+objectName, "The dropdown "+objectName+" has the value "+dataValue+" selected", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			            }
	 			    	flag=true;
	 			    	driver.close();
	 			    	break;
				    }else{
				        flag = false;            
				    }
	 		    }
	 		    if(!flag){
	 		    	if(!onFailLog.equalsIgnoreCase("")){
			              report.Log("Verifying the selected value in the dropdown "+objectName, onFailLog, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			        }else{
			              report.Log("Verifying the selected value in the dropdown "+objectName, "The dropdown "+objectName+" does not have the value "+dataValue+" selected", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			        }
	 		    }
   	 	  }
	 	  
	 	  driver.switchTo().window(currentWindow);
	}	
	
	public static ExpectedCondition<Boolean> numberOfWindowsToBe(final int numberOfWindows) {
	    return new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	                driver.getWindowHandles();
	        return driver.getWindowHandles().size() == numberOfWindows;
	      }
	    };
	}
	
	public void switchWindow(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
 		String title = dataValue;
 		if(title.equalsIgnoreCase("null")){
			driver.switchTo().window(null);
		}else{
			 String currentWindow = driver.getWindowHandle();
		 	    Set <String> availableWindows = driver.getWindowHandles();
		 	    boolean flag = false;
		 	   Report report = new Report();
		 	    if (!availableWindows.isEmpty()) {
		 		    for (String windowId : availableWindows) {
		 			    if (driver.switchTo().window(windowId).getTitle().contains(title)) {
		 			    	driver.switchTo().window(windowId);
		 			    	report.Log("Switching Window", "Switched the control to the Window with the title "+title, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		 			    	flag = true;
		 			    	break;
		 			    }else {
		 			        driver.switchTo().window(currentWindow);
		 			        try {
		 						Thread.sleep(3000);
		 					} catch (InterruptedException e) {
		 						// TODO Auto-generated catch block
		 						e.printStackTrace();
		 					}
		 			    }
		 		    }
		 	    }
		 	    if(!flag)
		 	    	report.Log("Switching Window", "Did not find any window with the title "+title, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		} 	   
 	}
	
	public void switchToMainWindow(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
 		String title = dataValue;
 		if(title.equalsIgnoreCase("null")){
			driver.switchTo().window(null);
		}else{
			
			 //String currentWindow = driver.getWindowHandle();
		 	    Set <String> availableWindows = driver.getWindowHandles();
		 	    boolean flag = false;
		 	   Report report = new Report();
		 	    if (!availableWindows.isEmpty()) {
		 		    for (String windowId : availableWindows) {
		 			    if (driver.switchTo().window(windowId).getTitle().contains(title)) {
		 			    	driver.switchTo().window(windowId);
		 			    	report.Log("Switching Window", "Switched the control to the Window with the title "+title, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		 			    	flag = true;
		 			    	break;
		 			    }else {
		 			        //driver.switchTo().window(currentWindow);
		 			        try {
		 						Thread.sleep(3000);
		 					} catch (InterruptedException e) {
		 						// TODO Auto-generated catch block
		 						e.printStackTrace();
		 					}
		 			    }
		 		    }
		 	    }
		 	    if(!flag)
		 	    	report.Log("Switching Window", "Did not find any window with the title "+title, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		} 	   
 	}
	
	public void navigateToURL(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
 		
		driver.navigate().to(dataValue);
 	}
	
	public void setAttribute(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		setAttributeValue(driver,findElementByType(driver, scenario, testCase, homePath, currentIteration, objectId, browser, passScreenshot, browserFolder),dataValue);
	}
	
	public void setAttributeValue(WebDriver driver, WebElement element, String dataValue){
		String attributeName = dataValue.split("=")[0];
		String attributeValue = dataValue.split("=")[1];
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
	    String scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";
	    
	    js.executeScript(scriptSetAttrValue, element, attributeName, attributeValue);
	}	
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: enter_Custom_CalendarDate
	 * Return Type: Nothing
	 * Description: This method enters the date specified in appDate key in Config
	 */
	public void enter_Custom_CalendarDate(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		Util utilObject = new Util();
		
		String mm = "", dd = "", yyyy = "";
		
		String actualObjectID = "";
		if(objectId.split(":").length>2){
			for(int z=1;z<(objectId.split(":").length);z++){
				if(z!=1){
					actualObjectID = actualObjectID+":"+objectId.split(":")[z].trim();
				}else if (z==1){
					actualObjectID = actualObjectID+objectId.split(":")[z].trim();
				}
			}
		}else{
			actualObjectID = objectId.split(":")[1].trim();
		}
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
		}
		
		//Boolean to compare current date and application date
		Boolean appDateMatchWithCurrentDate = false;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		
		if(overrideDBDate.equalsIgnoreCase("No")){
			if(sdf.format(cal.getTime()).toString().equalsIgnoreCase(value)){
				appDateMatchWithCurrentDate = true;
			}else{
				appDateMatchWithCurrentDate = false;
			}
		}else{
			if(sdf.format(cal.getTime()).toString().equalsIgnoreCase(utilObject.getValueFromConfigProperties("appDate").trim())){
				appDateMatchWithCurrentDate = true;
			}else{
				appDateMatchWithCurrentDate = false;
			}
		}		
		
		if(driver.getCurrentUrl().startsWith("https://")){
			if(overrideDBDate.trim().equalsIgnoreCase("No")){
				mm = value.split("-")[0].trim();
				dd = value.split("-")[1].trim();
				yyyy = value.split("-")[2].trim();
			}else if(overrideDBDate.trim().equalsIgnoreCase("Yes")){
				mm = utilObject.getValueFromConfigProperties("appDate").split("-")[0].trim();
				dd = utilObject.getValueFromConfigProperties("appDate").split("-")[1].trim();
				yyyy = utilObject.getValueFromConfigProperties("appDate").split("-")[2].trim();
			}
			
			if((!(appDateMatchWithCurrentDate)) || dataValue.equalsIgnoreCase("EnterDate")){
				
			     JavascriptExecutor js = (JavascriptExecutor)driver;
	          
				driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'month')]")).clear();
				 js.executeScript("arguments[0].value='"+mm+"';", driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'month')]")));
	             
				driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'date')]")).clear();
				 js.executeScript("arguments[0].value='"+dd+"';", driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'date')]")));
	             				
				driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'year')]")).clear();
				 js.executeScript("arguments[0].value='"+yyyy+"';", driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'year')]")));
	             
				reportObject.Log("Entering the custom date", "Entered the date in calendar "+objectName+" as "+mm+"-"+dd+"-"+yyyy, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
		}else if (driver.getCurrentUrl().startsWith("http://")){
			if(overrideDBDate.equalsIgnoreCase("No")){
				mm = value.split("-")[0].trim();
				dd = value.split("-")[1].trim();
				yyyy = value.split("-")[2].trim();
			}else{
				mm = utilObject.getValueFromConfigProperties("appDate").split("-")[0].trim();
				dd = utilObject.getValueFromConfigProperties("appDate").split("-")[1].trim();
				yyyy = utilObject.getValueFromConfigProperties("appDate").split("-")[2].trim();
			}
						
			driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'month')]")).clear();
			driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'month')]")).sendKeys(mm);
			
			driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'date')]")).clear();
			driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'date')]")).sendKeys(dd);
			
			driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'year')]")).clear();
			driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'year')]")).sendKeys(yyyy);
			
			reportObject.Log("Entering the custom date", "Entered the date in calendar "+objectName+" as "+mm+"-"+dd+"-"+yyyy, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: assertElementText
	 * Return Type: Nothing
	 * Description: This method verifies for the text of the element and stops the script in case of a failure
	 */
	public void assertElementText(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws Exception{
		Report reportObject = new Report();
		if(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).getText().equals(dataValue)){
			reportObject.Log("Verifying the Text of the object "+objectName, "The element "+objectName+" has the text "+dataValue, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}else{
			reportObject.Log("Verifying the Text of the object "+objectName, "The element "+objectName+" does not have the text "+dataValue, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			throw new Exception();
		}
	}
	
	public void removeAttribute(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		removeAttributeValue(driver,findElementByType(driver, scenario, testCase, homePath, currentIteration, objectId, browser, passScreenshot, browserFolder),dataValue);
	}
	
	public void removeAttributeValue(WebDriver driver, WebElement element, String dataValue){
		String attributeName = dataValue;
		//String attributeValue = dataValue.split("=")[1];
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
	    String scriptSetAttrValue = "arguments[0].removeAttribute(arguments[1])";
	    
	    js.executeScript(scriptSetAttrValue, element, attributeName);
	}
	
	public void enter_PeriodStartDate_CalendarDate(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
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
		
		String actualObjectID = "";
		if(objectId.split(":").length>2){
			for(int z=1;z<(objectId.split(":").length);z++){
				if(z!=1){
					actualObjectID = actualObjectID+":"+objectId.split(":")[z].trim();
				}else if (z==1){
					actualObjectID = actualObjectID+objectId.split(":")[z].trim();
				}
			}
		}else{
			actualObjectID = objectId.split(":")[1].trim();
		}

		  JavascriptExecutor js = (JavascriptExecutor)driver;
        
		driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'month')]")).clear();
		 js.executeScript("arguments[0].value='"+mm+"';", 		driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'month')]")));
			
		driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'year')]")).clear();
		 js.executeScript("arguments[0].value='"+yyyy+"';", 		driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'year')]")));
			
		reportObject.Log("Entering the custom date", "Entered the date in calendar "+objectName+" as "+mm+"-"+yyyy, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);		
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: enter_Custom_CalendarDate
	 * Return Type: Nothing
	 * Description: This method enters the date specified in appDate key in Config
	 */
	public void enter_HIX_AppDate(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
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
		
		findElementByType(driver, scenario, testCase, homePath, currentIteration, objectId, browser, passScreenshot, browserFolder).clear();
		findElementByType(driver, scenario, testCase, homePath, currentIteration, objectId, browser, passScreenshot, browserFolder).sendKeys(mm+"/"+dd+"/"+yyyy);
		
		reportObject.Log("Entering the application date", "Entered the date in "+objectName+" as "+mm+"/"+dd+"/"+yyyy, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);		
	}
	
	public void generateRandomUserid(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		StringBuffer randStr = new StringBuffer();
        for(int i=0; i<Integer.parseInt(dataValue); i++){
            int number = getRandomNumber();
            char ch = CHAR_NUM_LIST.charAt(number);
            randStr.append(ch);
        }
        String str = randStr.toString();
                			
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(randStr);
		
		//WriteDataToOutputFile(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
		reportObject.Log("Entering random user id in the text box "+objectName, "Entered the random text "+randStr, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		
		Util utilObject = new Util();
		utilObject.setDataINI("HIX_USER_ID", str, testCase, scenario);
	}
	
	public void generateRandomEmailID(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		StringBuffer randStr = new StringBuffer();
        for(int i=0; i<Integer.parseInt(dataValue); i++){
            int number = getRandomNumber();
            char ch = CHAR_NUM_LIST.charAt(number);
            randStr.append(ch);
        }
        String str = randStr.toString();

		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(str+"@gmail.com");
		
		//WriteDataToOutputFile(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
		reportObject.Log("Entering random user id in the text box "+objectName, "Entered the random text "+randStr, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		
		Util utilObject = new Util();
		utilObject.setDataINI("EMAIL_ID", str+"@gmail.com", testCase, scenario);
	}
	/**
	 * Author: Krishna Sai Katragadda
	 * Method Name: checkAndEnterReportedDate
	 * Return Type: Nothing
	 * Description: This method enters the date specified in appDate key in Config
	 */
	public void checkAndEnterReportedDate(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		Util utilObject = new Util();
		
		String mm = "", dd = "", yyyy = "";
		
		String actualObjectID = "";
		if(objectId.split(":").length>2){
			for(int z=1;z<(objectId.split(":").length);z++){
				if(z!=1){
					actualObjectID = actualObjectID+":"+objectId.split(":")[z].trim();
				}else if (z==1){
					actualObjectID = actualObjectID+objectId.split(":")[z].trim();
				}
			}
		}else{
			actualObjectID = objectId.split(":")[1].trim();
		}
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
		}
		else
			value=utilObject.getValueFromConfigProperties("appDate").trim();
		
		//Boolean to compare current date and application date
		Boolean appDateLessThanCurrentDate = false;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String date1=sdf.format(cal.getTime());
		
		if(overrideDBDate.equalsIgnoreCase("No")){
			try {
				if(sdf.parse(date1).after(sdf.parse(value))){
					appDateLessThanCurrentDate = true;
					driver.findElement(By.xpath("//label[@for='reportedTimely_no']")).click();
					
				}else{
					appDateLessThanCurrentDate = false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			try {
				if(sdf.parse(date1).after(sdf.parse(value))){
					appDateLessThanCurrentDate = true;
					driver.findElement(By.xpath("//label[@for='reportedTimely_no']")).click();
				}else{
					appDateLessThanCurrentDate = false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}		
		
		if(overrideDBDate.trim().equalsIgnoreCase("No")){
			mm = value.split("-")[0].trim();
			dd = value.split("-")[1].trim();
			yyyy = value.split("-")[2].trim();
		}else if(overrideDBDate.trim().equalsIgnoreCase("Yes")){
			mm = utilObject.getValueFromConfigProperties("appDate").split("-")[0].trim();
			dd = utilObject.getValueFromConfigProperties("appDate").split("-")[1].trim();
			yyyy = utilObject.getValueFromConfigProperties("appDate").split("-")[2].trim();
		}
			
		if(objectName.contains("REPORT_DATE") && (appDateLessThanCurrentDate)){
			driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'month')]")).clear();
			driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'month')]")).sendKeys(mm);
			
			driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'date')]")).clear();
			driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'date')]")).sendKeys(dd);
							
			driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'year')]")).clear();
			driver.findElement(By.xpath(actualObjectID+"/preceding-sibling::input[@type='text' and contains(@id,'year')]")).sendKeys(yyyy);
							
			reportObject.Log("Entering the reported date", "Entered the date in calendar "+objectName+" as "+mm+"-"+dd+"-"+yyyy, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
		driver.findElement(By.xpath("//label[@for='reportedTimely_yes']")).click();
	}
	public String genRandomAuthorization(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws Exception{
		Random rand=new Random();
		int randomNum = rand.nextInt((9999999 - 1000000) + 1) + 1000;
		dataValue=String.valueOf(randomNum);
		KeywordLibrary k=new KeywordLibrary();
		k.findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		k.findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);
		return dataValue;
	}
	public static String generateRandomEmailIDValue(String dataValue){
		Report reportObject = new Report();
		StringBuffer randStr = new StringBuffer();
        for(int i=0; i<Integer.parseInt(dataValue); i++){
            int number = getRandomNumber();
            char ch = CHAR_NUM_LIST.charAt(number);
            randStr.append(ch);
        }
        String str = randStr.toString();
            return str;
	
	}
	public void genRandomTwoDigitValue(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Random rand=new Random();
		Report reportObject = new Report();
		int randomNum = rand.nextInt((99 - 10) + 1) + 10;
		dataValue=String.valueOf(randomNum);
				
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);
		
		Util utilObject = new Util();
		utilObject.setDataINI("RANDOM_SSN_2Digit", dataValue, testCase, scenario);
		
		//WriteDataToOutputFile(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
		reportObject.Log("Entering text in the text box "+objectName, "Entered the text "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	
	/**
	 * Method Name: verifyElementPartialText
	 * Return Type: Nothing
	 * Description: This method verifies for the partial text of the element
	 */
	public void verifyElementPartialText(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		
		if(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).getText().contains(dataValue)){
			reportObject.Log("Verifying the Text of the object "+objectName, "The element "+objectName+" has the text "+dataValue, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}else{
			reportObject.Log("Verifying the Text of the object "+objectName, "The element "+objectName+" does not have the text "+dataValue, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: verifyElementText
	 * Return Type: Nothing
	 * Description: This method verifies for the text of the element
	 */
	public void verifyElementValue(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Report reportObject = new Report();
		if(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).getAttribute("value").equalsIgnoreCase(dataValue)){
			reportObject.Log("Verifying the Text of the object "+objectName, "The element "+objectName+" has the text "+dataValue, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}else{
			reportObject.Log("Verifying the Text of the object "+objectName, "The element "+objectName+" does not have the text "+dataValue, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}		
	}
	
	/**
	 * Author: Santosh Kumar Anupati
	 * Method Name: waitForElementToBeVisible
	 * Return Type: Nothing
	 * Description: This method waits for the object to appear in the application by waiting for a certain period of time
	 */
	public void waitForElementToBeVisible(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		
		WebDriverWait wait = new WebDriverWait(driver, 180);
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(objectId)));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objectId)));
		wait.until(ExpectedConditions.visibilityOf(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder)));
		
	}
	
	public void switchToFrame(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
 		driver.switchTo().defaultContent();
 		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		String frameName = dataValue;
 	    driver.switchTo().frame(driver.findElement(By.id(frameName)));
 	}
	
 	
 	public void switchToDefaultContent(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
 		driver.switchTo().defaultContent();
 	}
 	
 	public void closeWindow(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		  driver.close();
 	}
 	public  boolean enterDate(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){


		System.out.println("method is called");
      JavascriptExecutor executor = (JavascriptExecutor)driver;
      Actions action =new Actions(driver);
       boolean temp=false;
       try{

           By BywebElement =KeywordLibrary.findElement(driver, objectName, scenario);
          // By BywebElement1 =KeywordLibrary.findElement(driver, "APP_REG_SUB_LINK", scenario);
           if(KeywordLibrary.webElementExplict(driver, BywebElement)) {
               if (driver.findElements(BywebElement).size() > 0) {
                   List<WebElement> webElements = driver.findElements(BywebElement);
                   for (int i = 0; i < webElements.size(); i++) {
                       if (webElements.get(i).isDisplayed()) {
                      	 
                      	 WebElement element = driver.findElement(BywebElement);
                     // 	jse.executeScript("document.getElementById('gbqfq').value = 'Ripon Al Wasim';");
                      	executor.executeScript("arguments[0].value='"+dataValue+"';", webElements.get(i));
                           //  executor.executeScript("arguments[0].sendKeys();", webElements.get(i));
                        // action.doubleClick( webElements.get(i)).build().perform();
                         temp = true;
                           break;
                       }
                   }
                   if (temp) return temp;
                   else return temp;

               }
               
               return false;
           }
       
           return false;
       } catch (Exception e1) {
           
           e1.printStackTrace();
           return  false;
       }
   
}
	public static boolean webElementExplict(WebDriver driver,By by) {
		boolean webelementcheck=false;
		try {
			WebDriverWait wait =new WebDriverWait(driver, 5);
			if(wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)))!=null) {
				webelementcheck=true;
				return webelementcheck;
				}else return webelementcheck;
			}catch(Exception e) {
			return webelementcheck;
		}
	}
	
	
 	public static By findElement(WebDriver driver, String objectName, String module) {

        By object = null;
        try {
               Util utilObject = new Util();
               //WebElement object = null;
               String identifier,actualObjectID = "";
               String objectID = "";
        
               
               objectID = utilObject.getObjectFromObjectMap(objectName, module);
               identifier = objectID.split(":")[0].trim();
               if(objectID.split(":").length>2){
                     for(int z=1;z<(objectID.split(":").length);z++){
                            if(z!=1){
                                  actualObjectID = actualObjectID+":"+objectID.split(":")[z].trim();
                            }else if (z==1){
                                  actualObjectID = actualObjectID+objectID.split(":")[z].trim();
                            }
                     }
               }else{
                     actualObjectID = objectID.split(":")[1].trim();
               }
        // Reduced timeout from 60 to 10 seconds - Amit
               
               if(identifier.equalsIgnoreCase("id")){
                     object = By.id(actualObjectID);
               }else if(identifier.equalsIgnoreCase("xpath")){
                     object = By.xpath(actualObjectID);
               }else if(identifier.equalsIgnoreCase("className")){
                     object = By.className(actualObjectID);
               }else if(identifier.equalsIgnoreCase("name")){
                     object = By.name(actualObjectID);
               }else if(identifier.equalsIgnoreCase("css")){
                     object = By.cssSelector(actualObjectID);
               }else if(identifier.equalsIgnoreCase("linkText")){
                     object = By.linkText(actualObjectID);
               }else if(identifier.equalsIgnoreCase("partialLinkText")){
                     object = By.partialLinkText(actualObjectID);
               }
               return object;
               
        }catch(Exception e) {
               return object;
        }
        
        
  
}
 	
 	public void verifyElementTextForAddressValidation(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){

		Report reportObject = new Report();
		String textFromScreen;
		String textFromExcel=dataValue.trim();
		textFromScreen= findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).getText().trim();
		if(textFromScreen.equalsIgnoreCase(textFromExcel)){
			By BywebElement =KeywordLibrary.findElement(driver, "EDIT_ADDRESS_PENCIL", scenario);
			//By BywebElement =By.xpath("EDIT_ADDRESS_PENCIL");
	    	if(driver.findElements(BywebElement).size()!=0) {
	    		driver.findElement(BywebElement).click();
	    		//selectByValue(homePath, testCase, scenario, browser, "", "ADDRESS_VALIDATION", "NO", onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
	    		selectByVisibleText(homePath, testCase, scenario, browser, "xpath:.//*[@id='addressValidation']", "ADDRESS_VALIDATION", "NO", onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
	    		By BywebElement1 =KeywordLibrary.findElement(driver, "ADD_ADDRESS_BUTTON", scenario);	    		
				//By BywebElement =By.xpath("EDIT_ADDRESS_PENCIL");
		    	if(driver.findElements(BywebElement1).size()!=0) {
		    		driver.findElement(BywebElement1).click();
		    	}		    		
	    	}
			reportObject.Log("Verifying the Text of the object "+objectName, "The element "+objectName+" has the text "+dataValue, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}else{
			reportObject.Log("Verifying the Text of the object "+objectName, "The element "+objectName+" does not have the text "+dataValue, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}		
	
}
 	
 	public  boolean ClickButtonORLink(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){


 		System.out.println("method is called");
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        Actions action =new Actions(driver);
         boolean temp=false;
         try{

             By BywebElement =KeywordLibrary.findElement(driver, objectName, scenario);
            // By BywebElement1 =KeywordLibrary.findElement(driver, "APP_REG_SUB_LINK", scenario);
             if(KeywordLibrary.webElementExplict(driver, BywebElement)) {
                 if (driver.findElements(BywebElement).size() > 0) {
                     List<WebElement> webElements = driver.findElements(BywebElement);
                     for (int i = 0; i < webElements.size(); i++) {
                         if (webElements.get(i).isDisplayed()) {
                        	 
                        	 WebElement element = driver.findElement(BywebElement);
                        	//setAttributeValue(driver, element, "onmouseover=window.status='Application Registration';return true;");
                        	//setAttributeValue(driver, element, "onmouseover=");
                        	 //removeAttributeValue(driver, element, "onmouseover");
                        	action.moveToElement(element).build().perform();
                        	 action.click(element).build().perform();
                               executor.executeScript("arguments[0].click();", webElements.get(i));
                          // action.doubleClick( webElements.get(i)).build().perform();
                           temp = true;
                             break;
                         }
                     }
                     if (temp) return temp;
                     else return temp;

                 }
                 
                 return false;
             }
         
             return false;
         } catch (Exception e1) {
             
             e1.printStackTrace();
             return  false;
         }
     
}
 	

 	public static boolean selectValueFromDropdownCustom(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
 		
 		  boolean temp_var=false;
 			Util utilObject = new Util();
 			
         List<WebElement> webchildelements=null;
 		  try{

 			  By BywebElement =KeywordLibrary.findElement(driver, objectName, scenario);
      
        if(KeywordLibrary.webElementExplict(driver, BywebElement)) {
        	
            if (driver.findElements(BywebElement).size() > 0) {
                List<WebElement> webElements = driver.findElements(BywebElement);
                for (int i = 0; i < webElements.size(); i++) {
                    if (webElements.get(i).isDisplayed()) {
                   	  webchildelements=webElements.get(i).findElements(By.xpath(utilObject.getValueFromConfigProperties("Dropdown_child_tag")));    
                   	  for(int j=0;j<webchildelements.size();j++) {
                   		  if(webchildelements.get(j).getText().equalsIgnoreCase(dataValue)){
                   			 webchildelements.get(j).click();
                   			temp_var=true;
                   			 break;
                   			 
                   		  }
                   		  if(!temp_var)continue;else break;
                   	  }
                   	  
                    	
                   	 if(!temp_var)continue;else break;
                   	 
                    }
                }
                
                if (temp_var) return temp_var;
                else return temp_var;

            }
            
            return false;
        }
    
        return false;
    } catch (Exception e1) {
        
        e1.printStackTrace();
        return  false;
    }
 		
 	}


 	public static boolean selectValueFromDropdownCustomUsingIndex(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		  boolean temp=false;
		  boolean temp_var=false;
			Util utilObject = new Util();
			
       List<WebElement> webchildelements=null;
		  try{

			  By BywebElement =KeywordLibrary.findElement(driver, objectName, scenario);
    
      if(KeywordLibrary.webElementExplict(driver, BywebElement)) {
      	
          if (driver.findElements(BywebElement).size() > 0) {
              List<WebElement> webElements = driver.findElements(BywebElement);
              for (int i = 0; i < webElements.size(); i++) {
                  if (webElements.get(i).isDisplayed()) {
                 	  webchildelements=webElements.get(i).findElements(By.xpath(utilObject.getValueFromConfigProperties("Dropdown_child_tag")));    
                 	  for(int j=0;j<webchildelements.size();j++) {
                 		 if(webchildelements.get(Integer.parseInt(dataValue)).isDisplayed()) {
                 			webchildelements.get(Integer.parseInt(dataValue)).click();
                 			temp_var=true;
                  			 break;
                  			 
                  		  }
                  		  if(!temp_var)continue;else break;
                 	  }
                  	
          
                 	 if(!temp_var)continue;else break;
                  }
              }
              
              if (temp_var) return temp_var;
              else return temp_var;

          }
          
          return false;
      }
  
      return false;
  } catch (Exception e1) {
      
      e1.printStackTrace();
      return  false;
  }
		
	}
 	public static boolean enterCalenderDate(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
 		  boolean temp=false;
 		  boolean temp_var=false;
 			Util utilObject = new Util();
 			
         List<WebElement> webchildelements=null;
 		  try{

 			  By BywebElement =KeywordLibrary.findElement(driver, objectName, scenario);
      
        if(KeywordLibrary.webElementExplict(driver, BywebElement)) {
        	
            if (driver.findElements(BywebElement).size() > 0) {
                List<WebElement> webElements = driver.findElements(BywebElement);
                for (int i = 0; i < webElements.size(); i++) {
                    if (webElements.get(i).isDisplayed()) {
                   	  webchildelements=webElements.get(i).findElements(By.xpath(utilObject.getValueFromConfigProperties("Calendar_child_tag")));    
                   	  for(int j=0;j<webchildelements.size();j++) {
                   		  if(webchildelements.get(j).getText().equalsIgnoreCase(dataValue)){
                   			 webchildelements.get(j).click();
                   			temp_var=true;
                   			 break;
                   			 
                   		  }
                   		  if(!temp_var)continue;else break;
                   	  }
                   	  
                    	
                   	  if(!temp_var)continue;else break;
                    }
                }
                
                if (temp_var) return temp_var;
                else return temp_var;

            }
            
            return false;
        }
    
        return false;
    } catch (Exception e1) {
        
        e1.printStackTrace();
        return  false;
    }
 		
 	}






	 public static boolean ClickButtonORLink(WebDriver driver,String object,String scenario){

	    	JavascriptExecutor executor = (JavascriptExecutor)driver;
	    	
	        boolean temp=false;
	        try{

	            By BywebElement =KeywordLibrary.findElement(driver, object, scenario);
	            if(KeywordLibrary.webElementExplict(driver, BywebElement)) {
	                if (driver.findElements(BywebElement).size() > 0) {
	                    List<WebElement> webElements = driver.findElements(BywebElement);
	                    for (int i = 0; i < webElements.size(); i++) {
	                        if (webElements.get(i).isDisplayed()) {
	                        	executor.executeScript("arguments[0].click();", webElements.get(i));
	                        	
	                        		temp = true;
	                            break;
	                        }
	                    }
	                    if (temp) return temp;
	                    else return temp;

	                }
	                
	                return false;
	            }
	        
	            return false;
	        } catch (Exception e1) {
	            
	            e1.printStackTrace();
	            return  false;
	        }
	    }






	 public void currentYearMinusNoOfYears(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
		{			  
       Calendar c = Calendar.getInstance();
		  int year = c.get(Calendar.YEAR);
		  int month = c.get(Calendar.MONTH)+1;
		  int date = c.get(Calendar.DATE);
		  System.out.println("currentYearMinusNoOfYears():CurrentYear:"+year);
		  int enteredNoOfYears = Integer.parseInt(dataValue);
		  System.out.println("currentYearMinusNoOfYears():enteredNoOfYears:"+enteredNoOfYears);
		  int dobActualYear = year-enteredNoOfYears;
		  dataValue = String.valueOf(dobActualYear);
		  System.out.println("currentYearMinusNoOfYears():dataValue:"+dataValue);

		  enterDate(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);

		 
	}
	    public void currentMonthMinusNoOfMonths(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
		{			  
       Calendar c = Calendar.getInstance();		 
		  int month = c.get(Calendar.MONTH)+1;		 
		  System.out.println("currentMonthMinusNoOfMonths():CurrentMonth:"+month);  
		  int enteredMonth = Integer.parseInt(dataValue);
		  System.out.println("currentMonthMinusNoOfMonths():enteredMonthValue:"+enteredMonth);
		  if(month > enteredMonth)
		  {
			  int actualMonth = month-enteredMonth;
			  dataValue = String.valueOf(actualMonth);
		  }
		  else
		  {
			  int actualMonth = enteredMonth-month;
			  dataValue = String.valueOf(actualMonth);
		  }
		  System.out.println("currentMonthMinusNoOfMonths():dataValue:"+dataValue);
		  enterDate(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);

		 
	}
	    public void currentDateMinusNoOfDays(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
		{			  
       Calendar c = Calendar.getInstance();		  
		  int date = c.get(Calendar.DATE);
		  int enteredDate = Integer.parseInt(dataValue);
		  System.out.println("currentDateMinusNoOfDays():CurrentDate:"+date);
		  System.out.println("currentDateMinusNoOfDays():enteredDateValue:"+enteredDate);
		 if(date > enteredDate)
		 {			  
			  int actualDate = date-enteredDate;
			  dataValue = String.valueOf(actualDate);
		 }
		 else
		 {
			  int actualDate = enteredDate-date;
			  dataValue = String.valueOf(actualDate);
		 }
		 System.out.println("currentDateMinusNoOfDays():dataValue:"+dataValue);
		 enterDate(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);

		 
	}
	    public void currentDate(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	    {	    	 
	    	  Calendar c = Calendar.getInstance();			  
			  int date = c.get(Calendar.DATE);
			  System.out.println("currentDate():CurrentDate:"+date);			 
			  dataValue = String.valueOf(date);
			  System.out.println("currentDate():dataValue:"+dataValue);	
			  enterDate(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
	    }
	    public void currentMonth(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	    {	    	
	    	  Calendar c = Calendar.getInstance();			
			  int month = c.get(Calendar.MONTH)+1;			
			  System.out.println("currentMonth():CurrentMonth:"+month);			  
			  dataValue = String.valueOf(month);
			  System.out.println("currentMonth():dataValue:"+dataValue);
			  enterDate(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
	    }
	    public void currentYear(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	    {	    	  
	    	  Calendar c = Calendar.getInstance();
			  int year = c.get(Calendar.YEAR);
			  System.out.println("currentYear():CurrentYear:"+year);	
			  dataValue = String.valueOf(year);
			  System.out.println("currentYear():dataValue"+dataValue);
			  enterDate(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
	    }
	    public void enterDay
	    (String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	    {	    
			  enterDate(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
	    }
	    public void enterMonth(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	    {	    
			  enterDate(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
	    }
	    public void enterYear(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	    {	    
			  enterDate(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
	    }
	    
	    
	    /**
		 * Author: Navya Mallajosyula
		 * Method Name: isCheckboxUnchecked
		 * Return Type: Nothing
		 * Description: This method verifies if checkbox is unchecked
		 */
		public void isCheckboxUnchecked(String homePath, String testCase, String scenario, String browser, String objectId,
				String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
				String passScreenshot, Integer currentIteration, Boolean error, String browserFolder, Logger log) {
			Report reportObject = new Report();
			WebElement checkbox = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(),
					objectId, browser, passScreenshot, browserFolder, log);
			if (!checkbox.isSelected())
				reportObject.logger("Checkbox validation ", "Checkbox is unchecked " + dataValue, Status.pass, driver,
						testCase, scenario, browser, passScreenshot, browserFolder, log);
			else
				reportObject.logger("Checkbox validation ", "Checkbox is checked " + dataValue, Status.FAIL, driver, testCase,
						scenario, browser, passScreenshot, browserFolder, log);
		}
		
		/**
		 * Author: Navya Mallajosyula
		 * Method Name: isCheckChecked
		 * Return Type: Nothing
		 * Description: This method verifies if checkbox is checked
		 */
		public void isCheckboxChecked(String homePath, String testCase, String scenario, String browser, String objectId,
				String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
				String passScreenshot, Integer currentIteration, Boolean error, String browserFolder, Logger log) {
			Report reportObject = new Report();
			WebElement checkbox = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(),
					objectId, browser, passScreenshot, browserFolder, log);
			if (checkbox.isSelected())
				reportObject.logger("Checkbox validation ", "Checkbox is checked " + dataValue, Status.pass, driver,
						testCase, scenario, browser, passScreenshot, browserFolder, log);
			else
				reportObject.logger("Checkbox validation ", "Checkbox is unchecked " + dataValue, Status.FAIL, driver, testCase,
						scenario, browser, passScreenshot, browserFolder, log);
		}

		public WebElement findElementByType(WebDriver driver, String scenario, String testCase, String homePath, int currentIteration, String objectID, String browser, String passScreenshot, String browserFolder, Logger log){
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
							splittedString[i] = utilObject.GetData((splittedString[i]).split("getData=")[1], driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder,log);
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
		
		
		
	     public  boolean selectAllCheckBox(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	     {
	            JavascriptExecutor executor = (JavascriptExecutor)driver;
	            Actions action =new Actions(driver);
	            boolean temp=false;
	            try{
	                  By BywebElement =KeywordLibrary.findElement(driver, objectName, scenario);
	                  if(KeywordLibrary.webElementExplict(driver, BywebElement)) {
	                        if (driver.findElements(BywebElement).size() > 0) {
	                              List<WebElement> webElements = driver.findElements(BywebElement);
	                              for (int i = 0; i < webElements.size(); i++) {
	                                    if (webElements.get(i).isDisplayed()) {                                     
	                                           WebElement element = driver.findElement(BywebElement);
	                                          action.moveToElement(element).build().perform();                                     
	                                           executor.executeScript("arguments[0].click();", webElements.get(i));                         
	                                           temp = true;
	                                          break;
	                                    }
	                              }
	                              if (temp) return temp;
	                              else return temp;
	                        }                     
	                         return false;
	                  }             
	                   return false;
	            } catch (Exception e1) {                  
	                   e1.printStackTrace();
	                  return  false;
	            }
	      }
	     public void addressValidationNextButton(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	     {
	       
	     	    	By BywebElement1 =KeywordLibrary.findElement(driver, "APP_REG_NEXT_BUTTON2", scenario);
	     	    	if(driver.findElements(BywebElement1).size()!=0) {
	     	    		driver.findElement(BywebElement1).click();
	     	    	}
	     	    	
	     	    	Report reportObject = new Report();
	     			String textFromScreen;
	     			String addressValidationMessage="Data services is down. Please choose Address Validation not required switch to No and proceed.:";
	     			textFromScreen= findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).getText().trim();
	     			
	     			if(textFromScreen != null && textFromScreen.equalsIgnoreCase(addressValidationMessage))
	     			{
	     				verifyElementTextForAddressValidation(homePath, testCase, scenario,browser, objectId, objectName, addressValidationMessage, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
	     				By BywebElement11 =KeywordLibrary.findElement(driver, "APP_REG_NEXT_BUTTON3", scenario);
	     		    	if(driver.findElements(BywebElement11).size()!=0) 
	     		    	{
	     		    		driver.findElement(BywebElement11).click();
	     		    	}	
	     			}
	     }	
	     public void calculatePeriodEndDate(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws ParseException
	     {
	   	  boolean flag =  false;
	   	  WebElement projPerStartMonth;
	   	  WebElement projPerStartDate = null;
	   	  WebElement projPerStartYear;
	   	  String projPerStartDateValue = "";
	   	  int projPerStartDateValue1 = 1 ;
	   	  if(objectName != null && objectName.startsWith("SELF"))
	   		{ 
	   		   projPerStartMonth = driver.findElement(By.xpath("//*[@id='monthprojectionStartDt']"));
	   		   projPerStartYear = driver.findElement(By.xpath("//*[@id='yearprojectionStartDt']"));
	   		  flag = true;
	   		}else{
	   			projPerStartMonth = driver.findElement(By.xpath("//*[@id='monthprojectionStartDate']"));
	   			 projPerStartDate = driver.findElement(By.xpath("//*[@id='dateprojectionStartDate']"));
	   			 projPerStartYear = driver.findElement(By.xpath("//*[@id='yearprojectionStartDate']"));
	   			 projPerStartDateValue = projPerStartDate.getAttribute("Value");
	   			  projPerStartDateValue1 = Integer.parseInt(projPerStartDateValue);
	   		}
	   	  String projPerStartMonthValue = projPerStartMonth.getAttribute("Value");
	   	  int projPerStartMonthValue1 = Integer.parseInt(projPerStartMonthValue);
	   	  
	   		 String projPerStartYearValue = projPerStartYear.getAttribute("Value");
	   		int projPerStartYearValue1 = Integer.parseInt(projPerStartYearValue);
	   		 boolean flag1 = false;

	   		if (projPerStartYear.isEnabled() || projPerStartMonth.isEnabled()) {
	   			if (!("mm".equalsIgnoreCase(projPerStartMonthValue))
	   					&& !("yyyy".equalsIgnoreCase(projPerStartYearValue))
	   					&& !("".equalsIgnoreCase(projPerStartMonthValue.trim()))
	   					&& !("".equalsIgnoreCase(projPerStartYearValue.trim()))){
	   				flag1 = true;
	   			}
	   			if(!flag){
	   				flag1 = false;
	   				if(!flag && projPerStartDate.isEnabled()){
	   					if(!("dd".equalsIgnoreCase(projPerStartDateValue))
	   							&& !("".equalsIgnoreCase(projPerStartYearValue.trim()))){
	   						flag1 = true;
	   				}
	   				}
	   		}
	   				}
	   				if(flag1){
	   					
	   				JavascriptExecutor executor = (JavascriptExecutor)driver;
	   				if(!flag){
	   					String value ="-";
	   					String oldDate = projPerStartYearValue.concat(value).concat(projPerStartMonthValue).concat(value).concat(projPerStartDateValue);
	   					
	   					
	   					Calendar cal = Calendar.getInstance();
	   					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   					 cal.setTime(sdf.parse(oldDate));
	   					cal.add(Calendar.DAY_OF_MONTH, 30);
	   					String newDate = sdf.format(cal.getTime());  
	   					
	   					String[] arrOfStr = newDate.split("-", 0); 
	   					
	   					if(objectName != null && objectName.startsWith("EMPLOYMENT")){
	   						executor.executeScript("arguments[0].value='"+arrOfStr[1]+"';", driver.findElement(By.xpath("//*[@id='monthprojectionEndDate1']")));
	   						executor.executeScript("arguments[0].value='"+arrOfStr[2]+"';", driver.findElement(By.xpath("//*[@id='dateprojectionEndDate1']")));
	   						executor.executeScript("arguments[0].value='"+arrOfStr[0]+"';", driver.findElement(By.xpath("//*[@id='yearprojectionEndDate1']")));
	   					}else{
	   				executor.executeScript("arguments[0].value='"+arrOfStr[1]+"';", driver.findElement(By.xpath("//*[@id='monthprojectionEndDate']")));
	   				executor.executeScript("arguments[0].value='"+arrOfStr[2]+"';", driver.findElement(By.xpath("//*[@id='dateprojectionEndDate']")));
	   				executor.executeScript("arguments[0].value='"+arrOfStr[0]+"';", driver.findElement(By.xpath("//*[@id='yearprojectionEndDate']")));
	   					}
	   				}else{
	   					GregorianCalendar  gcal = new GregorianCalendar(projPerStartYearValue1, projPerStartMonthValue1, projPerStartDateValue1, 0, 0, 0);
	   					LocalDate localDate = LocalDate.of(gcal.get(Calendar.YEAR),
	   					        gcal.get(Calendar.MONTH)+1,
	   					        gcal.get(Calendar.DAY_OF_MONTH));
	   					executor.executeScript("arguments[0].value='"+localDate.getMonthValue()+"';", driver.findElement(By.xpath("//*[@id='monthprojectionEndDt']")));
	   					executor.executeScript("arguments[0].value='"+localDate.getYear()+"';", driver.findElement(By.xpath("//*[@id='yearprojectionEndDt']")));	
	   				}
	   				
	   				
	   			} else {
	   				if(!flag){
	   					if(objectName != null && objectName.startsWith("EMPLOYMENT")){
	   						driver.findElement(By.xpath("//*[@id='monthprojectionEndDate1']")).sendKeys("");
	   						driver.findElement(By.xpath("//*[@id='dateprojectionEndDate1']")).sendKeys("");
	   						driver.findElement(By.xpath("//*[@id='yearprojectionEndDate1']")).sendKeys("");
	   					}else{
	   						driver.findElement(By.xpath("//*[@id='monthprojectionEndDate']")).sendKeys("");
	   						driver.findElement(By.xpath("//*[@id='dateprojectionEndDate']")).sendKeys("");
	   						driver.findElement(By.xpath("//*[@id='yearprojectionEndDate']")).sendKeys("");
	   					}
	   				}else{
	   					driver.findElement(By.xpath("//*[@id='monthprojectionEndDt']")).sendKeys("");
	   					 driver.findElement(By.xpath("//*[@id='yearprojectionEndDt']")).sendKeys("");
	   				}
	   			}
	   		
	   			}
		
	     
	     public void verifyEdgeEligibilitySummaryResults(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	     {
	     			
	     			Report reportObject = new Report();
	     			String tableId      = "";
	     			if(objectName != null && objectName.startsWith("SNAP"))
	     			{
	     				tableId = "searchResults2";
	     			}
	     			if(objectName != null && objectName.startsWith("Cash"))
	     			{
	     				tableId = "searchResults0";
	     			}
	     			if(objectName != null && objectName.startsWith("Medicaid"))
	     			{
	     				tableId = "searchResults1";
	     			}
	     			if(objectName != null && objectName.startsWith("LIHEAP"))
	     			{
	     				tableId = "searchResults3";
	     			}
	     			
	     			String tableIdValue = " \""+tableId+"\" ";
	     			
	     			
	     			
	     			int countOfEDGs = 0;
	     			try{
	     				
	     				WebElement webElement = driver.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody"));
	     				List<WebElement> totalRowsListInScreen = webElement.findElements(By.tagName("tr"));
	     				int totalRowsListSizeInScreen = totalRowsListInScreen.size();
	     				
	     				
	     				String dataPath = homePath+"\\ModuleSheets\\"+scenario+".xlsx";
	     				
	     				POI_ReadExcel poiObject = new POI_ReadExcel();
	     				List<String> whereClause2 = new ArrayList<String>();
	     				whereClause2.add("TestScript::"+testCase);
	     				
	     				Map<String, List<String>> edgeEligibilitySummarySheetMap = poiObject.fetchWithCondition(dataPath,objectName, whereClause2);
	     				
	     				
	     				List edgDataList               = null;
	     				List indvDataList              = null;				
	     				List coeDataList               = null;
	     				List benifitPeriodDataList     = null;
	     				List benefitDataList           = null;
	     				List cgSizeDataList            = null;
	     				List edgStatusData             = null;
	     				List pendingReasonsDataList    = null;
	     				List dispositionStatusDataList = null;
	     				List dispositionDateList       = null;
	     				
	     				  Map<String, List<String>> screenEdgeEligibilitySummaryMap = new HashMap<String, List<String>>();
	     				
	     				  if(objectName != null && objectName.startsWith("Medicaid"))
	     				  {
	     					  for(int rowIndex=0;rowIndex<totalRowsListSizeInScreen;rowIndex++)
	     					  {


	     							int rowCount = rowIndex + 1;
	     							
	     							WebElement tableRow = driver.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]"));								
	     							String rowText = tableRow.getText();						
	     							
	     							 WebElement edgData = driver.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[1]"));
	     							 String edgDataValue = edgData.getText().trim();
	     							 
	     							 List list1 = new ArrayList();
	     							 list1.add(edgDataValue);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("EDG#"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("EDG#");
	     								 existingList.add(edgDataValue);
	     								 screenEdgeEligibilitySummaryMap.put("EDG#", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("EDG#", list1);
	     								 
	     							 }
	     							 
	     							 
	     							 WebElement indvData = driver.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[2]"));
	     							 String indvDataValue = indvData.getText().trim();
	     							 
	     							 
	     							 List list2 = new ArrayList();
	     							 list2.add(indvDataValue);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("Individual"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("Individual");
	     								 existingList.add(indvDataValue);
	     								 screenEdgeEligibilitySummaryMap.put("Individual", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("Individual", list2);							 
	     							 }
	     							 
	     							 WebElement coeData = driver.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[3]"));
	     							 String coeDataValue = coeData.getText().trim();
	     							 
	     							 
	     							 List list23 = new ArrayList();
	     							 list23.add(coeDataValue);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("COE"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("COE");
	     								 existingList.add(coeDataValue);
	     								 screenEdgeEligibilitySummaryMap.put("COE", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("COE", list23);							 
	     							 }
	     							
	     							 
	     							 WebElement benifitPeriodData = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[4]"));
	     							 String benifitPeriodDataValue = benifitPeriodData.getText().trim();
	     							
	     							 
	     							 List list3 = new ArrayList();
	     							 list3.add(benifitPeriodDataValue);
	     							
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("Benefit Period"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("Benefit Period");								 
	     								 existingList.add(benifitPeriodDataValue);
	     								 screenEdgeEligibilitySummaryMap.put("Benefit Period", existingList);								
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("Benefit Period", list3);									
	     							 }						 
	     							 
	     							
	     							 WebElement cgSizeData = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[5]"));
	     							 String cgSizeDataValue = cgSizeData.getText().trim();
	     							
	     							 
	     							 List list5 = new ArrayList();
	     							 list5.add(cgSizeDataValue);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("CG Size"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("CG Size");
	     								 existingList.add(cgSizeDataValue);
	     								 screenEdgeEligibilitySummaryMap.put("CG Size", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("CG Size", list5);							 
	     							 }
	     							 
	     							 WebElement edgStatusData1 = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[6]"));
	     							 String edgStatusDataValue1 = edgStatusData1.getText().trim();							
	     							 
	     							 List list6 = new ArrayList();
	     							 list6.add(edgStatusDataValue1);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("EDG Status"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("EDG Status");
	     								 existingList.add(edgStatusDataValue1);
	     								 screenEdgeEligibilitySummaryMap.put("EDG Status", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("EDG Status", list6);							 
	     							 }
	     							 
	     							 WebElement pendingReasonsData = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[7]"));
	     							 String pendingReasonsDataValue = pendingReasonsData.getText().trim();							
	     							 
	     							 List list7 = new ArrayList();
	     							 list7.add(pendingReasonsDataValue);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("Pending Reasons"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("Pending Reasons");
	     								 existingList.add(pendingReasonsDataValue);
	     								 screenEdgeEligibilitySummaryMap.put("Pending Reasons", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("Pending Reasons", list7);							 
	     							 }
	     							 
	     							 WebElement dispositionStatusData = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[8]"));
	     							 String dispositionStatusDataValue = dispositionStatusData.getText().trim();						
	     							 
	     							 List list8 = new ArrayList();
	     							 list8.add(dispositionStatusDataValue);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("Disposition Status"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("Disposition Status");
	     								 existingList.add(dispositionStatusDataValue);
	     								 screenEdgeEligibilitySummaryMap.put("Disposition Status", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("Disposition Status", list8);							 
	     							 }
	     							 
	     							 WebElement dispositionDate = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[9]"));
	     							 String dispositionDateValue = dispositionDate.getText().trim();							
	     							
	     							 List list9 = new ArrayList();
	     							 list9.add(dispositionDateValue);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("Disposition Date"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("Disposition Date");
	     								 existingList.add(dispositionDateValue);
	     								 screenEdgeEligibilitySummaryMap.put("Disposition Date", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("Disposition Date", list9);							 
	     							 }					
	     							
	     					  }
	     				  }
	     				  else
	     				  {
	     					  for(int rowIndex=0;rowIndex<totalRowsListSizeInScreen;rowIndex++)
	     					  {

	     							int rowCount = rowIndex + 1;
	     							
	     							WebElement tableRow = driver.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]"));								
	     							String rowText = tableRow.getText();						
	     						
	     							 WebElement edgData = driver.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[1]"));
	     							 String edgDataValue = edgData.getText().trim();
	     							 
	     							 List list1 = new ArrayList();
	     							 list1.add(edgDataValue);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("EDG#"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("EDG#");
	     								 existingList.add(edgDataValue);
	     								 screenEdgeEligibilitySummaryMap.put("EDG#", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("EDG#", list1);
	     								 
	     							 }
	     							 
	     							 WebElement coeData = driver.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[2]"));
	     							 String coeDataValue = coeData.getText().trim();
	     							 
	     							 
	     							 List list2 = new ArrayList();
	     							 list2.add(coeDataValue);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("COE"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("COE");
	     								 existingList.add(coeDataValue);
	     								 screenEdgeEligibilitySummaryMap.put("COE", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("COE", list2);							 
	     							 }
	     							
	     							 
	     							 WebElement benifitPeriodData = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[3]"));
	     							 String benifitPeriodDataValue = benifitPeriodData.getText().trim();
	     							
	     							 
	     							 List list3 = new ArrayList();
	     							 list3.add(benifitPeriodDataValue);
	     							
	     							
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("Benefit Period"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("Benefit Period");
	     								
	     								 existingList.add(benifitPeriodDataValue);
	     								 screenEdgeEligibilitySummaryMap.put("Benefit Period", existingList);
	     								 
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("Benefit Period", list3);	
	     								 
	     							 }
	     							 
	     							 
	     							 WebElement benefitData = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[4]"));
	     							 String benefitDataValue = benefitData.getText().trim();
	     							
	     							 
	     							 
	     							 List list4 = new ArrayList();
	     							 list4.add(benefitDataValue);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("Benefit"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("Benefit");
	     								 existingList.add(benefitDataValue);
	     								 screenEdgeEligibilitySummaryMap.put("Benefit", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("Benefit", list4);							 
	     							 }
	     							 
	     							 WebElement cgSizeData = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[5]"));
	     							 String cgSizeDataValue = cgSizeData.getText().trim();
	     							
	     							 
	     							 List list5 = new ArrayList();
	     							 list5.add(cgSizeDataValue);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("CG Size"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("CG Size");
	     								 existingList.add(cgSizeDataValue);
	     								 screenEdgeEligibilitySummaryMap.put("CG Size", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("CG Size", list5);							 
	     							 }
	     							 
	     							 WebElement edgStatusData1 = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[6]"));
	     							 String edgStatusDataValue1 = edgStatusData1.getText().trim();
	     							
	     							 
	     							 List list6 = new ArrayList();
	     							 list6.add(edgStatusDataValue1);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("EDG Status"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("EDG Status");
	     								 existingList.add(edgStatusDataValue1);
	     								 screenEdgeEligibilitySummaryMap.put("EDG Status", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("EDG Status", list6);							 
	     							 }
	     							 
	     							 WebElement pendingReasonsData = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[7]"));
	     							 String pendingReasonsDataValue = pendingReasonsData.getText().trim();
	     							
	     							 
	     							 List list7 = new ArrayList();
	     							 list7.add(pendingReasonsDataValue);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("Pending Reasons"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("Pending Reasons");
	     								 existingList.add(pendingReasonsDataValue);
	     								 screenEdgeEligibilitySummaryMap.put("Pending Reasons", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("Pending Reasons", list7);							 
	     							 }
	     							 
	     							 WebElement dispositionStatusData = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[8]"));
	     							 String dispositionStatusDataValue = dispositionStatusData.getText().trim();
	     						
	     							 
	     							 List list8 = new ArrayList();
	     							 list8.add(dispositionStatusDataValue);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("Disposition Status"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("Disposition Status");
	     								 existingList.add(dispositionStatusDataValue);
	     								 screenEdgeEligibilitySummaryMap.put("Disposition Status", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("Disposition Status", list8);							 
	     							 }
	     							 
	     							 WebElement dispositionDate = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[9]"));
	     							 String dispositionDateValue = dispositionDate.getText().trim();
	     							
	     							
	     							 List list9 = new ArrayList();
	     							 list9.add(dispositionDateValue);
	     							 if(screenEdgeEligibilitySummaryMap.containsKey("Disposition Date"))
	     							 {
	     								 List existingList = (List) screenEdgeEligibilitySummaryMap.get("Disposition Date");
	     								 existingList.add(dispositionDateValue);
	     								 screenEdgeEligibilitySummaryMap.put("Disposition Date", existingList);
	     							 }
	     							 else
	     							 {
	     								 screenEdgeEligibilitySummaryMap.put("Disposition Date", list9);							 
	     							 }
	     						
	     							
	     						
	     					  }
	     					
	     				  }
	     					
	     				 
	     				    System.out.println(" ");
	     				    System.out.println("#From Screen#screenEdgeEligibilitySummaryMap::"+screenEdgeEligibilitySummaryMap);
	     				    System.out.println("#From Sheet #edgeEligibilitySummarySheetMap::"+edgeEligibilitySummarySheetMap);
	     				    System.out.println(" ");
	     				    
	     				    if(edgeEligibilitySummarySheetMap != null && edgeEligibilitySummarySheetMap.containsKey("TestScript"))
	     				    {
	     				    	List list11 = (List)edgeEligibilitySummarySheetMap.get("TestScript");
	     				    	int list11Size = list11.size();
	     				    	for(int index=0;index<list11Size;index++)
	     					    {
	     				    		if(edgeEligibilitySummarySheetMap.containsKey("EDG#"))
	     							{    
	     				    			edgDataList  = null;
	     								edgDataList = (List)edgeEligibilitySummarySheetMap.get("EDG#");								
	     							}
	     				    		if(screenEdgeEligibilitySummaryMap.containsKey("EDG#"))
	     				    		{
	     				    			List screenEdgList = (List)screenEdgeEligibilitySummaryMap.get("EDG#");				    			

	     			    				String sheetEdgeData = (String)edgDataList.get(index);			    			
	     			    				if(sheetEdgeData == null || sheetEdgeData.equalsIgnoreCase("0") || sheetEdgeData.equalsIgnoreCase("NA") || sheetEdgeData.equalsIgnoreCase(""))//As per Dilip Suggestion
	     			    				{			    					
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" EDG#", "The "+objectName+" EDG# verification Success for the case.("+sheetEdgeData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);				    				 
	     			    				}
	     			    				else
	     			    				{
	     			    					if(screenEdgList.contains(sheetEdgeData))
	     				    				{				    				  
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" EDG#", "The "+objectName+" EDG# verification Success for the case.("+sheetEdgeData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);				    				  
	     				    				}
	     				    				else
	     				    				{				    					
	     				    					 reportObject.Log("Verifying the Edge Status of "+objectName+" EDG#", "The "+objectName+" EDG# verification Failed for the case.("+sheetEdgeData+")", 
	     					    						  Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     			    				}		    				
	     			    			
	     				    		}
	     				    		
	     				    		
	     				    		if(edgeEligibilitySummarySheetMap.containsKey("Individual"))
	     							{
	     								indvDataList = null;
	     								indvDataList = (List)edgeEligibilitySummarySheetMap.get("Individual");
	     							}
	     							if(screenEdgeEligibilitySummaryMap.containsKey("Individual"))
	     				    		{
	     				    			List screenINDVList = (List)screenEdgeEligibilitySummaryMap.get("Individual");
	     				    			
	     			    				String sheetINDVData = (String)indvDataList.get(index);
	     			    			
	     			    				if(sheetINDVData == null || sheetINDVData.equalsIgnoreCase("") || sheetINDVData.equalsIgnoreCase("NA"))//As per Dilip Suggestion
	     			    				{			    						
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" Individual", "The "+objectName+" Individual verification Success for the case.("+sheetINDVData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     			    				}
	     			    				else
	     			    				{
	     			    					if(screenINDVList.contains(sheetINDVData))
	     				    				{	
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" Individual", "The "+objectName+" Individual verification Success for the case.("+sheetINDVData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     				    				else
	     				    				{
	     				    					reportObject.Log("Verifying the Edge Status of "+objectName+" Individual", "The "+objectName+" Individual verification Failed for the case.("+sheetINDVData+")", 
	     					    						  Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     			    				}
	     				    		}
	     				    		
	     				    		//indv for medicaid end
	     			    				
	     							if(edgeEligibilitySummarySheetMap.containsKey("COE"))
	     							{
	     								coeDataList = null;
	     								coeDataList = (List)edgeEligibilitySummarySheetMap.get("COE");
	     							}
	     							if(screenEdgeEligibilitySummaryMap.containsKey("COE"))
	     				    		{
	     				    			List screenCOEList = (List)screenEdgeEligibilitySummaryMap.get("COE");
	     				    			
	     			    				String sheetCOEData = (String)coeDataList.get(index);
	     			    				if(sheetCOEData != null)
	     			    				{
	     			    					sheetCOEData = sheetCOEData.trim();
	     			    				}
	     			    				
	     			    				if(sheetCOEData == null || sheetCOEData.equalsIgnoreCase("") || sheetCOEData.equalsIgnoreCase("NA"))//As per Dilip Suggestion
	     			    				{
	     			    						
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" COE", "The "+objectName+" COE verification Success for the case.("+sheetCOEData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     			    				}
	     			    				else
	     			    				{
	     			    					if(screenCOEList.contains(sheetCOEData))
	     				    				{				    				  	
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" COE", "The "+objectName+" COE verification Success for the case.("+sheetCOEData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     				    				else
	     				    				{				    					
	     				    					reportObject.Log("Verifying the Edge Status of "+objectName+" COE", "The "+objectName+" COE verification Failed for the case.("+sheetCOEData+")", 
	     					    						  Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     			    				}			    				
	     				    		}
	     							
	     							if(edgeEligibilitySummarySheetMap.containsKey("Benefit Period"))
	     							{
	     								benifitPeriodDataList = null;
	     								benifitPeriodDataList = (List)edgeEligibilitySummarySheetMap.get("Benefit Period");
	     							}
	     							
	     							if(screenEdgeEligibilitySummaryMap.containsKey("Benefit Period"))
	     							{
	     				    			List screenbenifitPeriodList = (List)screenEdgeEligibilitySummaryMap.get("Benefit Period");
	     				    			
	     				    			//failed thatss wt triming
	     			    				String sheetBenifitPeriodData = (String)benifitPeriodDataList.get(index).toString().trim();
	     			    							    				
	     			    				if(sheetBenifitPeriodData == null || sheetBenifitPeriodData.equalsIgnoreCase("") || sheetBenifitPeriodData.equalsIgnoreCase("NA"))//As per Dilip Suggestion
	     			    				{			    					
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" Benefit Period", "The "+objectName+" Benefit Period verification Success for the case.("+sheetBenifitPeriodData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     			    				}
	     			    				else
	     			    				{
	     			    					if(screenbenifitPeriodList.contains(sheetBenifitPeriodData))
	     				    				{				    				  
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" Benefit Period", "The "+objectName+" Benefit Period verification Success for the case.("+sheetBenifitPeriodData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     				    				else
	     				    				{				    					
	     				    					reportObject.Log("Verifying the Edge Status of "+objectName+" Benefit Period", "The "+objectName+" Benefit Period verification Failed for the case.("+sheetBenifitPeriodData+")", 
	     					    						  Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     			    				}
	     			    				
	     				    		}
	     							
	     							if(edgeEligibilitySummarySheetMap.containsKey("Benefit"))
	     							{   benefitDataList = null;
	     								benefitDataList = (List)edgeEligibilitySummarySheetMap.get("Benefit");
	     							}
	     							if(screenEdgeEligibilitySummaryMap.containsKey("Benefit"))
	     							{
	     				    			List screenBenefitList = (List)screenEdgeEligibilitySummaryMap.get("Benefit");				    		

	     			    				String sheeteBenefitData = (String)benefitDataList.get(index);
	     			    				
	     			    				if(sheeteBenefitData == null || sheeteBenefitData.equalsIgnoreCase("") || sheeteBenefitData.equalsIgnoreCase("0")|| sheeteBenefitData.equalsIgnoreCase("NA"))//As per Dilip Suggestion
	     			    				{			    					
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" Benefit", "The "+objectName+" Benefit verification Success for the case.("+sheeteBenefitData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     			    				}
	     			    				else
	     			    				{
	     			    					if(screenBenefitList.contains(sheeteBenefitData))
	     				    				{				    				 
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" Benefit", "The "+objectName+" Benefit verification Success for the case.("+sheeteBenefitData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     				    				else
	     				    				{				    					
	     				    					reportObject.Log("Verifying the Edge Status of "+objectName+" Benefit", "The "+objectName+" Benefit verification Failed for the case.("+sheeteBenefitData+")", 
	     					    						  Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     			    				}
	     			    				
	     				    		}
	     							if(edgeEligibilitySummarySheetMap.containsKey("CG Size"))
	     							{
	     								cgSizeDataList = null;
	     								cgSizeDataList = (List)edgeEligibilitySummarySheetMap.get("CG Size");
	     							}
	     							if(screenEdgeEligibilitySummaryMap.containsKey("CG Size"))
	     							{
	     				    			List screencgSizetList = (List)screenEdgeEligibilitySummaryMap.get("CG Size");				    			

	     			    				String sheetecgSizeData = (String)cgSizeDataList.get(index);			    			
	     			    				
	     			    				if(sheetecgSizeData == null || sheetecgSizeData.equalsIgnoreCase("") || sheetecgSizeData.equalsIgnoreCase("NA"))//As per Dilip Suggestion
	     			    				{			    					
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" CG Size", "The "+objectName+" CG Size verification Success for the case.("+sheetecgSizeData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     			    				}
	     			    				else
	     			    				{
	     			    					if(screencgSizetList.contains(sheetecgSizeData))
	     				    				{				    				 
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" CG Size", "The "+objectName+" CG Size verification Success for the case.("+sheetecgSizeData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     				    				else
	     				    				{				    					
	     				    					reportObject.Log("Verifying the Edge Status of "+objectName+" CG Size", "The "+objectName+" CG Size verification Failed for the case.("+sheetecgSizeData+")", 
	     					    						  Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     			    				}
	     			    							    			
	     				    		}
	     							if(edgeEligibilitySummarySheetMap.containsKey("EDG Status"))
	     							{
	     								edgStatusData = null;
	     								edgStatusData = (List)edgeEligibilitySummarySheetMap.get("EDG Status");
	     							}
	     							if(screenEdgeEligibilitySummaryMap.containsKey("EDG Status"))
	     							{
	     				    			List screenEdgStatustList = (List)screenEdgeEligibilitySummaryMap.get("EDG Status");				    		
	     			    				String sheetEdgStatusData = (String)edgStatusData.get(index);
	     			    				
	     			    				if(sheetEdgStatusData != null)
	     			    				{
	     			    					sheetEdgStatusData = sheetEdgStatusData.trim();
	     			    				}
	     			    				
	     			    				if(sheetEdgStatusData == null || sheetEdgStatusData.equalsIgnoreCase("") || sheetEdgStatusData.equalsIgnoreCase("NA"))//As per Dilip Suggestion
	     			    				{			    				
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" EDG Status", "The "+objectName+" EDG Status verification Success for the case.("+sheetEdgStatusData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     			    				}
	     			    				else
	     			    				{
	     			    					if(screenEdgStatustList.contains(sheetEdgStatusData))
	     				    				{				    				  
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" EDG Status", "The "+objectName+" EDG Status verification Success for the case.("+sheetEdgStatusData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     				    				else
	     				    				{				    					
	     				    					reportObject.Log("Verifying the Edge Status of "+objectName+" EDG Status", "The "+objectName+" EDG Status verification Success for the case.("+sheetEdgStatusData+")", 
	     					    						  Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     			    				}
	     			    				
	     				    		}
	     							if(edgeEligibilitySummarySheetMap.containsKey("Pending Reasons"))
	     							{
	     								pendingReasonsDataList = null;
	     								pendingReasonsDataList = (List)edgeEligibilitySummarySheetMap.get("Pending Reasons");
	     							}
	     							if(screenEdgeEligibilitySummaryMap.containsKey("Pending Reasons"))
	     							{
	     				    			List screenPendingReasonsList = (List)screenEdgeEligibilitySummaryMap.get("Pending Reasons");
	     				    			
	     			    				String sheetPendingReasonsData = (String)pendingReasonsDataList.get(index);
	     			    				
	     			    				if(sheetPendingReasonsData == null || sheetPendingReasonsData.equalsIgnoreCase("") || sheetPendingReasonsData.equalsIgnoreCase("NA"))//As per Dilip Suggestion
	     			    				{			    					
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" Pending Reasons", "The "+objectName+" Pending Reasons verification Success for the case.("+sheetPendingReasonsData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     			    				}
	     			    				else
	     			    				{
	     			    					if(screenPendingReasonsList.contains(sheetPendingReasonsData))
	     				    				{				    				  
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" Pending Reasons", "The "+objectName+" Pending Reasons verification Success for the case.("+sheetPendingReasonsData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     				    				else
	     				    				{				    					
	     				    					reportObject.Log("Verifying the Edge Status of "+objectName+" Pending Reasons", "The "+objectName+" Pending Reasons verification Success for the case.("+sheetPendingReasonsData+")", 
	     					    						  Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     			    				}
	     			    							    			
	     				    		}
	     							if(edgeEligibilitySummarySheetMap.containsKey("Disposition Status"))
	     							{
	     								dispositionStatusDataList = null;
	     								dispositionStatusDataList = (List)edgeEligibilitySummarySheetMap.get("Disposition Status");
	     							}
	     							if(screenEdgeEligibilitySummaryMap.containsKey("Disposition Status"))
	     							{
	     				    			List screenDispositionStatusList = (List)screenEdgeEligibilitySummaryMap.get("Disposition Status");
	     				    		
	     			    				String sheetDispositionStatusData = (String)dispositionStatusDataList.get(index);
	     			    				if(sheetDispositionStatusData != null)
	     			    				{
	     			    					sheetDispositionStatusData = sheetDispositionStatusData.trim();
	     			    				}
	     			    				
	     			    				if(sheetDispositionStatusData == null || sheetDispositionStatusData.equalsIgnoreCase("") || sheetDispositionStatusData.equalsIgnoreCase("NA"))//As per Dilip Suggestion
	     			    				{			    					 
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" Disposition Status", "The "+objectName+" Disposition Status verification Success for the case.("+sheetDispositionStatusData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     			    				}
	     			    				else
	     			    				{
	     			    					if(screenDispositionStatusList.contains(sheetDispositionStatusData))
	     				    				{				    				  
	     				    				  reportObject.Log("Verifying the Edge Status of "+objectName+" Disposition Status", "The "+objectName+" Disposition Status verification Success for the case.("+sheetDispositionStatusData+")", 
	     				    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     				    				else
	     				    				{				    					
	     				    					reportObject.Log("Verifying the Edge Status of "+objectName+" Disposition Status", "The "+objectName+" Disposition Status verification Success for the case.("+sheetDispositionStatusData+")", 
	     					    						  Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     			    				}
	     			    				
	     				    		}
	     							if(edgeEligibilitySummarySheetMap.containsKey("Disposition Date"))
	     							{
	     								dispositionDateList = null;
	     								dispositionDateList = (List)edgeEligibilitySummarySheetMap.get("Disposition Date");
	     							}
	     							if(screenEdgeEligibilitySummaryMap.containsKey("Disposition Date"))
	     							{
	     				    			List screenDispositionDateList = (List)screenEdgeEligibilitySummaryMap.get("Disposition Date");
	     				    			
	     			    				String sheetDispositionDateData = (String)dispositionDateList.get(index);
	     			    				
	     			    				if(sheetDispositionDateData != null)
	     			    				{
	     			    					sheetDispositionDateData = sheetDispositionDateData.trim();
	     			    				}
	     			    				
	     			    				if(sheetDispositionDateData == null || sheetDispositionDateData.equalsIgnoreCase("") || sheetDispositionDateData.equalsIgnoreCase("NA"))//As per Dilip Suggestion
	     			    				{			    					 
	     				    					reportObject.Log("Verifying the Edge Status of "+objectName+" Disposition Date", "The "+objectName+" Disposition Date Status verification Success for the case.("+sheetDispositionDateData+")", 
	     					    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     			    				}
	     			    				else
	     			    				{
	     			    					if(screenDispositionDateList.contains(sheetDispositionDateData))
	     				    				{				    				  
	     				    					reportObject.Log("Verifying the Edge Status of "+objectName+" Disposition Date", "The "+objectName+" Disposition Date Status verification Success for the case.("+sheetDispositionDateData+")", 
	     					    						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);

	     				    				}
	     				    				else
	     				    				{				    					
	     				    					reportObject.Log("Verifying the Edge Status of "+objectName+" Disposition Date", "The "+objectName+" Disposition Date Status verification Success for the case.("+sheetDispositionDateData+")", 
	     					    						  Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	     				    				}
	     			    				}
	     			    				
	     				    		}
	     					    }
	     				    }				
	     				Thread.sleep(8000);
	     						
	     			}catch(Exception e){
	     				e.printStackTrace();				
	     			}
	     			
	     		
	     		}
	     
	      public void verifyEdgeStatusResults(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
		  {	  			
		  	    	System.out.println("##verifyEdgeStatusResults():"+objectName+" Start: ");
		  			Report reportObject = new Report();
		  			String tableId      = "";
		  			if(objectName != null && objectName.startsWith("SNAP"))
		  			{
		  				tableId = "searchResults2";
		  			}
		  			if(objectName != null && objectName.startsWith("Cash"))
		  			{
		  				tableId = "searchResults0";
		  			}
		  			if(objectName != null && objectName.startsWith("Medicaid"))
		  			{
		  				tableId = "searchResults1";
		  			}
		  			if(objectName != null && objectName.startsWith("LIHEAP"))
		  			{
		  				tableId = "searchResults3";
		  			}
		  			
		  			String tableIdValue = " \'"+tableId+"\' ";
		  			System.out.println(tableIdValue);
		  			System.out.println("##verifyEdgeStatusResults():"+objectName+":"+tableId);
		  			
		  			try{
		  				
		  				WebElement webElement = driver.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody"));
		  				List<WebElement> totalRowsListInScreen = webElement.findElements(By.tagName("tr"));
		  				int totalRowsListSizeInScreen = totalRowsListInScreen.size();
		  				System.out.println("Total number of Rows in the Screen for the "+objectName+ " : "+ totalRowsListSizeInScreen);
		  				
		  				    int columnCount = 3;
					    	if(objectName != null && objectName.startsWith("Medicaid"))
				  			{
					    		columnCount++;
				  			}
		  		
		  				  Map<Integer, String> map1 = new HashMap<Integer, String>();
		  				  int value=0;
		  					
		  				    for(int rowIndex=0;rowIndex<totalRowsListSizeInScreen;rowIndex++)
		  					{
		  				    	int rowCount = rowIndex + 1;
		  				    	
		  				    	WebElement tableRow = driver.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]"));								
								String rowText = tableRow.getText();			
								
		  				    	 WebElement edgStatusData1 = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[6]"));
		 						 String edgStatusDataValue1 = edgStatusData1.getText().trim();
								 
								  WebElement benifitPeriodData = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td["+columnCount+"]"));
		 						  String benifitPeriodDataValue = benifitPeriodData.getText().trim();
		 						  
		 						 WebElement dispositionStatusData = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[8]"));
		 						 String dispositionStatusDataValue = dispositionStatusData.getText().trim();
		 						 
		 						 WebElement dispositionDate = tableRow.findElement(By.xpath("//*[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[9]"));
		 						 String dispositionDateValue = dispositionDate.getText().trim();
		 						
		 						
		 						//as per Dlilip Suggestions on March12,Disoposition status and Disposistion Date should be empty for on going denied or terminated cases..
		 						// if("DENIED".equalsIgnoreCase(edgStatusDataValue1) || "TERIMINATED".equalsIgnoreCase(edgStatusDataValue1)){
		 						if(
		 								  ((dispositionStatusDataValue==null && dispositionDateValue==null) 
		 								|| (dispositionStatusDataValue=="" && dispositionDateValue==""))
		 								&& ("DENIED".equalsIgnoreCase(edgStatusDataValue1) || "TERIMINATED".equalsIgnoreCase(edgStatusDataValue1)))
		 						{
		 							map1.put(rowCount, benifitPeriodDataValue);
		 							
		 							WebElement elems = driver.findElement(By.xpath("//table[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]"));
		 	  						String rowText1 = elems.getText();	
		 	  						
		 	  						
		 	  						List<WebElement> cells =elems.findElements(By.xpath("td["+columnCount+"]"));
		 	  			          
		 	  			          if(cells != null && cells.get(0).getText().endsWith("-"))
		 	  			          {	 	  			        	
		 	  			        	if(objectName != null && objectName.startsWith("Medicaid"))
			 	  			         {		 	  			        	
			 	  			        	driver.findElement(By.xpath("//table[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[4]/a")).click();
			 	  			        	
			 	  			         }
			 	  			         else
			 	  			         {		 	  			        	
			 	  			        	driver.findElement(By.xpath("//table[@id="+tableIdValue+"]/tbody/tr["+rowCount+"]/td[3]/a")).click();
			 	  			        	
			 	  			         } 
		 	  			     	    boolean flag = false;
			 	 		  			String failedDATA = null;
			 	 		  			List<String> data = new ArrayList<String>();
			 	 		  			  for(int i=1; i<=5;i++)
			 	 		  			  {
			 	 		  			    WebElement tableElements = driver.findElement(By.xpath("//table[@class='pageHeaderTable'][3]/tbody/tr["+i+"]"));
			 	 		  			
			 	 		  			   List<WebElement> cellsDATA =tableElements.findElements(By.xpath("td[1]"));	
			 	 		  				 if(cellsDATA.get(0).getText().contains("Fail"))
			 	 		  				 {
			 	 		  					flag = true;
			 	 		  					 failedDATA = cellsDATA.get(0).getText();
			 	 		  					data.add(failedDATA); 
			 	 	  				 }
			 	 	  			  }
			 	 		  			  
			 	 	
			 	 		  			  
			 	 	  			/*reportObject.Log("Verifying the Edge Status of "+objectName+" EDG#", " "+objectName+" EDG# on going denied for "+data+" for the case.", 
			 	 						  Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);*/
			 	 	  			
			 	 	  		        reportObject.Log("Verifying the Edge Status of "+objectName+" EDG#", " "+objectName+" EDG# on going denied for "+data+" for the case.", 
		 	 						  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			 	 	  		
			 	 	  			  if(flag)
			 	 	  			  {
			 	 	  				    WebElement noticeReasons = driver.findElement(By.xpath("//*[@id='tab2']"));	
			 	 						noticeReasons.click();
			 	 						/*reportObject.Log("Verifying the notice reason of "+objectName+" EDG#", ""+objectName+" EDG# notice reasons for on going denied for "+data+" for the case.", 
			 	 							  Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);*/
			 	 						reportObject.Log("Verifying the notice reason of "+objectName+" EDG#", ""+objectName+" EDG# notice reasons for on going denied for "+data+" for the case.", 
				 	 							  Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			 	 						WebElement eligSummary = driver.findElement(By.xpath("//*[@id='summaryButton']"));
			 	 						eligSummary.click();
			 	 	  			  }
			 	 	  			  
		 	  			         }	 	  			            
		 	  			      
		 						 }
		  					}
		  				    
		  				  
		  				Thread.sleep(8000);
		  						
		  			}catch(Exception e)
		  			{
		  				e.printStackTrace();	  				
		  			}
		  		System.out.println("##veirfyEdgeEligibilitySummaryResults():"+objectName+" end ");
		  	}
	    
	      
	      public void getRequiredColumnFromDB(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws InterruptedException
	      {

	      		String columnValueFromDB  = "";		
	      		Util   utilObject = new Util();
	      		System.out.println("dataValue:Query:"+dataValue);
	      		if(dataValue != null && !dataValue.equalsIgnoreCase(""))
	      		{
	      			String query = dataValue;
	      			 HashMap columnValuesMapfromDB = null;
	      			
	      	    	try{
	      	    		    //columnValueFromDB = utilObject.getDataFromDB(query, utilObject.getDBParametersINI(utilObject.getValueFromConfigProperties("EnvironmentName"), "IES_SCHEMA"));
	      					columnValuesMapfromDB = getDataMapFromDB(query, utilObject.getDBParametersINI(utilObject.getValueFromConfigProperties("EnvironmentName"), "IES_SCHEMA"));
	      				}catch(Exception e){
	      					e.printStackTrace();
	      				}
	      				
	      				try{
	      					homePath  = new File (".").getCanonicalPath();
	      					File file = new File(homePath+"\\OutputDataINI.ini");
	      					Wini a    = new Wini(file);
	      					a.load(file);
	      					
	      					if(columnValuesMapfromDB != null && columnValuesMapfromDB.size()>0)
	      					{
	      						Set set =columnValuesMapfromDB.entrySet();
	      						Iterator iterator = set.iterator();
	      						while(iterator.hasNext())
	      						{
	      							Object obj = iterator.next();
	      							Map.Entry entry = (Map.Entry)obj;
	      							String columNameFromQuery = (String) entry.getKey();
	      							Object columnValueFromDatabase =entry.getValue();
	      							
	      							a.put(scenario+"_"+testCase, columNameFromQuery, columnValueFromDatabase);
	      							
	      							//System.out.println("##columNameFromQuery::"+columNameFromQuery);
	      							//System.out.println("##columnValueFromDatabase::"+columnValueFromDatabase);								
	      							columnValueFromDatabase = null;
	      						}
	      					}
	      					
	      					a.store();
	      					
	      				}catch(Exception e){
	      					e.printStackTrace();
	      				}			
	      		}
	      		
	      	
	      }
	      
	      public HashMap getDataMapFromDB(String query, String schema) throws ClassNotFoundException, SQLException
	      {

	    	  Util util = new Util();
	      	     HashMap dataMapFromDB = new HashMap();		 
	      		 String hostName = util.getDBParametersINI(util.getValueFromConfigProperties("EnvironmentName"),"HostName");
	      		 
	      		 String serviceName=util.getDBParametersINI(util.getValueFromConfigProperties("EnvironmentName"),"ServiceName");
	      		 String sid=util.getDBParametersINI(util.getValueFromConfigProperties("EnvironmentName"),"SID");
	      		 String connectionURL="";
	      		 String username= "";
	      		 String encryptedPassword="";
	      		 String iesUsername= "";
	      		 String iesPassword="";
	      		 String hixUsername= "";
	      		 String hixPassword="";
	      		 String decryptedPassword="";
	      		 
	      		 if(util.getDBParametersINI(util.getValueFromConfigProperties("EnvironmentName"),"IndividualUser").equalsIgnoreCase("Y")){
	      			 username= util.getDBParametersINI(util.getValueFromConfigProperties("EnvironmentName"),"UserName");
	      			 encryptedPassword=util.getDBParametersINI(util.getValueFromConfigProperties("EnvironmentName"),"Password");
	      			 //Temporary solution
	      			 decryptedPassword = encryptedPassword;
	      			 //decryptedPassword = base64decode(encryptedPassword);
	      		 }else{
	      			 iesUsername= util.getDBParametersINI(util.getValueFromConfigProperties("EnvironmentName"),"IES_UserName");
	      			 iesPassword=util.getDBParametersINI(util.getValueFromConfigProperties("EnvironmentName"),"IES_Password");
	      			 hixUsername= util.getDBParametersINI(util.getValueFromConfigProperties("EnvironmentName"),"HIX_UserName");
	      			 hixPassword=util.getDBParametersINI(util.getValueFromConfigProperties("EnvironmentName"),"HIX_Password");
	      		 }
	      		 
	      		 
	      		 if(!(serviceName.equalsIgnoreCase(""))){
	      		 	connectionURL="jdbc:oracle:thin:@"+hostName.trim()+":1521/"+serviceName.trim();
	      		 }else{
	      			connectionURL="jdbc:oracle:thin:@"+hostName.trim()+":1521:"+sid.trim();
	      		 }
	      		 
	      		 String caseNumber="";
	      		 String edgeNumber = "";
	      		 //System.out.println(connectionURL);
	      		 Class.forName("oracle.jdbc.driver.OracleDriver");
	      		 
	      		 Connection con = null;
	      		 
	      		 System.out.println("URL"+connectionURL+" Username "+username.trim()+" DecriptedPassword "+decryptedPassword);
	      		 try {
	      			 if(util.getDBParametersINI(util.getValueFromConfigProperties("EnvironmentName"),"IndividualUser").equalsIgnoreCase("Y")){
	      				 con = DriverManager.getConnection(connectionURL,username.trim(),decryptedPassword);
	      			 }else{
	      				 if(schema.contains("IE") || schema.contains("ie")){
	      					 con = DriverManager.getConnection(connectionURL,iesUsername.trim(),iesPassword.trim());
	      				 }else if(schema.contains("hix") || schema.contains("HIX")){
	      					 con = DriverManager.getConnection(connectionURL,hixUsername.trim(),hixPassword.trim());
	      				 }
	      			 }
	      			 
	      		 } catch (SQLException e) {
	      			 e.printStackTrace();
	      		 }
	      		 
	      		 //nagaraju changes for getting colunnames...start		 
	      		
	      			String firstOccuranceFROMKeyWord = query.split("FROM")[0];
	      			System.out.println("firstOccuranceFROMKeyWord::"+firstOccuranceFROMKeyWord);
	      			LinkedList columnNamesList = new LinkedList();
	      			if(firstOccuranceFROMKeyWord != null && firstOccuranceFROMKeyWord.contains(","))
	      			{
	      				String[] splitArray=firstOccuranceFROMKeyWord.split(",");
	      				int length = splitArray.length;
	      				for(int i=0;i<length;i++)
	      				{

	      					System.out.println("elements in array::"+splitArray[i]);
	      					if(splitArray[i].contains("SELECT") || splitArray[i].contains("select") || splitArray[i].contains("Select"))
	      					{
	      						//String splitArray2[] = splitArray[i].split("\\s+");
	      						if(splitArray[i] != null)
	      						{
	      							splitArray[i] = splitArray[i].trim();
	      							String splitArray2[] = splitArray[i].split("\\s+");
	      							if(splitArray2.length >=1)
	      							{
	      								String columname = splitArray2[1];
	      								columnNamesList.add(columname);
	      							}
	      						}
	      						
	      					}
	      					else
	      					{
	      						columnNamesList.add(splitArray[i]);
	      					}				
	      				
	      				}
	      			}
	      			else
	      			{
	      				if(firstOccuranceFROMKeyWord != null)
	      				{
	      					firstOccuranceFROMKeyWord = firstOccuranceFROMKeyWord.trim();
	      					String splitArray3[] = firstOccuranceFROMKeyWord.split("\\s+");
	      					
	      					if(splitArray3.length >=1)
	      					{
	      						String columname = splitArray3[1];
	      						columnNamesList.add(columname);
	      					}
	      				}
	      				
	      			}
	      		 System.out.println("columnNamesList::"+columnNamesList);
	      		 //end
	      		 
	      		 PreparedStatement ps = null;
	      		 
	      		 /*//ps = con.prepareStatement("alter session set current_schema="+schema);//ommented by nagaraju for test
	      		 ps = con.prepareStatement(query);
	      		 ps.executeQuery();*/
	      		 
	      		 try {
	      			 ps = con.prepareStatement(query);
	      		 } catch (SQLException e) {
	      			e.printStackTrace();
	      		 }
	      		 
	      		 ResultSet rs=ps.executeQuery();
	      		 
	      		 while(rs.next())
	      		 {
	      			 for(int index=1;index<=columnNamesList.size();index++)
	      			 {				 
	      				 dataMapFromDB.put(columnNamesList.get(index-1), rs.getObject(index));
	      			 }			
	      		 }
	      		 con.close();
	      		 
	      	    System.out.println("dataMapFromDB:: "+dataMapFromDB);
	      		return dataMapFromDB;
	      	
	      }

	      
	      
	      public void enableConditionalDropDown(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	      {
	       
	      			System.out.println("objectId:"+objectId);
	      			
	      			if(objectId != null)
	      			{
	      				String splitArray[]=objectId.split("=");
	      				String xpathArray[]=objectId.split("\\.");
	      				
	      				if(xpathArray.length > 1)
	      				{
	      					System.out.println("xpathArray[1]::"+xpathArray[1]);
	      					
	      					try{		  				
	      		  				
	      		  				WebElement webElement = driver.findElement(By.xpath(xpathArray[1]));
	      		  				
	      		  				boolean isEnabledFlag = webElement.isEnabled();
	      						System.out.println(isEnabledFlag);
	      						if(isEnabledFlag == false)
	      						{
	      							JavascriptExecutor executor = (JavascriptExecutor)driver;						
	      							String toEnable ="";
	      							
	      							if(splitArray.length > 1)
	      							{
	      								String id= splitArray[1];
	      								System.out.println("@id::"+id);
	      								String[] idArray=id.split("]");
	      								System.out.println("@idArray.length::"+idArray.length);
	      								if(idArray.length > 0)
	      								{									
	      									String obj = idArray[0];
	      									System.out.println("@obj::"+obj);
	      									if(objectId.contains("@id"))
	      									{
	      										toEnable = "document.getElementById("+obj+").removeAttribute('disabled');";
	      										System.out.println("@id:toEnable:"+toEnable);
	      									}
	      									if(objectId.contains("@name"))
	      									{
	      										toEnable = "document.getElementsByName("+obj+").removeAttribute('disabled');";
	      										System.out.println("@name:toEnable:"+toEnable);
	      									}									
	      									executor.executeScript(toEnable);
	      									
	      									try {
	      										Thread.sleep(3000);
	      									} catch (InterruptedException e) {
	      										// TODO Auto-generated catch block
	      										e.printStackTrace();
	      									}
	      								}
	      							}
	      							
	      						}
	      		  			
	      		  			}
	      		  			catch (Exception e) {
	      		  				e.printStackTrace();
	      						// TODO: handle exception
	      					}
	      					
	      				
	      				}				
	      			}			
	      		
	      		
	      }
	      
	      public void calculatePregnancyDueDate(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws ParseException
	        {
			  Util utilObject = new Util();
	          boolean flag =  false;
	          WebElement pregStartMonth = null;
	          WebElement pregStartDate = null;
	          WebElement pregStartYear = null;
	          int pregStartDateValue1 ;
	          
	          String[] monthStartDate = utilObject.getObjectFromObjectMap("PREGNANT_CIRCUMSTANCE_BEGIN_MONTH",scenario).split("xpath:.");
	          String[] dateStartDate = utilObject.getObjectFromObjectMap("PREGNANT_CIRCUMSTANCE_BEGIN_DATE",scenario).split("xpath:.");
	          String[] yearhStartDate = utilObject.getObjectFromObjectMap("PREGNANT_CIRCUMSTANCE_BEGIN_YEAR",scenario).split("xpath:.");
	            pregStartMonth= driver.findElement(By.xpath(monthStartDate[1]));
	            pregStartDate= driver.findElement(By.xpath(dateStartDate[1]));
	            pregStartYear = driver.findElement(By.xpath(yearhStartDate[1]));
	                                                
	          	String pregStartDateValue = pregStartDate.getAttribute("Value");
	          	pregStartDateValue1 = Integer.parseInt(pregStartDateValue);
	                                   
	            String pregStartMonthValue = pregStartMonth.getAttribute("Value");
	            int pregStartMonthValue1 = Integer.parseInt(pregStartMonthValue);
	          
	            String pregStartYearValue = pregStartYear.getAttribute("Value");
	            int pregStartYearValue1 = Integer.parseInt(pregStartYearValue);
	            boolean flag1 = false;

	          if (pregStartYear.isEnabled() || pregStartMonth.isEnabled() || pregStartDate.isEnabled()) {
	                 if (!("mm".equalsIgnoreCase(pregStartMonthValue))
	                  && !("dd".equalsIgnoreCase(pregStartDateValue))
	                  && !("yyyy".equalsIgnoreCase(pregStartYearValue))
	                  && !("".equalsIgnoreCase(pregStartMonthValue.trim()))
	                  && !("".equalsIgnoreCase(pregStartDateValue.trim()))
	                  && !("".equalsIgnoreCase(pregStartYearValue.trim()))){
	                    flag1 = true;
	                 }
	                 JavascriptExecutor executor = (JavascriptExecutor)driver;
	                 if(flag1){
	                   String value ="-";
	                   String oldDate = pregStartYearValue.concat(value).concat(pregStartMonthValue).concat(value).concat(pregStartDateValue);
	                   Calendar cal = Calendar.getInstance();
	                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                   cal.setTime(sdf.parse(oldDate));
	                   cal.add(Calendar.DAY_OF_MONTH, 270);
	                   String newDate = sdf.format(cal.getTime());  
	                   String[] arrOfStr = newDate.split("-", 0);
	                   String month= arrOfStr[1];
	                   String date= arrOfStr[2];
	                   String year= arrOfStr[0];
	                   
	                   
	                String[] monthDueDate = utilObject.getObjectFromObjectMap("PREGNANCY_DUE_MONTH",scenario).split("xpath:.");
	       			String[] dateDueDate = utilObject.getObjectFromObjectMap("PREGNANCY_DUE_DATE",scenario).split("xpath:.");
	       			String[] yearDueDate = utilObject.getObjectFromObjectMap("PREGNANCY_DUE_YEAR",scenario).split("xpath:.");
	       			executor.executeScript("arguments[0].value='"+month+"';", driver.findElement(By.xpath(monthDueDate[1])));
	                executor.executeScript("arguments[0].value='"+date+"';", driver.findElement(By.xpath(dateDueDate[1])));
	                executor.executeScript("arguments[0].value='"+year+"';", driver.findElement(By.xpath(yearDueDate[1])));   
	                                    
	                }                               

	               }
	   
	      }
	      
	      public void fileClearanceMatchCheck(String homePath, String testCase, String scenario, String browser,
					String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
					String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) 
			{
				Util utilObject                = new Util();
				Report reportObject            = new Report();			
				String FileClearanceMatchFound = "Potential matches for ";
				String xpath                   = utilObject.getObjectFromObjectMap("POTENTIAL_MATCHES_XPATH",scenario);
				String notFoundXpath           = utilObject.getObjectFromObjectMap("NO_POTENTIAL_MATCHES_XPATH",scenario);
				String notFoundMessage         = "AR12011: No potential matches found for the individual.";
				
				String notFoundTextFromScreen = findElementByType(driver, scenario, testCase, homePath,
						currentIteration.intValue(), notFoundXpath, browser, passScreenshot, browserFolder).getText().trim();
				
				String textFromScreen = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), xpath,
						browser, passScreenshot, browserFolder).getText().trim();
				
				if (notFoundTextFromScreen != null && notFoundTextFromScreen.equalsIgnoreCase(notFoundMessage)) 
				{
					System.out.println("fileClearanceMatchCheck():Match Not Found:");
				}
				else
				{
					if (textFromScreen != null && textFromScreen.contains(FileClearanceMatchFound)) 
					{
						System.out.println("fileClearanceMatchCheck():Match Found:");
						String viewAssociatedCasesArray[]= utilObject.getObjectFromObjectMap("FILE_CLEARANCE_VIEWASSOCIATED_CASE",scenario).split("xpath:.");
						if(viewAssociatedCasesArray.length >= 1)
						{
							WebElement FILE_CLEARANCE_VIEWASSOCIATED = driver
									.findElement(By.xpath(viewAssociatedCasesArray[1]));
							Select FILE_CLEARANCE_VIEWASSOCIATED_CASE = new Select(FILE_CLEARANCE_VIEWASSOCIATED);
							FILE_CLEARANCE_VIEWASSOCIATED_CASE.selectByVisibleText("NO");
						}
						
						String estabilshNewArray[]= utilObject.getObjectFromObjectMap("FILE_CLEARANCE_ESTABLISHNEW_CASE",scenario).split("xpath:.");
						
						if(estabilshNewArray.length >= 1)
						{
							WebElement FILE_CLEARANCE_ESTABLISHNEW = driver
									.findElement(By.xpath(estabilshNewArray[1]));
							Select FILE_CLEARANCE_ESTABLISHNEW_CASE_SELECTION = new Select(FILE_CLEARANCE_ESTABLISHNEW);
							FILE_CLEARANCE_ESTABLISHNEW_CASE_SELECTION.selectByVisibleText("YES");
						}					

					}
				}
			}
	      


	  	
	  	public void generateRandomMBINumber(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
		{
			Util utilObject = new Util();
			Random random1=new Random();
			String randomNum1 = "123456789";
			char position1 = randomNum1.charAt(random1.nextInt(randomNum1.length()));
			String dataValue1 = String.valueOf(position1);
			
			Random random2 = new Random();
			String randomChar2 = "ACDEFGHJKMNPQRTUVWXY";
			char position2 = randomChar2.charAt(random2.nextInt(randomChar2.length()));
			String dataValue2 = String.valueOf(position2);
			
			Random random3 = new Random();
			String randomNumChar3 = "123456789";
			char position3 = randomNumChar3.charAt(random3.nextInt(randomNumChar3.length()));
			String dataValue3 = String.valueOf(position3);
			
			Random random4=new Random();
			String randomNum4 = "123456789";
			char position4 = randomNum4.charAt(random4.nextInt(randomNum4.length()));
			String dataValue4 = String.valueOf(position4);
			
			String MBI1 = new StringBuilder().append(position1)
	        .append(position2)
	        .append(position3)
	        .append(position4)
	        .toString();
		
			Random random5 = new Random();
			String randomChar5 = "ACDEFGHJKMNPQRTUVWXY";
			char position5 = randomChar5.charAt(random5.nextInt(randomChar5.length()));
			String dataValue5 = String.valueOf(position5);
			
			Random random6 = new Random();
			String randomNumChar6 = "123456789";
			char position6 = randomNumChar6.charAt(random6.nextInt(randomNumChar6.length()));
			String dataValue6 = String.valueOf(position6);
			
			Random random7=new Random();
			String randomNum7 = "123456789";
			char position7 = randomNum7.charAt(random7.nextInt(randomNum7.length()));
			String dataValue7 = String.valueOf(position7);
			
			String MBI2 = new StringBuilder()
	        .append(position5)
	        .append(position6)
	        .append(position7)
	        .toString();
			
			
			Random random8 = new Random();
			String randomChar8 = "ACDEFGHJKMNPQRTUVWXY";
			char position8 = randomChar8.charAt(random8.nextInt(randomChar8.length()));
			String dataValue8 = String.valueOf(position8);
			
			Random random9 = new Random();
			String randomChar9 = "ACDEFGHJKMNPQRTUVWXY";
			char position9 = randomChar9.charAt(random9.nextInt(randomChar9.length()));
			String dataValue9 = String.valueOf(position9);
			
			Random random10=new Random();
			String randomNum10 = "123456789";
			char position10 = randomNum10.charAt(random10.nextInt(randomNum10.length()));
			String dataValue10 = String.valueOf(position10);
			
			Random random11=new Random();
			String randomNum11 = "123456789";
			char position11 = randomNum11.charAt(random11.nextInt(randomNum11.length()));
			String dataValue11 = String.valueOf(position11);
			
			String MBI3 = new StringBuilder()
			        .append(position8)
			        .append(position9)
			        .append(position10)
			        .append(position11)
			        .toString();
			
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			
			String[] MBI1Array = utilObject.getObjectFromObjectMap("MBI_1",scenario).split("xpath:.");
			String[] MBI2Array = utilObject.getObjectFromObjectMap("MBI_2",scenario).split("xpath:.");
			String[] MBI3Array = utilObject.getObjectFromObjectMap("MBI_3",scenario).split("xpath:.");
			executor.executeScript("arguments[0].value='"+MBI1+"';", driver.findElement(By.xpath(MBI1Array[1])));
            executor.executeScript("arguments[0].value='"+MBI2+"';", driver.findElement(By.xpath(MBI2Array[1])));
            executor.executeScript("arguments[0].value='"+MBI3+"';", driver.findElement(By.xpath(MBI3Array[1])));
			
            /*executor.executeScript("arguments[0].value='"+MBI1+"';", driver.findElement(By.xpath("//*[@id='mBIIdentifier_5']")));
            executor.executeScript("arguments[0].value='"+MBI2+"';", driver.findElement(By.xpath("//*[@id='mBIIdentifier_6']")));
            executor.executeScript("arguments[0].value='"+MBI3+"';", driver.findElement(By.xpath("//*[@id='mBIIdentifier_7']")));
            */   
		}
	  	
	  	public void generateRandomNumberByGivenRange(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	    {
	    	Report reportObject = new Report();
	     if(dataValue != null && !dataValue.equalsIgnoreCase(""))        
	     {
	         int length = Integer.parseInt(dataValue);
	         if(length > 0)
	         {
	              Random random = new Random();
	              char[] digits = new char[length];
	              digits[0] = (char) (random.nextInt(9) + '1');
	              for (int i = 1; i < length; i++) {
	                  digits[i] = (char) (random.nextInt(10) + '0');
	           }              
	              dataValue=String.valueOf(Long.parseLong(new String(digits)));
	              System.out.println("dataValue::"+dataValue);               
	              
	              findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
	                 findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);
	                 
	                 //WriteDataToOutputFile(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
	                 reportObject.Log("Entering random generated text in the text box "+objectName, "Entered the random number "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	         }
	    
	     }
	    }

	  	
	  	public void calculateLOCEndDate(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws ParseException
        {
		  Util utilObject = new Util();
          boolean flag =  false;
          WebElement locStartMonth = null;
          WebElement locStartDate = null;
          WebElement locStartYear = null;
          int locStartDateValue1 ;
          
          String[] monthStartDate = utilObject.getObjectFromObjectMap("LOC_START_MONTH",scenario).split("xpath:.");
          String[] dateStartDate = utilObject.getObjectFromObjectMap("LOC_START_DATE",scenario).split("xpath:.");
          String[] yearhStartDate = utilObject.getObjectFromObjectMap("LOC_START_YEAR",scenario).split("xpath:.");
          locStartMonth= driver.findElement(By.xpath(monthStartDate[1]));
          locStartDate= driver.findElement(By.xpath(dateStartDate[1]));
          locStartYear = driver.findElement(By.xpath(yearhStartDate[1]));
                                                
          	String locStartDateValue = locStartDate.getAttribute("Value");
          	locStartDateValue1 = Integer.parseInt(locStartDateValue);
                                   
            String locStartMonthValue = locStartMonth.getAttribute("Value");
            int locStartMonthValue1 = Integer.parseInt(locStartMonthValue);
          
            String locStartYearValue = locStartYear.getAttribute("Value");
            int locStartYearValue1 = Integer.parseInt(locStartYearValue);
            boolean flag1 = false;

          if (locStartYear.isEnabled() || locStartDate.isEnabled() || locStartMonth.isEnabled()) {
                 if (!("mm".equalsIgnoreCase(locStartMonthValue))
                  && !("dd".equalsIgnoreCase(locStartDateValue))
                  && !("yyyy".equalsIgnoreCase(locStartYearValue))
                  && !("".equalsIgnoreCase(locStartMonthValue.trim()))
                  && !("".equalsIgnoreCase(locStartDateValue.trim()))
                  && !("".equalsIgnoreCase(locStartYearValue.trim()))){
                    flag1 = true;
                 }
                 JavascriptExecutor executor = (JavascriptExecutor)driver;
                 if(flag1){
                   String value ="-";
                   String oldDate = locStartYearValue.concat(value).concat(locStartMonthValue).concat(value).concat(locStartDateValue);
                   Calendar cal = Calendar.getInstance();
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                   cal.setTime(sdf.parse(oldDate));
                   cal.add(Calendar.DAY_OF_MONTH, 365);
                   String newDate = sdf.format(cal.getTime());  
                   String[] arrOfStr = newDate.split("-", 0);
                   String month= arrOfStr[1];
                   String date= arrOfStr[2];
                   String year= arrOfStr[0];
                   
                   
                String[] locEndMonth = utilObject.getObjectFromObjectMap("LOC_END_MONTH",scenario).split("xpath:.");
       			String[] locEndDate = utilObject.getObjectFromObjectMap("LOC_END_DATE",scenario).split("xpath:.");
       			String[] locEndyear = utilObject.getObjectFromObjectMap("LOC_END_YEAR",scenario).split("xpath:.");
       			executor.executeScript("arguments[0].value='"+month+"';", driver.findElement(By.xpath(locEndMonth[1])));
                executor.executeScript("arguments[0].value='"+date+"';", driver.findElement(By.xpath(locEndDate[1])));
                executor.executeScript("arguments[0].value='"+year+"';", driver.findElement(By.xpath(locEndyear[1])));   
                                    
                }                               

               }
   
      }
	  	
	    public void enableConditionalCheckBox(String homePath, String testCase, String scenario, String browser,String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) 
		{		
			System.out.println("objectId:" + objectId);
			if (objectId != null) {
				String splitArray[] = objectId.split("=");
				String xpathArray[] = objectId.split("\\.");

				if (xpathArray.length > 1) {
					System.out.println("xpathArray[1]::" + xpathArray[1]);

					try {

						WebElement webElement = driver.findElement(By.xpath(xpathArray[1]));

						boolean isEnabledFlag = webElement.isEnabled();
						System.out.println(isEnabledFlag);
						if (isEnabledFlag == false) {
							JavascriptExecutor executor = (JavascriptExecutor) driver;
							String toEnable = "";

							if (splitArray.length > 1) {
								String id = splitArray[1];
								System.out.println("@id::" + id);
								String[] idArray = id.split("]");
								System.out.println("@idArray.length::" + idArray.length);
								if (idArray.length > 0) {
									String obj = idArray[0];
									System.out.println("@obj::" + obj);
									
									if (objectId.contains("@id")) {									
										toEnable = "document.getElementById(" + obj + ").disabled= false;";
										System.out.println("@id:toEnable:" + toEnable);
									}
									if (objectId.contains("@name")) {									
										toEnable = "document.getElementsByName(" + obj + ")[0].disabled= false;";
										System.out.println("@name:toEnable:" + toEnable);
									}
									executor.executeScript(toEnable);
									boolean enabledCheckbox = webElement.isEnabled();
									System.out.println(enabledCheckbox);								

									try {
										Thread.sleep(3000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}

				}
			}

		}
	    
	    
		public void alertHandler(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws ParseException
        {
			
			try {
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
        }
		
		
		
		public void switchToActiveFrame(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
	 		
			try {
				try {
					
					List<WebElement> iframes= driver.findElements(By.tagName("iframe"));
					if(!iframes.isEmpty()) {
					for(int i=0;i<iframes.size();i++) {
						if(iframes.get(i).isDisplayed()) {
							driver.switchTo().frame(iframes.get(i).getAttribute("id"));
							break;
						}
						
						
					}
					}else {
						System.out.println("No such iframes with tagname iframe in this page"+driver.getTitle());
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			
	 	}
		
public void switchToCustomWindow(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
	 		
			try {
				 final String pagetitle=objectName;
				 final String parent_window=driver.getWindowHandle();
				
				 if(parent_window!=null || !parent_window.isEmpty()) {
					 
					 Set<String> windows=driver.getWindowHandles();
					 
					 for (String openwindows : windows) {
						 
				        driver.switchTo().window(openwindows);
				        System.out.println("Title :"+driver.getTitle());
				        if(driver.getTitle().contains(pagetitle) && !openwindows.equalsIgnoreCase(parent_window)) {
				        	
				        	
				        	captureScreenshot(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
				        	
				        	break;
				        }else {
				        	continue;
				        }
				 
				          }
					 
					 
					 if(!driver.getWindowHandle().equalsIgnoreCase(parent_window)) {
						driver.switchTo().window(parent_window) ;
					 }
					 
				 }
				 
				 
					
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
	 	}




//ADA Implementation

public void checkADACompliantUsingWave(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
	Report reportObject = new Report();	
	Util util=new Util();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	try {
		
		boolean wavelaunch=false;
		
		if(KeywordLibrary.WaitForPageLoad(driver) && util.getValueFromConfigProperties("ADA_TESTING_WAVE_TOOL").equalsIgnoreCase("Yes")) {
			
			driver.switchTo().defaultContent();
			
			launchWave(driver, true);
			
			List<WebElement> wavetags=driver.findElements(By.tagName("iframe"));

			
			for(int i=0;i<wavetags.size();i++) {
				if(wavetags.get(i).isDisplayed()) {

					
					
					if(wavetags.get(i).getAttribute("id").equalsIgnoreCase("wave_sidebar_container")) {
						driver.switchTo().frame(wavetags.get(i).getAttribute("id"));
						System.out.println("*******Wave launched Successfully on "+driver.getTitle()+"***** ");
						wavelaunch=true;
					     break;
					}
					
				}else continue;
				
			}
			
			if(wavelaunch && KeywordLibrary.WaitForPageLoad(driver)) {
				
				int errors,alerts,features,structelements,html5nAria,contrastErrors=-1;
				
			   List<WebElement> errors_list=driver.findElements(By.xpath(".//li[@id='error']/span"));
				List<WebElement> alerts_list=driver.findElements(By.xpath(".//li[@id='alert']/span"));
				
			
				List<WebElement> features_list=driver.findElements(By.xpath(".//li[@id='feature']/span"));
				
				List<WebElement> structelements_list=driver.findElements(By.xpath(".//li[@id='structure']/span"));
				
				List<WebElement> html5nAria_list=driver.findElements(By.xpath(".//li[@id='html5']/span"));
				
				List<WebElement> contrastErrors_list=driver.findElements(By.xpath(".//li[@id='contrast']/span"));
				
				List<String> errors_wave=new ArrayList<String>();
				
				if(errors_list.isEmpty()) {
					errors=0;
					
				}else {
					errors=Integer.parseInt(errors_list.get(0).getText());
					
				}
				errors_wave.add("Errors :: "+errors);
				
				if(alerts_list.isEmpty()) {
					alerts=0;
				}else {
					alerts=Integer.parseInt(alerts_list.get(0).getText());
					
				}
				errors_wave.add("Alerts :: "+alerts);
				
				if(features_list.isEmpty()) {
					features=0;
				}else {
					features=Integer.parseInt(features_list.get(0).getText());
					
				}
				
				errors_wave.add("Features :: "+features);
				
				if(structelements_list.isEmpty()) {
					structelements=0;
				}else {
					structelements=Integer.parseInt(structelements_list.get(0).getText());
					
				}
			
				errors_wave.add("Structural Elements :: "+structelements);
				
				if(html5nAria_list.isEmpty()) {
					html5nAria=0;
				}else {
					html5nAria=Integer.parseInt(html5nAria_list.get(0).getText());
					
				}
				
				errors_wave.add("HTML ARIA :: "+html5nAria);
				
				if(contrastErrors_list.isEmpty()) {
					contrastErrors=0;
				}else {
					contrastErrors=Integer.parseInt(contrastErrors_list.get(0).getText());
					
				}
				
				errors_wave.add("CONTRAST ERRORS ::"+contrastErrors);
				
				
			String HtmlReport="";
			
			for(int i=0;i<errors_wave.size();i++) {
				HtmlReport=HtmlReport + errors_wave.get(i)+" ; ";
			}
				
			if(util.getValueFromConfigProperties("ADA_TESTING_WAVE_TOOL_DETAILED_REPORTS").equalsIgnoreCase("YES")) {
			
				if(KeywordLibrary.WaitForPageLoad(driver)) {
					reportObject.Log("ADA WAVE VALIDATIONS FOR PAGE "+driver.getTitle(),HtmlReport , Status.pass, driver, testCase, scenario, browser, "YES", browserFolder);
					
					if(KeywordLibrary.WaitForPageLoad(driver)) {
						if(driver.findElements(By.xpath(".//a[text()='Details']")).size()!=0) {
							driver.findElement(By.xpath(".//a[text()='Details']")).click();
							
						}
					}
					
					if(KeywordLibrary.WaitForPageLoad(driver)) {
						if(driver.findElements(By.xpath(".//a[text()='Contrast']")).size()!=0) {
							driver.findElement(By.xpath(".//a[text()='Contrast']")).click();
							reportObject.Log("ADA WAVE VALIDATIONS FOR  CONTRAST ERRORS IN PAGE "+driver.getTitle(),"CONTRAST ERRORS : "+contrastErrors , Status.pass, driver, testCase, scenario, browser, "YES", browserFolder);
							
						}
					}
					
				}
				
				if(KeywordLibrary.WaitForPageLoad(driver)) {
					if(driver.findElements(By.xpath(".//a[text()='Styles']")).size()!=0) {
						driver.findElement(By.xpath(".//a[text()='Styles']")).click();
				
					}
				}
				if(KeywordLibrary.WaitForPageLoad(driver)) {
					
					if(KeywordLibrary.WaitForPageLoad(driver)) {
						if(driver.findElements(By.xpath(".//a[text()='Details']")).size()!=0) {
							driver.findElement(By.xpath(".//a[text()='Details']")).click();
						
						}}
					
					
					if(driver.findElements(By.xpath(".//input[@id='toggle_group_error']")).size()!=0) {
						reportObject.Log("ADA WAVE VIOLATIONS FOR ERRORS IN PAGE "+driver.getTitle(),"PAGE ERROR VIOLATIONS :"+errors , Status.FAIL, driver, testCase, scenario, browser, "YES", browserFolder);
						driver.findElement(By.xpath(".//input[@id='toggle_group_error']")).click();
						KeywordLibrary.WaitForPageLoad(driver);
					}else {
						reportObject.Log("ADA WAVE VIOLATIONS FOR ERRORS IN PAGE"+driver.getTitle(),"NO PAGE ERROR VIOLATIONS IN THIS PAGE" , Status.pass, driver, testCase, scenario, browser, "YES", browserFolder);
						
					}
					
					if(driver.findElements(By.xpath(".//input[@id='toggle_group_alert']")).size()!=0) {
						
						reportObject.Log("ADA WAVE VIOLATIONS FOR ALERTS IN PAGE  "+driver.getTitle(),"PAGE ALERTS VIOLATIONS :"+alerts , Status.FAIL, driver, testCase, scenario, browser, "YES", browserFolder);
						driver.findElement(By.xpath(".//input[@id='toggle_group_alert']")).click();
						KeywordLibrary.WaitForPageLoad(driver);
						
					}else {
						reportObject.Log("ADA WAVE VIOLATIONS FOR ALERTS IN PAGE  "+driver.getTitle(),"NO PAGE ALERT VIOLATIONS IN THIS PAGE" , Status.pass, driver, testCase, scenario, browser, "YES", browserFolder);
						
					}
					
					if(driver.findElements(By.xpath(".//input[@id='toggle_group_feature']")).size()!=0) {
						
						reportObject.Log("ADA WAVE VIOLATIONS FOR FEATURE ELEMENTS IN PAGE "+driver.getTitle(),"FEATURES ELEMENT VIOLATIONS :"+features , Status.FAIL, driver, testCase, scenario, browser, "YES", browserFolder);
						driver.findElement(By.xpath(".//input[@id='toggle_group_feature']")).click();
						KeywordLibrary.WaitForPageLoad(driver);
						
					}else {
						reportObject.Log("ADA WAVE VIOLATIONS FOR FEATURE ELEMENTS IN PAGE "+driver.getTitle(),"NO  FEATURES ELEMENTS VIOLATIONS IN THIS PAGE" , Status.pass, driver, testCase, scenario, browser, "YES", browserFolder);
						
					}
					
					if(driver.findElements(By.xpath(".//input[@id='toggle_group_structure']")).size()!=0) {
						
						reportObject.Log("ADA WAVE VIOLATIONS FOR STRUCTRE ELEMENTS IN PAGE "+driver.getTitle(),"STRUCTURAL ELEMENT VIOLATIONS :"+structelements , Status.FAIL, driver, testCase, scenario, browser, "YES", browserFolder);
						driver.findElement(By.xpath(".//input[@id='toggle_group_structure']")).click();
						KeywordLibrary.WaitForPageLoad(driver);
						
					}else {
						reportObject.Log("ADA WAVE VIOLATIONS FOR STRUCTRE ELEMENTS IN PAGE "+driver.getTitle(),"NO STRUCTRE ELEMENTS VIOLATIONS IN THIS PAGE"  , Status.pass, driver, testCase, scenario, browser, "YES", browserFolder);
						
					}

					if(driver.findElements(By.xpath(".//input[@id='toggle_group_html5']")).size()!=0) {
	
						reportObject.Log("ADA WAVE VALIDAIONS HTML5 ARIA FOR PAGE"+driver.getTitle(), "HTML5 ARIA ELEMENT VIOLATIONS :"+html5nAria, Status.FAIL, driver, testCase, scenario, browser, "YES", browserFolder);
						driver.findElement(By.xpath(".//input[@id='toggle_group_html5']")).click();
						KeywordLibrary.WaitForPageLoad(driver);
	
					}else {
						reportObject.Log("ADA WAVE VALIDAIONS HTML5 ARIA FOR PAGE "+driver.getTitle(),"NO HTML5 ARIA  VIOLATIONS IN THIS PAGE" , Status.pass, driver, testCase, scenario, browser, "YES", browserFolder);
	
					}

					
				}
				
			}
					try {
					
					launchWave(driver, false);
					
					driver.switchTo().defaultContent();
					
					}catch (Exception e) {
						
						driver.switchTo().defaultContent();
						// TODO: handle exception
					}
					String filepath=util.getValueFromConfigProperties("ADA_TESTING_WAVE_TOOL_EXCEL_REPORT_PATH");
					int lastrow=ExcelRead.getLastRow(filepath, "ADA_WAVE_SUMMARY", -1);
					
					lastrow=lastrow+1;
					ExcelRead.WriteDataIntoExcel(filepath, "ADA_WAVE_SUMMARY", driver.getTitle(),lastrow,lastrow,0,0 );
					ExcelRead.WriteDataIntoExcel(filepath, "ADA_WAVE_SUMMARY", errors+"",lastrow,lastrow,1,1 );
					ExcelRead.WriteDataIntoExcel(filepath, "ADA_WAVE_SUMMARY", contrastErrors+"",lastrow,lastrow,2,2);
					ExcelRead.WriteDataIntoExcel(filepath, "ADA_WAVE_SUMMARY", features+"",lastrow,lastrow,3,3 );
					
					ExcelRead.WriteDataIntoExcel(filepath, "ADA_WAVE_SUMMARY", structelements+"",lastrow,lastrow,4,4 );

					ExcelRead.WriteDataIntoExcel(filepath, "ADA_WAVE_SUMMARY", html5nAria+"",lastrow,lastrow,5,5 );
					
				
				
				
			
				
			}
			
			
		}
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	
}


public static void launchWave(WebDriver driver,boolean pageaction) {

	boolean wavelaunch=false;
	try {
		
		if(KeywordLibrary.WaitForPageLoad(driver)) {
			if(!pageaction) {
				
			driver.switchTo().defaultContent();
				
			List<WebElement> wavetags=driver.findElement(By.tagName("iframe"));
			
			for(int i=0;i<wavetags.size();i++) {
				if(wavetags.get(i).isDisplayed()) {
					if(wavetags.get(i).getAttribute("id").equalsIgnoreCase("wave_sidebar_container")) {
						System.out.println("*******Wave working on "+driver.getTitle()+"***** ");
						 try {
	                    	  roboActions(driver);
	                      }catch (Exception e) {
							// TODO: handle exception
						}
						wavelaunch=true;
						return ;
					}
					
				}else continue;
				
			}
			
			if(!wavelaunch) {
				 try {
               	  roboActions(driver);
                 }catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			}
			else {
			
					if(KeywordLibrary.WaitForPageLoad(driver))  {
                      try {
                    	  roboActions(driver);
                      }catch (Exception e) {
						// TODO: handle exception
					}
					} 	
			}
			

		}
		
		
	}catch (Exception e) {
		
		if(!wavelaunch) {
			 try {
				roboActions(driver);
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		// TODO: handle exception
	}
	
	
	
}

public void handleForbiddenPageError(String homePath, String testCase, String scenario, String browser, String objectId,
		String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
		String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) {
	Report reportObject = new Report();
	ReusableFunctions rf = new ReusableFunctions();
	Util utilObject  = new Util();
	try {
		if (driver.getPageSource().contains("The resource you have requested is secured by Access Manager WebSEAL") || driver.getPageSource().contains("The username and password combination you have entered does not match") || driver.getPageSource().contains("Your account has been deactivated")) {
			reportObject.Log("Forbidden Page was displayed", "DONE", Status.done, driver, testCase, scenario, browser, "YES", browserFolder);
			
			rf.navigateToHIXURL(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
			waitForPageToLoad(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
			String sectionName = utilObject.getData("SectionName", driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder);
			String userName = utilObject.getDataINI(sectionName, "HIX_USER_ID");
			driver.findElement(By.xpath("//a[@href='CreateGenericUserAccount?accountFlow=true'][contains(text(),'Log In')]")).click();
			driver.findElement(By.id("username")).clear();
			driver.findElement(By.id("username")).sendKeys(userName);
			driver.findElement(By.id("password")).click();
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys("Deloitte@1");
			driver.findElement(By.xpath("//a[@id='submit-button-pkmsForm']")).click();	
			Thread.sleep(5000);
			waitForPageToLoad(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
			if (driver.getPageSource().contains("How can we help you?")){
				 reportObject.Log("Handle Forbidden Page", "Handled", Status.done, driver, testCase, scenario, browser, "YES", browserFolder);	
			} else {
				reportObject.Log("Handle Forbidden Page", "Handled", Status.Blocker, driver, testCase, scenario, browser, "YES", browserFolder);	
			}
		} else {
			reportObject.Log("Forbidden Page was not displayed", "Handled forbidden Page", Status.done, driver, testCase, scenario, browser, "YES", browserFolder);	

		}	
	} catch (Exception e) {
		// TODO: handle exception
	}

	
}

	
public static void roboActions(WebDriver driver) throws AWTException, InterruptedException {

    Actions action=new Actions(driver);
    
	  Robot robot=new Robot();
 
        //contextClick() method to do right click on the element
        action.contextClick().build().perform();
  
    Thread.sleep(500);
    robot.keyPress(KeyEvent.VK_DOWN);
    Thread.sleep(500);
 
    
    robot.keyPress(KeyEvent.VK_DOWN);
    Thread.sleep(500);
    robot.keyPress(KeyEvent.VK_DOWN);
    Thread.sleep(500);
    robot.keyPress(KeyEvent.VK_DOWN);
    Thread.sleep(500);
    robot.keyPress(KeyEvent.VK_DOWN);
    Thread.sleep(500);
    robot.keyPress(KeyEvent.VK_DOWN);
    Thread.sleep(500);
    robot.keyPress(KeyEvent.VK_DOWN);
    Thread.sleep(500);
    robot.keyPress(KeyEvent.VK_ENTER);
    
    Thread.sleep(500);
    KeywordLibrary.WaitForPageLoad(driver);
}
	

public void calculateApplicationDateBySubtractgivenMonth(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
{

	Calendar calendar   = Calendar.getInstance();
	int givenNoOfMonths = Integer.parseInt(dataValue);
	
	calendar.add(Calendar.MONTH, -givenNoOfMonths);

	int year  = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH) + 1;
	int date  = calendar.get(Calendar.DATE);
	
	System.out.println("#calculateApplicationDateBySubtractgivenMonth():year:" + year);
	System.out.println("#calculateApplicationDateBySubtractgivenMonth():month:" + month);
	System.out.println("#calculateApplicationDateBySubtractgivenMonth():date:" + date);

	Util utilObject = new Util();
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	

	String[] caliCulatedApplicationReceivedMonth = utilObject.getObjectFromObjectMap("APPLICATION_MONTH_RECEIVED", scenario).split("xpath:.");
	String[] caliCulatedApplicationReceivedDate = utilObject.getObjectFromObjectMap("APPLICATION_DATE_RECEIVED", scenario).split("xpath:.");
	String[] caliCulatedApplicationReceivedYear = utilObject.getObjectFromObjectMap("APPLICATION_YEAR_RECEIVED", scenario).split("xpath:.");
	
	executor.executeScript("arguments[0].value='" + month + "';", driver.findElement(By.xpath(caliCulatedApplicationReceivedMonth[1])));
	executor.executeScript("arguments[0].value='" + date + "';", driver.findElement(By.xpath(caliCulatedApplicationReceivedDate[1])));
	executor.executeScript("arguments[0].value='" + year + "';", driver.findElement(By.xpath(caliCulatedApplicationReceivedYear[1])));

 }


	public void calculateDateBySubtractingGivenMonths(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		
		Util utilObject     = new Util();
		Report reportObject = new Report();
		
		if(objectName!=null){
		String [] objectNameList = objectName.split(";");
		if(objectNameList!=null && objectNameList.length==3){
		String  monthObject = objectNameList[0];
	    String  dateObject = objectNameList[1];
	    String  yearObject = objectNameList[2];
	    
	    String monthValue="";
	    String dateValue="";
	    String yearValue="";
	    String monthXpathId="";
	    String dateXpathId="";
	    String yearXpathId="";
	    
		String monthObjetIdXpath = utilObject.getObjectFromObjectMap(monthObject, scenario);
		
		if(monthObjetIdXpath.contains("@id")){  
		monthXpathId = monthObjetIdXpath.substring(monthObjetIdXpath.indexOf('\'')+1,monthObjetIdXpath.length()-2);
					
		monthValue = driver.findElement(By.id(monthXpathId)).getAttribute("value");	
		}
		
		if(monthObjetIdXpath.contains("@name")){ 			
		monthXpathId = monthObjetIdXpath.substring(monthObjetIdXpath.indexOf('\'')+1,monthObjetIdXpath.length()-2);
							
		monthValue = driver.findElement(By.name(monthXpathId)).getAttribute("value");	
		}
				    
		String dateObjetIdXpath = utilObject.getObjectFromObjectMap(dateObject, scenario);
		if(dateObjetIdXpath.contains("@id")){ 			
		dateXpathId = dateObjetIdXpath.substring(dateObjetIdXpath.indexOf('\'')+1,dateObjetIdXpath.length()-2);
		dateValue = driver.findElement(By.name(dateXpathId)).getAttribute("value");
	    }
	    
	    if(dateObjetIdXpath.contains("@name")){							
		dateXpathId = dateObjetIdXpath.substring(dateObjetIdXpath.indexOf('\'')+1,dateObjetIdXpath.length()-2);
		dateValue = driver.findElement(By.name(dateXpathId)).getAttribute("value");
		}
	    
	    String yearObjetIdXpath = utilObject.getObjectFromObjectMap(yearObject, scenario);
		if(yearObjetIdXpath.contains("@id")){			
		yearXpathId = yearObjetIdXpath.substring(yearObjetIdXpath.indexOf('\'')+1,yearObjetIdXpath.length()-2);
		yearValue = driver.findElement(By.name(yearXpathId)).getAttribute("value");
	    }
		
	    if(yearObjetIdXpath.contains("@name")){		
		yearXpathId = yearObjetIdXpath.substring(yearObjetIdXpath.indexOf('\'')+1,yearObjetIdXpath.length()-2);
		yearValue = driver.findElement(By.name(yearXpathId)).getAttribute("value");
		}
	    
		int monthFromScreen=Integer.parseInt(monthValue);
		int dateFromScreen=Integer.parseInt(dateValue);
		int yearFromScreen=Integer.parseInt(yearValue);
		
		int givenMonth = Integer.parseInt(dataValue);
		int requiredMonth = monthFromScreen - givenMonth;
		 
		Calendar calendar   = Calendar.getInstance();
		calendar.set(yearFromScreen, monthFromScreen, dateFromScreen);
		calendar.add(Calendar.MONTH, -givenMonth);

		int year  = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int date  = calendar.get(Calendar.DATE);
		  			  		
		System.out.println("#calculateDateBySubtractingGivenMonths():year:" + year);
		System.out.println("#calculateDateBySubtractingGivenMonths():month:" + month);
		System.out.println("#calculateDateBySubtractingGivenMonths():date:" + date);

		Util utilObject1 = new Util();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		  			  		
   
		String[] calculatedMonth = utilObject1.getObjectFromObjectMap(monthObject, scenario).split("xpath:.");
		String[] calculatedDate = utilObject1.getObjectFromObjectMap(dateObject, scenario).split("xpath:.");
		String[] calculatedYear = utilObject1.getObjectFromObjectMap(yearObject, scenario).split("xpath:.");
		  			  		
		executor.executeScript("arguments[0].value='" + month + "';", driver.findElement(By.xpath(calculatedMonth[1])));
		executor.executeScript("arguments[0].value='" + date + "';", driver.findElement(By.xpath(calculatedDate[1])));
		executor.executeScript("arguments[0].value='" + year + "';", driver.findElement(By.xpath(calculatedYear[1])));
		
		}
		}
	            
 }
	
	
	
	
	
	
public void calculateDateBySubtractingGivenYears(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		
		Util utilObject     = new Util();
		Report reportObject = new Report();
		
		if(objectName!=null){
		String [] objectNameList = objectName.split(";");
		if(objectNameList!=null && objectNameList.length==3){
		String  monthObject = objectNameList[0];
	    String  dateObject = objectNameList[1];
	    String  yearObject = objectNameList[2];
	    
	    String monthValue="";
	    String dateValue="";
	    String yearValue="";
	    String monthXpathId="";
	    String dateXpathId="";
	    String yearXpathId="";
	    
		String monthObjetIdXpath = utilObject.getObjectFromObjectMap(monthObject, scenario);
		
		if(monthObjetIdXpath.contains("@id")){  
		monthXpathId = monthObjetIdXpath.substring(monthObjetIdXpath.indexOf('\'')+1,monthObjetIdXpath.length()-2);
					
		monthValue = driver.findElement(By.id(monthXpathId)).getAttribute("value");	
		}
		
		if(monthObjetIdXpath.contains("@name")){ 			
		monthXpathId = monthObjetIdXpath.substring(monthObjetIdXpath.indexOf('\'')+1,monthObjetIdXpath.length()-2);
							
		monthValue = driver.findElement(By.name(monthXpathId)).getAttribute("value");	
		}
				    
		String dateObjetIdXpath = utilObject.getObjectFromObjectMap(dateObject, scenario);
		if(dateObjetIdXpath.contains("@id")){ 			
		dateXpathId = dateObjetIdXpath.substring(dateObjetIdXpath.indexOf('\'')+1,dateObjetIdXpath.length()-2);
		dateValue = driver.findElement(By.name(dateXpathId)).getAttribute("value");
	    }
	    
	    if(dateObjetIdXpath.contains("@name")){							
		dateXpathId = dateObjetIdXpath.substring(dateObjetIdXpath.indexOf('\'')+1,dateObjetIdXpath.length()-2);
		dateValue = driver.findElement(By.name(dateXpathId)).getAttribute("value");
		}
	    
	    String yearObjetIdXpath = utilObject.getObjectFromObjectMap(yearObject, scenario);
		if(yearObjetIdXpath.contains("@id")){			
		yearXpathId = yearObjetIdXpath.substring(yearObjetIdXpath.indexOf('\'')+1,yearObjetIdXpath.length()-2);
		yearValue = driver.findElement(By.name(yearXpathId)).getAttribute("value");
	    }
		
	    if(yearObjetIdXpath.contains("@name")){		
		yearXpathId = yearObjetIdXpath.substring(yearObjetIdXpath.indexOf('\'')+1,yearObjetIdXpath.length()-2);
		yearValue = driver.findElement(By.name(yearXpathId)).getAttribute("value");
		}
	    
		int monthFromScreen=Integer.parseInt(monthValue);
		int dateFromScreen=Integer.parseInt(dateValue);
		int yearFromScreen=Integer.parseInt(yearValue);
		
		int givenYear = Integer.parseInt(dataValue);
				 
		Calendar calendar   = Calendar.getInstance();
		calendar.set(yearFromScreen, monthFromScreen, dateFromScreen);
		calendar.add(Calendar.YEAR, -givenYear);

		int year  = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int date  = calendar.get(Calendar.DATE);
		  			  		
		System.out.println("#calculateDateBySubtractingGivenYears():year:" + year);
		System.out.println("#calculateDateBySubtractingGivenYears():month:" + month);
		System.out.println("#calculateDateBySubtractingGivenYears():date:" + date);

		Util utilObject1 = new Util();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		  			  		
   
		String[] calculatedMonth = utilObject1.getObjectFromObjectMap(monthObject, scenario).split("xpath:.");
		String[] calculatedDate = utilObject1.getObjectFromObjectMap(dateObject, scenario).split("xpath:.");
		String[] calculatedYear = utilObject1.getObjectFromObjectMap(yearObject, scenario).split("xpath:.");
		  			  		
		executor.executeScript("arguments[0].value='" + month + "';", driver.findElement(By.xpath(calculatedMonth[1])));
		executor.executeScript("arguments[0].value='" + date + "';", driver.findElement(By.xpath(calculatedDate[1])));
		executor.executeScript("arguments[0].value='" + year + "';", driver.findElement(By.xpath(calculatedYear[1])));
		
		}
		}          
 
	}


public void calculateDOBySubtractOneMonth(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	{
		Calendar c = Calendar.getInstance();
		int enteredNoOfYears = Integer.parseInt(dataValue);
		c.add(Calendar.YEAR, -enteredNoOfYears);
		c.add(Calendar.MONTH, -1);

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		
		System.out.println("#calculateDOBySubstractOneMonth():year:" + year);
		System.out.println("#calculateDOBySubstractOneMonth():month:" + month);
		System.out.println("#calculateDOBySubstractOneMonth():date:" + date);

		Util utilObject = new Util();
		JavascriptExecutor executor = (JavascriptExecutor) driver;

		String caliCulatedDOByear = String.valueOf(year);
		String caliCulatedDOBmonth = String.valueOf(month);
		String caliCulatedDOBdate = String.valueOf(date);

		String[] finalDOBMonth = utilObject.getObjectFromObjectMap("DOBMONTH", scenario).split("xpath:.");
		String[] finalDOBDate = utilObject.getObjectFromObjectMap("DOBDATE", scenario).split("xpath:.");
		String[] finalDOBYear = utilObject.getObjectFromObjectMap("DOBYEAR", scenario).split("xpath:.");
		
		executor.executeScript("arguments[0].value='" + month + "';", driver.findElement(By.xpath(finalDOBMonth[1])));
		executor.executeScript("arguments[0].value='" + date + "';", driver.findElement(By.xpath(finalDOBDate[1])));
		executor.executeScript("arguments[0].value='" + year + "';", driver.findElement(By.xpath(finalDOBYear[1])));

	}





public void verifyEnrollmentMCOName(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
{
	 Util utilObject                    = new Util();
		 Report reportObject                = new Report();			  		
		 String sectionName                 = scenario+"_"+testCase;
		 String mcoNameFromMCOScreen        = null;			         
	 String caseNumFromEnrollmentScreen = driver.findElement(By.id("caseNo")).getAttribute("value");
	 System.out.println("#verifyEnrollmentMCOName():caseNumFromEnrollmentScreen:"+caseNumFromEnrollmentScreen);
	 
	 String indvIDFromEnrollmentScreen = driver.findElement(By.id("cin")).getAttribute("value");					 
	 System.out.println("#verifyEnrollmentMCOName():indvIDFromEnrollmentScreen:"+indvIDFromEnrollmentScreen);
	 if(indvIDFromEnrollmentScreen != null)
	 {
		 indvIDFromEnrollmentScreen = indvIDFromEnrollmentScreen.trim();
		 String key                 = "MCOName_"+indvIDFromEnrollmentScreen;
		 mcoNameFromMCOScreen       = utilObject.getDataINI(sectionName, key);
		 System.out.println("#verifyEnrollmentMCOName():mcoNameFromMCOScreen:"+mcoNameFromMCOScreen);
		 
		 if(mcoNameFromMCOScreen != null && !mcoNameFromMCOScreen.equalsIgnoreCase(""))
	  	 {
	  		String enrollmentMCONameXpath      = utilObject.getObjectFromObjectMap("ENROLLMENT_MCO_NAME",scenario);
			String mcoNameFromEnrollmentScreen = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), enrollmentMCONameXpath, browser, passScreenshot, browserFolder).getText().trim();
			System.out.println("#verifyEnrollmentMCOName():mcoNameFromEnrollmentScreen:"+mcoNameFromEnrollmentScreen);
			
			if(mcoNameFromEnrollmentScreen != null) 
			{
				mcoNameFromEnrollmentScreen = mcoNameFromEnrollmentScreen.trim();
				mcoNameFromMCOScreen        = mcoNameFromMCOScreen.trim();
				
				if(mcoNameFromEnrollmentScreen.equalsIgnoreCase(mcoNameFromMCOScreen))
				{
					reportObject.Log("Verifying the presence of MCO Name in Enrollement Screen "+mcoNameFromEnrollmentScreen, "The given MCO Name in MCO Screen "+mcoNameFromMCOScreen+"  and MCO Name in the Enrollement Screen "+mcoNameFromEnrollmentScreen+" both are equal", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
				}	
				else
				{
					reportObject.Log("Verifying the presence of MCO Name in Enrollement Screen "+mcoNameFromEnrollmentScreen, "The given MCO Name in MCO Screen "+mcoNameFromMCOScreen+"  and MCO Name in the Enrollement Screen "+mcoNameFromEnrollmentScreen+" both are not equal", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);			 									
				} 
			}
			
	  	  }
	  	else
	    {
	    	reportObject.Log("Verifying the presence of MCO Name in Enrollement Screen ", "Eihter MCO Screen is not entered in this script. ", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	    }
	 }					 

}


public void verifyTextNotPresent(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	Report reportObject = new Report();		
    if(objectId != null && dataValue != null)
    {
    	String textFromScreen  = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).getText().trim();
		if(textFromScreen !=null)
		{
			textFromScreen = textFromScreen.trim();
			dataValue      =  dataValue.trim();
			if(!textFromScreen.equalsIgnoreCase(dataValue))
			{
				reportObject.Log("Verifying that "+dataValue+" text is not present", "The given text "+dataValue+" is not match with the text from the screen "+textFromScreen, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);					
			}
			else
			{
				reportObject.Log("Verifying that "+dataValue+" text is not present", "The given text "+dataValue+"  is match with the text from the screen "+textFromScreen,Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);					
			}
		}
		else
		{
			reportObject.Log("Verifying the presence of text "+dataValue, "unable to read the text from the screen by given objectId "+objectId, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
    }
    else
    {
    	reportObject.Log("Verifying the presence of text ", "Eihter objectId or dataValue is null.Please provide the objectId and dataValue details in the sheet. ", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
    }		
	
}
public void verifyTextPresent(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	Report reportObject = new Report();		
    if(objectId != null && dataValue != null)
    {
    	String textFromScreen  = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).getText().trim();
		if(textFromScreen !=null)
		{
			textFromScreen = textFromScreen.trim();
			dataValue      =  dataValue.trim();
			if(textFromScreen.equalsIgnoreCase(dataValue))
			{
				reportObject.Log("Verifying the presence of text "+dataValue, "The given text "+dataValue+"  and text from the screen "+textFromScreen+" both are equal", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);					
			}
			else
			{
				reportObject.Log("Verifying the presence of text "+dataValue, "The given text "+dataValue+"  and text from the screen "+textFromScreen+" both are not equal", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);					
			}
		}
		else
		{
			reportObject.Log("Verifying the presence of text "+dataValue, "unable to read the text from the screen by given objectId "+objectId, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
    }
    else
    {
    	reportObject.Log("Verifying the presence of text ", "Eihter objectId or dataValue is null.Please provide the objectId and dataValue details in the sheet. ", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
    }		
	
}




public void writeSeletedMCONameforIndvID(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
{
			    	
			    	Util utilObject                         = new Util();		  		
			  		String indvIDFromScreen                 = "";		
			  		String mcoIndvIdXpath                   = utilObject.getObjectFromObjectMap("MCO_INDV_ID",scenario);
			  		indvIDFromScreen                        = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), mcoIndvIdXpath, browser, passScreenshot, browserFolder).getText().trim();
			  		System.out.println("#indvIDFromScreen:"+indvIDFromScreen);			  		
			  		
			  		String mcoXpathID ="mcoName";
			  		Select select = new Select(driver.findElement(By.id(mcoXpathID)));
			  		
			  		

			  		WebElement option = select.getFirstSelectedOption();
			  		String selectedMCOName = option.getText();

			  		System.out.println("#selectedMCOName:"+selectedMCOName );
			  		
			  		utilObject.setDataINI("MCOName_"+indvIDFromScreen, selectedMCOName, testCase, scenario);
			  		
}

public void calculateDateBySubtractingGivenDays(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
	
	Util utilObject     = new Util();
	Report reportObject = new Report();
	
	if(objectName!=null){
	String [] objectNameList = objectName.split(";");
	if(objectNameList!=null && objectNameList.length==3){
	String  monthObject = objectNameList[0];
    String  dateObject = objectNameList[1];
    String  yearObject = objectNameList[2];
    
    String monthValue="";
    String dateValue="";
    String yearValue="";
    String monthXpathId="";
    String dateXpathId="";
    String yearXpathId="";
    
	String monthObjetIdXpath = utilObject.getObjectFromObjectMap(monthObject, scenario);
	
	if(monthObjetIdXpath.contains("@id")){  
	monthXpathId = monthObjetIdXpath.substring(monthObjetIdXpath.indexOf('\'')+1,monthObjetIdXpath.length()-2);
				
	monthValue = driver.findElement(By.id(monthXpathId)).getAttribute("value");	
	}
	
	if(monthObjetIdXpath.contains("@name")){ 			
	monthXpathId = monthObjetIdXpath.substring(monthObjetIdXpath.indexOf('\'')+1,monthObjetIdXpath.length()-2);
						
	monthValue = driver.findElement(By.name(monthXpathId)).getAttribute("value");	
	}
			    
	String dateObjetIdXpath = utilObject.getObjectFromObjectMap(dateObject, scenario);
	if(dateObjetIdXpath.contains("@id")){ 			
	dateXpathId = dateObjetIdXpath.substring(dateObjetIdXpath.indexOf('\'')+1,dateObjetIdXpath.length()-2);
	dateValue = driver.findElement(By.name(dateXpathId)).getAttribute("value");
    }
    
    if(dateObjetIdXpath.contains("@name")){							
	dateXpathId = dateObjetIdXpath.substring(dateObjetIdXpath.indexOf('\'')+1,dateObjetIdXpath.length()-2);
	dateValue = driver.findElement(By.name(dateXpathId)).getAttribute("value");
	}
    
    String yearObjetIdXpath = utilObject.getObjectFromObjectMap(yearObject, scenario);
	if(yearObjetIdXpath.contains("@id")){			
	yearXpathId = yearObjetIdXpath.substring(yearObjetIdXpath.indexOf('\'')+1,yearObjetIdXpath.length()-2);
	yearValue = driver.findElement(By.name(yearXpathId)).getAttribute("value");
    }
	
    if(yearObjetIdXpath.contains("@name")){		
	yearXpathId = yearObjetIdXpath.substring(yearObjetIdXpath.indexOf('\'')+1,yearObjetIdXpath.length()-2);
	yearValue = driver.findElement(By.name(yearXpathId)).getAttribute("value");
	}
    
	int monthFromScreen=Integer.parseInt(monthValue);
	int dateFromScreen=Integer.parseInt(dateValue);
	int yearFromScreen=Integer.parseInt(yearValue);
	
	int givenDate = Integer.parseInt(dataValue);
				 
	Calendar calendar   = Calendar.getInstance();
	calendar.set(yearFromScreen, monthFromScreen, dateFromScreen);
	calendar.add(Calendar.DATE, -givenDate);

	int year  = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH);
	int date  = calendar.get(Calendar.DATE);
	  			  		
	System.out.println("#calculateDateBySubstractingGivenDays():year:" + year);
	System.out.println("#calculateDateBySubstractingGivenDays():month:" + month);
	System.out.println("#calculateDateBySubstractingGivenDays():date:" + date);

	Util utilObject1 = new Util();
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	  			  		

	String[] calculatedMonth = utilObject1.getObjectFromObjectMap(monthObject, scenario).split("xpath:.");
	String[] calculatedDate = utilObject1.getObjectFromObjectMap(dateObject, scenario).split("xpath:.");
	String[] calculatedYear = utilObject1.getObjectFromObjectMap(yearObject, scenario).split("xpath:.");
	  			  		
	executor.executeScript("arguments[0].value='" + month + "';", driver.findElement(By.xpath(calculatedMonth[1])));
	executor.executeScript("arguments[0].value='" + date + "';", driver.findElement(By.xpath(calculatedDate[1])));
	executor.executeScript("arguments[0].value='" + year + "';", driver.findElement(By.xpath(calculatedYear[1])));
	
	}
	}          
}


public void writeGivenObjectDateIntoOutputFile(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
{
	System.out.println("#writeGivenObjectDateIntoOutputFile():"+driver.getTitle());
	Util utilObject     = new Util();
	Report reportObject = new Report();
	
	if(objectName!=null){
	String [] objectNameList = objectName.split(";");
	if(objectNameList!=null && objectNameList.length==3){
	String  monthObject = objectNameList[0];
   String  dateObject = objectNameList[1];
   String  yearObject = objectNameList[2];
   
   String monthValue="";
   String dateValue="";
   String yearValue="";
   String monthXpathId="";
   String dateXpathId="";
   String yearXpathId="";
   
	String monthObjetIdXpath = utilObject.getObjectFromObjectMap(monthObject, scenario);
	
	if(monthObjetIdXpath.contains("@id")){  
	monthXpathId = monthObjetIdXpath.substring(monthObjetIdXpath.indexOf('\'')+1,monthObjetIdXpath.length()-2);
				
	monthValue = driver.findElement(By.id(monthXpathId)).getAttribute("value");	
	}
	
	if(monthObjetIdXpath.contains("@name")){ 			
	monthXpathId = monthObjetIdXpath.substring(monthObjetIdXpath.indexOf('\'')+1,monthObjetIdXpath.length()-2);
						
	monthValue = driver.findElement(By.name(monthXpathId)).getAttribute("value");	
	}
			    
	String dateObjetIdXpath = utilObject.getObjectFromObjectMap(dateObject, scenario);
	if(dateObjetIdXpath.contains("@id")){ 			
	dateXpathId = dateObjetIdXpath.substring(dateObjetIdXpath.indexOf('\'')+1,dateObjetIdXpath.length()-2);
	dateValue = driver.findElement(By.name(dateXpathId)).getAttribute("value");
   }
   
   if(dateObjetIdXpath.contains("@name")){							
	dateXpathId = dateObjetIdXpath.substring(dateObjetIdXpath.indexOf('\'')+1,dateObjetIdXpath.length()-2);
	dateValue = driver.findElement(By.name(dateXpathId)).getAttribute("value");
	}
   
   String yearObjetIdXpath = utilObject.getObjectFromObjectMap(yearObject, scenario);
	if(yearObjetIdXpath.contains("@id")){			
	yearXpathId = yearObjetIdXpath.substring(yearObjetIdXpath.indexOf('\'')+1,yearObjetIdXpath.length()-2);
	yearValue = driver.findElement(By.name(yearXpathId)).getAttribute("value");
   }
	
   if(yearObjetIdXpath.contains("@name")){		
	yearXpathId = yearObjetIdXpath.substring(yearObjetIdXpath.indexOf('\'')+1,yearObjetIdXpath.length()-2);
	yearValue = driver.findElement(By.name(yearXpathId)).getAttribute("value");
	}
   			    			    
   utilObject.setDataINI(objectNameList[0], monthValue, testCase, scenario);
   utilObject.setDataINI(objectNameList[1], dateValue, testCase, scenario);
   utilObject.setDataINI(objectNameList[2], yearValue, testCase, scenario);
  			    				
	System.out.println("#writeGivenObjectDateIntoOutputFile()"+objectNameList[0]+":" + monthValue);
	System.out.println("#writeGivenObjectDateIntoOutputFile()"+objectNameList[1]+":" + dateValue);
	System.out.println("#writeGivenObjectDateIntoOutputFile()"+objectNameList[2]+":" + yearValue);
	
	
	
	}
	}          

}



public  void selectByPartOfVisibleText(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
{			    				
	String actualObjectID = "";
	actualObjectID        = objectId.split(":",2)[1].trim();			    				
	WebElement dropdown = driver.findElement(By.xpath(actualObjectID));
    dropdown.click();

    List<WebElement> options = dropdown.findElements(By.tagName("option"));
   
    for(WebElement option : options)
    {
        String optTxt = option.getText();
        if(optTxt != null && dataValue != null)
        {
        if(optTxt.contains(dataValue))			    			        
        {			    			        	
        	System.out.println("#selectByPartOfVisibleText():optTxt.contains(dataValue):optTxt:"+optTxt);
	        System.out.println("#selectByPartOfVisibleText():optTxt.contains(dataValue):dataValue:"+dataValue);				    			        
        	option.click();			    			           
            break;
        }
       }
    }
    
}

public void getRequiredColumnFromDBForDynamicWhereCondition(String homePath, String testCase, String scenario, String browser,
		String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
		String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
		throws InterruptedException 
{

	String columnValueFromDB = "";
	Util utilObject = new Util();
	System.out.println("@getRequiredColumnFromDBForDynamicWhereCondition()Given Query:" + dataValue);
	if (dataValue != null && !dataValue.equalsIgnoreCase("")) {
        if(objectName != null)
        {
        	String[] whereConditionStringArray = objectName.split(";");
        	if(whereConditionStringArray != null && whereConditionStringArray.length>0)
        	{
        		String testCaseReplace             = testCase.replace("POST", "PRE");
        		String sectionName                 = scenario+"_"+testCaseReplace;
        		for(int i=0;i<whereConditionStringArray.length;i++)
        		{
        		  System.out.println("@@whereConditionStringArray[i].:"+whereConditionStringArray[i]);
        		 
        		  if(dataValue.contains(whereConditionStringArray[i].toString()))
        		  {			  
        			  String withoutFind ="";
        			  if(whereConditionStringArray[i].contains("_FIND"))
        			  {
        				  withoutFind = whereConditionStringArray[i].replace("_FIND", "");
        			  }
        			  if(whereConditionStringArray[i].contains("_find"))
        			  {
        				  withoutFind = whereConditionStringArray[i].replace("_find", "");
        			  }
        			  withoutFind.trim();
        			 
        			  String value       = utilObject.getDataINI(sectionName+"_"+currentIteration, withoutFind);
        			  if(value != null)
        			  {
        				  dataValue = dataValue.replace(whereConditionStringArray[i], value);
        			  }
        			  
        			  
        		  }
        		}
        	}
        }
		
        System.out.println("@getRequiredColumnFromDBForDynamicWhereCondition()Final Query:" + dataValue);
		String query = dataValue;
		HashMap columnValuesMapfromDB = null;

		try {
			columnValuesMapfromDB = getDataMapFromDB(query, utilObject
					.getDBParametersINI(utilObject.getValueFromConfigProperties("EnvironmentName"), "IES_SCHEMA"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			homePath = (new File(".")).getCanonicalPath();
			File file = new File(homePath + "\\OutputDataINI.ini");
			Wini a = new Wini(file);
			a.load(file);

			if (columnValuesMapfromDB != null && columnValuesMapfromDB.size() > 0) {

				Set set = columnValuesMapfromDB.entrySet();
				Iterator iterator = set.iterator();
				while (iterator.hasNext()) {

					Object obj = iterator.next();
					Map.Entry entry = (Map.Entry) obj;
					String columNameFromQuery = (String) entry.getKey();
					Object columnValueFromDatabase = entry.getValue();						
					a.put(scenario + "_" + testCase + "_" + currentIteration,
							columNameFromQuery, columnValueFromDatabase);

					columnValueFromDatabase = null;
				}
			}

			a.store();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
}
public void getRequiredColumnFromDBForIteration(String homePath, String testCase, String scenario, String browser,
		String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
		String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
		throws InterruptedException {
	String columnValueFromDB = "";
	Util utilObject = new Util();
	System.out.println("#getRequiredColumnFromDBForIteration():Given Query:" + dataValue);
	if (dataValue != null && !dataValue.equalsIgnoreCase("")) {

		String query = dataValue;
		HashMap columnValuesMapfromDB = null;

		try {
			columnValuesMapfromDB = getDataMapFromDB(query, utilObject
					.getDBParametersINI(utilObject.getValueFromConfigProperties("EnvironmentName"), "IES_SCHEMA"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			homePath = (new File(".")).getCanonicalPath();
			File file = new File(homePath + "\\OutputDataINI.ini");
			Wini a = new Wini(file);
			a.load(file);

			if (columnValuesMapfromDB != null && columnValuesMapfromDB.size() > 0) {

				Set set = columnValuesMapfromDB.entrySet();
				Iterator iterator = set.iterator();
				while (iterator.hasNext()) {

					Object obj = iterator.next();
					Map.Entry entry = (Map.Entry) obj;
					String columNameFromQuery = (String) entry.getKey();
					Object columnValueFromDatabase = entry.getValue();
					
					a.put(scenario + "_" + testCase + "_" + currentIteration,
							columNameFromQuery, columnValueFromDatabase);

					columnValueFromDatabase = null;
				}
			}

			a.store();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

public void getDataINIForIteration(String homePath, String testCase, String scenario, String browser,
		String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
		String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
		throws InterruptedException 
{
	        Util utilObject          = new Util();
			String sectionName       = scenario+"_"+testCase+"_"+currentIteration;
			String dynamicDataValue  = utilObject.getDataINI(sectionName,dataValue);
			System.out.println("#getDataINIFoIteration():data from OutputINIFile:"+dynamicDataValue);
			enter_text(homePath, testCase, scenario, browser, objectId, objectName, dynamicDataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
}


public void verifyTheGivenColumnIfpresentsinIniFile(String homePath, String testCase, String scenario, String browser,
		String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
		String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
		throws InterruptedException 
{
	    Util utilObject          = new Util();
		String sectionName       = scenario+"_"+testCase+"_"+currentIteration;
		String value             = utilObject.getDataINI(sectionName,objectName);
		
		System.out.println("#verifyTheStatusIndColumnIfpresentsinIniFile():value from OutputINIfile for the given column:"+value);
		System.out.println("#verifyTheStatusIndColumnIfpresentsinIniFile():keyInData:"+dataValue);
		Report reportObject = new Report();
		if(value != null)
		{
			if(dataValue != null && value.equalsIgnoreCase(dataValue))
			{
				reportObject.Log("Verifying the results of "+objectName+" after Batch executoin.Actual value is "+ value +" and Expected value is "+ dataValue ,"Success", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}	
			else
				{
				reportObject.Log("Verifying the results of "+objectName+" after Batch executoin.Actual value is "+ value +" and Expected value is "+ dataValue,"Fail", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);			 									
				} 
			
		}
		else
		{
			reportObject.Log("Verifying the results of "+objectName+" after Batch executoin.Actual value is "+ value +" and Expected value is "+ dataValue ," Query return empty record for this.", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
}

public void verifyMultipleColumnsIfPresentsinINIFile(String homePath, String testCase, String scenario, String browser,
		String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
		String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
		throws InterruptedException 
{
	    Util utilObject          = new Util();
		String sectionName       = scenario+"_"+testCase+"_"+currentIteration;			
		
		String[] objectNameArray = null ;
		String[] dataValueArray = null;
		if(objectName != null && objectName.contains(";"))
		{
			objectNameArray = objectName.split(";");
			
			if(dataValue != null && dataValue.contains(";"))
			{
				dataValueArray = dataValue.split(";");
			}
		}			
		Report reportObject = new Report();
		if(objectNameArray != null && dataValueArray != null && objectNameArray.length == dataValueArray.length )
		{
		 for(int i=0;i<objectNameArray.length;i++)
		 {
			String value = utilObject.getDataINI(sectionName,objectNameArray[i]);				
			if(value != null)
			{
				if(value.equalsIgnoreCase(dataValueArray[i].toString()))
				{
					reportObject.Log("Verifying the results of "+objectNameArray[i]+" after Batch executoin.Actual value is "+ value +" and Expected value is "+ dataValueArray[i] ,"Success", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
				}	
				else
 				{
					reportObject.Log("Verifying the results of "+objectNameArray[i]+" after Batch executoin.Actual value is "+ value +" and Expected value is "+ dataValueArray[i],"Fail", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);			 									
 				} 
				
			}
			else
			{
				reportObject.Log("Verifying the results of "+objectNameArray[i]+" after Batch executoin.Actual value is "+ value +" and Expected value is "+ dataValueArray[i] ," Query return empty record for this.", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
		 }
			
		}
		
}

public void clickGivenObjectInGivenPage(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
{			    	        	
	Report reportObject = new Report();
	try{
		if((driver.getPageSource().contains(dataValue)))
		{
			System.out.println("#clickGivenObjectInGivenPage : contains given dataValue:"+dataValue);			    	      				
	    	Util utilObject       = new Util();
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	String xpath          = utilObject.getObjectFromObjectMap(objectName,scenario);
	    	String actualObjectID = "";
	    	actualObjectID        = objectId.split(":",2)[1].trim();
	    	WebElement buttonSubmit= driver.findElement(By.xpath(actualObjectID));
	    	js.executeScript("arguments[0].click();", buttonSubmit);			    		 		    	
			reportObject.Log("Clicked the button successfully","In the given page "+dataValue+",cilcked the "+objectName+"",Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
		else
		{
			reportObject.Log("Failed to click the button in the given page","As the given page "+dataValue+"  is not displayed.", 
					Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);			    	      				
		}
		
	}catch(Exception e){
		reportObject.Log("Click the "+objectName+" button", ""+objectName+" exception block", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
}			  




public void WriteDataToOutputFileForIteration(String homePath, String testCase, String scenario, String browser,
		String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
		String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) {
	String columnValue = dataValue;
	String data = columnValue.split(";")[0].trim();
	String key = columnValue.split(";")[1].trim();
	Util utilObject = new Util();
	
	if (data.startsWith("ObjectText")) {
		try {
			data = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId,
					browser, passScreenshot, browserFolder).getText().toString();
			System.out.println("The application number is " + data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDataINIForIteration(key, data, testCase, scenario, currentIteration);
		
	} else {
		setDataINIForIteration(key, data, testCase, scenario, currentIteration);
	
	}
	
}
public void getRequiredColumnFromDBForDynamicWhereConditionForNonIteration(String homePath, String testCase, String scenario, String browser,
			String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
			String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
			throws InterruptedException 
	{

		String columnValueFromDB = "";
		Util utilObject = new Util();
		System.out.println("@getRequiredColumnFromDBForDynamicWhereConditionForNonIteration()Given Query:" + dataValue);
		if (dataValue != null && !dataValue.equalsIgnoreCase("")) 
		{
          if(objectName != null)
          {
          	
          	if(objectName.contains(";"))
          	{
          		String[] whereConditionStringArray = objectName.split(";");
          		//String testCaseReplace             = testCase.replace("POST", "PRE");
          		//String sectionName                 = scenario+"_"+testCaseReplace;
          		String sectionName                 = scenario+"_"+testCase;
          		for(int i=0;i<whereConditionStringArray.length;i++)
          		{
          		  System.out.println("@@whereConditionStringArray[i].:"+whereConditionStringArray[i]);
          		 
          		  if(dataValue.contains(whereConditionStringArray[i].toString()))
          		  {			  
          			  String withoutFind ="";
          			  if(whereConditionStringArray[i].contains("_FIND"))
          			  {
          				  withoutFind = whereConditionStringArray[i].replace("_FIND", "");
          			  }
          			  if(whereConditionStringArray[i].contains("_find"))
          			  {
          				  withoutFind = whereConditionStringArray[i].replace("_find", "");
          			  }
          			  withoutFind.trim();
          			 
          			  String value       = utilObject.getDataINI(sectionName, withoutFind);
          			  if(value != null)
          			  {
          				  dataValue = dataValue.replace(whereConditionStringArray[i], value);
          			  }
          			  
          			  
          		  }
          		}
          	}
          	else
          	{
          		String sectionName                 = scenario+"_"+testCase;
          		
          		  if(dataValue.contains(objectName.toString()))
          		  {			  
          			  String withoutFind ="";
          			  if(objectName.contains("_FIND"))
          			  {
          				  withoutFind = objectName.replace("_FIND", "");
          			  }
          			  if(objectName.contains("_find"))
          			  {
          				  withoutFind = objectName.replace("_find", "");
          			  }
          			  withoutFind.trim();
          			 
          			  String value       = utilObject.getDataINI(sectionName, withoutFind);
          			  if(value != null)
          			  {
          				  dataValue = dataValue.replace(objectName, value);
          			  }
          			  
          			  
          		  }
          	}
          }
			
          System.out.println("@getRequiredColumnFromDBForDynamicWhereConditionForNonIteration()Final Query:" + dataValue);
			String query = dataValue;
			HashMap columnValuesMapfromDB = null;

			try {
				columnValuesMapfromDB = getDataMapFromDB(query, utilObject
						.getDBParametersINI(utilObject.getValueFromConfigProperties("EnvironmentName"), "IES_SCHEMA"));
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				homePath = (new File(".")).getCanonicalPath();
				File file = new File(homePath + "\\OutputDataINI.ini");
				Wini a = new Wini(file);
				a.load(file);

				if (columnValuesMapfromDB != null && columnValuesMapfromDB.size() > 0) {

					Set set = columnValuesMapfromDB.entrySet();
					Iterator iterator = set.iterator();
					while (iterator.hasNext()) {

						Object obj = iterator.next();
						Map.Entry entry = (Map.Entry) obj;
						String columNameFromQuery = (String) entry.getKey();
						Object columnValueFromDatabase = entry.getValue();						
						a.put(scenario + "_" + testCase,
								columNameFromQuery, columnValueFromDatabase);

						columnValueFromDatabase = null;
					}
				}

				a.store();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	
		
	}

public void verifyTheGivenColumnIfpresentsinIniFileForNonIteration(String homePath, String testCase, String scenario, String browser,
			String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
			String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
			throws InterruptedException 
	{
		    Util utilObject          = new Util();
			String sectionName       = scenario+"_"+testCase;
			String value             = utilObject.getDataINI(sectionName,objectName);
			
			System.out.println("#verifyTheGivenColumnIfpresentsinIniFileForNonIteration():value from OutputINIfile for the given column:"+value);
			System.out.println("#verifyTheGivenColumnIfpresentsinIniFileForNonIteration():keyInData:"+dataValue);
			Report reportObject = new Report();
			if(value != null)
			{
				if(dataValue != null && value.equalsIgnoreCase(dataValue))
				{
					reportObject.Log("Verifying the results of "+objectName+" after query executoin.Actual value is "+ value +" and Expected value is "+ dataValue ,"Success", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
				}	
				else
				{
					reportObject.Log("Verifying the results of "+objectName+" after query executoin.Actual value is "+ value +" and Expected value is "+ dataValue,"Fail", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);			 									
				} 
				
			}
			else
			{
				reportObject.Log("Verifying the results of "+objectName+" after query executoin.Actual value is "+ value +" and Expected value is "+ dataValue ," Query return empty record for this.", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
	}
public void verifyMultipleColumnsIfPresentsinINIFileForNonIteration(String homePath, String testCase, String scenario, String browser,
			String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver,
			String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
			throws InterruptedException 
	{
		    Util utilObject          = new Util();
			String sectionName       = scenario+"_"+testCase;			
			
			String[] objectNameArray = null ;
			String[] dataValueArray = null;
			if(objectName != null && objectName.contains(";"))
			{
				objectNameArray = objectName.split(";");
				
				if(dataValue != null && dataValue.contains(";"))
				{
					dataValueArray = dataValue.split(";");
				}
			}			
			Report reportObject = new Report();
			if(objectNameArray != null && dataValueArray != null && objectNameArray.length == dataValueArray.length )
			{
			 for(int i=0;i<objectNameArray.length;i++)
			 {
				String value = utilObject.getDataINI(sectionName,objectNameArray[i]);				
				if(value != null)
				{
					if(value.equalsIgnoreCase(dataValueArray[i].toString()))
					{
						reportObject.Log("Verifying the results of "+objectNameArray[i]+" after query executoin.Actual value is "+ value +" and Expected value is "+ dataValueArray[i] ,"Success", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
					}	
					else
	 				{
						reportObject.Log("Verifying the results of "+objectNameArray[i]+" after query executoin.Actual value is "+ value +" and Expected value is "+ dataValueArray[i],"Fail", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);			 									
	 				} 
					
				}
				else
				{
					reportObject.Log("Verifying the results of "+objectNameArray[i]+" after query executoin.Actual value is "+ value +" and Expected value is "+ dataValueArray[i] ," Query return empty record for this.", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
				}
			 }
				
			}
			
	}

public void setDataINIForIteration(String key,String value, String testCase, String scenario,Integer currentIteration){
	
	try{
		
		String homePath = new File (".").getCanonicalPath();
		File f = new File(homePath+"\\OutputDataINI.ini");
		
		Wini a = new Wini(f);
		a.load(f);			
		a.put(scenario+"_"+testCase+"_"+currentIteration, key, value);
		
		a.store();
		
	}catch(Exception e){
		e.printStackTrace();
	}
}



public void CallAPI(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws InterruptedException, ParserConfigurationException, SAXException, IOException 
{		
	 
	String requiredParams_arr[]=objectName.split("::");
	
	
	 String inputRequest="";
	inputRequest="APIRequest//"+requiredParams_arr[0]+".xml";
	 
	 
	 String filepath_expectedResponse="";
	 filepath_expectedResponse="APIResponse//ExepectedStatus//"+requiredParams_arr[1]+".xml";
	 
	 String filepath_actualResponse="";
	 filepath_actualResponse="APIResponse//"+requiredParams_arr[2]+".xml";
	 
	
	 try {
	 
	 File file =new File(inputRequest);
	 if(file.exists()) {
		inputRequest=generateStringFromResource(inputRequest);
		System.out.println(inputRequest);
	 }
	
	 }catch (Exception e) {
		// TODO: handle exception
	}
	 
String endurl = Util.getValueFromConfigProperties(Util.getValueFromConfigProperties("EnvironmentName")+"_"+requiredParams_arr[3]);
System.out.println("End Url"+endurl);
if(!endurl.isEmpty()) {
	

         Response response=given()
                .header("Content-Type", "text/xml;charset=UTF-8")
                .and()
                .body(inputRequest)
         .when()
            .post(endurl)
         .then()
                .statusCode(200)
                .and()
                .log().all()
                .extract().response();
         
         if(response!=null)
         {

        XmlPath jsXpath= new XmlPath(response.asString());
         String outputresponse=jsXpath.getString("responseStatus");
         
         File file=new File(filepath_actualResponse);
		
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
            output.write(response.then().log().all().extract().response().prettyPrint());
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
          if ( output != null ) {
            output.close();
          }
        }
	    
         DocumentBuilderFactory parser=DocumentBuilderFactory.newInstance();
         parser.setNamespaceAware(true);
         parser.setCoalescing(true);
         parser.setIgnoringComments(true);
         parser.setIgnoringElementContentWhitespace(true);
         
         
DocumentBuilder builder=parser.newDocumentBuilder();
org.w3c.dom.Document doc=builder.parse( new File(filepath_expectedResponse));

org.w3c.dom.Document doc2=builder.parse( new File(filepath_actualResponse));
doc.normalizeDocument();
doc2.normalizeDocument();
if(doc.isEqualNode(doc2))
{
	System.out.println("NO Mismatch between Request and respose");
}else {
	System.out.println("Mismatch between Request and respose");
}

    
List<String> expectedResponse=ReadLineByLine(filepath_expectedResponse);
List<String> actualResponse=ReadLineByLine(filepath_actualResponse);

printDifferences(expectedResponse,actualResponse);


        
        
        
         }      
        
	    
}else {
	
}


	}


public static List<String> ReadLineByLine(String path) {
	BufferedReader reader;
	List<String> temp=new ArrayList<String>();
	
	try {
		reader = new BufferedReader(new FileReader(
				new File(path)));
		String line = reader.readLine();
		
		while (line != null) {
			
			// read next line
			line = reader.readLine();
			if(line!=null)
			temp.add(line.trim());
			
		}
		reader.close();
		
		if(!temp.isEmpty()) {
			return temp;
		}else return null;
		
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	}
}


public static void printDifferences(List<String> expectedResponse,List<String>actualResponse){

for(int i=0;i<expectedResponse.size();i++) {
	if(actualResponse.contains(expectedResponse.get(i))) {
		actualResponse.remove(expectedResponse.get(i));
		
	}
	
	
}



int totalDifferences = actualResponse.size();
System.out.println("===============================");
System.out.println("Total differences : " + totalDifferences);
System.out.println("================================");

for(int j=0;j<actualResponse.size();j++) {
	System.out.println("Tag doesnt exists in Expected Response :"+actualResponse.get(j));
}

}


public static String generateStringFromResource(String path) throws IOException {

    return new String(Files.readAllBytes(Paths.get(path)));

}
	

public void PickCaseDetails(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
	
	try {
	
		Util util=new Util();
		String caseNum="";
		String testcasechange=scenario+"_"+testCase;
		String finalString="";
		String caseNumber="";
		
		if(objectId!=null || !objectId.isEmpty()) {
			finalString=objectId.split("::")[0];
		}else {
			finalString=Global.gblobjectId.split("::")[0];
		}
	
		
		if(!finalString.isEmpty()) {
			
		caseNum=util.getDataINI(finalString, Global.gblobjectId.split("::")[1]);
		
		}
		
		
		if(caseNum!=null ){
			System.out.println("Setting case number for testcase :"+scenario+"_"+testCase);
			
			util.setDataINI(Global.gblobjectId.split("::")[1], caseNum, testCase, scenario);	
			
		}else {

		}
		
	
		
	}
	catch(Exception e) {
		
	}
	}
public void generateRandomStringAndEnterTextAndWriteIntoOutputINIFile(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
{
		  		Report reportObject = new Report();
		  		StringBuffer randStr = new StringBuffer();
		          for(int i=0; i<Integer.parseInt(dataValue); i++){
		              int number = getRandomNumber();
		              char ch = CHAR_LIST.charAt(number);
		              randStr.append(ch);
		          }
		          			
		  		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		  		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(randStr);
		  		
		  		Util utilObject = new Util();
				utilObject.setDataINI(objectName, randStr.toString(), testCase, scenario);
						  		
		  		reportObject.Log("Entering random generated text in the text box "+objectName, "Entered the random text "+randStr, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
}


public void generateRandomNumberByGivenRangeAndWriteIntoOutputINIFile(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
{
		       	Report reportObject = new Report();
		        if(dataValue != null && !dataValue.equalsIgnoreCase(""))        
		        {
		            int length = Integer.parseInt(dataValue);
		            if(length > 0)
		            {
		                 Random random = new Random();
		                 char[] digits = new char[length];
		                 digits[0] = (char) (random.nextInt(9) + '1');
		                 for (int i = 1; i < length; i++) {
		                     digits[i] = (char) (random.nextInt(10) + '0');
		              }              
		                 dataValue=String.valueOf(Long.parseLong(new String(digits)));
		                 System.out.println("dataValue::"+dataValue);               
		                 
		                 findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		                 findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);
		                    
		                    Util utilObject = new Util();
		    				utilObject.setDataINI(objectName, dataValue, testCase, scenario);
		                    
		                    reportObject.Log("Entering random generated text in the text box "+objectName, "Entered the random number "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		            }
		       
		        }
 }


public void WriteCurrentDateToOutputFile(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
{
		  		Calendar c = Calendar.getInstance();
		  		
		  		int year = c.get(Calendar.YEAR);
		  		int month = c.get(Calendar.MONTH) + 1;
		  		int date = c.get(Calendar.DATE);
		  		
		  		System.out.println("#WriteCurrentDateToOutputFile():year:" + year);
		  		System.out.println("#WriteCurrentDateToOutputFile():month:" + month);
		  		System.out.println("#WriteCurrentDateToOutputFile():date:" + date);

		  		Util utilObject = new Util();
		  		String monthString = String.valueOf(month);
		  		String dateString = String.valueOf(date);
		  		String yearString = String.valueOf(year);
		  		
		  		if(month<=9)
		  		{
		  			monthString = "0"+monthString;
		  		}
		  		
		  		if(date<=9)
		  		{
		  			dateString = "0"+dateString;
		  		}
		  	
		  		
		  		String currentDate = monthString+dateString+yearString;
		  		System.out.println("#WriteCurrentDateToOutputFile():CURRENT_DATE:" + currentDate);
		  		utilObject.setDataINI("CURRENT_DATE", currentDate, testCase, scenario);

}

public void WriteDOBToOutputFileByGivenAge(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
{
		  		Calendar c = Calendar.getInstance();
		  		int enteredNoOfYears = Integer.parseInt(dataValue);
		  		c.add(Calendar.YEAR, -enteredNoOfYears);

		  		
		  		int year = c.get(Calendar.YEAR);
		  		int month = c.get(Calendar.MONTH) + 1;
		  		int date = c.get(Calendar.DATE);
		  		
		  		System.out.println("#WriteDOBToOutputFileByGivenAge():year:" + year);
		  		System.out.println("#WriteDOBToOutputFileByGivenAge():month:" + month);
		  		System.out.println("#WriteDOBToOutputFileByGivenAge():date:" + date);

		  		Util utilObject = new Util();
		  		String monthString = String.valueOf(month);
		  		String dateString = String.valueOf(date);
		  		String yearString = String.valueOf(year);
		  		
		  		
		  		
		  		if(month<=9)
		  		{
		  			monthString = "0"+monthString;
		  		}
		  		
		  		if(date<=9)
		  		{
		  			dateString = "0"+dateString;
		  		}
		  		
		  		
		  		
		  		String dob = monthString+dateString+yearString;
		  		System.out.println("#WriteDOBToOutputFileByGivenAge():Final DOB:" + dob);
		  		utilObject.setDataINI("DOB", dob, testCase, scenario);

 }




}






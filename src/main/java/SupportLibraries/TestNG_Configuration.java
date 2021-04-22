package SupportLibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_Configuration {
	String mainresultsFolder="";
	String resultsFolder = "";
	boolean flag = false;
 
	@BeforeSuite
	public void CheckGridSetupValue(){
		
		Util utilObject = new Util();
		if(!(utilObject.getValueFromConfigProperties("GridSetup").equalsIgnoreCase("Yes"))){
			JOptionPane.showMessageDialog(null,"Grid Setup is set to No, please execute Standalone Driver", "Configuration Error", JOptionPane.ERROR_MESSAGE);
		}else{
			try {
				ExecuteScripts.executionMode = "Parallel";
				ExecuteScripts testngDriver = new ExecuteScripts();
				POI_ReadExcel poiObject = new POI_ReadExcel();
				String homePath = new File (".").getCanonicalPath();
				
				String path = homePath+"\\TestCaseSelection_Modular.xlsx";
				FileInputStream file = new FileInputStream(new File(path));
				//File file = new File(path);
				
				//XSSFWorkbook workBook = new XSSFWorkbook(file);
				//XSSFWorkbook workBook = null;
				Workbook workBook = null;
				try {
					workBook = WorkbookFactory.create(file);
				} catch (EncryptedDocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int numberOfSheets = workBook.getNumberOfSheets();
				workBook.close();
				
				int numberOfExecutedTCs = 0;
				
				for(int i=0;i<numberOfSheets;i++){
					List<String> whereClause1 = new ArrayList<String>();
					whereClause1.add("Execute::Yes");
					Map<String, List<String>> result1 = poiObject.fetchWithCondition(path, workBook.getSheetName(i), whereClause1);
					numberOfExecutedTCs = numberOfExecutedTCs+result1.get("Execute").size();
				}
				
				if(numberOfExecutedTCs!=0){
					Report reportObject = new Report();
					mainresultsFolder=testngDriver.CreateDateFolder(homePath);
					resultsFolder = testngDriver.CreateExecutionFolder(mainresultsFolder, "WebAutomation", true);
					//Creating the overall summary file inside the above created folder for the execution
					reportObject.CreateOverallSummaryFile(resultsFolder);
					
					flag = true;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@AfterSuite
	public void CloseSummary(){
		//if flag value is true then the close summary step is executed
		if(flag){
			Util utilObject = new Util();
			Report reportObject = new Report();
			
			String env = utilObject.getValueFromConfigProperties("EnvironmentName");
			String outputFolderName = utilObject.getValueFromConfigProperties("OutputFolder");
			reportObject.CloseSummary(resultsFolder);

			//Method which writes the status and other details of the Web related test cases to Overall Summary report
			reportObject.AddRowToOverallSummary(resultsFolder, "WebAutomation", ExecuteScripts.browsersList);
						
			if(utilObject.getValueFromConfigProperties("SendEmail").trim().equalsIgnoreCase("Yes")){
				try {
				 	File f = new File("C:\\"+outputFolderName);
					if(!f.exists()){
						f.mkdir();
					}else{
						//FileUtils.cleanDirectory(new File("C:\\Output_Results_Parallel"));
					}
					
					f = new File("C:\\"+outputFolderName+"\\"+env);
					if(!f.exists()){
						f.mkdir();
					}else{
						FileUtils.cleanDirectory(new File("C:\\"+outputFolderName+"\\"+env));
					}
					
					try {
						// TODO Auto-generated method stub
						File directoryToZip = new File(resultsFolder+"\\..");
						String zipFilePath = "C:\\"+outputFolderName+"\\"+env;
						List<File> fileList = new ArrayList<File>();
						System.out.println("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
						getAllFiles(directoryToZip, fileList);
						System.out.println("---Creating zip file");
						writeZipFile(directoryToZip, fileList, zipFilePath, ExecuteScripts.dateNTime);
						System.out.println("---Done");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					FileUtils.copyFile(new File(Report.executionSummaryHTML), new File("C:\\"+outputFolderName+"\\"+env+"\\Execution_Summary.htm"));
					FileUtils.copyFile(new File(new File(".").getCanonicalPath()+"\\"+ExecuteScripts.dateNTime+".zip"), new File("C:\\"+outputFolderName+"\\"+env+"\\"+ExecuteScripts.dateNTime+".zip"));
					
					Thread.sleep(5000);
					
					sendEmail("C:\\"+outputFolderName+"\\"+env+"\\Execution_Summary.htm", "C:\\"+outputFolderName+"\\"+env+"\\"+ExecuteScripts.dateNTime+".zip");
					
					FileUtils.deleteQuietly(new File(new File(".").getCanonicalPath()+"\\"+ExecuteScripts.dateNTime+".zip"));
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			 
		}
	}
	
	public void getAllFiles(File dir, List<File> fileList) {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				fileList.add(file);
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());
					getAllFiles(file, fileList);
				} else {
					System.out.println("     file:" + file.getCanonicalPath());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeZipFile(File directoryToZip, List<File> fileList, String zipFilePath, String zipFileName) {

		try {
			//FileOutputStream fos = new FileOutputStream(directoryToZip.getName() + ".zip");
			FileOutputStream fos = new FileOutputStream(zipFileName + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (File file : fileList) {
				if (!file.isDirectory()) { // we only zip files, not directories
					addToZip(directoryToZip, file, zos);
				}
			}

			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,	IOException {

		FileInputStream fis = new FileInputStream(file);

		// we want the zipEntry's path to be a relative path that is relative
		// to the directory being zipped, so chop off the rest of the path
		
		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,file.getCanonicalPath().length());
				
		System.out.println("Writing '" + zipFilePath + "' to zip file");
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}
	
	public void sendEmail(String summaryFilePath, String attachmentName) throws FileNotFoundException,	IOException {

		try {
			
			Util utilObject = new Util();
			String env = utilObject.getValueFromConfigProperties("EnvironmentName");
			
			String path = new File(".").getCanonicalPath();
			 File f = null;
		     // create new file
		     f = new File(path+"\\SupportLibraries\\EmailPS.bat");
		         
	         if(!f.exists()){
		         // tries to create new file in the system
		         f.createNewFile();
	         }
		
            FileWriter writer = new FileWriter(path+"\\SupportLibraries\\EmailPS.bat");
            writer.write("%SYSTEMROOT%\\System32\\WindowsPowerShell\\v1.0\\powershell.exe  -executionpolicy remotesigned -File  "+path+"\\SupportLibraries\\email.ps1"+" -filePath  \""+"C:\\Output_Results_Parallel\\"+env+"\\Execution_Summary.htm\" -attachmentPath  \""+attachmentName+"\"\npause");
            writer.close();
            
            Runtime.getRuntime().exec("cmd.exe /c start "+path+"\\SupportLibraries\\EmailPS.bat");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}


	@Test
	  public void SNAP() {
		if(flag){
			ExecuteScripts driverObject = new ExecuteScripts();
			driverObject.ExecuteModule(Thread.currentThread().getStackTrace()[1].getMethodName(),resultsFolder);
		}
	}
	
	@Test
	  public void CCAP() {
		if(flag){
			ExecuteScripts driverObject = new ExecuteScripts();
			driverObject.ExecuteModule(Thread.currentThread().getStackTrace()[1].getMethodName(),resultsFolder);
		}
	}
	
	@Test
	  public void RIW() {
		if(flag){
			ExecuteScripts driverObject = new ExecuteScripts();
			driverObject.ExecuteModule(Thread.currentThread().getStackTrace()[1].getMethodName(),resultsFolder);
		}
	}
	
	@Test
	  public void SEP() {
		if(flag){
			ExecuteScripts driverObject = new ExecuteScripts();
			driverObject.ExecuteModule(Thread.currentThread().getStackTrace()[1].getMethodName(),resultsFolder);
		}
	}	
	
	@Test
	  public void HIXTOIES() {
		if(flag){
			ExecuteScripts driverObject = new ExecuteScripts();
			driverObject.ExecuteModule(Thread.currentThread().getStackTrace()[1].getMethodName(),resultsFolder);
		}
	}
	
	@Test
	  public void PA_RMC() {
		if(flag){
			ExecuteScripts driverObject = new ExecuteScripts();
			driverObject.ExecuteModule(Thread.currentThread().getStackTrace()[1].getMethodName(),resultsFolder);
		}
	}
	
	@Test
	  public void Regression() {
		if(flag){
			ExecuteScripts driverObject = new ExecuteScripts();
			driverObject.ExecuteModule(Thread.currentThread().getStackTrace()[1].getMethodName(),resultsFolder);
		}
	}
	
	@Test
	  public void CaseDataLoadFlows() {
		if(flag){
			ExecuteScripts driverObject = new ExecuteScripts();
			driverObject.ExecuteModule(Thread.currentThread().getStackTrace()[1].getMethodName(),resultsFolder);
		}
	}
}
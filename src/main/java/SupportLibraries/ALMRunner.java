package SupportLibraries;

import java.io.FileReader;
import java.net.HttpURLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.common.io.Files;




import org.testng.internal.PropertyUtils;

//import com.bddframework.config.SeleniumNGSuite;

public class ALMRunner{
	
	static String hostname = "";
	static String port = "";
	static String domain = "";
	static String project = "";
	static String username = "";
	static String password = "";

public static void PostALMResults(String testsetid, String testcasename, String status) throws Exception {
		Properties propConfig = new Properties();
		FileReader reader = new FileReader("ALMRunner_Config.properties");
		propConfig.load(reader);
		hostname = propConfig.getProperty("HOST");
		port = propConfig.getProperty("PORT");
		domain = propConfig.getProperty("DOMAIN");
		project = propConfig.getProperty("PROJECT");
		username = propConfig.getProperty("USERNAME");
		password = propConfig.getProperty("PASSWORD");
		
		//Reading user credentials and ALM project details from config file 
		if (port.isEmpty())
		{RestConnector con = RestConnector.getInstance().init(new HashMap<String, String>(),"http://"+ hostname + "/qcbin", domain, project);
		}
		else
		{RestConnector con = RestConnector.getInstance().init(new HashMap<String, String>(),"http://"+ hostname + ":" + port + "/qcbin", domain, project);
		}
		ALMRunner almrunner = new ALMRunner();

		// Reading authentication point
		String authenticationPoint = almrunner.isAuthenticated();
		System.out.println("connecting to alm :" + authenticationPoint);

		// Logging in and printing login status
		boolean loginResponse = almrunner.login(authenticationPoint, username, password);
		System.out.println("Login status : " + loginResponse);

		// After login set the session
		//con.getQCSession(); //Check later

		// create request headers
		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("Content-Type", "application/xml");
		requestHeaders.put("Accept", "application/xml");

		// Building test instances URL within a test set using test set ID

		String testInstancesUrl = con.buildEntityCollectionUrl("test-instance");
		testInstancesUrl += "?query={cycle-id[" + testsetid + "]}";

		// Reading all the test instances
		Response testinstanceresponse = con.httpGet(testInstancesUrl, null, requestHeaders);

		// Identifying an individual test using test name by iterating over all
		// the instances in the test cycle

		/*Entities testinstances = EntityMarshallingUtils.marshal(Entities.class, testinstanceresponse.toString());
		Entity in = null;

		for (Object object : testinstances.entities()) {
			Entity in1 = (Entity) object;
			if (in1.fieldValue("name").contains(testcasename)) {
				in = in1;
				break;
			}
		}

		// Creating a new run for the test

		System.out.println("Creating test run for test case - " + in.fieldValue("name"));*/ //Check later

		// Building test run url
		String testrunurl = con.buildEntityCollectionUrl("run");

		// Building xml for new run
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		final SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		final String runName = testcasename + "Run_" + dateFormat2.format(new Date());

	/*	String newrun = String.format(ALM_Constants.testrunformat, "name", runName, "test-instance", "1", "testcycl-id",
				in.fieldValue("id"), "cycle-id", testsetid, "test-id", in.fieldValue("test-id"), "subtype-id",
				"hp.qc.run.MANUAL", "status", "Not Completed", "owner", username);

		// Creating new test run
		String createdtestrunurl = almrunner.createEntity(testrunurl, newrun);
		//Check Later

		//Reading status
		String stat ="";
		if(status.contains("pass")){ stat = "Passed";}
		if(status.contains("fail")){ stat = "Failed";}
		
		
		// reading the created test run and updating the staus to pass

		/*System.out.println("Executing test : " + in.fieldValue("name"));
		
		String runupdatepass = String.format(ALM_Constants.testrunstatus, "status", stat); 

		almrunner.updateEntity(createdtestrunurl, runupdatepass);
*/
		//Check Later
		
		// reading all test steps of created test run

		//Response teststeps = con.httpGet(createdtestrunurl + "/run-steps", null, requestHeaders);
		Response teststeps = con.httpGet("" + "/run-steps", null, requestHeaders);
		
		// Iterating over all the test steps and updating status for each test
		// step
/*
		Entities teststepsentities = EntityMarshallingUtils.marshal(Entities.class, teststeps.toString());
		String stepupdatepass = String.format(ALM_Constants.teststepstatus, "status", stat);

		for (Object object : teststepsentities.entities()) {
			Entity in1 = (Entity) object;
			almrunner.updateEntity(createdtestrunurl + "/run-steps/" + in1.fieldValue("id"), stepupdatepass);
		}

		
		// Reading attachment filename and file contents
		Path filepath = Paths.get(resultfilepath);
		String FileName = filepath.getFileName().toString();
		byte[] fileContent = Files.readAllBytes(filepath);

		// Creating attachment to the updated test run

		almrunner.attachWithOctetStream(createdtestrunurl, fileContent, FileName); */  //Check Later
		System.out.println("Execution complete");
		
		
		// logout
		System.out.println("logout status :" + almrunner.logout());
	}

	public boolean login(String username, String password) throws Exception {

		String authenticationPoint = this.isAuthenticated();
		if (authenticationPoint != null) {
			return this.login(authenticationPoint, username, password);
		}
		return true;
	}

	public boolean login(String loginUrl, String username, String password) throws Exception {

		byte[] credBytes = (username + ":" + password).getBytes();
		String credEncodedString = "Basic " + Base64Encoder.encode(credBytes);

		Map<String, String> map = new HashMap<String, String>();
		map.put("Authorization", credEncodedString);

		Response response = con.httpGet(loginUrl, null, map);

		boolean ret = response.getStatusCode() == HttpURLConnection.HTTP_OK;

		return ret;
	}

	public boolean logout() throws Exception {

		Response response = con.httpGet(con.buildUrl("authentication-point/logout"), null, null);

		return (response.getStatusCode() == HttpURLConnection.HTTP_OK);

	}

	public ALMRunner() {
		con = RestConnector.getInstance();
	}

	private static RestConnector con;

	public String isAuthenticated() throws Exception {

		String isAuthenticateUrl = con.buildUrl("rest/is-authenticated");
		String ret;

		Response response = con.httpGet(isAuthenticateUrl, null, null);
		int responseCode = response.getStatusCode();

		// if already authenticated
		if (responseCode == HttpURLConnection.HTTP_OK) {
			ret = null;
		}

		// if not authenticated - get the address where to authenticate
		// via WWW-Authenticate
		else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
			
			ret = "http://"+hostname+":"+port+"/qcbin/authentication-point/authenticate"; 
		}

		// Not ok, not unauthorized. An error, such as 404, or 500
		else {

			throw response.getFailure();
		}

		return ret;
	}

	public String createEntity(String collectionUrl, String postedEntityXml) throws Exception {

		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("Content-Type", "application/xml");
		requestHeaders.put("Accept", "application/xml");

		Response response = con.httpPost(collectionUrl, postedEntityXml.getBytes(), requestHeaders);

		Exception failure = response.getFailure();
		if (failure != null) {
			throw failure;
		}

		String entityUrl = response.getResponseHeaders().get("Location").iterator().next();

		return entityUrl;
	}

	public void updateEntity(String collectionUrl, String postedEntityXml) throws Exception {

		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("Content-Type", "application/xml");
		requestHeaders.put("Accept", "application/xml");

		Response response = con.httpPut(collectionUrl, postedEntityXml.getBytes(), requestHeaders);

		Exception failure = response.getFailure();
		if (failure != null) {
			throw failure;
		}

	}

	@SuppressWarnings("unused")
	private String attachWithOctetStream(String entityUrl, byte[] fileData, String filename) throws Exception {

		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("Slug", filename);
		requestHeaders.put("Content-Type", "application/octet-stream");

		Response response = con.httpPost(entityUrl + "/attachments", fileData, requestHeaders);

		if (response.getStatusCode() != HttpURLConnection.HTTP_CREATED) {
			throw new Exception(response.toString());
		}

		return response.getResponseHeaders().get("Location").iterator().next();
	}

}
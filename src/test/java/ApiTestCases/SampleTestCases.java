package ApiTestCases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import execution.RestAssuredCRUDTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileNotFoundException;

import java.util.List;
import java.util.Map;

public class SampleTestCases {
	
	private static final String BASE_URL = "https://gorest.co.in/public/v2";
    private static final String ACCESS_TOKEN = "8416c70904c511ecb4711f84992abe2fe89ccb4a84b511b47d773eb9464cae61";
    
@Test
        public void testGetRequest() {
            String testCaseName = "Get Users"; // Change this to the desired test case name
            RestAssuredCRUDTest crudTest = new RestAssuredCRUDTest();
            try {
            	Response response = crudTest.executeTestCases(testCaseName);
            	
            	System.out.print("QQQQQQQQQQQQQ");
            	
            	System.out.print(response.then().log().body());
            	
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

@Test
public void testPOSTRequest() {
    String testCaseName = "Create User"; // Change this to the desired test case name
    RestAssuredCRUDTest crudTest = new RestAssuredCRUDTest();
    try {
    	Response response = crudTest.executeTestCases(testCaseName);
    	
    	System.out.print("QQQQQQQQQQQQQ");
    	
    	System.out.print(response.then().log().body());
    	
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}
 
@Test
public void testDeleteRequest() throws FileNotFoundException {
	
	String testCaseName = "Delete User"; // Change this to the desired test case name
	RestAssuredCRUDTest crudTest = new RestAssuredCRUDTest();
	
    try {
    	Response response = crudTest.executeTestCases(testCaseName);
    	
    	System.out.print("QQQQQQQQQQQQQ");
    	
    	System.out.print(response.then().log().body());
    	
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
	

}


}
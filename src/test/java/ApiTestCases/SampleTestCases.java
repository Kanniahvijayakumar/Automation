package apiTestCases;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.testng.annotations.Test;
import excelRead.ApiReadJsonResponse;
import execution.RestAssuredCRUDTest;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.equalTo;


public class SampleTestCases {

    // Create an instance of RestAssuredCRUDTest to be used in all test methods
    private final RestAssuredCRUDTest crudTest = new RestAssuredCRUDTest();
    
    private static final Logger logger = LogManager.getLogger(SampleTestCases.class);
    
    JSONArray jsonArray;
    
    String jsonData;

    /**
     * Test case for GET request to retrieve users.
     * @throws FileNotFoundException 
     */
    @Test(priority = 1)
    public void testGetRequest() throws FileNotFoundException {
        String testCaseName = "Get_Users"; // Name of the test case


            // Execute the test case and get the response
            Response response = crudTest.executeTestCases(testCaseName, null);

            // Log the response body
            logger.info("Response Body: {}", response.then().log().body().log());

    }

    /**
     * Test case for POST request to create a user.
     * @throws FileNotFoundException 
     */
    @Test(priority = 2)
    public void testPOSTRequest() throws FileNotFoundException {
        String testCaseName = "Create_User"; // Name of the test case


            // Execute the test case and get the response
            Response response = crudTest.executeTestCases(testCaseName, null);

            // Log the response body
            logger.info("Response Body: {}", response.then().log().body().log());

    }
    
    @Test(priority = 3)
    public void testUpdateUser() throws FileNotFoundException {
        String testCaseName = "Update_User"; // Name of the test case


            // Execute the test case and get the response
            Response response = crudTest.executeTestCases("Get_Users", null);

            // Log the response body
            logger.info("Response Body: {}", response.then().log().body());
            
            // Get the id of the email
            String id = String.valueOf( response.jsonPath().getInt("find { it.email == 'vijay123@gmail.com' }.id"));
            
            response = crudTest.executeTestCases(testCaseName, id);
            
            logger.info("Response Body: {}", response.then().log().body());
            
    }
    
    
    /**
     * Test case for DELETE request to delete a user.
     * @throws FileNotFoundException 
     */
    @Test(priority = 4)
    public void testDeleteRequest() throws FileNotFoundException {
        String testCaseName = "Delete_User"; // Name of the test case
            
            // Execute the test case and get the response
        	Response response = crudTest.executeTestCases("Get_Users", null);

            // Log the response body
            logger.info("Response Body: {}", response.then().log().body());
            
            // Get the id of the email
            String id = String.valueOf( response.jsonPath().getInt("find { it.email == 'vijay123@gmail.com' }.id"));
            
            response = crudTest.executeTestCases(testCaseName, id);
            
            logger.info("Response Body: {}", response.then().log().body());
            
    }
}

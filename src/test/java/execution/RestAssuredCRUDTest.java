package execution;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestAssuredCRUDTest {
	
    String accessToken = "8416c70904c511ecb4711f84992abe2fe89ccb4a84b511b47d773eb9464cae61";
    private static final Logger logger = LogManager.getLogger(RestAssuredCRUDTest.class);
    
    ///////
    
    /**
     * Get the value from YAML based on the test case name and keyword.
     * @param testCaseName the name of the test case.
     * @param keyword the keyword to search for in the test case.
     * @return the value corresponding to the keyword.
     * @throws FileNotFoundException if the YAML file containing test cases is not found.
     */
    public String getTestCaseValue(String testCaseName, String keyword) throws FileNotFoundException {
        // Get the project directory
        String projectDir = System.getProperty("user.dir");

        // Load the test cases from the YAML file
        FileInputStream fileInputStream = new FileInputStream(projectDir + "/src/test/resources/YamlFiles/testcases.yaml");
        Yaml yaml = new Yaml();
        Map<String, List<Map<String, Object>>> testCases = yaml.load(fileInputStream);

        // Find the test case based on the provided name
        Map<String, Object> testCase = null;
        for (Map<String, Object> tc : testCases.get("testCases")) {
            if (tc.get("name").equals(testCaseName)) {
                testCase = tc;
                break;
            }
        }

        // Throw an exception if the test case is not found
        if (testCase == null) {
            throw new IllegalArgumentException("Test case with name '" + testCaseName + "' not found.");
        }
        
     // Check if the keyword exists in the body section
        if (testCase.containsKey("body")) {
            @SuppressWarnings("unchecked")
            Map<String, Object> body = (Map<String, Object>) testCase.get("body");
            if (body.containsKey(keyword)) {
                return String.valueOf(body.get(keyword));
            }
        }

        // Return the value corresponding to the keyword
        return String.valueOf(testCase.get(keyword));
    }
    
    /////
    
    /**
     * Executes a test case based on the test case name provided.
     * @param testCaseName the name of the test case to execute.
     * @return Response from the executed test case.
     * @throws FileNotFoundException if the YAML file containing test cases is not found.
     */
    public Response executeTestCases(String testCaseName, String id) throws FileNotFoundException {
        // Get the project directory
        String projectDir = System.getProperty("user.dir");

        // Load the test cases from the YAML file
        FileInputStream fileInputStream = new FileInputStream(projectDir + "/src/test/resources/YamlFiles/testcases.yaml");
        Yaml yaml = new Yaml();
        Map<String, List<Map<String, Object>>> testCases = yaml.load(fileInputStream);

        // Find the test case based on the provided name
        Map<String, Object> testCase = null;
        for (Map<String, Object> tc : testCases.get("testCases")) {
            if (tc.get("name").equals(testCaseName)) {
                testCase = tc;
                break;
            }
        }

        // Throw an exception if the test case is not found
        if (testCase == null) {
            throw new IllegalArgumentException("Test case with name '" + testCaseName + "' not found.");
        }

        // Execute the found test case
        return executeTestCase(testCase, id);
    }

    /**
     * Executes the test case provided as a map of test case details.
     * @param testCase the test case details.
     * @return Response from the executed test case.
     */
    private Response executeTestCase(Map<String, Object> testCase, String id) {
        // Get the HTTP method and endpoint from the test case
        String method = (String) testCase.get("method");
        String endpoint = (String) testCase.get("endpoint");

        // Create a request specification
        RequestSpecification request = RestAssured.given();
        
        // Add Authorization header with a token
        request.header("Authorization", "Bearer " + accessToken);
        
        
        try {
        // Set path parameters if present
        if (testCase.containsKey("parameters")) {
            @SuppressWarnings("unchecked")
            Map<String, Object> parameters = (Map<String, Object>) testCase.get("parameters");
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                request.pathParam(entry.getKey(), entry.getValue());
            }
        }

        // Set body and headers if present
        if (testCase.containsKey("body")) {
            request.body(testCase.get("body"));
            request.header("Content-Type", "application/json");


        }

        // Execute the HTTP request based on the method
        Response response;
        switch (method) {
            case "GET":
                response = request.get(endpoint);
                break;
            case "POST":
                response = request.post(endpoint);
                break;
            case "PUT":
                response = request.put(endpoint + "/" + id);
                break;
            case "DELETE":
                response = request.delete(endpoint + "/" + id);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
        
        // Validate the response based on the expected results
        validateResponse(testCase, response);

        // Return the response
        return response;

        // Additional assertions can be added here based on test case requirements
        
        } catch (IllegalArgumentException e) {
            logger.error("Invalid argument: {}", e.getMessage());
            throw e; // Rethrow the exception if necessary
        } catch (Exception e) {
            logger.error("Unexpected error occurred during API execution: {}", e.getMessage());
            throw new RuntimeException("Unexpected error during API execution", e);
        }
    }
    
    private void validateResponse(Map<String, Object> testCase, Response response) {
        if (testCase.containsKey("expectedResult")) {
            @SuppressWarnings("unchecked")
            Map<String, Object> expectedResult = (Map<String, Object>) testCase.get("expectedResult");

            // Validate status code
            if (expectedResult.containsKey("statusCode")) {
                int expectedStatusCode = (int) expectedResult.get("statusCode");
                response.then().statusCode(expectedStatusCode);
            }

            // Validate response body fields
            for (Map.Entry<String, Object> entry : expectedResult.entrySet()) {
                if (!entry.getKey().equals("statusCode")) { // Skip the status code
                    response.then().body(entry.getKey(), equalTo(entry.getValue()));
                }
            }
        }
    }

}

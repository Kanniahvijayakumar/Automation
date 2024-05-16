package execution;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class RestAssuredCRUDTest {
	public Response executeTestCases(String testCaseName) throws FileNotFoundException {
        // Read test cases from YAML file
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\kanni\\OneDrive\\Desktop\\Interview\\FrameWork\\src\\main\\resources\\YamlFiles\\testcases.yaml");
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

        if (testCase == null) {
            throw new IllegalArgumentException("Test case with name '" + testCaseName + "' not found.");
        }

        // Execute the found test case
        return  executeTestCase(testCase);
    }

    private Response executeTestCase(Map<String, Object> testCase) {
        String method = (String) testCase.get("method");
        String endpoint = (String) testCase.get("endpoint");
        RequestSpecification request = RestAssured.given();

        // Set parameters if present
        if (testCase.containsKey("parameters")) {
            @SuppressWarnings("unchecked")
			Map<String, Object> parameters = (Map<String, Object>) testCase.get("parameters");
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                request.pathParam(entry.getKey(), entry.getValue());
            }
        }
        


        // Set body if present
        if (testCase.containsKey("body")) {
            request.body(testCase.get("body"));
            
            System.out.print(testCase.get("body"));
            
         // Add Content-Type header
            request.header("Content-Type", "application/json");
            
            // Add Authorization header
            String accessToken = "8416c70904c511ecb4711f84992abe2fe89ccb4a84b511b47d773eb9464cae61";
            request.header("Authorization", "Bearer " + accessToken);
            
            System.out.println("aaaaaaaaaaaaa");
            
        }

        // Execute request
        Response response;
        switch (method) {
            case "GET":
                response = request.get(endpoint);
                break;
            case "POST":
                response = request.post(endpoint);
                break;
            case "PUT":
                response = request.put(endpoint);
                break;
            case "DELETE":
                response = request.delete(endpoint);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }

//        // Assert response
//        response.then().statusCode(200); // Assuming 200 is the expected status code
//        
//        System.out.println(response.then().log().body());
        
        return response;
        
        // You can add more assertions here based on your test case requirements
    }

}

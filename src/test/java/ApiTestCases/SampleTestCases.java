package ApiTestCases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SampleTestCases {
	
	private static final String BASE_URL = "https://gorest.co.in/public/v2";
    private static final String ACCESS_TOKEN = "c3025ece9f7715439cf26cc7cadb94244ca633b999ebb4fdae247c067ad64771";
    
    @BeforeClass
    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//        RestAssured.baseURI = "https://gorest.co.in/public/v2/";
    }

    @Test
    public void testGetRequest() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2/";

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/users")
        .then()
            .statusCode(200);
    }

//c3025ece9f7715439cf26cc7cadb94244ca633b999ebb4fdae247c067ad64771

@Test
public void testListUsers() {
    given()
        .header("Accept", "application/json")
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer " + ACCESS_TOKEN)
    .when()
        .get(BASE_URL + "/users")
    .then()
        .statusCode(200);
}

}
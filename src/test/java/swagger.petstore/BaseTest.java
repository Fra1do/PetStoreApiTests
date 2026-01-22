package swagger.petstore;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        baseURI = "https://petstore.swagger.io";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}

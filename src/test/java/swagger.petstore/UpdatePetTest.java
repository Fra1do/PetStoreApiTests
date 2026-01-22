package swagger.petstore;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import swagger.petstore.enums.PetStatus;
import swagger.petstore.requests.CreatePetRequest;
import swagger.petstore.requests.UpdatePetRequest;
import swagger.petstore.responses.CreatePetResponse;
import swagger.petstore.responses.UpdatePetResponse;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UpdatePetTest extends BaseTest {

    @Test
    @DisplayName("update pet with all fields")
    public void updatePetWithAllFields() {

        var pet = CreatePetRequest.build()
                .setId(33333)
                .setName("Barsik");

        var createPetResponse = given()
                .with()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/v2/pet")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(CreatePetResponse.class);

        String updatedName = String.format("update %s", createPetResponse.getName());
        String updatedStatus = PetStatus.SOLD.getValue();

        UpdatePetRequest request = UpdatePetRequest.build()
                .setPetId(createPetResponse.getId())
                .setName(updatedName)
                .setStatus(updatedStatus);

        UpdatePetResponse expectedResponse = UpdatePetResponse.build()
                .setCode(200)
                .setType("unknown")
                .setMessage(request.getPetId().toString());

        var actualResponse = given()
                .with()
                .pathParam("id", request.getPetId())
                .formParam("name", request.getName())
                .formParam("status", request.getStatus())
                .when()
                .post("/v2/pet/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(UpdatePetResponse.class);

        assertThat(actualResponse)
                .as("update response is equals")
                .isEqualTo(expectedResponse);

        var updatedPetResponse = given().with()
                .pathParam("id", request.getPetId())
                .when()
                .get("/v2/pet/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(CreatePetResponse.class);

        var updatedPet = CreatePetResponse.build()
                .setId(request.getPetId())
                .setName(updatedName)
                .setStatus(updatedStatus);

        assertThat(updatedPetResponse)
                .as("update pet response is equals")
                .isEqualTo(updatedPet);
    }

    @Test
    @DisplayName("update pet with required fields")
    public void updatePetWithRequireFields() {

        var pet = CreatePetRequest.build()
                .setId(33335)
                .setName("Barsik");

        var createPetResponse = given()
                .with()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/v2/pet")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(CreatePetResponse.class);

        UpdatePetResponse expectedResponse = UpdatePetResponse.build()
                .setCode(200)
                .setType("unknown")
                .setMessage(String.valueOf(createPetResponse.getId()));

        var actualResponse = given()
                .with()
                .pathParam("id", createPetResponse.getId())
                .when()
                .post("/v2/pet/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(UpdatePetResponse.class);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    @DisplayName("update pet with required fields")
    public void updatePetWithRequireFieldsTest() {

        var pet = CreatePetRequest.build()
                .setId(33335)
                .setName("Barsik");

        var createPetResponse = given()
                .with()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/v2/pet")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(CreatePetResponse.class);

        UpdatePetResponse expectedResponse = UpdatePetResponse.build()
                .setCode(200)
                .setType("unknown")
                .setMessage(String.valueOf(createPetResponse.getId()));

        var actualResponse = given()
                .with()
                .pathParam("id", createPetResponse.getId())
                .when()
                .post("/v2/pet/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(UpdatePetResponse.class);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    @DisplayName("update pet with empty fields")
    public void updatePetWithEmptyFields() {

        var pet = CreatePetRequest.build()
                .setId(33336)
                .setName("Barsik");

        var createPetResponse = given()
                .with()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/v2/pet")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(CreatePetResponse.class);

        String emptyString = "";

        UpdatePetRequest request = UpdatePetRequest.build()
                .setPetId(createPetResponse.getId())
                .setName(emptyString)
                .setStatus(emptyString);

        UpdatePetResponse expectedResponse = UpdatePetResponse.build()
                .setCode(200)
                .setType("unknown")
                .setMessage(request.getPetId().toString());

        var actualResponse = given()
                .with()
                .pathParam("id", request.getPetId())
                .formParam("name", emptyString)
                .formParam("status", emptyString)
                .when()
                .post("/v2/pet/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(UpdatePetResponse.class);

        assertThat(actualResponse).isEqualTo(expectedResponse);

        var updatedPetResponse = given().with()
                .pathParam("id", request.getPetId())
                .when()
                .get("/v2/pet/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(CreatePetResponse.class);

        var updatedPet = CreatePetResponse.build()
                .setId(request.getPetId())
                .setName(emptyString)
                .setStatus(emptyString);

        assertThat(updatedPetResponse)
                .as("update pet response is equals")
                .isEqualTo(updatedPet);
    }

    @Test
    @DisplayName("update non-existed pet")
    public void updateNonExistedPetTest() {

               UpdatePetRequest request = UpdatePetRequest.build()
                .setPetId(111111111)
                .setName("Barsik")
                .setStatus(PetStatus.AVAILABLE.getValue());

        UpdatePetResponse expectedResponse = UpdatePetResponse.build()
                .setCode(404)
                .setType("unknown")
                .setMessage("not found");

        var actualResponse = given()
                .with()
                .pathParam("id", request.getPetId())
                .formParam("name", request.getName())
                .formParam("status", request.getStatus())
                .when()
                .post("/v2/pet/{id}")
                .then()
                .log().all()
                .statusCode(404)
                .extract()
                .as(UpdatePetResponse.class);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}

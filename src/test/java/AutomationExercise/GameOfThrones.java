package AutomationExercise;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GameOfThrones {

    @Test
    public void validateCharacterInformation(){

        RestAssured.baseURI="https://thronesapi.com";
        RestAssured.basePath="/api/v2/Characters/10";

        Response response=RestAssured.given().header("Accept","application/json")
                .when()
                .get()
                .then()
                .statusCode(200).log().body().extract().response();

        Map<String,Object> deserializedResponse=response.as(new TypeRef<Map<String, Object>>() {});

         Assertions.assertEquals(10,deserializedResponse.get("id"));
         Assertions.assertEquals("Stark",deserializedResponse.get("lastName"));
         Assertions.assertEquals( "https://thronesapi.com/assets/images/catelyn-stark.jpg",deserializedResponse.get("imageUrl"));

    }
}

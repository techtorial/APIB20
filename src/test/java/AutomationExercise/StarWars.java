package AutomationExercise;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.StarWarsPlanetPojo;

public class StarWars {

    @Test
    public void validatePlanetInformationWithPojo(){

        RestAssured.baseURI="https://swapi.dev";
        RestAssured.basePath="/api/planets/1";

        Response response=RestAssured.given().accept("application/json")
                .when().get().then().statusCode(200).log().body().extract().response();

        StarWarsPlanetPojo deserializedResponse=response.as(StarWarsPlanetPojo.class);
        Assertions.assertEquals("Tatooine",deserializedResponse.getName());
        Assertions.assertEquals("arid",deserializedResponse.getClimate());
        Assertions.assertEquals("desert",deserializedResponse.getTerrain());
    }
}

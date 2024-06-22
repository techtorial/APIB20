package AutomationExercise;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.StarWarsPlanetPojo;
import pojo.StarwarsPeoplePojo;

import java.util.Arrays;
import java.util.List;

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

     @Test
    public void validateCharactersInfo(){

         RestAssured.baseURI="https://swapi.dev";
         RestAssured.basePath="/api/people/1";

         Response response=RestAssured.given().accept("application/json")
                 .when().get().then().statusCode(200).log().body().extract().response();

         StarwarsPeoplePojo deserializedResponse=response.as(StarwarsPeoplePojo.class);

         Assertions.assertEquals("Luke Skywalker",deserializedResponse.getName());
         Assertions.assertEquals("172",deserializedResponse.getHeight());
         Assertions.assertEquals("77",deserializedResponse.getMass());
         Assertions.assertEquals("male",deserializedResponse.getGender());
         Assertions.assertEquals(4,deserializedResponse.getFilms().size());

         List<String> expectedVehicles= Arrays.asList("https://swapi.dev/api/vehicles/14/",
                                                     "https://swapi.dev/api/vehicles/30/");
         Assertions.assertEquals(expectedVehicles,deserializedResponse.getVehicles());
     }
    /*
    1-Validate Name height,mass,gender, size of films(4) and vehicles name(Arrays.aslist)

     */
}

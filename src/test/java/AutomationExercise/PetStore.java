package AutomationExercise;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.PetStorePojo;

public class PetStore {

    @Test
    public void createPet(){

        RestAssured.baseURI="https://petstore.swagger.io/v2";
        RestAssured.basePath="pet";

        Response response=RestAssured.given().contentType("application/json").accept("application/json")
                .body("{\n" +
                        "  \"id\": 999,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"Birds\"\n" +
                        "  },\n" +
                        "  \"name\": \"MeRCY\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"www.amazon.png\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"Really Nice Birds\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"On Hold\"\n" +
                        "}")
                .when().post().then().statusCode(200).log().body().extract().response();

        PetStorePojo deserializedResponse=response.as(PetStorePojo.class);

        Assertions.assertEquals(999,deserializedResponse.getId());
        Assertions.assertEquals(0,deserializedResponse.getCategory().getId());
        Assertions.assertEquals("Birds",deserializedResponse.getCategory().getName());
        Assertions.assertEquals("MeRCY",deserializedResponse.getName());
        Assertions.assertEquals("www.amazon.png",deserializedResponse.getPhotoUrls().getFirst());
        Assertions.assertEquals(0,deserializedResponse.getTags().getFirst().getId());
        Assertions.assertEquals("Really Nice Birds",deserializedResponse.getTags().getFirst().getName());
        Assertions.assertEquals("On Hold",deserializedResponse.getStatus());
    }

     @Test
    public void CreateBookWithJsonPath(){
         RestAssured.baseURI="https://petstore.swagger.io/v2";
         RestAssured.basePath="pet";

         Response response=RestAssured.given().contentType("application/json").accept("application/json")
                 .body("{\n" +
                         "  \"id\": 999,\n" +
                         "  \"category\": {\n" +
                         "    \"id\": 0,\n" +
                         "    \"name\": \"Birds\"\n" +
                         "  },\n" +
                         "  \"name\": \"MeRCY\",\n" +
                         "  \"photoUrls\": [\n" +
                         "    \"www.amazon.png\"\n" +
                         "  ],\n" +
                         "  \"tags\": [\n" +
                         "    {\n" +
                         "      \"id\": 0,\n" +
                         "      \"name\": \"Really Nice Birds\"\n" +
                         "    }\n" +
                         "  ],\n" +
                         "  \"status\": \"On Hold\"\n" +
                         "}")
                 .when().post().then().statusCode(200).log().body().extract().response();

         JsonPath deserializedResponse=response.jsonPath();

         Assertions.assertEquals("MeRCY",deserializedResponse.get("name"));
         Assertions.assertEquals("On Hold",deserializedResponse.getString("status"));
         Assertions.assertEquals("www.amazon.png",deserializedResponse.getList("photoUrls").getFirst());
         Assertions.assertEquals("www.amazon.png",deserializedResponse.get("photoUrls[0]"));
         Assertions.assertEquals("Really Nice Birds",deserializedResponse.get("tags[0].name"));
         Assertions.assertEquals("Really Nice Birds",deserializedResponse.getList("tags.name").getFirst());
     }

     @Test
    public void validateAirportDetails(){

        RestAssured.baseURI="https://airportgap.dev-tester.com";
        RestAssured.basePath="/api/airports";

        Response response=RestAssured.given().accept("application/json").when().get()
                .then().statusCode(200).log().body().extract().response();

        JsonPath deserializedResponse=response.jsonPath();

        Assertions.assertEquals("GKA",deserializedResponse.getList("data.id").getFirst());
        Assertions.assertEquals("GKA",deserializedResponse.get("data[0].id"));
        Assertions.assertEquals("GKA",deserializedResponse.getString("data[0].id"));
        Assertions.assertEquals("Goroka Airport",deserializedResponse.get("data[0].attributes.name"));
     }
}

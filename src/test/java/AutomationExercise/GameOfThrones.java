package AutomationExercise;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
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

    @Test
    public void validateContinents(){

        RestAssured.baseURI="https://thronesapi.com";
        RestAssured.basePath="/api/v2/Continents";

        Response response=RestAssured.given().header("Accept","application/json")
                .when().get()
                .then().statusCode(200).log().body().extract().response();

        List<Map<String,Object>> deserializedAllContinents=response.as(new TypeRef<List<Map<String, Object>>>() {});

        Assertions.assertEquals(4,deserializedAllContinents.size());

        List<String> expectedContinents= Arrays.asList("Westeros","Essos","Sothoryos","Ulthos");

        for(int i=0;i<deserializedAllContinents.size();i++){
            if(expectedContinents.size()==deserializedAllContinents.size()){
                  System.out.println(deserializedAllContinents.get(i).get("name"));
                  Assertions.assertEquals(expectedContinents.get(i),deserializedAllContinents.get(i).get("name"));
            }else{
                Assertions.fail("Size are not matching");
            }
          
        }

    }
















}

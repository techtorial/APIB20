package AutomationExercise;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FakeProduct {

    @Test
    public void validateSingleProductInformation(){

        RestAssured.baseURI="https://fakestoreapi.com"; //BASE URL
        RestAssured.basePath="/products/1"; //WHATEVER AFTER BASE URL

        Response response=RestAssured.given()//give is referring what is preCondition for the end point
                .header("Accept","application/json")
                .when()
                .get()
                .then()
                .log().body().statusCode(200).extract().response();

        //Deserialization is a way to convert JSON OBJECT TO JAVA CODE.

        Map<String,Object> deserializedResponse=response.as(new TypeRef<Map<String, Object>>() {});
        System.out.println(deserializedResponse);
        Assertions.assertEquals(1,deserializedResponse.get("id"));
        Assertions.assertEquals("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",deserializedResponse.get("title"));
        Assertions.assertEquals(109.95,deserializedResponse.get("price"));
        Assertions.assertEquals("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",deserializedResponse.get("description"));
        Assertions.assertEquals("men's clothing",deserializedResponse.get("category"));
        Assertions.assertEquals("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",deserializedResponse.get("image"));

        Map<String,Object> ratingInformation= (Map<String, Object>) deserializedResponse.get("rating");
        System.out.println(ratingInformation);
        Assertions.assertEquals(3.9,ratingInformation.get("rate"));
        Assertions.assertEquals(120,ratingInformation.get("count"));

    }
}

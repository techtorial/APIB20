package AutomationExercise;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class BookApi {

    /*
    1-I want you validate the token is not null with RestAssured and Body
     */
    @Test
    public void getToken(){

        RestAssured.baseURI="https://restful-booker.herokuapp.com";
        RestAssured.basePath="/auth";

        Response response= RestAssured.given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .when()
                .post()
                .then().statusCode(200).log().body().extract().response();

        Map<String,Object> deserializedToken=response.as(new TypeRef<Map<String, Object>>() {});
        Assertions.assertNotNull(deserializedToken.get("token"));
    }






}

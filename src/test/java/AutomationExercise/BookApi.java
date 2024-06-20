package AutomationExercise;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class BookApi {

    /*
    1-I want you validate the token is not null with RestAssured and Body
     */
    @Test
    public void getToken() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "/auth";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .when()
                .post()
                .then().statusCode(200).log().body().extract().response();

        Map<String, Object> deserializedToken = response.as(new TypeRef<Map<String, Object>>() {
        });
        Assertions.assertNotNull(deserializedToken.get("token"));
    }

    @Test
    public void validateBookDetails() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "/booking/99";

        Response response = RestAssured.given().header("Accept", "application/json")
                .when().get()
                .then().statusCode(200).log().body().extract().response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        Assertions.assertEquals("Josh", deserializedResponse.get("firstname"));
        Assertions.assertEquals(true, deserializedResponse.get("depositpaid"));

        Map<String,Object> bookingDatesInformation= (Map<String, Object>) deserializedResponse.get("bookingdates");
        Assertions.assertEquals("2018-01-01",bookingDatesInformation.get("checkin"));
    }

    @Test
    public void createBook(){

         RestAssured.baseURI="https://restful-booker.herokuapp.com";
         RestAssured.basePath="/booking";

         Response response=RestAssured.given().contentType("application/json").accept("application/json")
                 .body("{\n" +
                         "    \"firstname\" : \"Ahmet\",\n" +
                         "    \"lastname\" : \"Baldir\",\n" +
                         "    \"totalprice\" : 999,\n" +
                         "    \"depositpaid\" : true,\n" +
                         "    \"bookingdates\" : {\n" +
                         "        \"checkin\" : \"2024-01-01\",\n" +
                         "        \"checkout\" : \"2025-01-01\"\n" +
                         "    },\n" +
                         "    \"additionalneeds\" : \"Breakfast\"\n" +
                         "}")
                 .when().post()
                 .then().statusCode(200).log().body().extract().response();

         Map<String,Object> deserializedResponse=response.as(new TypeRef<Map<String, Object>>() {});

         Assertions.assertNotNull(deserializedResponse.get("bookingid"));

          Map<String,Object> bookingInformation= (Map<String, Object>) deserializedResponse.get("booking");
         Assertions.assertEquals("Ahmet",bookingInformation.get("firstname"));
         Assertions.assertEquals("Breakfast",bookingInformation.get("additionalneeds"));
         Map<String,Object> bookingDates= (Map<String, Object>) bookingInformation.get("bookingdates");
         Assertions.assertEquals("2024-01-01",bookingDates.get("checkin"));
    }

    @Test
    public void updateBook(){

        RestAssured.baseURI="https://restful-booker.herokuapp.com";
        RestAssured.basePath="/booking/5";

        Response response=RestAssured.given().header("Content-Type","application/json").header("Accept","application/json")
//                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .auth().preemptive().basic("admin","password123")
                .body("{\n" +
                        "    \"firstname\" : \"Ahmet\",\n" +
                        "    \"lastname\" : \"Baldir\",\n" +
                        "    \"totalprice\" : 555,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2025-01-01\",\n" +
                        "        \"checkout\" : \"2030-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"UPDATED SOFTWARE ENGINEER\"\n" +
                        "}")
                .when().put()
                .then().statusCode(200).log().body().extract().response();

            Map<String,Object> deserializedResponse=response.as(new TypeRef<Map<String, Object>>() {});
            Assertions.assertEquals("Ahmet",deserializedResponse.get("firstname"));
    }

        //Please do create and update automation. Update needs Authorization(Basic etsts34234) id can be AssertNotNull

}

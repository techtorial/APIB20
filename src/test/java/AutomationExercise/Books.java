package AutomationExercise;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.BookPojo;

public class Books {

    @Test
    public void validateBookDetailsWithPojo(){

        RestAssured.baseURI="https://restful-booker.herokuapp.com";
        RestAssured.basePath="/booking/990";

        Response response=RestAssured.given().header("Accept","application/json")
                .when().get()
                .then().statusCode(200).log().body().extract().response();

        BookPojo deserializedResponse=response.as(BookPojo.class);
        Assertions.assertEquals("Josh",deserializedResponse.getFirstname());
        Assertions.assertTrue(deserializedResponse.isDepositpaid());
        Assertions.assertEquals("2018-01-01",deserializedResponse.getBookingdates().getCheckin());

    }

    @Test
    public void validateUpdateFunctionalityOfAPIWithPojo(){

        RestAssured.baseURI="https://restful-booker.herokuapp.com";
        RestAssured.basePath="/booking/83";

        Response response=RestAssured.given().contentType("application/json").accept("application/json")
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
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
                .when().put().then().statusCode(200).log().body().extract().response();

    BookPojo deserializedResponse=response.as(BookPojo.class);

     Assertions.assertEquals(555,deserializedResponse.getTotalprice());
     Assertions.assertEquals("2030-01-01",deserializedResponse.getBookingdates().getCheckout());
     Assertions.assertEquals("UPDATED SOFTWARE ENGINEER",deserializedResponse.getAdditionalneeds());

    }












}

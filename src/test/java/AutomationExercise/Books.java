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
}

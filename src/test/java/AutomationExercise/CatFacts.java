package AutomationExercise;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class CatFacts {

    /*
    1-Call the endpoint
    2-Count How many of them has more than 50 length --> 299
    3-Count How many of them has less than 200 length -->293
    4-Count How many of them more than 50 and less than 200 at the same time -->260
    5-Count How many of them not contains cat in facts. -->25
     */

    @Test
    public void CountingFactsAndSize() {

        RestAssured.baseURI = "https://catfact.ninja";
        RestAssured.basePath = "/facts";

        Response response = RestAssured.given().header("Accept", "application/json")
                .queryParam("limit", "332")
                .when().get()
                .then().statusCode(200).log().body().extract().response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String, Object>> allData = (List<Map<String, Object>>) deserializedResponse.get("data");

        int moreThan50 = 0;
        int lessThan200 = 0;
        int more50Less200 = 0;
        int noCatContent = 0;

        for (Map<String, Object> data : allData) {
            if ((int) data.get("length") > 50) {
                moreThan50++;
            }
            if ((int) data.get("length") < 200) {
                lessThan200++;
            }
            if ((int) data.get("length") > 50 && (int) data.get("length") < 200) {
                more50Less200++;
            }
            if(!data.get("fact").toString().toLowerCase().contains("cat")){
                noCatContent++;
            }
        }

        Assertions.assertEquals(299,moreThan50);
        Assertions.assertEquals(293,lessThan200);
        Assertions.assertEquals(260,more50Less200);
        Assertions.assertEquals(25,noCatContent);

    }

}

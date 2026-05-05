package org.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.utils.ConfigReader;

public class GetPeopleApi {
    String apiURL = ConfigReader.get("apiURL");

    public String getValidRequestPeopleApi(int index) {
        Response response = RestAssured.given()
                .baseUri(apiURL)
                .when()
                .get("/planets/" + index )
                .then()
                .statusCode(200)
                .extract().response();

        String responseExpected = response.getBody().asString();

        return responseExpected;
    }
}

package org.example.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.api.models.StarshipResponse;
import org.example.utils.ConfigReader;

public class GetStarshipsApi {
    String apiURL = ConfigReader.get("apiURL");

    public StarshipResponse getValidRequestStarshipsApi(int index) {
        Response response = RestAssured.given()
                .baseUri(apiURL)
                .log().all()
                .when()
                .get("/starships/" + index)
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        return response.as(StarshipResponse.class);
    }

    public Response getStarships(String id, String method) {
        var request = RestAssured.given()
                .baseUri(apiURL)
                .log().all();
        String path = "/starships/" + (id == null ? "" : id);
        
        if (method.equalsIgnoreCase("POST")) {
            return request.when().post(path).then().log().all().extract().response();
        } else {
            return request.when().get(path).then().log().all().extract().response();
        }
    }
}

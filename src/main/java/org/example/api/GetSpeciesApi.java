package org.example.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.api.models.SpeciesResponse;
import org.example.utils.ConfigReader;

public class GetSpeciesApi {
    String apiURL = ConfigReader.get("apiURL");

    public SpeciesResponse getValidRequestSpeciesApi(int index) {
        Response response = RestAssured.given()
                .baseUri(apiURL)
                .log().all()
                .when()
                .get("/species/" + index)
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        return response.as(SpeciesResponse.class);
    }

    public Response getSpecies(String id, String method) {
        var request = RestAssured.given()
                .baseUri(apiURL)
                .log().all();
        String path = "/species/" + (id == null ? "" : id);
        
        if (method.equalsIgnoreCase("POST")) {
            return request.when().post(path).then().log().all().extract().response();
        } else {
            return request.when().get(path).then().log().all().extract().response();
        }
    }
}

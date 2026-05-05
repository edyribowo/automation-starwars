package org.example.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.api.models.FilmResponse;
import org.example.utils.ConfigReader;

public class GetFilmsApi {
    String apiURL = ConfigReader.get("apiURL");

    public FilmResponse getValidRequestFilmsApi(int index) {
        Response response = RestAssured.given()
                .baseUri(apiURL)
                .log().all()
                .when()
                .get("/films/" + index)
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        return response.as(FilmResponse.class);
    }

    public Response getFilms(String id, String method) {
        var request = RestAssured.given()
                .baseUri(apiURL)
                .log().all();
        String path = "/films/" + (id == null ? "" : id);
        
        if (method.equalsIgnoreCase("POST")) {
            return request.when().post(path).then().log().all().extract().response();
        } else {
            return request.when().get(path).then().log().all().extract().response();
        }
    }
}

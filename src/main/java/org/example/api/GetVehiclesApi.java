package org.example.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.api.models.VehicleResponse;
import org.example.utils.ConfigReader;

public class GetVehiclesApi {
    String apiURL = ConfigReader.get("apiURL");

    public VehicleResponse getValidRequestVehiclesApi(int index) {
        Response response = RestAssured.given()
                .baseUri(apiURL)
                .log().all()
                .when()
                .get("/vehicles/" + index)
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        return response.as(VehicleResponse.class);
    }

    public Response getVehicles(String id, String method) {
        var request = RestAssured.given()
                .baseUri(apiURL)
                .log().all();
        String path = "/vehicles/" + (id == null ? "" : id);
        
        if (method.equalsIgnoreCase("POST")) {
            return request.when().post(path).then().log().all().extract().response();
        } else {
            return request.when().get(path).then().log().all().extract().response();
        }
    }
}

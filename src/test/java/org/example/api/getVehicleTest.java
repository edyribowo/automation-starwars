package org.example.api;

import io.restassured.response.Response;
import org.example.api.models.VehicleResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class getVehicleTest {
    GetVehiclesApi getVehiclesApi = new GetVehiclesApi();

    @Test
    public void getSandCrawler() {
        VehicleResponse response = getVehiclesApi.getValidRequestVehiclesApi(4);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getName(), "Sand Crawler", "Name does not match");
        Assert.assertEquals(response.getModel(), "Digger Crawler", "Model does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getT16Skyhopper() {
        VehicleResponse response = getVehiclesApi.getValidRequestVehiclesApi(6);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getName(), "T-16 skyhopper", "Name does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getInvalidId() {
        Response response = getVehiclesApi.getVehicles("999999", "GET");
        Assert.assertEquals(response.getStatusCode(), 404, "Should return 404 for invalid ID");
    }

    @Test
    public void postWrongMethod() {
        Response response = getVehiclesApi.getVehicles("4", "POST");
        Assert.assertEquals(response.getStatusCode(), 400, "Should return 400 for POST method");
    }

    private void validateCriticalFields(VehicleResponse response) {
        Assert.assertNotNull(response.getName(), "Name should not be null");
        Assert.assertNotNull(response.getUrl(), "URL should not be null");
        Assert.assertNotNull(response.getCreated(), "Created date should not be null");
        Assert.assertNotNull(response.getEdited(), "Edited date should not be null");
        
        Assert.assertFalse(response.getName().isEmpty(), "Name should not be empty");
        Assert.assertTrue(response.getUrl().contains("vehicles"), "URL should contain 'vehicles'");
    }
}

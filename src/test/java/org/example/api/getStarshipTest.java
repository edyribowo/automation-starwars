package org.example.api;

import io.restassured.response.Response;
import org.example.api.models.StarshipResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class getStarshipTest {
    GetStarshipsApi getStarshipsApi = new GetStarshipsApi();

    @Test
    public void getCR90Corvette() {
        StarshipResponse response = getStarshipsApi.getValidRequestStarshipsApi(2);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getName(), "CR90 corvette", "Name does not match");
        Assert.assertEquals(response.getModel(), "CR90 corvette", "Model does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getStarDestroyer() {
        StarshipResponse response = getStarshipsApi.getValidRequestStarshipsApi(3);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getName(), "Star Destroyer", "Name does not match");
        Assert.assertEquals(response.getManufacturer(), "Kuat Drive Yards", "Manufacturer does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getDeathStar() {
        StarshipResponse response = getStarshipsApi.getValidRequestStarshipsApi(9);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getName(), "Death Star", "Name does not match");
        Assert.assertEquals(response.getStarshipClass(), "Deep Space Mobile Battlestation", "Starship class does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getInvalidId() {
        Response response = getStarshipsApi.getStarships("999999", "GET");
        Assert.assertEquals(response.getStatusCode(), 404, "Should return 404 for invalid ID");
    }

    @Test
    public void getNonNumericId() {
        Response response = getStarshipsApi.getStarships("abc", "GET");
        Assert.assertEquals(response.getStatusCode(), 404, "Should return 404 for non-numeric ID");
    }

    @Test
    public void postWrongMethod() {
        Response response = getStarshipsApi.getStarships("2", "POST");
        Assert.assertEquals(response.getStatusCode(), 400, "Should return 400 for POST method");
    }

    private void validateCriticalFields(StarshipResponse response) {
        Assert.assertNotNull(response.getName(), "Name should not be null");
        Assert.assertNotNull(response.getUrl(), "URL should not be null");
        Assert.assertNotNull(response.getCreated(), "Created date should not be null");
        Assert.assertNotNull(response.getEdited(), "Edited date should not be null");
        
        Assert.assertFalse(response.getName().isEmpty(), "Name should not be empty");
        Assert.assertTrue(response.getUrl().contains("starships"), "URL should contain 'starships'");
    }
}

package org.example.api;

import io.restassured.response.Response;
import org.example.api.models.PlanetResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class getPlanetTest {
    GetPlanetsApi getPlanetsApi = new GetPlanetsApi();

    @Test
    public void getTatooine() {
        PlanetResponse response = getPlanetsApi.getValidRequestPlanetsApi(1);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getName(), "Tatooine", "Name does not match");
        Assert.assertEquals(response.getClimate(), "arid", "Climate does not match");
        Assert.assertEquals(response.getTerrain(), "desert", "Terrain does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getAlderaan() {
        PlanetResponse response = getPlanetsApi.getValidRequestPlanetsApi(2);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getName(), "Alderaan", "Name does not match");
        Assert.assertEquals(response.getClimate(), "temperate", "Climate does not match");
        Assert.assertEquals(response.getPopulation(), "2000000000", "Population does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getYavinIV() {
        PlanetResponse response = getPlanetsApi.getValidRequestPlanetsApi(3);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getName(), "Yavin IV", "Name does not match");
        Assert.assertEquals(response.getTerrain(), "jungle, rainforests", "Terrain does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getInvalidId() {
        Response response = getPlanetsApi.getPlanets("999999", "GET");
        Assert.assertEquals(response.getStatusCode(), 404, "Should return 404 for invalid ID");
    }

    @Test
    public void getNonNumericId() {
        Response response = getPlanetsApi.getPlanets("abc", "GET");
        Assert.assertEquals(response.getStatusCode(), 404, "Should return 404 for non-numeric ID");
    }

    @Test
    public void postWrongMethod() {
        Response response = getPlanetsApi.getPlanets("1", "POST");
        Assert.assertEquals(response.getStatusCode(), 400, "Should return 400 for POST method");
    }

    private void validateCriticalFields(PlanetResponse response) {
        Assert.assertNotNull(response.getName(), "Name should not be null");
        Assert.assertNotNull(response.getUrl(), "URL should not be null");
        Assert.assertNotNull(response.getCreated(), "Created date should not be null");
        Assert.assertNotNull(response.getEdited(), "Edited date should not be null");
        
        Assert.assertFalse(response.getName().isEmpty(), "Name should not be empty");
        Assert.assertTrue(response.getUrl().contains("planets"), "URL should contain 'planets'");
    }
}

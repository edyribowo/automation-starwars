package org.example.api;

import io.restassured.response.Response;
import org.example.api.models.SpeciesResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class getSpeciesTest {
    GetSpeciesApi getSpeciesApi = new GetSpeciesApi();

    @Test
    public void getHuman() {
        SpeciesResponse response = getSpeciesApi.getValidRequestSpeciesApi(1);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getName(), "Human", "Name does not match");
        Assert.assertEquals(response.getClassification(), "mammal", "Classification does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getDroid() {
        SpeciesResponse response = getSpeciesApi.getValidRequestSpeciesApi(2);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getName(), "Droid", "Name does not match");
        Assert.assertEquals(response.getClassification(), "artificial", "Classification does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getWookiee() {
        SpeciesResponse response = getSpeciesApi.getValidRequestSpeciesApi(3);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getName(), "Wookie", "Name does not match");
        Assert.assertEquals(response.getLanguage(), "Shyriiwook", "Language does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getInvalidId() {
        Response response = getSpeciesApi.getSpecies("999999", "GET");
        Assert.assertEquals(response.getStatusCode(), 404, "Should return 404 for invalid ID");
    }

    @Test
    public void postWrongMethod() {
        Response response = getSpeciesApi.getSpecies("1", "POST");
        Assert.assertEquals(response.getStatusCode(), 400, "Should return 400 for POST method");
    }

    private void validateCriticalFields(SpeciesResponse response) {
        Assert.assertNotNull(response.getName(), "Name should not be null");
        Assert.assertNotNull(response.getUrl(), "URL should not be null");
        Assert.assertNotNull(response.getCreated(), "Created date should not be null");
        Assert.assertNotNull(response.getEdited(), "Edited date should not be null");
        
        Assert.assertFalse(response.getName().isEmpty(), "Name should not be empty");
        Assert.assertTrue(response.getUrl().contains("species"), "URL should contain 'species'");
    }
}

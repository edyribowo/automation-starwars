package org.example.api;

import io.restassured.response.Response;
import org.example.api.models.PeopleResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class getPeopleTest {
    GetPeopleApi getPeopleApi = new GetPeopleApi();

    @Test
    public void getLukeSkywalker() {
        PeopleResponse response = getPeopleApi.getValidRequestPeopleApi(1);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getName(), "Luke Skywalker", "Name does not match");
        Assert.assertEquals(response.getHeight(), "172", "Height does not match");
        Assert.assertEquals(response.getGender(), "male", "Gender does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getC3PO() {
        PeopleResponse response = getPeopleApi.getValidRequestPeopleApi(2);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getName(), "C-3PO", "Name does not match");
        Assert.assertEquals(response.getHeight(), "167", "Height does not match");
        Assert.assertEquals(response.getGender(), "n/a", "Gender does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getR2D2() {
        PeopleResponse response = getPeopleApi.getValidRequestPeopleApi(3);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getName(), "R2-D2", "Name does not match");
        Assert.assertEquals(response.getHeight(), "96", "Height does not match");
        Assert.assertEquals(response.getGender(), "n/a", "Gender does not match");
        validateCriticalFields(response);
    }

    private void validateCriticalFields(PeopleResponse response) {
        Assert.assertNotNull(response.getName(), "Name should not be null");
        Assert.assertNotNull(response.getUrl(), "URL should not be null");
        Assert.assertNotNull(response.getHomeworld(), "Homeworld should not be null");
        Assert.assertNotNull(response.getCreated(), "Created date should not be null");
        Assert.assertNotNull(response.getEdited(), "Edited date should not be null");
        
        Assert.assertFalse(response.getName().isEmpty(), "Name should not be empty");
        Assert.assertTrue(response.getUrl().contains("http"), "URL should be a valid link");
    }

    @Test
    public void getInvalidId() {
        Response response = getPeopleApi.getPeople("9999999999999999", "GET");
        Assert.assertEquals(response.getStatusCode(), 404, "Should return 404 for invalid large ID");
    }

    @Test
    public void getNonNumericId() {
        Response response = getPeopleApi.getPeople("abc", "GET");
        Assert.assertEquals(response.getStatusCode(), 404,
                "Should return 404 for non-numeric ID");
    }

    @Test
    public void getMissingId() {
        Response response = getPeopleApi.getPeople("", "GET");
        Assert.assertEquals(response.getStatusCode(), 200,
                "Should return 200 (list) for missing ID");
    }

    @Test
    public void postWrongMethod() {
        Response response = getPeopleApi.getPeople("5", "POST");
        Assert.assertEquals(response.getStatusCode(), 400,
                "Should return 400 for wrong method POST");
    }
}

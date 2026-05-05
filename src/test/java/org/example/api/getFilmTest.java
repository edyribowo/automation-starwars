package org.example.api;

import io.restassured.response.Response;
import org.example.api.models.FilmResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class getFilmTest {
    GetFilmsApi getFilmsApi = new GetFilmsApi();

    @Test
    public void getANewHope() {
        FilmResponse response = getFilmsApi.getValidRequestFilmsApi(1);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getTitle(), "A New Hope", "Title does not match");
        Assert.assertEquals(response.getEpisodeId(), 4, "Episode ID does not match");
        Assert.assertEquals(response.getDirector(), "George Lucas", "Director does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getTheEmpireStrikesBack() {
        FilmResponse response = getFilmsApi.getValidRequestFilmsApi(2);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getTitle(), "The Empire Strikes Back", "Title does not match");
        Assert.assertEquals(response.getEpisodeId(), 5, "Episode ID does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getReturnOfTheJedi() {
        FilmResponse response = getFilmsApi.getValidRequestFilmsApi(3);
        
        Assert.assertNotNull(response, "Response should not be null");
        Assert.assertEquals(response.getTitle(), "Return of the Jedi", "Title does not match");
        Assert.assertEquals(response.getEpisodeId(), 6, "Episode ID does not match");
        validateCriticalFields(response);
    }

    @Test
    public void getInvalidId() {
        Response response = getFilmsApi.getFilms("999999", "GET");
        Assert.assertEquals(response.getStatusCode(), 404, "Should return 404 for invalid ID");
    }

    @Test
    public void getNonNumericId() {
        Response response = getFilmsApi.getFilms("abc", "GET");
        Assert.assertEquals(response.getStatusCode(), 404, "Should return 404 for non-numeric ID");
    }

    @Test
    public void postWrongMethod() {
        Response response = getFilmsApi.getFilms("1", "POST");
        Assert.assertEquals(response.getStatusCode(), 400, "Should return 400 for POST method");
    }

    private void validateCriticalFields(FilmResponse response) {
        Assert.assertNotNull(response.getTitle(), "Title should not be null");
        Assert.assertNotNull(response.getUrl(), "URL should not be null");
        Assert.assertNotNull(response.getCreated(), "Created date should not be null");
        Assert.assertNotNull(response.getEdited(), "Edited date should not be null");
        
        Assert.assertFalse(response.getTitle().isEmpty(), "Title should not be empty");
        Assert.assertTrue(response.getUrl().contains("films"), "URL should contain 'films'");
    }
}

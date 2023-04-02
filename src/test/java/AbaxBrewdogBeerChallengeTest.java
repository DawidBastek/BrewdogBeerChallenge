   Documentation:
    In order to validate the release we need the following 4 tests.
        We want to validate that all the beer produced after December 2015
        • has a valid ‘abv’
        o it must be a double
        o it must not be null
        o it must not be an empty string
        o it must be over 4.0
        • has a valid ‘name’ for each beer
        o it must not be null
        o it must not be an empty string
        • two extra tests which you think should be included:
        1. has a valid ‘description’ for each beer
          o it must be a string
          o it must not be null
          o it must not be an empty string
        2. has a valid ‘image_url’ for each beer
          o it must not be null
          o it must not be an empty string

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AbaxBrewdogBeerChallengeTest {

    private static final String API_URL = "https://api.punkapi.com/v2/beers";

    @Test
    public void testAbvIsValid() {

        given()
                .param("brewed_after", "12-2015")
                .when()
                .get(API_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .body("abv", everyItem(instanceOf(Double.class)))
                .body("abv", everyItem(notNullValue()))
                .body("abv", everyItem(not(empty())))
                .body("abv", everyItem(greaterThan(4.0)));
    }

    @Test
    public void testBeerNameIsValid() {
        given()
                .param("brewed_after", "12-2015")
                .when()
                .get(API_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", everyItem(notNullValue()))
                .body("name", everyItem(not(empty())));
    }

    @Test
    public void testDescriptionIsValid() {
        given()
                .param("brewed_after", "12-2015")
                .when()
                .get(API_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .body("description", everyItem(instanceOf(String.class)))
                .body("description", everyItem(notNullValue()))
                .body("description", everyItem(not(empty())));
    }

    @Test
    public void testImageUrlIsValid() {
        given().param("brewed_after", "12-2015")
                .when()
                .get(API_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .body("image_url", everyItem(notNullValue()))
                .body("image_url", everyItem(not(empty())));
    }
}

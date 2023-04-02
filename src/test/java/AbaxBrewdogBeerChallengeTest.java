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

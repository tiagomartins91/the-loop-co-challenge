package com.loopco.pricesolution.boundary;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "local.server.port=8080")
class PriceResourceIT {

    @LocalServerPort
    private int serverPort;

    @Test
    void should_get_prices_with_success() {
        final String url = "http://localhost:" + serverPort + "/api/v1/prices";

        //@formatter:off
        given()
            .contentType(APPLICATION_JSON_VALUE)
        .when()
            .get(url)
        .then()
            .statusCode(200)
            .log()
            .all()
            .body(Matchers.notNullValue());
        //@formatter:on
    }

    @Test
    void should_get_right_price_on_day_14_at_10_am() {
        final String url = "http://localhost:" + serverPort + "/api/v1/prices/search";

        //@formatter:off
        given()
            .contentType(APPLICATION_JSON_VALUE)
            .queryParam("date", "2022-06-14T10:00:00")
            .queryParam("productId", "35455")
            .queryParam("brandId", "1")
        .when()
            .get(url)
        .then()
            .statusCode(200)
            .log()
            .all()
            .body(Matchers.notNullValue())
            .body("brandId", Matchers.equalTo(1))
            .body("productId", Matchers.equalTo(35455))
            .body("finalPrice", Matchers.equalTo(35.50f));
        //@formatter:on
    }

    @Test
    void should_get_right_price_on_day_14_at_16_pm() {
        final String url = "http://localhost:" + serverPort + "/api/v1/prices/search";

        //@formatter:off
        given()
            .contentType(APPLICATION_JSON_VALUE)
            .queryParam("date", "2022-06-14 16:00:00")
            .queryParam("productId", "35455")
            .queryParam("brandId", "1")
        .when()
             .get(url)
        .then()
             .statusCode(200)
             .log()
             .all()
             .body(Matchers.notNullValue())
             .body("brandId", Matchers.equalTo(1))
             .body("productId", Matchers.equalTo(35455))
             .body("finalPrice", Matchers.equalTo(25.45f));
        //@formatter:on
    }

    @Test
    void should_get_right_price_on_day_14_at_21_pm() {
        final String url = "http://localhost:" + serverPort + "/api/v1/prices/search";

        //@formatter:off
        given()
            .contentType(APPLICATION_JSON_VALUE)
            .queryParam("date", "2022-06-14 21:00:00")
            .queryParam("productId", "35455")
            .queryParam("brandId", "1")
        .when()
            .get(url)
        .then()
            .statusCode(200)
            .log()
            .all()
            .body(Matchers.notNullValue())
            .body("brandId", Matchers.equalTo(1))
            .body("productId", Matchers.equalTo(35455))
            .body("finalPrice", Matchers.equalTo(35.50f));
        //@formatter:on
    }

    @Test
    void should_get_right_price_on_day_15_at_10_am() {
        final String url = "http://localhost:" + serverPort + "/api/v1/prices/search";

        //@formatter:off
        given()
            .contentType(APPLICATION_JSON_VALUE)
            .queryParam("date", "2022-06-15 10:00:00")
            .queryParam("productId", "35455")
            .queryParam("brandId", "1")
        .when()
            .get(url)
        .then()
            .statusCode(200)
            .log()
            .all()
            .body(Matchers.notNullValue())
            .body("brandId", Matchers.equalTo(1))
            .body("productId", Matchers.equalTo(35455))
            .body("finalPrice", Matchers.equalTo(30.50f));
        //@formatter:on
    }

    @Test
    void should_get_right_price_on_day_16_at_21_pm() {
        final String url = "http://localhost:" + serverPort + "/api/v1/prices/search";

        //@formatter:off
        given()
            .contentType(APPLICATION_JSON_VALUE)
            .queryParam("date", "2022-06-16 21:00:00")
            .queryParam("productId", "35455")
            .queryParam("brandId", "1")
        .when()
            .get(url)
        .then()
            .statusCode(200)
            .log()
            .all()
            .body(Matchers.notNullValue())
            .body("brandId", Matchers.equalTo(1))
            .body("productId", Matchers.equalTo(35455))
            .body("finalPrice", Matchers.equalTo(38.95f));
        //@formatter:on
    }

    @Test
    void should_get_404_not_found_if_price_doesnt_exists() {
        final String url = "http://localhost:" + serverPort + "/api/v1/prices/search";

        //@formatter:off
        given()
            .contentType(APPLICATION_JSON_VALUE)
            .queryParam("date", "2022-06-13 21:00:00")
            .queryParam("productId", "35455")
            .queryParam("brandId", "1")
        .when()
            .get(url)
            .then()
            .statusCode(404);
        //@formatter:on
    }

}

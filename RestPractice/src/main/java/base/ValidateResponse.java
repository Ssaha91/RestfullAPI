package base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class ValidateResponse {
/**
    given() -> Initialization/ Auth
    login to application
    request header
    parameters, cookies
            body

    when() -> Resources
    ex: GET(resource path)
    POST(resource path)
    PUT(resource path)
    DELETE(resource path)

    then() -> Validation
assert
    extract the response, header

    Given I have this information
    When I perform this action
    Then This should be the output
*/

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI="https://maps.googleapis.com";
        RestAssured.basePath="/maps/api";
    }

    @Test
    public void validateResponse(){
        given()
                .param("origins", "Vancouver+BC|Seattle")
                .param("destinations", "San+Francisco|Victoria+BC")
                .param("key", "AIzaSyBmocIEt6REBzX4e2ho75s1BOY8mr19gK4")
        .when()
                .get("/distancematrix/json")
        .then()
                .statusCode(200)
                .and()
                .body("rows[1].elements[1].duration.value", equalTo(16651))
                .contentType(ContentType.JSON);
    }

    @Test(enabled = true)
    public  void getResponseBody(){
        Response res =
                given()
                        .param("origins", "Vancouver+BC|Seattle")
                        .param("destinations", "San+Francisco|Victoria+BC")
                        .param("key", "AIzaSyDvTwEYo7dpGnurZU0Ch7TLk4CDCD8JtAQ" )
                .when()
                        .get("distancematrix/json");
        System.out.println(res.body().prettyPrint());

    }
}



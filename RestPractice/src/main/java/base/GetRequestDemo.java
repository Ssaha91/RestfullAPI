package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
public class GetRequestDemo {
    /*
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

    @Test(enabled = false)
    public  void statusCodeVerification(){
        given()
                .param("origins", "Vancouver+BC|Seattle")
                .param("destinations", "San+Francisco|Victoria+BC")
                .param("key", "AIzaSyDvTwEYo7dpGnurZU0Ch7TLk4CDCD8JtAQ" )

        .when()
                .get("distancematrix/json")
        .then()
                .statusCode(200);
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



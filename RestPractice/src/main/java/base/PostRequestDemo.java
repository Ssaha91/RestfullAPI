package base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestDemo {
    @BeforeClass
    public void setUp(){
        RestAssured.baseURI="https://maps.googleapis.com";
        RestAssured.basePath="/maps/api";
    }
    @Test
    public void verifyStatusCode(){
        given()
                .queryParam("key", "AIzaSyAK_1RacqHpeNoCrVQ4ZMAp-s3qurjrGNE")
        .body("{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -33.8669710,\n" +
                "    \"lng\": 151.1958750\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Google Shoes!\",\n" +
                "  \"phone_number\": \"(02) 9374 4000\",\n" +
                "  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\",\n" +
                "  \"types\": [\"shoe_store\"],\n" +
                "  \"website\": \"http://www.google.com.au/\",\n" +
                "  \"language\": \"en-AU\"\n" +
                "}")

        .when()
                .post("place/add/json")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON).and()
                .body("status", equalTo("OK"));
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


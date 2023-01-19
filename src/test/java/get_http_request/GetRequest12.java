package get_http_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utility.Authentication;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends Authentication {
    //Authentication Class'ının içerisindeki generateToken() methodu kullanılacak!!!

    String endPoint="http://www.gmibank.com/api/tp-customers";


    @Test
    public void test12(){

        Response response=given()
                .header("Authorization","Bearer " + generateToken())
                .when()
                .get(endPoint)
                .then()
                .extract()
                .response();

        response.prettyPeek();

        response.then().assertThat().contentType("application/json").statusCode(200);
    }

}

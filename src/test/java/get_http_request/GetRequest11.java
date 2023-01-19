package get_http_request;

import io.restassured.response.Response;
import org.junit.Test;
import utility.Authentication;

import static io.restassured.RestAssured.given;

public class GetRequest11 {

    String endPoint="http://www.gmibank.com/api/tp-customers";


    String bearerToken="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtZW1vIiwiYXV0aCI6IlJPTEVfQ1VTVE9NRVIiLCJleHAiOjE2NzQyMDA0OTB9.s-YK4AxJoNkqNPsi8CVKlEI9mRYl7FUhK_qy3qGW2R9AUi9G3KjKt7uRfUB4U2IGwgVekVlXi2lxD6rW3ycTyQ";

    @Test
    public void test11(){

        Response response = given().header("Authorization","Bearer "+bearerToken)
                .when().get(endPoint).then().extract().response();

        response.prettyPrint();
    }
}

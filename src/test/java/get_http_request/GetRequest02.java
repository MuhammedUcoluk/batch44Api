package get_http_request;

import io.restassured.response.Response;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest02 {
    @Test
    public void test02(){
        String url="https://reqres.in/api/users";

        Response response = given().when().get("https://reqres.in/api/users");

        response.prettyPrint(); // response daki bodyi getirir.

        //response.prettyPeek(); // response deki her şeyi getirir.

        //response.then().log().all(); //  responce deki her şeyi getirir.

        //Burada headers ı test ettik.
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                statusLine("HTTP/1.1 200 OK");

        //Burada body yi test ettik.
        response.then().body("data[0].first_name", equalTo("George")
                , "data[0].last_name", equalTo("Bluth")
                ,"data[0].email", equalTo("george.bluth@reqres.in"));

        //yukarıdaki kodla aynı. Matchers class ını static yaptım.
        response.then().body("data[1].id", equalTo(2)
                ,"data[1].email", equalTo("janet.weaver@reqres.in")
                ,"data[1].first_name", equalTo("Janet")
                ,"data[1].last_name", equalTo("Weaver")
                ,"data[1].avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));


    }
}

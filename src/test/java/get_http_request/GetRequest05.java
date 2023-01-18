package get_http_request;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest05 {
    /*
    https://jsonplaceholder.typicode.com/todos/123 url ine
    accept typi "application/json" olan GET requesti yolldaıgımda
    gelen response'un
    1.status kodunun 200
    2.content type ının "application/json"
    3.Headers daki "Server" in " cloudflare" ve "Pragma" nın "no-cache"
    4.response body deki "userId" nin 7
    5."title" in "esse et quis iste est earum aut impedit"
    6."completed" bölümünün false oldugunu test edin.
     */
    @Test
    public void test05(){
    String url="https://jsonplaceholder.typicode.com/todos/123";

        Response response=given().when().get(url);

        response.prettyPrint();

        response.then()
                .assertThat()
                .contentType("application/json")
                .statusCode(200)
                .headers("Server", equalTo("cloudflare"),"Pragma","no-cache");


        response.then().assertThat().body("userId",equalTo(7)
                        , "title",equalTo("esse et quis iste est earum aut impedit"),
                        "completed",equalTo(false));




    }
}

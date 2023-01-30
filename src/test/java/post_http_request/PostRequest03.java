package post_http_request;

import TestData.JsonPlaceHolderTestData;
import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequest03 extends JsonPlaceHolderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde
    {
    "userId" : 55,
    "title" : "Tidy your room",
    "completed" : "false
    }
    Dönen response un Status Code 200 ve response body nin aşağıdaki gibi olduğunu
    {
    "userId":55,
    "title" : "Tidy your room",
    "completed" : false,
    "id" : ...
    tes
     */

    @Test
    public void test03(){
        //1-Url Oluşturma
        spec04.pathParams("1","todos");

        //2-Expected

        JsonPlaceHolderTestData testobje=new JsonPlaceHolderTestData();
        JSONObject expectedRequest=testobje.setupPostData();
        System.out.println("Expected Data : " +expectedRequest);

        //Response ve Request
        Response response=given()
                .spec(spec04)
                .contentType(ContentType.JSON)
                .body(expectedRequest.toString())
                .when()
                .post("/{1}");

        response.prettyPrint();

        //Doğrulama
            //Matchers Class
        response.then().assertThat().statusCode(201);
    }
}

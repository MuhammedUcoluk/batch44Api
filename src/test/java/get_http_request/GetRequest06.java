package get_http_request;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest06 {
    /*
        https://restful-booker.herokuapp.com/booking/5 url ine
        accept typi "application/json" olan GET request i yolldaıgımda
        gelen response un
        Status kodunun 200
        content typinin "application/json"
        firstname in "Eric"
        totalprice nin 783
        checkin date in 2016-02-08 oldugunu ve
        Headers teki Server in cowboy
        Connection un keep-alive

         */
    @Test
    public void test06(){

        String url=" https://restful-booker.herokuapp.com/booking/5";

        Response response=given().when().get(url);

        response.prettyPrint();

        response.then().assertThat().contentType("application/json").statusCode(200);

        response.then().assertThat().body("firstname", equalTo("Sally")
                ,"totalprice",equalTo(254)
                ,"bookingdates.checkin",equalTo("2023-01-11"));

        response.then().assertThat().headers("Server",equalTo("Cowboy")
                ,"Connection",equalTo("keep-alive"));

    }
}

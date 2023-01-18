package get_http_request;

import io.restassured.response.Response;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest03 {
    /*accept typi "application/json" olan GET request i yolladıgımda
    gelen response un
    status kodunun 200
    ve content type ının "application/json"
    ve firstname inin "Susan"
    ve lastname inin "Wilson"
    ve checkin date in 2018-10-07
    ve checkout date in 2020-09-30 oldugunu test edin
     */
    @Test
    public void test03(){
        String url="https://restful-booker.herokuapp.com/booking/7";

        Response response = given().when().get(url);

        response.prettyPrint();

        response.then().contentType("application/json").statusCode(200);

        //asserThat() yazmasakta olur. Assert yapacağımı bildirmek için yazdık.
        response.then().assertThat().body("firstname",equalTo("Susan")
                                    ,"lastname",equalTo("Wilson")
                                    ,"bookingdates.checkin",equalTo("2015-06-15")
                                    ,"bookingdates.checkout",equalTo("2021-03-27"));



    }
}

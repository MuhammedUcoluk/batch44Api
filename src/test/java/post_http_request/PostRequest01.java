package post_http_request;

import TestData.HerOkuAppTestData;
import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest01 extends HerOkuAppBaseUrl {
    /*
    https://restful-booker.herokuapp.com/booking
    {"firstname": "Ali"
    "lastname":"Can"
    "totalprice":500
    "depositpaid":true,
    "bookingdates": {
            "checkin":"2022-03-01"
            "checkout":"2022-03-11"
         }
         }
         gönderildiğinde, status code 200 oldugunu response body nin,
         }
         "booking":{
            "firstname":"Ali"
            "lastname":"Can"
            "totalprice":500
             "depositpaid":true,
            "bookingdates": {
                    "checkin":"2022-03-01"
                    "checkout":"2022-03-11"
                    }
                    }
                    }
                    olduğunu test edin.
     */
    @Test
    public void test01(){

        //1- Url Oluşturma
        spec05.pathParams("1","booking");

        //2- Expected Data
        HerOkuAppTestData expectedDataObj=new HerOkuAppTestData();
        JSONObject expectedDataRequest=expectedDataObj.setupTestAndRequestData();
        System.out.println(expectedDataRequest);

        //3- Response Request
        Response response=given()
                .contentType(ContentType.JSON)
                .auth()
                .basic("admin","password123")
                .spec(spec05)
                .body(expectedDataRequest.toString())
                .when()
                .post("/{1}");

        //JSONObject'te toString() kullanmalıyız. Ör==.body(expectedDataRequest.toString())

        response.prettyPrint();

        //4- Doğrulama
            //JsonPath ile Doğrulama

        JsonPath json=response.jsonPath();

        response.then().assertThat().statusCode(200);

        assertEquals(expectedDataRequest.getString("firstname"),json.getString("booking.firstname"));
        //expectedDataRequest.getString("firstname")==TESTDATA da bizim oluşturduğumuz
        //json.getString("booking.firstname"));==BODY deki
        assertEquals(expectedDataRequest.getString("lastname"),json.getString("booking.lastname"));
        assertEquals(expectedDataRequest.getInt("totalprice"),json.getInt("booking.totalprice"));
        assertEquals(expectedDataRequest.getBoolean("depositpaid"),json.getBoolean("booking.depositpaid"));
        assertEquals(expectedDataRequest.getJSONObject("bookingdates").getString("checkin"),
                json.getString("booking.bookingdates.checkin"));
        assertEquals(expectedDataRequest.getJSONObject("bookingdates").getString("checkout"),
                json.getString("booking.bookingdates.checkout"));



    }
}

package get_http_request;

import TestData.HerOkuAppTestData;
import base_url.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest22 extends HerOkuAppBaseUrl {
    /*
    https://restful-booker.herokuapp.com/booking/47

    "firstname:  "John"
    "lastname: "Smith"
    "totalprice" : 111
    "depositpaid" : true,
    "bookingdates" : {
            "checkin" : "2018-01-01"
            "checkout": "2019-01-01"
      }
      }
      1- JsonPath
      2- De Serialization
     */
    @Test
    public void test22(){
        //Url Oluşturma
        spec05.pathParams("1","booking","2",47);

        //Expected Data Oluşturma
        HerOkuAppTestData expectedDataObje= new HerOkuAppTestData();
        HashMap<String,Object> expectedData=expectedDataObje.setUpTestData();
        System.out.println("Test Data içerisindeki Expected Data  : " +  expectedData);
        //{firstname=John,
        // bookingdates={checkin=2018-01-01, checkout=2019-01-01},
        // totalprice=111,
        // depositpaid=true,
        // lastname=Smith}

        //Response ve Request Oluşturma
        Response response=given().spec(spec05).when().get("/{1}/{2}");
        response.prettyPrint();

        //Json Path İle
        JsonPath json=response.jsonPath();
        assertEquals(expectedData.get("firstname"),json.getString("firstname"));
        assertEquals(expectedData.get("lastname"),json.getString("lastname"));
        assertEquals(expectedData.get("totalprice"),json.getInt("totalprice"));
        assertEquals(expectedData.get("depositpaid"),json.getBoolean("depositpaid"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),json.getString("bookingdates.checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),json.getString("bookingdates.checkout"));


        //De Serialization ile
        HashMap<String,Object> actualData=response.as(HashMap.class);
        //JSON formatındaki datayı Hashmap e dönüştürür.

        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));


    }
}

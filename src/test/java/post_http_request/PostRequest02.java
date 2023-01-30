package post_http_request;

import TestData.DummyTestData;
import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest02 extends DummyBaseUrl {
    /*
    https://dummy.restapiexample.com/api/v1/create url ine ,Request Body olarak

    "name":"Ali Can",
    "salary":"2000",
    "age":"40",

    gönderildiğinde Status Code 200 ve dönen response body nin

    "status":"success",
    "data":{
    "id":...
    },
    "message":" "

    olduğunu test edin.
     */
    @Test
    public void test02(){
        //Url Oluşturma
        spec02.pathParams("bir","api","iki","v1","üç","create");

        //ExpectedData
        DummyTestData obje=new DummyTestData();
        //Request için
        HashMap<String,Object> requestBodyMap=obje.setUpRequestBody();
        //Expected Data
        HashMap<String,Object> expectedMap=obje.SetUpexpectedData();
        System.out.println("Expected Data ;" + expectedMap);

        //Request ve Response
        Response response=given()
                .accept(ContentType.JSON)
                .spec(spec02)
                .body(requestBodyMap)
                .when()
                .post("/{bir}/{iki}/{üç}");
        //Map kullandığımız için toString() e gerek yok.

        response.prettyPrint();

        //Doğrulama
        //De-Serialization
        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        System.out.println("Actual Data :" + actualDataMap);

        assertEquals(expectedMap.get("statusCode"),response.statusCode());
        assertEquals(expectedMap.get("status"),actualDataMap.get("status"));
        assertEquals(expectedMap.get("message"),actualDataMap.get("message"));

        //Doğrulama
        //JSonPath ile
        JsonPath json=response.jsonPath();
        assertEquals(expectedMap.get("statusCode"),response.statusCode());
        assertEquals(expectedMap.get("status"),json.getString("status"));
        assertEquals(expectedMap.get("message"),json.getString("message"));
    }
}

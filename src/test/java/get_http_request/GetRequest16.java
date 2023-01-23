package get_http_request;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest16 extends JsonPlaceHolderBaseUrl {
       /*
    https://jsonplaceholder.typicode.com/todos/7
    "userId": 1,
    "id":7,
    "title":"illo expedita consequatur quia in",
    "completed": false
     */

    @Test
    public void test16(){
        //1)URL OLUŞTURMA
        spec04.pathParams("bir","todos","iki",7);

        //2)EXPECTED(BEKLENEN) DATA OLUŞTUR

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id",7);
        expectedData.put("title","illo expedita consequatur quia in");
        expectedData.put("completed",false);

        System.out.println("Expected Data : " + expectedData);
        //Expected Data : {id=7, completed=false, title=illo expedita consequatur quia in, userId=1}

        //REQUEST VE RESPONSE

       Response response=  given().spec(spec04).when().get("/{bir}/{iki}");
        //"/{bir}/{iki}" ====> spec04 te kayıtlı adrese  /todos/7 bunu ekledik.
        response.prettyPrint();

        //DATAyı JSON dan JAVA ya dönüştürme işlemine De-SERİALİZATİON
        //DATAyı JAVA dan JSON a dönüştürmeye SERİALİZATİON

        Map<String,Object> actualData=response.as(HashMap.class); //De-Serialization

        System.out.println("Actual Data : " + actualData);
        //Actual Data : {id=7, completed=false, title=illo expedita consequatur quia in, userId=1}

        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("id"),actualData.get("id"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));


    }
}

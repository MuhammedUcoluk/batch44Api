package get_http_request;

import TestData.JsonPlaceHolderTestData;
import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest21TestData extends JsonPlaceHolderBaseUrl {
    /*
   https://jsonplaceholder.typicode.com/todos/2
   1. Status code 200
   2. Response body de
       "completed" : değerinin false
       "title" değerinin "quis ut nam facilis et officia qui"
       "userId" sinin 1 ve
    header değerlerinden
       "via" değerinin "1.1 vegur" ve
       "Server" değerinin "cloudflare" olduğunu test edin.

    */
    @Test
    public void test21(){
        spec04.pathParams("1","todos","2",2);
        JsonPlaceHolderTestData expectedDataObje=new JsonPlaceHolderTestData();
        HashMap<String,Object>expectedData= (HashMap<String, Object>) expectedDataObje.setUpTestData();
        System.out.println("Test Data içerisindeki expected data :" + expectedData);
       //Test Data içerisindeki expected data :{Server=cloudflare,
        // completed=false,
        // title=quis ut nam facilis et officia qui,
        // userId=1,
        // statusCode=200,
        // via=1.1 vegur}

        //Response ve Request
        Response response=given().spec(spec04).when().get("/{1}/{2}");
        response.prettyPrint();

        //Matchers Class Doğrulama
        response.then().assertThat().statusCode((Integer)expectedData.get("statusCode")).
                headers("via",equalTo(expectedData.get("via")),
                        "Server",equalTo(expectedData.get("Server"))).
                body("userId",equalTo(expectedData.get("userId")),
                        "completed",equalTo(expectedData.get("completed")),
                        "title",equalTo(expectedData.get("title")));

        //JsonPath ile Doğrulama
        JsonPath json=response.jsonPath();
        assertEquals(expectedData.get("statusCode"),response.statusCode());
        assertEquals(expectedData.get("via"),response.getHeader("via"));
        assertEquals(expectedData.get("Server"),response.getHeader("Server"));
        assertEquals(expectedData.get("userId"),json.getInt("userId"));
        assertEquals(expectedData.get("title"),json.getString("title"));
        assertEquals(expectedData.get("completed"),json.getBoolean("completed"));

        //De Serialization
        Map<String,Object> actualData=response.as(HashMap.class);
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));





    }

}

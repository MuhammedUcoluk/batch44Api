package get_http_request;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest20 extends JsonPlaceHolderBaseUrl {
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
    public void test20(){
        spec04.pathParams("1","todos", "2",2);

        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("completed",false);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("userId",1);
        expectedData.put("via","1.1 vegur");
        expectedData.put("Server","cloudflare");

        //Request ve Response
        Response response=given().spec(spec04).when().get("/{1}/{2}");
        response.prettyPrint();

        //Doğrulama

        //1. Yol Matchers Class
        response.then().assertThat().
                statusCode((Integer)expectedData.get("statusCode")).
                headers("via", equalTo(expectedData.get("via")),
                        "Server",equalTo(expectedData.get("Server")))
                .body("userId",equalTo(expectedData.get("userId")),
                        "title",equalTo(expectedData.get("title")),
                        "completed",equalTo(expectedData.get("completed")));

        //2. Yol JsonPAth

        JsonPath json=response.jsonPath();

       assertEquals(expectedData.get("statusCode"),response.statusCode());
       assertEquals(expectedData.get("via"),response.getHeader("via"));
       assertEquals(expectedData.get("Server"),response.getHeader("Server"));
       assertEquals(expectedData.get("userId"),json.getInt("userId"));
       assertEquals(expectedData.get("title"),json.getString("title"));
       assertEquals(expectedData.get("completed"),json.getBoolean("completed"));

        //3. Yol De-Serialization
        Map<String,Object> actualData=response.as(HashMap.class);
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
    }
}

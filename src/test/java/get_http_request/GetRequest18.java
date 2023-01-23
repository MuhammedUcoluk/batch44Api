package get_http_request;

import base_url.GMIBankBaseUrl;
import com.fasterxml.jackson.annotation.JsonAlias;
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

public class GetRequest18 extends GMIBankBaseUrl {
        /*
         http://www.gmibank.com/api/tp-customers/43703
         "firstName" : "Alda",
         "lastName" : "Monahan",
         "middleInitial" : "Nichelle Hermann Kohler",
         "email" : "com.github.javafaker.Name@7c011174@gmail.com",
         "mobilePhoneNumber" : "909-162-8114",
         "city": "St Louis",
         "ssn": "108-53-6655"

         1. Matchers Class ile
         2. Json Path ile
         3. De-Serialization ile

                  */
    @Test
    public void test18(){
        spec03.pathParams("bir","tp-customers","iki","43703");

        Map<String ,Object> expectedData=new HashMap<>();
        expectedData.put("firstName","Alda");
        expectedData.put("lastName","Monahan");
        expectedData.put( "middleInitial","Nichelle Hermann Kohler");
        expectedData.put( "email","com.github.javafaker.Name@7c011174@gmail.com");
        expectedData.put( "mobilePhoneNumber","909-162-8114");
        expectedData.put( "city","St Louis");
        expectedData.put( "ssn","108-53-6655");
        System.out.println("Expected Data : " + expectedData);

        Response response=given().
                spec(spec03).
                header("Authorization","Bearer " + generateToken()).
                when().get("/{bir}/{iki}");

        response.prettyPrint();

        //MATCHERS
        response.then().assertThat().body("firstName", equalTo("Alda"),
                "lastName",equalTo("Monahan"),
                "middleInitial",equalTo("Nichelle Hermann Kohler"),
                "email",equalTo("com.github.javafaker.Name@7c011174@gmail.com"),
                "mobilePhoneNumber",equalTo("909-162-8114"),
                "city",equalTo("St Louis"),
                "ssn",equalTo("108-53-6655"));

        //JSONPATH

        JsonPath json=response.jsonPath();

       assertEquals("Alda",json.getString("firstName"));
       assertEquals("Monahan",json.getString("lastName"));
       assertEquals("Nichelle Hermann Kohler",json.getString("middleInitial"));
       assertEquals("com.github.javafaker.Name@7c011174@gmail.com",json.getString("email"));
       assertEquals("909-162-8114",json.getString("mobilePhoneNumber"));
       assertEquals("St Louis",json.getString("city"));
       assertEquals("108-53-6655",json.getString("ssn"));

       Map<String,Object> actualData=response.as(HashMap.class);
       actualData.put("firstName","Alda");
       actualData.put("lastName","Monahan");
       actualData.put("middleInitial","Nichelle Hermann Kohler");
       actualData.put("email","com.github.javafaker.Name@7c011174@gmail.com");
       actualData.put("city","St Louis");
       actualData.put("ssn","108-53-6655");
        System.out.println("Actual Data : " + actualData);

       //De-Serialization

        assertEquals(expectedData.get("firstName"),actualData.get("firstName"));
        assertEquals(expectedData.get("lastName"),actualData.get("lastName"));
        assertEquals(expectedData.get("middleInitial"),actualData.get("middleInitial"));
        assertEquals(expectedData.get("email"),actualData.get("email"));
        assertEquals(expectedData.get("city"),actualData.get("city"));
        assertEquals(expectedData.get("ssn"),actualData.get("ssn"));
    }

}

package get_http_request;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.security.jarsigner.JarSigner;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest09 extends DummyBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/employee/12 URLe git.
    1)Matcher class ile
    2)JsonPath ile doğrulayın
     */

    @Test
    public void test09(){
        spec02.pathParams("first","api","second","v1","third","employee","fourth","12");

        Response response=given().spec(spec02).when().get("/{first}/{second}/{third}/{fourth}");

       response.prettyPrint();

       // 1)Matcher class ile

        response.then().assertThat().statusCode(200).contentType("application/json");

        response.then().assertThat().body("data.id",equalTo(12),
                "data.employee_name",equalTo("Quinn Flynn"),
                "data.employee_salary",equalTo(342000),
                "data.employee_age",equalTo( 22),
                "data.profile_image",equalTo(""));

        // 2)JsonPath ile doğrulayın

        JsonPath json=response.jsonPath();

        json.getString("data.employee_name");
        json.getString("data.profile_image");
        json.getInt("data.id");
        json.getInt("data.employee_salary");
        json.getInt("data.employee_age");

        assertEquals(12,json.getInt("data.id"));
        assertEquals("Quinn Flynn",json.getString("data.employee_name"));
        assertEquals(342000,json.getInt("data.employee_salary"));
        assertEquals(22,json.getInt("data.employee_age"));
        assertEquals("",json.getString("data.profile_image"));

    }
}


package get_http_request;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest15 extends GMIBankBaseUrl {
    /*
    http://www.gmibank.com/api/tp-customers/85694
    login
    firstname
    lastname
    email
     */
    @Test
    public void test15(){
        spec03.pathParams("bir","tp-customers","iki","85694");
        Response response=given().spec(spec03).
                header("Authorization","Bearer " + generateToken()).
                when().
                get("/{bir}/{iki}");
        response.prettyPrint();

        //MATCHERS CLASS ile doğrulama

        response.then().assertThat().body("user.login", equalTo("dino.kohler"),
                "firstName",equalTo("Winona"),
                "lastName",equalTo("Abernathy"),
                "email",equalTo("winonaabernathy@gmail.com"));

        //JSONPATH ile doğrulama

        JsonPath json=response.jsonPath();
        assertEquals("dino.kohler",json.getString("user.login"));
        assertEquals("Winona",json.getString("firstName"));
        assertEquals("Abernathy",json.getString("lastName"));
        assertEquals("winonaabernathy@gmail.com",json.getString("email"));

    }
}

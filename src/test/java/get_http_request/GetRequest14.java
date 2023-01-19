package get_http_request;

import base_url.GMIBankBaseUrl;
import com.fasterxml.jackson.annotation.JsonAlias;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest14 extends GMIBankBaseUrl {
    /*
    http://www.gmibank.com/api/tp-customers/110472 adresindeki müşteri bilgilerini doğrulayınız.
    "firstName": "Melva",
    "lastName": "Bernhard",
    "email":"chas.kuhlman@yahoo.com
    "zipCode": "40207",
    "country" "name" :"San Jose"
    "login" : "delilah.metz"
     */
    @Test
    public void test14(){
        spec03.pathParams("ilk","tp-customers","iki","110472");
        Response response=given().
                spec(spec03).
                header("Authorization","Bearer " + generateToken()).
                when().
                get("/{ilk}/{iki}");

        response.prettyPrint();

        //MATCHERS CLASS ile doğrulama

        response.then().assertThat().body("firstName", equalTo("Melva"),
                "lastName",equalTo("Bernhard"),
                "email",equalTo("chas.kuhlman@yahoo.com"),
                "zipCode",equalTo("40207"),
                "country.name",equalTo("San Jose"),
                "user.login",equalTo("delilah.metz"));

        //JSONPATH ile doğrulama

        JsonPath json=response.jsonPath();

        assertEquals("Melva",json.getString("firstName"));
        assertEquals("Bernhard",json.getString("lastName"));
        assertEquals("chas.kuhlman@yahoo.com",json.getString("email"));
        assertEquals("40207",json.getString("zipCode"));
        assertEquals("San Jose",json.getString("country.name"));
        assertEquals("delilah.metz",json.getString("user.login"));

    }

}

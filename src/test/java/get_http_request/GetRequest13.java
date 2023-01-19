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

public class GetRequest13 extends GMIBankBaseUrl {
    /*
    http://www.gmibank.com/api/tp-customers//114351 adresindeki müşteri bilgilerini doğrulayınız.
    "firstName": "Della",
    "lastName": "Heaney",
    "mobilePhoneNumber": "123-456-7893",
    "address": "75164 McClure Stream",
    "country":"USA"
    "state":"New York43"
    "CREDIT_CARD", HESABINDA 69700$,
    "CHECKING"hesabında 11190$
     */

    @Test
    public void test13(){
        spec03.pathParams("bir","tp-customers","iki","114351");

        //http://www.gmibank.com/api
        Response response=given().spec(spec03).
                header("Authorization","Bearer " + generateToken()).
                when().
                get("/{bir}/{iki}"); // http://www.gmibank.com/api ==> bunu ekledim/tp-customers//114351

        response.prettyPeek();

        ///MATCERS CLASS ile doğrulama

        response.then().assertThat().statusCode(200).contentType("application/json");
        response.then().assertThat().body("firstName", equalTo("Della"),
                "lastName",equalTo("Heaney"),
                "mobilePhoneNumber",equalTo("123-456-7893"),
                "address",equalTo("75164 McClure Stream"),
                "country.name",equalTo("USA"),
                "state",equalTo("New York43"),
                "accounts[0].balance",equalTo(69700),
                "accounts[1].balance",equalTo(11190));

        //JSONPATH ile doğrulama

        JsonPath json=response.jsonPath();
        assertEquals("Della",json.getString("firstName"));
        assertEquals("Heaney",json.getString("lastName"));
        assertEquals("123-456-7893",json.getString("mobilePhoneNumber"));
        assertEquals("75164 McClure Stream",json.getString("address"));
        assertEquals("USA",json.getString("country.name"));
        assertEquals("New York43",json.getString("state"));
        assertEquals("69700",json.getString("accounts[0].balance"));
        assertEquals("11190",json.getString("accounts[1].balance"));

    }
}

package get_http_request;

import base_url.ReqresinBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest07 extends ReqresinBaseUrl {
    /*
    https://reqres.in/api/users URL request oluştur
    body içerisindeki idsi 5 olan datayı
    1) Matcher Class ile
    2) JsonPath ile doğrula
     */
    @Test
    public void test07(){
     spec01.pathParams("parametre1","api","parametre2","users");

        //https://reqres.in
        Response response=given().spec(spec01).when().get("/{parametre1}/{parametre2}");
        //"/{parametre1}/{parametre2}" --> /api/users

        response.prettyPrint();

        //=====MATCHERS CLASS İLE DOĞRULAMA==================
        response.then().assertThat().body("data[4].email", equalTo("charles.morris@reqres.in"),
                "data[4].first_name",equalTo("Charles"),
                "data[4].last_name",equalTo("Morris"),
                "data[4].avatar",equalTo("https://reqres.in/img/faces/5-image.jpg"));


        //==========JSONPATH İLE DOĞRULAMA====================

        JsonPath json=response.jsonPath();

        json.getList("data.email");
        json.getString("data.first_name");
        json.getString("data.last_name");

        assertEquals("charles.morris@reqres.in",json.getString("data[4].email"));
        assertEquals("Charles",json.getString("data[4].first_name"));
        assertEquals("Morris",json.getString("data[4].last_name"));


    }
}

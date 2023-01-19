package get_http_request;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRequest10 extends DummyBaseUrl {
    /*
    https://dummy.restapiexample.com/api/v1/employees url'ine
    Get requesti yolldaıgımda gelen response un
    status kodunun 200 ve content type ının "application/json"
    gelen body de 5. çalışanın "Airi Satou" oldugunu
    6. çalışanın maaşının "372000" oldugunu
    ve employees sayısının 24
    ve employee lerden birinin "Rhona Davidson"
    ve gelen yaslar içinde 21,23, ve 61 değerlerinden birinin oldugunu test edin.

    Json Path ile
     */
    @Test
    public void test10(){
        spec02.pathParams("bir","api","iki","v1","üç","employees");
        Response response=given().spec(spec02).when().get("/{bir}/{iki}/{üç}");

        JsonPath json=response.jsonPath();
        json.prettyPrint();

        //status kodunun 200 ve content type ının "application/json"
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        //gelen body de 5. çalışanın "Airi Satou" oldugunu
        json.getString("data.employee_name");
        assertEquals("Airi Satou",json.getString("data[4].employee_name"));

        //6. çalışanın maaşının "372000" oldugunu
       json.getString("data.employee_salary");
       assertEquals(372000,json.getInt("data[5].employee_salary"));

       //ve employees sayısının 24
        assertEquals(24,json.getList("data.id").size());

       // ve employee lerden birinin "Rhona Davidson"
        assertTrue(json.getList("data.employee_name").contains("Rhona Davidson"));

        //ve gelen yaslar içinde 21,23, ve 61 değerlerinden birinin oldugunu test edin.
        //1.yol
        List<Integer> list=new ArrayList<>();
        list.add(21);
        list.add(23);
        list.add(61);
        assertTrue(json.getList("data.employee_age").containsAll(list));




    }
}


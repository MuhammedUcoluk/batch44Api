package get_http_request;

import base_url.DummyBaseUrl;
import base_url.ReqresinBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends DummyBaseUrl {
    /*
    https://dummy.restapiexample.com/api/v1/employees url inde bulunan

    1) Bütün çalışanların isimlerini consola yazdırın
    2) 3. çalışan kişinin ismini consola yazdırın
    3) ilk 5 çalışanın ismini consola yazdırın
    4) En son çalışanın adını consola yazdırın.
     */
    @Test
    public void test08() {
        spec02.pathParams("first","api","second","v1","third","employees");

        Response response=given().spec(spec02).when().get("/{first}/{second}/{third}");

        //"/{first}/{second}/{third}" == /api/v1/employees

       // response.prettyPrint();

        //1) Bütün çalışanların isimlerini consola yazdırın
        JsonPath json=response.jsonPath();
        System.out.println(json.getString("data.employee_name"));

        //2) 3. çalışan kişinin ismini consola yazdırın

        System.out.println(json.getString("data.employee_name[2]"));

        // 3) ilk 5 çalışanın ismini consola yazdırın

        //1.Yol
        for (int i = 0; i < 5 ; i++){
            System.out.println(i+1+". calisan : " + json.getString("data["+i+"].employee_name"));
        }
        //2.Yol

        System.out.println(json.getString("data.employee_name[0,1,2,3,4]"));
        //System.out.println(json.getString("data[0,1,2,3,4].employee_name"));   AYNI SONUCU VERİR...


        //4) En son çalışanın adını consola yazdırın.
        System.out.println(json.getString("data.employee_name[-1]"));

    }
}

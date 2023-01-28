package get_http_request;

import TestData.DummyTestData;
import base_url.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRequest23 extends DummyBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/employees url sine bir istek gönderildiğinde
    Status code 200
    14.Çalışanın "Haley Kennedy"
    Çalışan sayısının 24 olduğu
    Sondan 3. çalışanın maaşının 675000 olduğu
    40,21, ve 19 yaşlarında çalışanların olup olmadığını
    10. çalışanın bilgilerinin aşağıdaki gibi
    {
        "id": 10,
        "employee_name": "Sonya Frost",
        "employee_salary": 103600
        "employee_age":23,
        "profile_image": ""
       }
       olduğunu test edin.
     */
    @Test
    public void test23(){
        //1 - URL oluşturma
        spec02.pathParams("1","api","2","v1","3","employees");

        //2-  Expected Data Oluşturma
        DummyTestData expectedDataObje=new DummyTestData();
        HashMap<String,Object>expectedDataTest=expectedDataObje.setUpTestData();
        System.out.print("Test Data İçerisindeki Test Data :"+ expectedDataTest);
        //Test Data İçerisindeki Test Data :
        // {arananYaslar=[40, 21, 19],
        // sondanUcuncuCalisanMaasi=675000,
        // calisanSayisi=24,
        // statusCode=200,
        // ondorduncuCalisan=Haley Kennedy,
        // onuncuCalisan={profile_image=, employee_name=Sonya Frost, employee_salary=103600, id=10, employee_age=23}}

        //3- Request ve Response

        Response response=given().spec(spec02).when().get("/{1}/{2}/{3}");
        response.prettyPrint();

        //4- Doğrulama

        //1.Yol De-Serialization
        HashMap<String,Object> actualData=response.as(HashMap.class);
        System.out.println("ActualDataMap  : " + actualData);

        //Status code 200
        assertEquals(expectedDataTest.get("statusCode"),response.statusCode());

        //14.Çalışanın "Haley Kennedy"
        assertEquals(expectedDataTest.get("ondorduncuCalisan"),
                ((Map)((List)actualData.get("data")).get(13)).get("employee_name"));

        //Çalışan sayısının 24 olduğu
        assertEquals(expectedDataTest.get("calisanSayisi"),((List<?>) actualData.get("data")).size());

        // Sondan 3. çalışanın maaşının 675000 olduğu
        int dataSize=((List<?>) actualData.get("data")).size();

        assertEquals(expectedDataTest.get("sondanUcuncuCalisanMaasi"),
                ((Map)((List) actualData.get("data")).get(dataSize-3)).get("employee_salary"));

        //40,21, ve 19 yaşlarında çalışanların olup olmadığını

        List<Integer> actualYasListesi=new ArrayList<>();

        for (int i =0; i< dataSize;i++){

            actualYasListesi.add((Integer)((Map) ((List<?>) actualData.get("data")).get(i)).get("employee_age"));
        }

        assertTrue(actualYasListesi.containsAll((Collection<?>) expectedDataTest.get("arananYaslar")));



        //10. çalışanın bilgilerinin aşağıdaki gibi
        //    {
        //        "id": 10,
        //        "employee_name": "Sonya Frost",
        //        "employee_salary": 103600
        //        "employee_age":23,
        //        "profile_image": ""
        //       }
        //       olduğunu test edin.




    }
}

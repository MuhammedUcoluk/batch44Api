package get_http_request;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest19 extends DummyBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/employees
    1. Status kodun 200 oldugunu
    2. 10 dan büyük tüm id leri ekrana yazdırın ve 10 dan büyük 14 id oldugunu test edin.
    3. 30 dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 oldugunu test edin.
    4. Maaşı 350000 den büyük olan tüm employee name leri ekrana yazdırın
        ve bunların içerisinde "Charde Marshall" oldugunu test edin.
     */

    @Test
    public void test19(){

        spec02.pathParams("first","api","second","v1","third","employees");

        Response response=given().spec(spec02).when().get("/{first}/{second}/{third}");

        response.prettyPrint();

        //1. Status kodun 200 oldugunu
        Assert.assertEquals(200,response.statusCode());
       // response.then().assertThat().statusCode(200);

        //2. 10 dan büyük tüm id leri ekrana yazdırın ve 10 dan büyük 14 id oldugunu test edin.
        JsonPath json=response.jsonPath();

        List<Integer> idList=json.getList("data.findAll{it.id>10}.id"); //Groovy dili.
        System.out.println("idList : " + idList); //idList : [11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]
        //===>>gROOVY JAVA PLATFORMU ÜZERİNDE ÇALIŞAN BİR BİLGİSAYAR DİLİDİR.
        //===>>GROOVY İLE LOOP KULLANMADAN RESPONSE DEN GELEN DEĞERLERİ BİR ŞARTA GÖRE ALABİLİRİZ.

        //3. 30 dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 oldugunu test edin.

        List<Integer> yaslar=json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println("30 dan küçük yaslar : " + yaslar); //30 dan küçük yaslar : [22, 23, 22, 19, 21, 23]

        Collections.sort(yaslar);
        Assert.assertEquals(23,(int)yaslar.get(yaslar.size()-1));
       // Assert.assertTrue(yaslar.get(yaslar.size()-1)==23);

        //4. Maaşı 350000 den büyük olan tüm employee name leri ekrana yazdırın
        //        ve bunların içerisinde "Charde Marshall" oldugunu test edin.
        List<Integer> maas=json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println("350000 den büyük maaşlar : " + maas);

        Assert.assertTrue(maas.contains("Charde Marshall"));
    }
}

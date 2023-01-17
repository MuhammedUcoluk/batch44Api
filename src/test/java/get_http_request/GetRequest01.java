package get_http_request;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

//=================İNTERWİEW SORUSU =========================
//poma json - junit ve rest-assured yüklemek yeterli. Bunun sebebi api de userface bir işlem yok.
//kullanıcının görmesi gereken bir işlem olmadıgı için webdriver, bonigarcia gibi bir dependencies
//yüklememize gerek yok.
public class GetRequest01 {
    @Test
    public void test01(){
        // bu url ye git dedik. ve url içindeki
        String url="https://restful-booker.herokuapp.com/booking";

        Response response=given().when().get(url);

        //response.prettyPrint();
                    //response içindeki datayı yazdır.
        System.out.println("Status code:  "  +response.statusCode());
        System.out.println("Content Type: " + response.contentType());
        System.out.println("Status Line : " + response.statusLine());
        System.out.println("Test Zamani :" + response.time());


        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());
        Assert.assertEquals("HTTP/1.1 200 OK",response.statusLine());

        //Yukarıdaki assertleri yapmanın kolay yolu.
        //response hardassertion kullanor. Yani testin ilk geçmediği yerde durur ve hata verir.
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                statusLine("HTTP/1.1 200 OK");

    }
}

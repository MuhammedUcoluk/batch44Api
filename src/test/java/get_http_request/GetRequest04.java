package get_http_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest04 {
    /*
    https://dummy.restapiexample.com/api/v1/employees url'ine
    Get requesti yolldaıgımda gelen response un
    status kodunun 200 ve content type ının "application/json"
    ve employees sayısının 24
    ve employee lerden birinin "Ashton Cox"
    ve gelen yaslar içinde 21,61, ve 23 değerlerinden birinin oldugunu test edin.
     */
    @Test
    public void test04(){
        String url="https://dummy.restapiexample.com/api/v1/employees";

        Response response=given().when().get(url);

        response.prettyPrint();

        //contentype ve status code dogrulama
        response.then().contentType("application/json").statusCode(200);


        response.then().assertThat().body("data", Matchers.hasSize(24)//body içerisinde 24 data var mı?
                                    ,"data.employee_name",Matchers.hasItem("Ashton Cox")//ashton cox var mı?
                                    ,"data.employee_age",Matchers.hasItems(21,61,23));//21,61,23 yaşları var mı?

    }
}

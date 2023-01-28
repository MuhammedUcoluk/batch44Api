package TestData;

import org.json.JSONObject;

import java.util.HashMap;

public class HerOkuAppTestData {

    public HashMap<String,Object> setUpTestData(){

        HashMap<String,Object> bookingdates=new HashMap<>();
        bookingdates.put("checkin","2018-01-01");
        bookingdates.put("checkout","2019-01-01");

        HashMap<String,Object> expectedData=new HashMap<>();
        expectedData.put("firstname","Josh");
        expectedData.put("lastname","Allen");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingdates);

        return expectedData;

    }
    /*
    https://restful-booker.herokuapp.com/booking
    {"firstname": "Ali"
    "lastname":"Can"
    "totalprice":500
    "depositpaid":true,
    "bookingdates": {
            "checkin":"2022-03-01"
            "checkout":"2022-03-11" */
    public JSONObject setupTestAndRequestData(){

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2022-03-01");
        bookingDates.put("checkout","2022-03-11");

        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("firstname", "Ali");
        expectedRequest.put("lastname", "Can");
        expectedRequest.put("totalprice", 500);
        expectedRequest.put("depositpaid", true);
        expectedRequest.put("bookingdates",bookingDates);
        return expectedRequest;

    }
}

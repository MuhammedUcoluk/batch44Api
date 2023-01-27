package TestData;

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
}

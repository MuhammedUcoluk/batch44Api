package TestData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public
class JsonPlaceHolderTestData {

    public Map<String ,Object> setUpTestData(){

     Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("completed",false);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("userId",1);
        expectedData.put("via","1.1 vegur");
        expectedData.put("Server","cloudflare");

        return expectedData;

    }


    public JSONObject setupPostData(){
        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("userId",55);
        expectedRequest.put("title","Tidy your room");
        expectedRequest.put("completed",false);
        expectedRequest.put("statusCode",201);

        return expectedRequest;

    }
}

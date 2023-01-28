package TestData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {
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

    public HashMap<String,Object> setUpTestData(){

        List<Integer> yaslar=new ArrayList<>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);

        HashMap<String,Object> onuncu=new HashMap<>();
        onuncu.put("id",10);
        onuncu.put("employee_name","Sonya Frost");
        onuncu.put("employee_salary",103600);
        onuncu.put("employee_age",23);
        onuncu.put("profile_image","");

        HashMap<String,Object> expectedData=new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("ondorduncuCalisan","Haley Kennedy");
        expectedData.put("calisanSayisi",24);
        expectedData.put("sondanUcuncuCalisanMaasi",675000);
        expectedData.put("arananYaslar",yaslar);
        expectedData.put("onuncuCalisan",onuncu);

        return expectedData;
    }
}

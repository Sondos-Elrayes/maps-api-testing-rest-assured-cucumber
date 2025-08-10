package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReUsableMethods {
    public static String getResponseKey(Response response , String key) {
        String resp =response.asString() ;
        JsonPath jsonPath = JsonPath.from(resp);
        Object value = jsonPath.get(key);
        String valueForTheKey = value != null ? value.toString() : null;
        return valueForTheKey;
    }
}

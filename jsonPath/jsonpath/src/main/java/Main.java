import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {
    final static String URL = "https://api.publicapis.org/";

    public static void main(String[] args) {
        RestAssured.baseURI = URL;
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.get("entries");

        JsonPath jsonPath = response.jsonPath();

        //get String
        String category = jsonPath.getString("entries[0].Category");
        System.out.println(category);

        //get List
        List<String> categories = Collections.singletonList(jsonPath.getString("entries.Category"));
        System.out.println(categories);

        //getBoolean
        Boolean aBoolean = jsonPath.getBoolean("entries[1].HTTPS");
        System.out.println(aBoolean);

        //get JsonObject
        Object jsonObject = jsonPath.getJsonObject("entries[0]");
        System.out.println(jsonObject.toString());

        //get Map
        Map<Object, Object> map = jsonPath.getMap("entries[0]");
        for (Map.Entry<Object, Object> objectObjectEntry : map.entrySet()) {
            System.out.println(objectObjectEntry.toString());
        }
    }
}

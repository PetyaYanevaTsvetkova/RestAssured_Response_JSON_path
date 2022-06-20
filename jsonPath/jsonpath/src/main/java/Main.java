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
        Response responseEntries = requestSpecification.get("entries");
        Response responseCategories = requestSpecification.get("categories");

        JsonPath jsonPathEntries = responseEntries.jsonPath();
        JsonPath jsonPathCategories = responseCategories.jsonPath();

        //get String
        String category = jsonPathEntries.getString("entries[0].Category");
        System.out.println("Get String: " + category);

        //get List
        List<String> categories = jsonPathCategories.getList("categories");
        System.out.println("Get List: " + categories);

        //getBoolean
        Boolean aBoolean = jsonPathEntries.getBoolean("entries[1].HTTPS");
        System.out.println("Get boolean: " + aBoolean);

        //get JsonObject
        Object jsonObject = jsonPathEntries.getJsonObject("entries[0]");
        System.out.println("Get Object: " + jsonObject.toString());

        //get Map
        System.out.println("Get Map:");
        Map<Object, Object> map = jsonPathEntries.getMap("entries[0]");

        for (Map.Entry<Object, Object> objectObjectEntry : map.entrySet()) {
            System.out.println(objectObjectEntry.toString());
        }
    }
}

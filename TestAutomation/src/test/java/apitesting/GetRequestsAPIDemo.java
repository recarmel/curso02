package apitesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExecuteRequest;
import utils.GlobalVariables;

import static io.restassured.RestAssured.given;

public class GetRequestsAPIDemo {

    @Test
    public void buildGetRequest(){
        RestAssured.baseURI = "http://localhost:5000";
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users/findbyid/3")
                .then()
                .extract().response();

        System.out.println(response.getBody().prettyPrint());
    }

    @Test
    public void GetCorreo(){
        RestAssured.baseURI = "http://localhost:5000";
        Response respuesta = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users/findbyemail/username3@test.com")
                .then()
                .extract().response();

        System.out.println(respuesta.getBody().prettyPrint());
    }

    @Test(groups = {"getAll","get"})
    public void getAllUsers(){
        String url = GlobalVariables.apiHost +  "/users/all";

        Response response =  ExecuteRequest.makeGetRequest(url);
        System.out.println(response.asString());
        JSONArray jsonArray = new JSONArray(response.asString());
        System.out.println(jsonArray.toString(10));

        if(jsonArray.length() > 10){
            Assert.fail("");
          //  Assert.fail("Length should be 10");
        }
    }

    @Test(groups={"getAll", "get"})
    public void countCasos(){
        String url = GlobalVariables.apiHost +  "/users/all";
        int contador = 0;
        String nombre;
        Response response =  ExecuteRequest.makeGetRequest(url);
        System.out.println(response.asString());
        JSONArray jsonArray = new JSONArray(response.asString());
      //  System.out.println(jsonArray.toString(10));
        for(int i = 0; i < 500; i++){
            JSONObject rec = jsonArray.getJSONObject(i);
            //  JSONObject rec = jsonArray.getJSONArray(i);
            nombre = rec.getString("username");
            if (nombre.contains("5")) contador++;
        }
       System.out.println("el número de usuarios que contiene el numero 5 es:" + contador);
    }

    @Test
    public void findById(){
        String url = GlobalVariables.apiHost +  "/users/findbyid/14";
        Response response = ExecuteRequest.makeGetRequest(url);
        System.out.println(response.asString());
        JSONObject jsonArray = new JSONObject(response.asString());
        System.out.println(jsonArray.toString(10));
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("id").toString(), "14");
    }

}

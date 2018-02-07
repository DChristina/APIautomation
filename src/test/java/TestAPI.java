import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TestAPI
{
    @Test
    public void makeSureThatGoogleIsUp() {
        Response response= given().get("https://www.google.com.ua/");//это можно использовать для получения курса страницы
        response.prettyPrint();
    }
    @Test
    public void postRequest() {
        Response response = given()
                .config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation()))
                //        .log().all().with()
                .body("user_name=fhfgh&password=fgfuytf&Submit=Submit&login=true")
                .with().contentType(ContentType.JSON).then().when()
                .post("http://www.aavtrain.com/index.asp");
        response.prettyPrint();
    }
    @Test
    public void getAllUsers() {
        Response response = given().get("http://localhost:8088/SpringBootRestApi/api/user/");//это можно использовать для получения курса страницы
        //response.prettyPrint();
        //response.getBody().prettyPrint();
        //Assert.assertNotNull(response.getBody().prettyPrint());
       System.out.println(response.asString());
        Assert.assertTrue(response.asString().contains("id"));
    }
    @Test
    public void getUserWithDefiendId(){
        Response response = given().get("http://localhost:8088/SpringBootRestApi/api/user/2");
       // System.out.println(response.asString());
        //Assert.assertTrue(response.asString().contains("id"));
        Assert.assertTrue(response.asString().contains("\"id\":2"));
    }
    @Test
    public void postNewUser(){
        RestAssured.baseURI  = "http://localhost:8088/SpringBootRestApi/api/user/";
         given()
       // Response r = given()
                .contentType("application/json").
                        body("{\n" +
                                "        \"id\": 11,\n" +
                                "        \"name\": \"Marfushawqa\",\n" +
                                "        \"age\": 30,\n" +
                                "        \"salary\": 100000\n" +
                                "    }").
                        when().
                        post("");
       // response.then().statusCode(201);
        //String body = response.getBody().asString();
        //System.out.println(body);
        Response response = given().get("http://localhost:8088/SpringBootRestApi/api/user/");
        Assert.assertTrue(response.asString().contains("Marfushawqa"));
    }
    @Test
    public void putNewUser() {
        RestAssured.baseURI = "http://localhost:8088/SpringBootRestApi/api/user/";
        given()
                // Response r = given()
                .contentType("application/json").
                body("{\n" +
                        "        \"id\": 11,\n" +
                        "        \"name\": \"Marfushawqa\",\n" +
                        "        \"age\": 6,\n" +
                        "        \"salary\": 100000\n" +
                        "    }").
                when().
                post("");
        // response.then().statusCode(201);
        //String body = response.getBody().asString();gfdgdgdfgdgdfgdfgdffdsfsdfsdfsdfsdsf
        //System.out.println(body);
        Response response = given().get("http://localhost:8088/SpringBootRestApi/api/user/");
        Assert.assertTrue(response.asString().contains("Marfushawqa"));
    }
}
//доделать put delete id delete all
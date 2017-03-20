package com.eodessa.framework;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
/**
 * Created by luda bruksha on 1/19/17.
 */
public class RestAssuredConfiguration {

    @BeforeSuite(alwaysRun = true)
    public void configure() {

        RestAssured.useRelaxedHTTPSValidation();
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }


        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/api/apiservice.svc";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "https://push.notification.marketing";
        }
        RestAssured.baseURI = baseHost;
    }

    public RequestSpecification getRequestSpecification() {
        return RestAssured.given().contentType(ContentType.JSON);
    }

    public Response getResponse(RequestSpecification requestSpecification,String endpoint, int
            status){
        Response response = requestSpecification.get(endpoint);
        Assert.assertEquals(response.getStatusCode(),status);
        response.then().log().all();
        return response;
    }

}

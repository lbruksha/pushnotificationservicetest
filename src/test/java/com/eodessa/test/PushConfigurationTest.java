package com.eodessa.test;

import com.eodessa.bin.PushConfigurationBin;
import com.eodessa.common.EndPoint;
import com.eodessa.framework.RestAssuredConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by luda bruksha on 1/19/17.
 */
public class PushConfigurationTest extends RestAssuredConfiguration {


    @Test(groups = "demo")
    public void getPushConfigurationService() {

        given().get(EndPoint.GET_PUSHCONFIGURATION).then().statusCode(200).log().all();

    }

    @Test(groups = {"demo", "response"})
    public void getPushConfigurationServiceDetails() {
        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
        requestSpecification.pathParam("widgetId", 1).pathParam("companyId", 1).log().all();
        given().spec(requestSpecification).get(EndPoint.GET_PUSHCONFIGURATION_PATH_PARAM).
                then().statusCode(200).log().all();

        //Getting Response
        Response response = given().spec(requestSpecification).get(EndPoint.GET_PUSHCONFIGURATION_PATH_PARAM);
        //Inline Validation
        //1.Hard Assertion
        response.then().body("Title", equalTo("Do you like to receive push notifications from XYZ Inc?"));
        //Path Validation
        //1.Hard Assertion
        Assert.assertEquals(response.path("Title"), "Do you like to receive push notifications from XYZ Inc?");
        //2.Soft Assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.path("Title"), "Do you like to receive push notifications from XYZ Inc?", "Title Not Equal");
        softAssert.assertAll();
        //Java Object
        PushConfigurationBin pushNotificationBin = response.as(PushConfigurationBin.class);
        //1.Hard Assertion
        Assert.assertEquals(pushNotificationBin.getId(), 1);
        //2.Soft Assertion
        SoftAssert newSoftAssert = new SoftAssert();
        newSoftAssert.assertEquals(pushNotificationBin.getTitle(), "Do you like to receive push notifications from XYZ Inc?", "Title Not Equal");
        newSoftAssert.assertAll();
    }

    @Test(groups = {"demo", "response"})
    public void getPushConfigurationServiceTitle() {
        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
        requestSpecification.pathParam("widgetId", 1).pathParam("companyId", 1).log().all();
        given().spec(requestSpecification).get(EndPoint.GET_PUSHCONFIGURATION_PATH_PARAM).
                then().statusCode(200).log().all();

        //Getting Response
        Response response = given().spec(requestSpecification).get(EndPoint.GET_PUSHCONFIGURATION_PATH_PARAM);

        //Java Object
        PushConfigurationBin pushNotificationBin = response.as(PushConfigurationBin.class);
        //2.Soft Assertion
        SoftAssert newSoftAssert = new SoftAssert();
        newSoftAssert.assertEquals(pushNotificationBin.getTitle(), "Do you like to receive push notifications from XYZ Inc?", "Title Not Equal");
        newSoftAssert.assertAll();
    }
}


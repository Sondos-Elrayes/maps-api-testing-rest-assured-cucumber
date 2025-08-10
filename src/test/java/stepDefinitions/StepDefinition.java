package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import utils.Builder;
import utils.ReUsableMethods;
import utils.Resources;
import utils.TestDataBuild;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static utils.TestDataBuild.deletePlacePayload;
import static utils.TestDataBuild.getPlace;

public class StepDefinition  {

    ResponseSpecification res;
    RequestSpecification mapSpec;
    Response response;
    static String placeId;


    @Given("Add place by payload with {string} {string} {string}")
    public void add_place_by_payload_with(String name, String language, String address) throws FileNotFoundException {


        res = new ResponseSpecBuilder()
                .expectStatusCode(200).expectContentType(ContentType.JSON).build();
         mapSpec= given().spec(Builder.addPlaceRequestSpec()).body(getPlace(name,language,address));

    }
    @When("user calls the {string} with {string} request")
    public void user_calls_the_with_request(String requestName, String requestMethod) {
        // constructor will be called with the value of resource
        Resources apiResources = Resources.valueOf(requestName);

        if (requestMethod.equalsIgnoreCase("POST")) {
            response = mapSpec
                    .when().post(apiResources.getPath())
                    .then().extract().response();

        } else if (requestMethod.equalsIgnoreCase("GET")) {
            response = mapSpec
                    .when().get(apiResources.getPath())
                    .then().spec(res).extract().response();

        } else if (requestMethod.equalsIgnoreCase("PUT")) {
            response = mapSpec
                    .when().put(apiResources.getPath())
                    .then().spec(res).extract().response();

        } else if (requestMethod.equalsIgnoreCase("DELETE")) {
            response = mapSpec
                    .when().delete(apiResources.getPath())
                    .then().extract().response();
        }
    }
    @Then("Validate the place added successfully with status code {int}")
    public void validate_the_place_added_successfully_with_status_code(Integer int1) {

        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Then("{string} in response body should be {string}")
    public void in_response_body_should_be(String keyValue, String valueExpected) {
        String actualStatus = ReUsableMethods.getResponseKey(response,keyValue);
        Assert.assertEquals(valueExpected, (ReUsableMethods.getResponseKey(response,keyValue)));
    }
    @Then("verify Place_id is created maps to {string} using {string}")
    public void verify_place_id_is_created_maps_to_using(String expectedName, String resource)throws FileNotFoundException  {
        placeId =ReUsableMethods.getResponseKey(response,"place_id");
        mapSpec=given().spec(Builder.addPlaceRequestSpec()).queryParam("place_id",placeId);
        user_calls_the_with_request(resource, "GET");

        String actualName = ReUsableMethods.getResponseKey(response,"name");
        Assert.assertEquals(expectedName,actualName);
    }


    @Given("Delete place payload")
    public void delete_place_payload() throws  FileNotFoundException{
        mapSpec=given().spec(Builder.addPlaceRequestSpec()).body(deletePlacePayload(placeId));
    }



}

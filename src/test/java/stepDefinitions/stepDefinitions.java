package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.JsonBuilderLocation;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinitions extends Utils {
	RequestSpecification requestSpecification;
	ResponseSpecification res;
	Response response;
	static String place_id;
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		requestSpecification = given().spec(requestSpecification()).body(TestDataBuild.addPlacePayLoad(name,language,address));
	}
	
	@Given("DeletePlace Payload")
	public void deletePlacePayload() throws IOException {
		requestSpecification = given().spec(requestSpecification()).body(TestDataBuild.deletePlacePayload(place_id));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		/*Invoke constructor of enum with value of this resource*/
		APIResources resourceAPI = APIResources.valueOf(resource); // constructor of enum class will be invoked

		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if (method.equalsIgnoreCase("Post"))
			response = requestSpecification.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = requestSpecification.when().get(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("DELETE")) 
			response = requestSpecification.when().delete(resourceAPI.getResource());
	}
	
	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), int1.intValue());
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		assertEquals(getJsonPath(response,key), value);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String resource) throws IOException {
		place_id = getJsonPath(response,"place_id");
		requestSpecification = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response,"name");
		assertEquals(name,actualName);
	}
}

package StepDefinations;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import POJO.Addplace;
import POJO.location;
import Resources.APIsList;
import Resources.TestDataBuild;
import Resources.Utils;
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

public class sample extends Utils {

	ResponseSpecification res;
	RequestSpecification response;
	Response getresponse;
	TestDataBuild testdata = new TestDataBuild();
	String placeid;
	JsonPath json;

	@Given("add place payload with {string} {string} {string}")
	public void add_place_payload(String name, String address, String language) throws FileNotFoundException {

		response = given().spec(requestspecification()).body(testdata.data_addplace_payload(name, address, language));

	}

	@When("user call {string} with {string} http request")
	public void user_call_with_post_http_request(String resource, String method) {

		APIsList apiresource = APIsList.valueOf(resource);
		System.out.println(apiresource.getResource());

		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("post")) 
			getresponse = response.when().post(apiresource.getResource()).then().spec(res).extract().response();
		if (method.equalsIgnoreCase("get"))
			getresponse = response.when().get(apiresource.getResource()).then().spec(res).extract().response();
			
		

	}

	@Then("user verify status code is {int}")
	public void user_verify_status_code_is(Integer int1) {

		assertEquals(getresponse.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void status_in_reponse_body_is_ok(String keyvalue, String Expectedvalue) {	
		
		assertEquals(getjsonpath(getresponse, keyvalue), Expectedvalue);
	}
	
	@Then("Verify placeid created map to {string} using {string}")
	public void verify_placeid_created_map_to_using(String expectedname, String resource) throws FileNotFoundException {
	    
		placeid = getjsonpath(getresponse, "place_id");
		
		System.out.println("placeid is -->"+placeid);
		response = given().spec(requestspecification()).queryParam("place_id", placeid);
		user_call_with_post_http_request(resource, "get");
		
		String actualname = getjsonpath(getresponse, "name");
		System.out.println("actualname -->"+actualname);
		assertEquals(actualname, expectedname);
		
		
	}

}

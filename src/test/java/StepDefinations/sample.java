package StepDefinations;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import POJO.Addplace;
import POJO.location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class sample {

	Addplace addplc = new Addplace();
	location loc = new location();
	RequestSpecification req;
	ResponseSpecification res;
	RequestSpecification response;
	Response getresponse;

	@Given("add place payload")
	public void add_place_payload() {

		req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();

		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		addplc.setAccuracy(100);
		addplc.setName("Preyas rana");
		addplc.setPhone_number("(+91) 990 123 1111");
		addplc.setAddress("30, new layout, side 09");
		addplc.setWebsite("https://www.google.com");
		addplc.setLanguage("English US");

		List<String> mylist = new ArrayList<>();
		mylist.add("new park");
		mylist.add("new");
		addplc.setTypes(mylist);

		loc.setLat(-23.1234);
		loc.setLng(12.4566);

		addplc.setLocation(loc);
		// Code
		response = given().spec(req).body(addplc);

	}

	@When("user call {string} with post http request")
	public void user_call_with_post_http_request(String string) {
		getresponse = response.when().post("/maps/api/place/add/json")
				.then().spec(res).extract().response();

	}

	@Then("user verify status code is {int}")
	public void user_verify_status_code_is(Integer int1) {

		assertEquals(getresponse.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void status_in_reponse_body_is_ok(String keyvalue,String Expectedvalue) {
	
		String res = getresponse.asString();
		JsonPath json = new JsonPath(res);
		assertEquals(json.get(keyvalue).toString(),Expectedvalue);
	}

}

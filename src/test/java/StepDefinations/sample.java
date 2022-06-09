package StepDefinations;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import POJO.Addplace;
import POJO.location;
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

	@Given("add place payload")
	public void add_place_payload() throws FileNotFoundException {

		response = given().spec(requestspecification()).body(testdata.data_addplace_payload());

	}

	@When("user call {string} with post http request")
	public void user_call_with_post_http_request(String string) {
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		getresponse = response.when().post("/maps/api/place/add/json").then().spec(res).extract().response();

	}

	@Then("user verify status code is {int}")
	public void user_verify_status_code_is(Integer int1) {

		assertEquals(getresponse.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void status_in_reponse_body_is_ok(String keyvalue, String Expectedvalue) {

		String res = getresponse.asString();
		JsonPath json = new JsonPath(res);
		assertEquals(json.get(keyvalue).toString(), Expectedvalue);
	}

}

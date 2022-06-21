package StepDefinations;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.sun.tools.jxc.ConfigReader;

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
	RequestSpecification req;
	Response getresponse;
	TestDataBuild testdata = new TestDataBuild();

	static String placeid;
	static String isbnid;
	static String userID;
	static String token;
	JsonPath json;

	@Given("add place payload with {string} {string} {string}")
	public void add_place_payload(String name, String address, String language) throws FileNotFoundException {

		req = given().spec(requestspecification()).body(testdata.data_addplace_payload(name, address, language));

	}

	@Given("add user with {string} {string}")
	public void add_user_payload(String USRNAME, String PASSWORD) throws FileNotFoundException {

		req = given().spec(basicauth_requestspecification())
				.body(testdata.Create_user_payload(USRNAME, PASSWORD));
	}
	
	
	@Given("add user with username and password")
	public void add_user_payload() throws FileNotFoundException {
		
    	req = given().spec(basicauth_requestspecification()).body(testdata.create_user());
		
	}


	@When("user call {string} with {string} http request")
	public void user_call_with_post_http_request(String resource, String method) {

		APIsList apiresource = APIsList.valueOf(resource);
		System.out.println(apiresource.getResource());

		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		System.out.println(res);
		if (method.equalsIgnoreCase("post"))
			getresponse = req.when().post(apiresource.getResource()).then().spec(res).extract().response();
		System.out.println(getresponse);
		if (method.equalsIgnoreCase("get"))
			getresponse = req.when().get(apiresource.getResource()).then().spec(res).extract().response();

	}

	@Then("user verify status code is {int}")
	public void user_verify_status_code_is(Integer int1) {

		System.out.println(getresponse.getStatusCode());
		assertEquals(getresponse.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void status_in_reponse_body_is_ok(String keyvalue, String Expectedvalue) {

		assertEquals(getjsonpath(getresponse, keyvalue), Expectedvalue);
	}

	@Then("Verify placeid created map to {string} using {string}")
	public void verify_placeid_created_map_to_using(String expectedname, String resource) throws FileNotFoundException {

		placeid = getjsonpath(getresponse, "place_id");

		System.out.println("placeid is -->" + placeid);
		req = given().spec(requestspecification()).queryParam("place_id", placeid);
		user_call_with_post_http_request(resource, "get");

		String actualname = getjsonpath(getresponse, "name");
		System.out.println("actualname -->" + actualname);
		assertEquals(actualname, expectedname);

	}

	@Given("deleteplacepayload")
	public void deleteplacepayload() throws FileNotFoundException {

		req = given().spec(requestspecification()).body(testdata.deleteplacepayload(placeid));
	}

	@When("user are call {string} with {string} http request")
	public void getBooksapi(String resource, String method) throws FileNotFoundException {

		APIsList apiresource = APIsList.valueOf(resource);
		System.out.println("apiresource" + apiresource.getResource());

		if (method.equalsIgnoreCase("get")) {
			
			res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			System.out.println("Response is " + res);

			getresponse = given().spec(basicauth_requestspecification()).get(apiresource.getResource()).then().spec(res)
					.extract().response();
			//getresponse = response.when().get(apiresource.getResource()).then().spec(res).extract().response();
			System.out.println(getresponse);
			// get isbn no
			isbnid = getjsonpath(getresponse, "books[7].isbn");
			System.out.println("isbnid is -->" + isbnid);

		} else if (method.equalsIgnoreCase("post")) {
			
			res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
			System.out.println("Response is " + res);
			
			getresponse = req.when().post(apiresource.getResource()).then().spec(res).extract().response();
			System.out.println(getresponse);
			
			userID = getjsonpath(getresponse, "userID");
		    System.out.println("userID is -->" + userID);

		}

	}
	
	
	@When("userare call {string} with {string} http request")
	public void createtoken(String resource, String method) throws FileNotFoundException {

		APIsList apiresource = APIsList.valueOf(resource);
		System.out.println("apiresource" + apiresource.getResource());
		
		if (method.equalsIgnoreCase("post")) {
			
			res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
			System.out.println("Response is " + res);
			
			getresponse = req.when().post(apiresource.getResource()).then().spec(res).extract().response();
			System.out.println(getresponse);
			
			
			token = getjsonpath(getresponse, "token");
		    System.out.println("token is -->" + token);
		}
	
	}
	
	@Given("assignbooktoUSer")
	public void assignbooktoUSer() throws FileNotFoundException {

		req = given().spec(basicauth_requestspecification()).headers("Authorization","Bearer " + token).body(testdata.post_assignbook_touser(userID,isbnid));
	}
	
	@When("user shouldbe call {string} with {string} http request")
	public void Booksapi(String resource, String method) throws FileNotFoundException {
		

		APIsList apiresource = APIsList.valueOf(resource);
		System.out.println("apiresource" + apiresource.getResource());
		
		if (method.equalsIgnoreCase("post")) {
			
			res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
			System.out.println("Response is " + res);
			
			
			getresponse = req.when().post(apiresource.getResource()).then().spec(res).extract().response();
			System.out.println(getresponse);
			
			isbnid = getjsonpath(getresponse, "books[0].isbn");
		    System.out.println("isbnid is -->" +isbnid);
		}
		
	}


}

package StepDefinations;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import java.io.FileNotFoundException;
import Resources.APIsList;
import Resources.ConfigReader;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
	static String Assignbook_isbnid;
	static String spotify_uri;
	static String playlistid;

	JsonPath json;

	@Given("add place payload with {string} {string} {string}")
	public void add_place_payload(String name, String address, String language) throws FileNotFoundException {

		req = given().spec(requestspecification()).body(testdata.data_addplace_payload(name, address, language));

	}

	@Given("add user with {string} {string}")
	public void add_user_payload(String USRNAME, String PASSWORD) throws FileNotFoundException {

		req = given().spec(basicauth_requestspecification()).body(testdata.Create_user_payload(USRNAME, PASSWORD));
	}

	@Given("add user with username and password")
	public void add_user_payload() throws FileNotFoundException {

		req = given().spec(basicauth_requestspecification()).body(testdata.create_user());

	}

	@Given("add playlist")
	public void add_playlist() throws FileNotFoundException {

		req = given().spec(auth2_requestspecification()).body(testdata.create_playlist());

	}
	
	@Given("update itemtoplaylist")
	public void update_itemtoplaylist() throws FileNotFoundException {

		req = given().spec(auth2_requestspecification()).body(testdata.update_playlist());

	}
	
	
	@Given("delete itemtoplaylist")
	public void delete_itemtoplaylist() throws FileNotFoundException {

		req = given().spec(auth2_requestspecification()).body(testdata.delete_playlist_item());

	}

	@Given("add itemtoplaylist")
	public void add_itemtoplaylist() throws FileNotFoundException {

		req = given().spec(auth2_requestspecification()).queryParams("uris",ConfigReader.init_prop().getProperty("trackid"));
				

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

	@When("user are calling {string} with {string} http request")
	public void user_are_calling_with_http_request(String resource, String method) throws FileNotFoundException {

		APIsList apiresource = APIsList.valueOf(resource);
		String strAPIResource = apiresource.getResource();
		System.out.println(strAPIResource);

		if (strAPIResource.contains(":user_id")) {
			strAPIResource = strAPIResource.replace(":user_id", ConfigReader.init_prop().getProperty("UserId"));
			System.out.println("Replaced userId in enum: " + strAPIResource);
		} else if (strAPIResource.contains(":playlistid")) {
			strAPIResource = strAPIResource.replace(":playlistid", playlistid);
			System.out.println("Replaced userId in enum: " + strAPIResource);
		}
		
		

		if (method.equalsIgnoreCase("get")) {

			res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			System.out.println("Response is " + res);

			getresponse = given().spec(auth2_requestspecification()).get(apiresource.getResource()).then().spec(res)
					.extract().response();

			System.out.println(getresponse);

			spotify_uri = getjsonpath(getresponse, "albums.items[0].uri");
			System.out.println("spotify_uri is -->" + spotify_uri);

		} else if (method.equalsIgnoreCase("post")) {

			res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
			System.out.println("Response is " + res);

			getresponse = req.when().post(strAPIResource).then().spec(res).extract().response();
			System.out.println(getresponse);

		}
		else if(method.equalsIgnoreCase("put")) {
			
			res = new ResponseSpecBuilder().expectStatusCode(200).build();
			System.out.println("Response is " + res);

			getresponse = req.when().put(strAPIResource).then().spec(res).extract().response();
			System.out.println(getresponse);
		}
		else if(method.equalsIgnoreCase("delete")) {
			
			res = new ResponseSpecBuilder().expectStatusCode(200).build();
			System.out.println("Response is " + res);

			getresponse = req.when().delete(strAPIResource).then().spec(res).extract().response();
			System.out.println(getresponse);
			
		}
		

	}

	@Then("get playlistid")
	public void get_playlistid() {

		playlistid = getjsonpath(getresponse, "id");
		System.out.println("playlistid is -->" + playlistid);
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
			// getresponse =
			// response.when().get(apiresource.getResource()).then().spec(res).extract().response();
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

		req = given().spec(basicauth_requestspecification()).headers("Authorization", "Bearer " + token)
				.body(testdata.post_assignbook_touser(userID, isbnid));
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

			Assignbook_isbnid = getjsonpath(getresponse, "books[0].isbn");
			System.out.println("Assignbook_isbnid is -->" + Assignbook_isbnid);
		}

	}

}

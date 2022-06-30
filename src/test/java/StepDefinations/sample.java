package StepDefinations;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;

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
	static String invalidjson_response;
	static String username;
	
	static String oauthtoken = "Bearer BQCUB3ATFT_ADa7iLIzdb3kkR4Yj5-d2qpuOqCCa-WSWxPcEn56T-XghwbXLK2QEBUBtx91-dzGuCEOUOafGO9WWaCvfU79A36-leTdox9ct68-SFNoXmZrW3AdasXd26mMir-LV40FOquINNmSHLYg1Q-EVI5cit0cpxJayqxwFoJuwYh-UwqW27KaoYYyLmUJ647nsQbyOH7kERBM";

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
	
	
	@Given("add user with username and password to create account")
	public void add_user_payload_create_account() throws FileNotFoundException {

		req = given().spec(basicauth_requestspecification()).body(testdata.new_user_create(username));

	}

	@Given("add playlist")
	public void add_playlist() throws FileNotFoundException, InterruptedException {

		req = given().spec(auth2_requestspecification()).body(testdata.create_playlist());

	}
	
	@Given("without auth add playlist")
	public void without_auth_add_playlist() throws FileNotFoundException, InterruptedException {

		req = given().body(testdata.create_playlist());

	}
	
	
	@Given("without auth add itemtoplaylist")
	public void without_auth_additemtoplaylist() throws FileNotFoundException, InterruptedException {

		req = given().queryParams("uris",ConfigReader.init_prop().getProperty("trackid"));

	}
	
	
	@Given("missing requiredfield payload to playlist")
	public void missing_requiredfield_payload_to_add_playlist() throws FileNotFoundException, InterruptedException {

		req = given().spec(auth2_requestspecification()).body(testdata.missing_required_payload());

	}
	
	@Given("empty requiredfield payload to update itemtoplaylist")
	public void empty_requiredfield_payload_to_update_itemtoplaylist() throws FileNotFoundException, InterruptedException {

		req = given().spec(auth2_requestspecification()).body(testdata.empty_attribute_change_playlist_detail());

	}
	
	
	@Given("empty requiredfield payload to remove itemtoplaylist")
	public void empty_requiredfield_payload_to_remove_itemtoplaylist() throws FileNotFoundException, InterruptedException {

		req = given().spec(auth2_requestspecification()).body(testdata.missing_uri_id());

	}
	
	@Given("without URI payload to remove itemtoplaylist")
	public void without_URI_payload_to_remove_itemtoplaylist() throws FileNotFoundException, InterruptedException {

		req = given().spec(auth2_requestspecification()).body(testdata.without_uri());

	}
	
	
	@Given("blank payload to playlist")
	public void blank_payload_to_add_playlist() throws FileNotFoundException, InterruptedException {

		req = given().spec(auth2_requestspecification());

	}
	
	
	@Given("update itemtoplaylist")
	public void update_itemtoplaylist() throws FileNotFoundException, InterruptedException {

		req = given().spec(auth2_requestspecification()).body(testdata.update_playlist());

	}
	
	@Given("without auth update itemtoplaylist")
	public void without_auth_update_itemtoplaylist() throws FileNotFoundException, InterruptedException {

		req = given().body(testdata.update_playlist());

	}
	
	
	@Given("without auth remove playlistitem")
	public void without_auth_remove_playlistitem() throws FileNotFoundException, InterruptedException {

		req = given().body(testdata.delete_playlist_item());

	}
	
	
	@Given("delete itemtoplaylist")
	public void delete_itemtoplaylist() throws FileNotFoundException, InterruptedException {

		req = given().spec(auth2_requestspecification()).body(testdata.delete_playlist_item());

	}
	
	@Given("invaliddelete itemtoplaylist")
	public void invaliddelete_itemtoplaylist() throws FileNotFoundException, InterruptedException {

		req = given().spec(auth2_requestspecification()).body(testdata.invalid_delete_playlist_item());

	}

	@Given("add itemtoplaylist")
	public void add_itemtoplaylist() throws FileNotFoundException, InterruptedException {

		req = given().spec(auth2_requestspecification()).queryParams("uris",ConfigReader.init_prop().getProperty("trackid"));
				

	}
	
	
	@Given("add blank trackid with itemtoplaylist")
	public void add_blank_trackid_with_itemtoplaylist() throws FileNotFoundException, InterruptedException {

		req = given().spec(auth2_requestspecification()).queryParams("uris",ConfigReader.init_prop().getProperty("blank_trackid"));
				

	}
	
	@Given("add without track uri with itemtoplaylist")
	public void add_without_track_uri_with_itemtoplaylist() throws FileNotFoundException, InterruptedException {

		req = given().spec(auth2_requestspecification()).queryParams("uris","");
				

	}
	
	@Given("invalid trackid with add itemtoplaylist")
	public void invalid_trackid_with_add_itemtoplaylist() throws FileNotFoundException, InterruptedException {

		req = given().spec(auth2_requestspecification()).queryParams("uris",ConfigReader.init_prop().getProperty("invalidtrackid"));
				
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
	
	
	@When("user are using without token {string} with {string} http request")
	public void user_are_using_with_http_request(String resource, String method) throws FileNotFoundException, InterruptedException {
		
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
			
			res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
			System.out.println("Response is " + res);

			getresponse = given().get(apiresource.getResource()).then().spec(res)
					.extract().response();
			System.out.println(getresponse);
		}
		
		else if (method.equalsIgnoreCase("post")) {
			
			res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
			System.out.println("Response is " + res);

			getresponse = req.when().post(strAPIResource).then().spec(res).extract().response();
			System.out.println(getresponse);
	
			
		}
		
		else if (method.equalsIgnoreCase("put")) {
			
			res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
			System.out.println("Response is " + res);

			getresponse = req.when().put(strAPIResource).then().spec(res).extract().response();
			System.out.println(getresponse);
	
		}
		else if(method.equalsIgnoreCase("delete")) {
			
			res = new ResponseSpecBuilder().build();
			System.out.println("Response is " + res);

			getresponse = req.when().delete(strAPIResource).then().spec(res).extract().response();
			System.out.println(getresponse);
			
		}
		
		
		
		
	
	}
	
	
	@When("user are using Expire token {string} with {string} http request")
	public void user_are_using_Expire_token_with_http_request(String resource, String method) throws FileNotFoundException, InterruptedException {
		
		APIsList apiresource = APIsList.valueOf(resource);
		String strAPIResource = apiresource.getResource();
		System.out.println(strAPIResource);
		
		if (method.equalsIgnoreCase("get")) {
			
			res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
			System.out.println("Response is " + res);

			getresponse = given().header("Authorization",oauthtoken).get(apiresource.getResource()).then().spec(res)
					.extract().response();
			System.out.println(getresponse);
		}
		else if (method.equalsIgnoreCase("post")) {
				
			res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
			System.out.println("Response is " + res);

			getresponse = given().header("Authorization",oauthtoken).get(apiresource.getResource()).then().spec(res)
					.extract().response();
			System.out.println(getresponse);		
			
		}
		else if(method.equalsIgnoreCase("put")) {
			
			res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
			System.out.println("Response is " + res);

			getresponse = req.header("Authorization",oauthtoken).when().put(strAPIResource).then().spec(res).extract().response();
			System.out.println(getresponse);
		}
		else if(method.equalsIgnoreCase("delete")) {
			
			res = new ResponseSpecBuilder().build();
			System.out.println("Response is " + res);

			getresponse = req.header("Authorization",oauthtoken).when().delete(strAPIResource).then().spec(res).extract().response();
			System.out.println(getresponse);
			
		}
		
		
		
	
	}
	
	
	@When("user are using invalid url with {string} with {string} http request")
	public void user_are_using_invalid_url_with_http_request(String resource, String method) throws FileNotFoundException, InterruptedException {
		
		APIsList apiresource = APIsList.valueOf(resource);
		String strAPIResource = apiresource.getResource();
		System.out.println(strAPIResource);
		
		if (method.equalsIgnoreCase("get")) {
			
			res = new ResponseSpecBuilder().build();
			System.out.println("Response is " + res);

			getresponse = given().spec(auth2_requestspecification()).get(apiresource.getResource()).then().spec(res)
					.extract().response();
			System.out.println(getresponse);
		}
	
	}
	
	
	@When("user are calling with invalid id to {string} with {string} http request")
	public void user_are_calling_with_invalid_id_with_http_request(String resource, String method) throws FileNotFoundException, InterruptedException {
		
		APIsList apiresource = APIsList.valueOf(resource);
		String strAPIResource = apiresource.getResource();
		System.out.println(strAPIResource);

		if (strAPIResource.contains(":user_id")) {
			strAPIResource = strAPIResource.replace(":user_id", ConfigReader.init_prop().getProperty("InvalidUserId"));
			System.out.println("Replaced userId in enum: " + strAPIResource);
		} 
		else if (strAPIResource.contains(":playlistid")) {
			strAPIResource = strAPIResource.replace(":playlistid", playlistid);
			System.out.println("Replaced userId in enum: " + strAPIResource);
		}
		
		
		
		
		
		  if (method.equalsIgnoreCase("post")) {

				res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
				System.out.println("Response is " + res);

				getresponse = req.when().post(strAPIResource).then().spec(res).extract().response();
				System.out.println(getresponse);

			}
		
		
	
	}
	
	
	@When("user are calling with blank id to {string} with {string} http request")
	public void user_are_calling_with_blank_userid_with_http_request(String resource, String method) throws FileNotFoundException, InterruptedException, DecoderException {
		
		APIsList apiresource = APIsList.valueOf(resource);
		String strAPIResource = apiresource.getResource();
		System.out.println(strAPIResource);
		String decodedUrl = new URLCodec().decode(strAPIResource);
		

		if (decodedUrl.contains(":user_id")) {
			decodedUrl = decodedUrl.replace(":user_id", ConfigReader.init_prop().getProperty("blankUserId"));
			System.out.println("Replaced userId in enum: " + decodedUrl);
		} 
		else if (decodedUrl.contains(":playlistid")) {
			decodedUrl = decodedUrl.replace(":playlistid", ConfigReader.init_prop().getProperty("blankplaylistid"));
			System.out.println("Replaced userId in enum: " + decodedUrl);
		}
		
		
		
		
		
		
		  if (method.equalsIgnoreCase("post")) {

				res = new ResponseSpecBuilder().build();	
				
				System.out.println("Response is " + res);

				getresponse = req.when().post(strAPIResource).then().spec(res).extract().response();
				System.out.println(getresponse);

			}
		  else if (method.equalsIgnoreCase("put")) {

				res = new ResponseSpecBuilder().build();
				System.out.println("Response is " + res);

				getresponse = req.when().put(strAPIResource).then().spec(res).extract().response();
				System.out.println(getresponse);
				
		   }
		  else if(method.equalsIgnoreCase("delete")) {
				
				res = new ResponseSpecBuilder().build();
				System.out.println("Response is " + res);

				getresponse = req.when().delete(strAPIResource).then().spec(res).extract().response();
				System.out.println(getresponse);
				
			}
		  
		  
		  
		  
		  
	    }
	
	
	
	@When("user are calling with invaliddata {string} with {string} http request")
	public void user_are_calling_with_invaliddata_http_request(String resource, String method) throws FileNotFoundException, InterruptedException {
		
		APIsList apiresource = APIsList.valueOf(resource);
		String strAPIResource = apiresource.getResource();
		System.out.println(strAPIResource);
		
		if (strAPIResource.contains(":user_id")) {
			strAPIResource = strAPIResource.replace(":user_id", ConfigReader.init_prop().getProperty("UserId"));
			System.out.println("Replaced userId in enum: " + strAPIResource);
		} else if (strAPIResource.contains(":playlistid")) {
			strAPIResource = strAPIResource.replace(":playlistid", ConfigReader.init_prop().getProperty("invalid_playlistid"));
			System.out.println("Replaced playlistid in enum: " + strAPIResource);
		}
		
		
		
		if (method.equalsIgnoreCase("get")) {

		

		} else if (method.equalsIgnoreCase("post")) {

			res = new ResponseSpecBuilder().build();
			System.out.println("Response is " + res);

			getresponse = req.when().post(strAPIResource).then().spec(res).extract().response();
			System.out.println(getresponse);

		}
		
		else if (method.equalsIgnoreCase("put")) {

			res = new ResponseSpecBuilder().build();
			System.out.println("Response is " + res);

			getresponse = req.when().put(strAPIResource).then().spec(res).extract().response();
			System.out.println(getresponse);
		}
	   else if(method.equalsIgnoreCase("delete")) {
			
			res = new ResponseSpecBuilder().build();
			System.out.println("Response is " + res);

			getresponse = req.when().delete(strAPIResource).then().spec(res).extract().response();
			System.out.println(getresponse);
			
		}
		
		
		
		
		
	}
	
	
	

	@When("user are calling {string} with {string} http request")
	public void user_are_calling_with_http_request(String resource, String method) throws FileNotFoundException, InterruptedException {

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

			res = new ResponseSpecBuilder().build();
			System.out.println("Response is " + res);

			getresponse = req.when().post(strAPIResource).then().spec(res).extract().response();
			System.out.println(getresponse);

		}
		else if(method.equalsIgnoreCase("put")) {
			
			res = new ResponseSpecBuilder().build();
			System.out.println("Response is " + res);

			getresponse = req.when().put(strAPIResource).then().spec(res).extract().response();
			System.out.println(getresponse);
		}
		else if(method.equalsIgnoreCase("delete")) {
			
			res = new ResponseSpecBuilder().build();
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
	
	
	
	@Then("user verify invalid status code is {int}")
	public void user_verify_invalid_status_code_is(Integer int1) {

		System.out.println(getresponse.getStatusCode());
		assertEquals(getresponse.getStatusCode(), 400);

	}
	
	@Then("user are verify invalid status code is {string}")
	public void user_are_verify_invalid_status_code_is(String status_code) {
		
		System.out.println(getresponse.getStatusCode());
		assertEquals(getresponse.getStatusCode(),Integer.parseInt(status_code));
	}
	
	
	@Then("user verify invalid message is {string}")
	public void user_verify_invalid_message(String message) {
		
		invalidjson_response = getjsonpath(getresponse, "error.message");
		System.out.println(invalidjson_response);
		
		assertTrue(invalidjson_response.contains(message));
		
		
	}
	
	@Then("user verify message is {string}")
	public void user_verify_message_is(String message) {
		
		invalidjson_response = getjsonpath(getresponse, "error.message");
		System.out.println(invalidjson_response);
		
		assertEquals(invalidjson_response, message);
		
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
			
			username = getjsonpath(getresponse, "username");
			System.out.println("username is -->" + username);

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

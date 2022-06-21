package StepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;

import org.testng.annotations.BeforeClass;

import Resources.Utils;
import io.cucumber.java.Before;
import io.restassured.path.json.JsonPath;

public class Hooks {
	
	static String accesstoken;
	public JsonPath json;

  //	@Before("@Deleteplace")
	public void beforeScenario() throws FileNotFoundException {

		sample step = new sample();

		if (step.placeid == null) {
			step.add_place_payload("jack", "Asia", "french");
			step.user_call_with_post_http_request("addplaceapi", "post");
			step.verify_placeid_created_map_to_using("jack", "getplaceapi");
		}

	}
	
	
	
	

}

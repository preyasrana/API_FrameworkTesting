package StepDefinations;

import java.io.FileNotFoundException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@Deleteplace")
	public void beforeScenario() throws FileNotFoundException {

		sample step = new sample();

		if (step.placeid == null) {
			step.add_place_payload("jack", "Asia", "french");
			step.user_call_with_post_http_request("addplaceapi", "post");
			step.verify_placeid_created_map_to_using("jack", "getplaceapi");
		}

	}

}

package Resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	
	

	public RequestSpecification requestspecification() throws FileNotFoundException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

			RestAssured.baseURI = ConfigReader.init_prop().getProperty("baseurl");

			req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
					.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			
			return req;
		}
		return req;
	}
	
	
	public RequestSpecification basicauth_requestspecification() throws FileNotFoundException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("basicauthlogging.txt"));
			
			
			RestAssured.baseURI = ConfigReader.init_prop().getProperty("bookstore_baseurl");
			
			req = new RequestSpecBuilder().setBaseUri(ConfigReader.init_prop().getProperty("bookstore_baseurl"))
					.setAuth(basic(ConfigReader.init_prop().getProperty("Username"), 
							ConfigReader.init_prop().getProperty("Password")))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON)
					.build();
			
			return req;
		}
		return req;
		
	}
	
	
	
	
	

	public String getjsonpath(Response response, String key) {

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();

	}

}

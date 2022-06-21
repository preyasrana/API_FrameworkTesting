package Resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.OAuth2Scheme;
import io.restassured.authentication.OAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	static String oauthtoken = "BQDU6V16uvCbj2xe1vA92zeFK6NUFWJUhs_G95XDXXC0VqDIgogO1KM1fJ4o6uRu9K8ks9RxPA58_jCY7BS7vmImzXqPtd0QWxO2O_CDvD0B3MB-UJZItP9log7poWAw8KBJ6rh8m94sxRSikjkgMnKUeJBUBy02mFlufihkOhvbG-RAkDzaaPAX5RgqYvC-vrCvNPiJGqDIJRony5Q__YQurvh5GUmDao-XoNRJ-YJkbkbCT73m8JqVQk01pXC6FJs1NwwHAg823wU";
	static String accesstoken;
	public JsonPath json;

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
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();

			return req;
		}
		return req;

	}

	public RequestSpecification auth2_requestspecification() throws FileNotFoundException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("auth2logging.txt"));

			RestAssured.baseURI = ConfigReader.init_prop().getProperty("spotify_baseurl");

			req = new RequestSpecBuilder().setBaseUri(ConfigReader.init_prop().getProperty("spotify_baseurl"))
					.setAuth(oauth2(oauthtoken)).addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();

			return req;
		}
		return req;

	}

	public static AuthenticationScheme oauth(String consumerKey, String consumerSecret, String accessToken,
			String secretToken) {
		OAuthScheme scheme = new OAuthScheme();
		scheme.setConsumerKey(consumerKey);
		scheme.setConsumerSecret(consumerSecret);
		scheme.setAccessToken(accessToken);
		scheme.setSecretToken(secretToken);
		return scheme;
	}

	

	public String getjsonpath(Response response, String key) {

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();

	}

	public static JsonPath rawToJson(String response) {

		JsonPath jsonpath_get = new JsonPath(response);
		return jsonpath_get;

	}

}

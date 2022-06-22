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
	static String oauthtoken = "BQAXhb6nHOpoOmKABeH5KVq0bAaC4LdweFTE8gHZyQ_e8YHq9-ltx0ZCG8vQqQddzdggS9LzFxiK-seUpFwt9AoXXPwKBNQg9RmxYMy46UFSy386Us1gTuCn0Yf_eo8tVpaOxxr9hSQfs0g9BU2vA4ZOr02zEhxYHS0hIpu0Z2k3_xgXPsZ1-Hyl5bPWPmBnICo8iU42rii9W_EpU_K2AqzyRivmgQ__52ZsjZuAw9dFVAiw3-MQjy62MeG5ukyQjy3HWjGsAkMP0pw";
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

	public String auth2token() {

		String token = given().urlEncodingEnabled(false)

				.queryParam("client_id", "4e95ed2a5096419d92787be74f2e0e8c")
				.queryParam("client_secret", "b72989f7c4ca4a49982fe66e60a0edb1")
				.queryParam("callback_url", "https://oauth.pstmn.io/v1/browser-callback")
				.queryParam("grant_type", "authorization_code")
				.queryParam("Auth_URL", "https://accounts.spotify.com/authorize")
				.queryParam("Scope", "playlist-modify-public playlist-read-private playlist-modify-private")

				.when().log().all().post("https://accounts.spotify.com/api/token").asString();

		System.out.println(token);

		json = Utils.rawToJson(token);

		accesstoken = json.getString("access_token");
		System.out.println("access token is ::" + accesstoken);

		return accesstoken;

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

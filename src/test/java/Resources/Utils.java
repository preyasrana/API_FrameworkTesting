package Resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.restassured.RestAssured.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
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
	static String oauthtoken = "BQCUB3ATFT_ADa7iLIzdb3kkR4Yj5-d2qpWSWxPcEn56T-XghwbXLK2QEBUBtx91-dzGuCEOUOafGO9WWaCvfU79A36-leTdox9ct68-SFNoXmZrW3AdasXd26mMir-LV40FOquINNmSHLYg1Q-EVI5cit0cpxJayqxwFoJuwYh-UwqW27KaoYYyLmUJ647nsQbyOH7kERBM";
	static String accesstoken;
	public static JsonPath json;
	public static WebDriver driver;

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

	public RequestSpecification auth2_requestspecification() throws FileNotFoundException, InterruptedException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("auth2logging.txt"));

			RestAssured.baseURI = ConfigReader.init_prop().getProperty("spotify_baseurl");

			req = new RequestSpecBuilder().setBaseUri(ConfigReader.init_prop().getProperty("spotify_baseurl"))
					.setAuth(oauth2(auth2token())).addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();

			return req;
		}
		return req;

	}

	
	public static String auth2token() throws InterruptedException {
		
		
		WebDriverManager.chromedriver().setup();
		
		//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/test/java/Download/chromedriver");
		//final ChromeOptions chromeOptions = new ChromeOptions();
		// chromeOptions.setBinary("/usr/bin/google-chrome-stable");

		// chromeOptions.setHeadless(true);

		//chromeOptions.addArguments("prefs", {"profile.managed_default_content_settings.images": 2});
	/*	
		chromeOptions.addArguments("--no-sandbox"); 
		chromeOptions.addArguments("--disable-setuid-sandbox") ;

		chromeOptions.addArguments("--remote-debugging-port=8080") ; //# this
		chromeOptions.addArguments("--headless");
		
		chromeOptions.addArguments("--single-process", "--no-sandbox", "--disable-dev-shm-usage");
		//driver = new ChromeDriver();
		chromeOptions.addArguments("--disable-dev-shm-using");
		// chromeOptions.addArguments("--disable-extensions");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("disable-infobars");		
		chromeOptions.addArguments("start-maximized");
		*/
		
		// chromeOptions.addArguments("disable-infobars");
		// chromeOptions.addArguments("user-data-dir=.\cookies\\test");

		//driver = new ChromeDriver(chromeOptions);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://accounts.spotify.com/authorize?client_id=4e95ed2a5096419d92787be74f2e0e8c&response_type=code\n"
				+ "&scope=playlist-modify-public playlist-read-private playlist-modify-private&redirect_uri=https%3A%2F%2Foauth.pstmn.io%2Fv1%2Fbrowser-callback");
		
		driver.findElement(By.id("login-username")).sendKeys(ConfigReader.init_prop().getProperty("emailid"));
		Thread.sleep(2000);
		driver.findElement(By.id("login-password")).sendKeys(ConfigReader.init_prop().getProperty("password"));
		Thread.sleep(2000);
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(3000);
		

		String url = driver.getCurrentUrl();
		System.out.println("url is --->" + url);
		
		String Partialcode = url.split("code=")[1];
		String code = Partialcode.split("&scope")[0];
		System.out.println(code);
		
		String token = given().urlEncodingEnabled(false).queryParam("code", code)
				.queryParam("client_id", "4e95ed2a5096419d92787be74f2e0e8c")
				.queryParam("client_secret", "b72989f7c4ca4a49982fe66e60a0edb1")
				.queryParam("redirect_uri", "https://oauth.pstmn.io/v1/browser-callback")
				.queryParam("grant_type", "authorization_code")

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

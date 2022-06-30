package Resources;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;

import POJO.Addplace;
import POJO.CreateUser;
import POJO.location;



public class TestDataBuild {

	Addplace addplc = new Addplace();
	location loc = new location();
	public Faker faker = new Faker();

	CreateUser user = new CreateUser();

	public CreateUser Create_user_payload(String USRNAME, String PASSWORD) {

		user.setUserName(USRNAME);
		System.out.println(USRNAME);
		user.setPassword(PASSWORD);
		System.out.println(PASSWORD);

		return user;

	}

	public Addplace data_addplace_payload(String name, String address, String language) {

		addplc.setAccuracy(100);
		addplc.setName(name);
		addplc.setPhone_number("(+91) 990 123 1111");
		addplc.setAddress(address);
		addplc.setWebsite("https://www.google.com");
		addplc.setLanguage(language);

		List<String> mylist = new ArrayList<>();
		mylist.add("new park");
		mylist.add("new");
		addplc.setTypes(mylist);

		loc.setLat(-23.1234);
		loc.setLng(12.4566);

		addplc.setLocation(loc);
		return addplc;

	}
	
	
	public String faker_getname() {
		return faker.name().fullName();
	}
	
	
	

	public String deleteplacepayload(String placeid) {
		return "{\n" + "    \"place_id\":\"" + placeid + "\"\n" + "}";
	}

	public String post_assignbook_touser(String userID, String isbnid) {
		return "{\n" + "	    \"userId\": \"" + userID + "\",\n" + "	    \"collectionOfIsbns\": [\n" + "	        {\n"
				+ "	            \"isbn\": \"" + isbnid + "\"\n" + "	        }\n" + "	    ]\n" + "	}";
	}
	
	public String create_user() {
		
		return "{\n"
				+ "    \"userName\": \""+faker_getname()+"\",\n"
				+ "    \"password\": \""+ConfigReader.init_prop().getProperty("Password")+"\"\n"
				+ "}";
	}
	
	public String new_user_create(String username) {
		
		return "{\n"
				+ "    \"userName\": \""+username+"\",\n"
				+ "    \"password\": \""+ConfigReader.init_prop().getProperty("Password")+"\"\n"
				+ "}";
	}
	
	public String create_playlist() {
		
		return "{\n"
				+ "  \"name\": \"Preyas Playlist\",\n"
				+ "  \"description\": \"Preyas playlist description\",\n"
				+ "  \"public\": false\n"
				+ "}";
	}
	
	public String update_playlist() {
		
		return "{\n"
				+ "  \"name\": \"Updated Preyas Playlist\",\n"
				+ "  \"description\": \"Updated Preyas playlist description\",\n"
				+ "  \"public\": false\n"
				+ "}";
	}
	
	public String delete_playlist_item() {
		
		return "{\n"
				+ "    \"tracks\": [\n"
				+ "        {\n"
				+ "            \"uri\": \""+ConfigReader.init_prop().getProperty("trackid")+"\"\n"
				+ "        }\n"
				+ "\n"
				+ "    ]\n"
				+ "}";
	}
	
	
	public String invalid_delete_playlist_item() {
		
		return "{\n"
				+ "    \"tracks\": [\n"
				+ "        {\n"
				+ "            \"uri\": \""+ConfigReader.init_prop().getProperty("invalidtrackid")+"\"\n"
				+ "        }\n"
				+ "\n"
				+ "    ]\n"
				+ "}";
	}
	
	
	public String missing_required_payload()
	{
		return "{\n"
				+ "\n"
				+ "}\n"
				+ "";
	}
	
	
	public String empty_attribute_change_playlist_detail() {
		
		
		return "{\n"
				+ "  \"name\": \"\",\n"
				+ "  \"description\": \"\",\n"
				+ "  \"public\": false\n"
				+ "}";
	}
	
	public String missing_uri_id() {
		
		return "{\n"
				+ "    \"tracks\": [\n"
				+ "        {\n"
				+ "            \"uri\": \"\"\n"
				+ "        }\n"
				+ "\n"
				+ "    ]\n"
				+ "}";
	}
	
	public String without_uri() {
		
		return "{\n"
				+ "    \"tracks\": [\n"
				+ "        {\n"
				+ "        \n"
				+ "        }\n"
				+ "\n"
				+ "    ]\n"
				+ "}";
	}
	
	

}

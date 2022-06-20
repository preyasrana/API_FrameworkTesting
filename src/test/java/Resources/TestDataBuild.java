package Resources;

import java.util.ArrayList;
import java.util.List;

import POJO.Addplace;
import POJO.CreateUser;
import POJO.location;

public class TestDataBuild {

	Addplace addplc = new Addplace();
	location loc = new location();

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

	public String deleteplacepayload(String placeid) {
		return "{\n" + "    \"place_id\":\"" + placeid + "\"\n" + "}";
	}

	public String post_assignbook_touser(String userID, String isbnid) {
		return "{\n" + "	    \"userId\": \"" + userID + "\",\n" + "	    \"collectionOfIsbns\": [\n" + "	        {\n"
				+ "	            \"isbn\": \"" + isbnid + "\"\n" + "	        }\n" + "	    ]\n" + "	}";
	}

}

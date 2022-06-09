package Resources;

import java.util.ArrayList;
import java.util.List;

import POJO.Addplace;
import POJO.location;

public class TestDataBuild {
	
	Addplace addplc = new Addplace();
	location loc = new location();
	
	public Addplace data_addplace_payload() {

		addplc.setAccuracy(100);
		addplc.setName("Preyas rana");
		addplc.setPhone_number("(+91) 990 123 1111");
		addplc.setAddress("30, new layout, side 09");
		addplc.setWebsite("https://www.google.com");
		addplc.setLanguage("English US");

		List<String> mylist = new ArrayList<>();
		mylist.add("new park");
		mylist.add("new");
		addplc.setTypes(mylist);

		loc.setLat(-23.1234);
		loc.setLng(12.4566);

		addplc.setLocation(loc);
		return addplc;
		
	}

}

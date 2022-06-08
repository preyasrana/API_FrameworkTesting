package POJO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Addplace {
	
		private int accuracy;		
		private String name;
		private String phone_number;
		private String address;
		private String website;
		private String language;
		private location location;
		private List<String> types;
		
		
}

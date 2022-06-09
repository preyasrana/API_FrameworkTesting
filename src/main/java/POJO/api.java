package POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class api {
	
	/*
	 * public String getCourseTitle() { return courseTitle; } public void
	 * setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; } public
	 * String getPrice() { return price; } public void setPrice(String price) {
	 * this.price = price; }
	 */
	
	private String courseTitle;
	private String price;

}

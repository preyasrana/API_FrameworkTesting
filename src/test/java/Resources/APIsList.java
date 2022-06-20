
//Author : User
package Resources;

public enum APIsList {

	
	//Demo APIs
	addplaceapi("/maps/api/place/add/json"),
	getplaceapi("/maps/api/place/get/json"),
	deleteplaceapi("/maps/api/place/delete/json"),
	
	
	//Book Store APIs
	getbooksapi("/BookStore/v1/Books"),
	postcreateuserapi("/Account/v1/User"),
	post_Assign_book_user("/BookStore/v1/Books");
	
	

	private String resource;

	APIsList(String resource) {
		this.resource = resource;

	}

	public String getResource() {

		return resource;
	}

}

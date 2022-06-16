
//Author : User
package Resources;

public enum APIsList {

	addplaceapi("/maps/api/place/add/json"),
	getplaceapi("/maps/api/place/get/json"),
	deleteplaceapi("/maps/api/place/delete/json");

	private String resource;

	APIsList(String resource) {
		this.resource = resource;

	}

	public String getResource() {

		return resource;
	}

}

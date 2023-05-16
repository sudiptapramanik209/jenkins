package demo;

import static io.restassured.RestAssured.given;

import org.junit.runner.Request;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class simplebook {
	public static String baseurl="https://simple-books-api.glitch.me";
	public static String token;
	public static String orderId;
	
	@Test(priority = 0)
	public void createBoard() {
		String RequestBody="{\r\n"
				+ "   \"clientName\": \"bala1223445\",\r\n"
				+ "   \"clientEmail\": \"bala1223445@example.com\"\r\n"
				+ "}";
		RestAssured.baseURI=baseurl;
		
		//i have to used pre-condition
		Response response=given()
		.header("Content-Type","application/json")
		.body(RequestBody)
		.when()
		.post("/api-clients/")
		.then()
		.assertThat().statusCode(201).contentType(ContentType.JSON)
		.extract().response();
		String jsonrespons=response.asString();
		System.out.println(jsonrespons);
		JsonPath js=new JsonPath(jsonrespons);
		// this is to fatch the specific object
		token=js.get("accessToken");
		System.out.println(token);
		
	}
	@Test(priority = 1)
	public void orderBook() {
	    RestAssured.baseURI = baseurl;
	    
	    // Set the request body with the book details
	    String RequestBody = "{\r\n"
	    		+ "  \"bookId\": 1,\r\n"
	    		+ "  \"customerName\": \"John\"\r\n"
	    		+ "}";
	    
	    // Send the POST request to order the book
	    Response response = given()
	    		.header("Authorization", "Bearer " + token)
	            .header("Content-Type","application/json")
	            .body(RequestBody)
	            .when()
	            .post("/orders")
	            .then()
	            .assertThat().statusCode(201).contentType(ContentType.JSON)
	            .extract().response();
	    
	    String jsonrespons = response.asString();
	    System.out.println(jsonrespons);
	    
	    JsonPath js=new JsonPath(jsonrespons);
		// this is to fatch the specific object
	    orderId=js.get("orderId");
		System.out.println(orderId);
	}
	@Test(priority = 2)
	public void getOrderUpdate() {
	    RestAssured.baseURI = baseurl;

	    // Send the GET request to fetch the order details
	    Response response = given()
	            .header("Authorization", "Bearer " + token)
	            .when()
	            .get("/orders/{orderId}", orderId)
	            .then()
	            .assertThat().statusCode(200).contentType(ContentType.JSON)
	            .extract().response();

	    String jsonResponse = response.asString();
	    System.out.println(jsonResponse);
	}
	

	@Test(priority = 3)
	public void deleteOrder() {
	    RestAssured.baseURI = baseurl;

	    // Send the DELETE request to delete the order
	    Response response = given()
	            .header("Authorization", "Bearer " + token)
	            .when()
	            .delete("/orders/{orderId}", orderId)
	            .then()
	            .assertThat().statusCode(204)
	            .extract().response();
	    
	    // Check if the order was deleted successfully
	    Response checkResponse = given()
	            .header("Authorization", "Bearer " + token)
	            .when()
	            .get("/orders/{orderId}", orderId)
	            .then()
	            .extract().response();
	    
	    int statusCode = checkResponse.getStatusCode();
	    System.out.println("Status code after deletion: " + statusCode);
	}




}

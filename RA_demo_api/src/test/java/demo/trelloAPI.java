package demo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class trelloAPI {
	public static String baseurl="https://api.trello.com";
	public static String key="f161c5845a8bf175be15f39d43159506";
	public static String token="ATTA4619d0b32928e64bb80507bc4769c8f078e5b921fee755f3c6071f17be7291f1C92507ED";
	public static String id;
	public static String name="sudipta";
	public static String desc="i am a good boy";
	
	@Test(priority = 1)
	public void createBoard() {
		RestAssured.baseURI=baseurl;
		
		//i have to used pre-condition
		Response response=given().queryParam("name","masai")
		.queryParam("key",key)
		.queryParam("token",token)
		.header("Content-Type","application/json")
		.when()
		.post("/1/boards")
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		String jsonrespons=response.asString();
		System.out.println(jsonrespons);
		JsonPath js=new JsonPath(jsonrespons);
		// this is to fatch the specific object
		id=js.get("id");
		System.out.println(id);
		
	}
	@Test(priority = 2)
	public void updateBoard() {
	    RestAssured.baseURI = baseurl;
	    Response response=given()
	        .queryParam("key", key)
	        .queryParam("token", token)
	        .header("Content-Type","application/json")
	        .queryParam("name", name)
	        .queryParam("desc", desc)
	        .when()
	        .put("/1/boards/"+id)
	        .then()
	        .statusCode(200).contentType(ContentType.JSON)
			.extract().response();
			String jsonrespons=response.asString();
			System.out.println(jsonrespons);
			JsonPath js=new JsonPath(jsonrespons);
			// this is to fatch the specific object
			name=js.get("name");
			System.out.println(name);
	}

	
	@Test(priority = 3)
	public void deleteBoard() {
	    RestAssured.baseURI=baseurl;
	    given()
	        .queryParam("key",key)
	        .queryParam("token",token)
	        .when()
	        .delete("/1/boards/"+id)
	        .then()
	        .statusCode(200);
	}


}

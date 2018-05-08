import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import io.restassured.response.Response;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class GetTheWeatherURL {
	
	@DataProvider()
	public Object[][] cityDataProvider() {
		return new Object[][] {
				{"nirmal", 200},
				{"Hyderabad", 200},
				{"sdasdasdasdasdas", 400}
		};
	}
	
	@Test(dataProvider="cityDataProvider")
	public void getWeatherDetails(String cityName, int expectedStatus){
		
	Response res=given().pathParam("CityNameForWeather",cityName).when().get("http://restapi.demoqa.com/utilities/weather/city/{CityNameForWeather}");
	System.out.println("response for city "+cityName+" ="+res.prettyPrint());
	assertEquals(res.getStatusCode(), expectedStatus);
	String city = res.jsonPath().getString("City");
	if(expectedStatus == 200){
		assertEquals(city.toLowerCase(), cityName.toLowerCase());
	}
	}
	
	@Test
	public void passingAuthenticaton(){
		//given().header("ToolsQA", "TestPassword").when().get("http://restapi.demoqa.com/authentication/CheckForAuthentication").then().assertThat().statusCode(200);
		Response respnse = given()
		.auth()
		.preemptive()
		.basic("ToolsQA", "TestPassword")
		.header("content-Type", "application/json")
		.when()
		.get("http://restapi.demoqa.com/authentication/CheckForAuthentication")
		;
		assertEquals(respnse.getStatusCode(), 200);
		assertEquals(respnse.getContentType(), "application/json");
	}
	
	@DataProvider(name="years")
	public Object[][] YearsNumber(){
		
		return new Object[][]{
				{2014,200},
				{2015,200},
				{2016,200},
				{2017,200}
		};
	}
	
	@Test(dataProvider="years")
	public void listOfYearsJsonData(int yearnumb, int expectedStatusCode){
		given().pathParam("yearNumbering",yearnumb).when().get("http://ergast.com/api/f1/{yearNumbering}/circuits.json").then().assertThat().statusCode(expectedStatusCode);
	}
}

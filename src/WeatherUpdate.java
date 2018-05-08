import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import io.restassured.response.Response;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class WeatherUpdate 
{
	@DataProvider
	public Object[][] cityNamesProvidder(){
		return new Object[][]{
				{"nirmal",200},
				{"Hyderabad",200},
				{"Adilabad",200},
				{"ncjcjd",400}
		};
	}
	
	@Test(dataProvider="cityNamesProvidder")
	public void getHeaders(String cityNames, int expectedStatusCode){
		
		Response res=given().pathParam("CityNameForWeather",cityNames).when().get("http://restapi.demoqa.com/utilities/weather/city/{CityNameForWeather}");
		assertEquals(res.getStatusCode(),expectedStatusCode);
	}
}

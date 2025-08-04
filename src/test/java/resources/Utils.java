package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	static RequestSpecification req;
	
	public static RequestSpecification requestSpecification() throws IOException {
		if(req==null){
			PrintStream log = new PrintStream(new FileOutputStream("Externallogging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalVariable("baseURL")).setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.build();
			return req;
		} else {
			return req;
		}
	}
	
	public static String getGlobalVariable(String key) throws IOException {
		Properties prop  = new Properties(); // Using this on file which have .properties as file extension
		String filePath = "D:\\All Desktop folders\\Rest API\\Shubham API Code\\APIFrameworkGooglePlaces\\src\\test\\java\\resources\\global.properties";
		FileInputStream file = new FileInputStream(filePath);
		prop.load(file); // This will scan the file
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		return js.get(key).toString();
	}
}

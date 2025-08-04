package resources;

import java.util.ArrayList;

import pojo.JsonBuilderLocation;
import pojo.Location;

public class TestDataBuild {
	
	public static JsonBuilderLocation addPlacePayLoad(String name, String language, String address) {
		JsonBuilderLocation j = new JsonBuilderLocation();
		Location l = new Location();
		
		l.setLat(-38.383494);
		l.setLng(33.427362);
		j.setLocation(l);
		j.setAccuracy(50);
		j.setName(name);
		j.setPhone_number("(+91) 983 893 3937");
		j.setAddress(address);
		ArrayList<String> t = new ArrayList<String>();
		t.add("shoe park");
		t.add("shop");
		j.setTypes(t);
		j.setWebsite("http://google.com");
		j.setLanguage(language);
		
		return j;
	}
	
	public static String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
}

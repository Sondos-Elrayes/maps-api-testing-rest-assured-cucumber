package utils;

import pojoMapsApi.AddPlace;
import pojoMapsApi.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public static AddPlace getPlace(String name,String language  ,String address) {
    AddPlace addPlace = new AddPlace();
    addPlace.setAccuracy(50);
    addPlace.setAddress(address);
    addPlace.setLanguage(language);
    addPlace.setName(name);
    addPlace.setWebsite("http://google.com");
    addPlace.setPhone_number("(+91) 983 893 3937");
    List<String> type =  new ArrayList<>();
    type.add("sondos park");
    type.add("shop");
    addPlace.setTypes(type);
    Location location = new Location();
    location.setLat(-38.383494);
    location.setLng(33.427362);
    addPlace.setLocation(location);
    return addPlace;
  }
    public static String deletePlacePayload(String placeId) {
        return "{\"place_id\":\""+placeId+"\"}";
  }
}

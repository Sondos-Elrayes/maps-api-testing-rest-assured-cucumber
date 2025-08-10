package utils;

public enum Resources {
    AddPlaceAPI("maps/api/place/add/json"),
    getPlaceAPI("maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");
    private String path;
    Resources(String path){
        this.path = path;
    }
    public String getPath() {
        return path;
    }





}

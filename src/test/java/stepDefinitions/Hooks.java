package stepDefinitions;
import io.cucumber.java.Before;
import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
     StepDefinition m = new StepDefinition();
     if(StepDefinition.placeId==null)
     {
         m.add_place_by_payload_with("house1","Arabic","12street");
         m.user_calls_the_with_request("AddPlaceAPI","POST");
         m.verify_place_id_is_created_maps_to_using("house1","getPlaceAPI");
     }
    }

}

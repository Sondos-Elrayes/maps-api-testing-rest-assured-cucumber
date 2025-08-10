
 Feature: Validate Maps Api
@AddPlace @Regression
  Scenario Outline: verify if the place added successfully using the Maps API
    Given Add place by payload with "<name>" "<language>" "<address>"
    When user calls the "AddPlaceAPI" with "POST" request
    Then Validate the place added successfully with status code 200
    And "status" in response body should be "OK"
    And "scope" in response body should be "APP"
    And verify Place_id is created maps to "<name>" using "getPlaceAPI"
  Examples:
    |name        |language|address    |
    |AHouse      |English |Shikh-zayed|
    |BHouse     |English |Rehab city |

@DeletePlace @Regression
   Scenario: verify if deleteApi functionality is working
     Given Delete place payload
     When  user calls the "deletePlaceAPI" with "POST" request
     Then Validate the place added successfully with status code 200
     And "status" in response body should be "OK"
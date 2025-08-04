Feature: Validating Place API's 

@AddPlace
Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI 
	Given Add place payload with "<name>" "<language>" "<address>" 
	When user calls "AddPlaceAPI" with "Post" http request 
	Then The API call got success with status code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP" 
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
	
	Examples: 
		|name 	 | language |address		   |
#		|Shubham | English  |New Delhi 		   |
		|Anjali  | Spanish  |Old Manali   	   |
		
@DeletePlace
Scenario: Verify if Place is being Succesfully deleted using DeletePlaceAPI 
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "DELETE" http request 
    Then The API call got success with status code 200
    And "status" in response body is "OK"
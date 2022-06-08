

Feature: Verify Place APIs call

  @smoketest
  Scenario: Verify Add Place API Successfully
    Given add place payload
    When user call "addplaceapi" with post http request
    Then user verify status code is 200 
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    
    


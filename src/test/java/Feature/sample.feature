Feature: Verify Place APIs call

  @smoketest
  Scenario Outline: Verify Add Place API Successfully
    Given add place payload with "<name>" "<address>" "<language>"
    When user call "addplaceapi" with post http request
    Then user verify status code is 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

    Examples: 
      | name      | address                 | language   |
      | test user | 30, new layout, side 09 | English US |
      
      

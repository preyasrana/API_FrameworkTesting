Feature: Verify Place APIs call

  @smoketestnew
  Scenario Outline: Verify Add Place API Successfully
    Given add place payload with "<name>" "<address>" "<language>"
    When user call "addplaceapi" with "post" http request
    Then user verify status code is 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify placeid created map to "<name>" using "getplaceapi"

    Examples: 
      | name       | address                 | language   |
      | test user  | 30, new layout, side 09 | English US |
   
  @smoketestnew
  Scenario: Verify if delete place functionality its working
      Given deleteplacepayload
      When user call "deleteplaceapi" with "post" http request
      Then user verify status code is 200
      And "status" in response body is "OK"
      
  @smoketest
  Scenario: Verify if delete place functionality its working
       When user are call "getbooksapi" with "get" http request
       Then user verify status code is 200
   

      
      
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
      | name      | address                 | language   |
      | test user | 30, new layout, side 09 | English US |

  @smoketestnew
  Scenario: Verify if delete place functionality its working
    Given deleteplacepayload
    When user call "deleteplaceapi" with "post" http request
    Then user verify status code is 200
    And "status" in response body is "OK"

  @smoketest
  Scenario Outline: Verify Create user API Successfully
    #Given add user with "<USRNAME>" "<PASSWORD>"
    Given add user with username and password
    When user are call "postcreateuserapi" with "post" http request

    Examples: 
      | USRNAME                | PASSWORD      |
      | Testing_new_user_10010 | Test10@Preyas |

  @smoketest
  Scenario: Verify if Getbookstore api functionality its working
    When user are call "getbooksapi" with "get" http request
    Then user verify status code is 200
    
  @smoketest
  Scenario: Verify Create token functionality its working 
     

  @smoketest
  Scenario Outline: Verify AssignBook to user API Successfully
    Given assignbooktoUSer
    When user shouldbe call "post_Assign_book_user" with "post" http request

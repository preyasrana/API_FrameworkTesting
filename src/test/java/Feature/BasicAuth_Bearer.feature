Feature: Verify Basic Auth & Bearer Token Functionality

  @smoketest
  Scenario Outline: Verify Create user API Successfully
    #Given add user with "<USRNAME>" "<PASSWORD>"
    Given add user with username and password
    When user are call "postcreateuserapi" with "post" http request

    Examples: 
      | USRNAME                | PASSWORD      |
      | Testing_new_user_10014 | Test10@Preyas |

  @smoketest
  Scenario: Verify if Getbookstore api functionality its working
    When user are call "getbooksapi" with "get" http request
    Then user verify status code is 200
    
  @smoketest
  Scenario: Verify Create token functionality its working 
     Given add user with username and password to create account
     When userare call "generate_token" with "post" http request

  @smoketest
  Scenario Outline: Verify AssignBook to user API Successfully
    Given assignbooktoUSer
    When user shouldbe call "post_Assign_book_user" with "post" http request
    
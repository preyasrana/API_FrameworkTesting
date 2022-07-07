Feature: Verify Basic Auth & Bearer Token Functionality

  @smoketest
  Scenario Outline: Verify Create user API Successfully
    Given add user with username and password
    When user are call "postcreateuserapi" with "post" http request

  @smoketest
  Scenario: Verify if Getbookstore api functionality its working
    When user are call "getbooksapi" with "get" http request
    Then user verify status code is 200

  @smoketest
  Scenario Outline: Verify AssignBook to user API Successfully
     Given add user with username and password for jwt
     When userare call "generate_token" with "post" http request
     Given assignbooktoUSer using jwt
     When user shouldbe call "post_Assign_book_user" with "post" http request
    
    
    
    